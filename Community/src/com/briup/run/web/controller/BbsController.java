package com.briup.run.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.briup.run.common.bean.Bbsrecord;
import com.briup.run.common.bean.Memberinfo;
import com.briup.run.common.bean.Replyrecord;
import com.briup.run.service.IBbsService;

@Controller
@SessionAttributes(value = { "member", "subject", "reply", "authCode" })
public class BbsController {

	@Autowired
	private IBbsService bbsServiceImpl;

	@RequestMapping("/bbspublish.html")
	public String s1(){
		return "/other/bbspublish";
	}
	@RequestMapping("/other/bbs.html")
	public String showSubjects(Model model) {
		List<Bbsrecord> list = null;
		try {
			list = bbsServiceImpl.listBbsRecords(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(list);
		model.addAttribute("subject", list);
		return "/other/bbs";
	}

	@RequestMapping("/bbspublish.action")
	public String publish(String title, String content, HttpSession session
			,@ModelAttribute("authCode") String authCode, String authcode,RedirectAttributes ra) {
		Memberinfo memberinfo = (Memberinfo) session.getAttribute("member");
		if(!authcode.equals(authCode)){
			ra.addFlashAttribute("msg", "验证码错误");
			return "redirect:/bbspublish.html";
		}
		if("".equals(content)){
			ra.addFlashAttribute("msg", "标题不能为空");
			return "redirect:/bbspublish.html";
		}
		Bbsrecord bbs = new Bbsrecord();
		bbs.setAuthor(memberinfo.getNickname());
		bbs.setTitle(title);
		bbs.setContent(content);
		bbs.setPublishtime(new java.sql.Date(new Date().getTime()));
		try {
			bbsServiceImpl.saveBbsRecord(bbs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/other/bbs.html";
	}

	@RequestMapping("/detail.html")
	public String detail(int subjectid, Model model, String reply,HttpSession session,
			RedirectAttributes ra) {
		//Memberinfo memberinfo = (Memberinfo) session.getAttribute("member");
		System.out.println("进入  detail、");
		Bbsrecord bbsrecord = null;
		List<Replyrecord> list = null;
		System.out.println("subjectid: " + subjectid);
		try {

			bbsrecord = bbsServiceImpl.findBbsrecordById(subjectid);
			list = bbsServiceImpl.listReplyByBbsid(subjectid);
			System.out.println(bbsrecord);
			System.out.println(list.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("subject", bbsrecord);
		model.addAttribute("reply", list);
		// ra.addFlashAttribute("subject", bbsrecord);
		// ra.addFlashAttribute("reply", list);
		//return "redirect:/other/bbsdetail";
		return "/other/bbsdetail";
	}

	@RequestMapping("other/reply.html")
	public String a1(int subjectid, Model model, String reply,HttpSession session,
			@ModelAttribute("authCode") String authCode, String authcode) {
		Memberinfo memberinfo = (Memberinfo) session.getAttribute("member");
		if (authcode.equals(authCode)) {
			if (reply != null) {
				System.out.println("reply.length()  : " + reply.length());
				try {
					if (reply.length() >= 2) {
						bbsServiceImpl.saveReply(new Replyrecord(0, memberinfo.getNickname(), reply, subjectid));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Bbsrecord bbsrecord = null;
			List<Replyrecord> list = null;
			System.out.println("subjectid: " + subjectid);
			try {

				bbsrecord = bbsServiceImpl.findBbsrecordById(subjectid);
				list = bbsServiceImpl.listReplyByBbsid(subjectid);
				System.out.println(bbsrecord);
				System.out.println(list.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addAttribute("subject", bbsrecord);
			model.addAttribute("reply", list);
		}else{
			return "/other/bbsdetail";
		}

		return "/other/bbsdetail";
	}
	
	/**
	 * 判断用户发帖前是否已经登录
	 */
	@RequestMapping("/isLogin")
	public void isLogin(HttpSession session,HttpServletResponse response){
		//System.out.println(memberinfo);
		Memberinfo memberinfo = (Memberinfo) session.getAttribute("member");
		System.out.println("123456789");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(memberinfo == null){
			writer.write("请先登录");
		}
		writer.flush();
		writer.close();
	}
	
	@RequestMapping("deleteBbs.action")
	public String deleteBbs(int bbsid){
		try {
			bbsServiceImpl.deleteBbsRecord(bbsid);
			// TODO 未完成
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
