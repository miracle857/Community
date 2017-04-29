package com.briup.run.web.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.briup.run.common.bean.Memberinfo;
import com.briup.run.common.bean.Memberspace;
import com.briup.run.common.exception.MemberServiceException;
import com.briup.run.service.IMemberService;

@Controller
@SessionAttributes(value = { "memberlist", "authCode" })
public class MemberController {

	@Autowired
	private IMemberService memberServiceImpl;

	@RequestMapping("/loginUI.html")
	public String a0() {
		return "loginUI";
	}

	@RequestMapping("messenger/buddyList.html")
	public String a1() {
		return "messenger/buddyList";
	}

	@RequestMapping("activity.html")
	public String a5() {
		return "/member/activity";
	}

	@RequestMapping("/createSpace.jsp")
	public String a2() {
		return "member/createSpace";
	}

	@RequestMapping("/member/activity")
	public String a7() {
		return "/member/activity";
	}

	@RequestMapping("/register.html")
	public String a3() {
		return "register";
	}

	@RequestMapping("/memberList.html")
	public String a4() {
		return "messenger/memberList";
	}

	/**
	 * 个人信箱
	 * 
	 * @param session
	 * @param ra
	 * @return
	 */
	@RequestMapping("inbox.html")
	public String inbox(HttpSession session, RedirectAttributes ra) {
		Memberinfo memberinfo = (Memberinfo) session.getAttribute("member");
		if (memberinfo != null) {
			return "messenger/inbox";
		}
		ra.addFlashAttribute("msg", "请先登录");
		return "redirect:loginUI.html";
	}

	@RequestMapping("modify.html")
	public String modifyInfo(HttpSession session, RedirectAttributes ra) {
		Memberinfo memberinfo = (Memberinfo) session.getAttribute("member");
		if (memberinfo != null) {
			// TODO
			return "";
		}
		ra.addFlashAttribute("msg", "请先登录");
		return "redirect:loginUI.html";
	}

	@RequestMapping("/login")
	public String login(HttpSession session, HttpServletRequest req, Model model) {
		Cookie[] cookies = req.getCookies();

		if (cookies != null) {
			String user = null;
			String passwd = null;
			for (Cookie cookie : cookies) {
				if ("username".equals(cookie.getName())) {
					user = cookie.getValue();
				}
				if ("password".equals(cookie.getName())) {
					passwd = cookie.getValue();
				}
			}
			try {
				if (user != null && passwd != null) {
					Memberinfo memberinfo = memberServiceImpl.login(user, passwd);
					if (memberinfo != null) {
						model.addAttribute("member", memberinfo);
						System.out.println("member添加到session成功");
						return "redirect:/member/activity";
					}
				}
			} catch (MemberServiceException e) {
				e.printStackTrace();
			}
		}
		List<Memberinfo> list = null;
		try {
			list = memberServiceImpl.findMemberinfoByNum(10);
		} catch (MemberServiceException e) {
			e.printStackTrace();
		}
		session.setAttribute("pointRank", list);
		return "login";
	}

	@RequestMapping("/register.action")
	public String register(Memberinfo member, String authCode) {
		System.out.println(authCode);
		try {
			if (memberServiceImpl.findMemberinfoByName(member.getNickname()) != null) {
				System.out.println("用户名存在！");
				return "registerFailed";
			}
			memberServiceImpl.registerMemberinfo(member);
		} catch (MemberServiceException e) {
			e.printStackTrace();
		}
		return "login";
	}

	/**
	 * 登录
	 * 
	 * @param session
	 * @param model
	 * @param username
	 * @param password
	 * @param autoLogin
	 * @param resp
	 * @param authCode
	 * @param code
	 * @param ra
	 * @return
	 */
	@RequestMapping("login.action")
	public String login(HttpSession session, Model model, String username, String password, String autoLogin,
			HttpServletResponse resp, @ModelAttribute("authCode") String authCode, String code, RedirectAttributes ra) {
		if (code != null && code.equals(authCode)) {
			try {
				Memberinfo member = memberServiceImpl.login(username, password);
				if (member != null) {
					session.setAttribute("member", member);
					System.out.println("member添加到session成功");
				}
			} catch (MemberServiceException e) {
				e.printStackTrace();
			}
			// 如果勾选自动登录，则将用户名及密码添加到 cookie中
			if ("0".equals(autoLogin)) {
				Cookie user = new Cookie("username", username);
				Cookie passwd = new Cookie("password", password);
				user.setMaxAge(3600);
				passwd.setMaxAge(3600);
				resp.addCookie(user);
				resp.addCookie(passwd);
			}
			return "redirect:/member/activity";
		} else {
			ra.addFlashAttribute("msg", "验证码错误");
			return "redirect:login";
		}
	}

