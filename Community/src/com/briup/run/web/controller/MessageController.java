package com.briup.run.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.briup.run.common.bean.Memberinfo;
import com.briup.run.common.bean.Messagerecord;
import com.briup.run.common.exception.MessengerServiceException;
import com.briup.run.service.IMessengerService;

@Controller
@SessionAttributes(value = {"memberlist","authCode" })
public class MessageController {
	
	@Autowired
	private IMessengerService messengerServiceImpl;
	
	@RequestMapping("messenger/sendInfo.html")
	public String a1(){
		return "messenger/sendInfo";
	}
	@RequestMapping("messenger/inbox.action")
	public String inbox(){
		
		return "messenger/inbox";
	}
	
	@RequestMapping("messenger/sendMessage.do")
	public String sendMessage(Messagerecord messagerecord,HttpSession session){
		Memberinfo member = (Memberinfo) session.getAttribute("member");
		try {
			messagerecord.setSender(member.getNickname());
			messengerServiceImpl.saveMessage(messagerecord);
		} catch (MessengerServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