	/**
	 * 创建个人空间
	 * 
	 * @param memberspace
	 * @param file
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/member/createSpace.action")
	public String createSpace(Memberspace memberspace, MultipartFile file, HttpServletRequest request,
			HttpSession session, Model model) {
		Memberinfo memberinfo = (Memberinfo) session.getAttribute("member");
		try {
			String path = request.getServletContext().getRealPath("/") + "upload/" + file.getOriginalFilename();
			File newFile = new java.io.File(path);
			if (!newFile.getParentFile().exists()) {
				newFile.getParentFile().mkdirs();
			}
			file.transferTo(newFile);
			memberspace.setIcon("upload/" + file.getOriginalFilename());
			System.out.println("快要保存个人空间了：" + memberinfo);
			memberspace.setMemberinfo(memberinfo);
			memberServiceImpl.saveSpace(memberspace);
			memberinfo.setMemberSpace(memberspace);
			System.out.println("这里这里！！！！！！！" + memberinfo);
			model.addAttribute("member", memberinfo);
			return "/member/space";
		} catch (Exception e) {
			e.printStackTrace();
			return "/member/createSpace";
		}

	}

	/**
	 * 用户个人空间相关 存在 则跳入 不存在 则创建
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/isSpace.action")
	public String isExitSpace(HttpSession session, RedirectAttributes ra) {
		Memberinfo memberinfo = (Memberinfo) session.getAttribute("member");
		if (memberinfo != null) {
			try {
				if (memberServiceImpl.isMemberspace(memberinfo.getId())) {
					System.out.println(memberinfo.getGraderecord());
					return "member/space";
				}
			} catch (MemberServiceException e) {
				e.printStackTrace();
			}
			return "member/noSpace";
		}
		ra.addFlashAttribute("msg", "请先登录");
		return "redirect:loginUI.html";
	}

	/**
	 * 随机x名列出用户
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/matchFriend.action")
	public String matchFriend(Model model) {
		List<Memberinfo> list = null;
		try {
			list = memberServiceImpl.findMemberinfoByNum(10);
		} catch (MemberServiceException e) {
			e.printStackTrace();
		}
		model.addAttribute("memberlist", list);
		return "/messenger/matchFriend";
	}

	/**
	 * 添加朋友
	 * 
	 * @param session
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping("/addFriend.action")
	public String addFriend(HttpSession session, HttpServletRequest req, Model model) {
		Memberinfo memberinfo = (Memberinfo) session.getAttribute("member");
		String friName = req.getParameter("friName");
		System.out.println(memberinfo.getNickname() + " 添加 " + friName + " 为好友！");
		try {
			memberServiceImpl.moveToFriend(memberinfo.getNickname(), friName);
		} catch (MemberServiceException e) {
			e.printStackTrace();
		}
		model.addAttribute("msg", "添加好友成功");
		return "/messenger/matchFriend";
	}

	/**
	 * 展示我的好友列表
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/friendlist.action")
	public String showFriends(HttpSession session, Model model, RedirectAttributes ra) {
		Memberinfo memberinfo = (Memberinfo) session.getAttribute("member");
		if (memberinfo != null) {
			List<Memberinfo> myFriends = null;
			try {
				myFriends = memberServiceImpl.listFriend(memberinfo.getNickname());
			} catch (MemberServiceException e) {
				e.printStackTrace();
			}
			model.addAttribute("friends", myFriends);
			return "/messenger/buddyList";
		}
		ra.addFlashAttribute("msg", "请先登录");
		return "redirect:loginUI.html";
	}

	/**
	 * 判断用户名是否被占用
	 * 
	 * @param isnickname
	 * @param response
	 */
	@RequestMapping("/isNicknameExit")
	public void isNicknameExit(String isnickname, HttpServletResponse response) {
		Memberinfo member = null;
		PrintWriter writer = null;
		try {
			member = memberServiceImpl.findMemberinfoByName(isnickname);
			writer = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (member != null) {
			writer.write("昵称存在");
		}
		writer.flush();
		writer.close();
		// return "";
	}

	/**
	 * 注销登录信息
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("logout.action")
	public String logout(HttpSession session) {
		session.removeAttribute("member");
		// session.invalidate();
		return "login";
	}
}
