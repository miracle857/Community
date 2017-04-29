package com.briup.run.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.run.common.bean.Memberinfo;
import com.briup.run.common.bean.Messagerecord;
import com.briup.run.common.exception.DataAccessException;
import com.briup.run.common.exception.MessengerServiceException;
import com.briup.run.dao.IMessengerDao;
import com.briup.run.service.IMessengerService;

@Service("messengerServiceImpl")
public class MessengerServiceImpl implements IMessengerService{

	@Autowired
	IMessengerDao dao;
	
	@Override
	public Integer findNewMessageNum(String nickname) throws MessengerServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Memberinfo findOneMemberinfo() throws MessengerServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Memberinfo> findFriends(String age, String gender, String city) throws MessengerServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveMessage(Messagerecord message) throws MessengerServiceException {
		message.setSenddate(new Date());
		message.setReceiverstatus(0L);
		message.setSenderstatus(0L);
		message.setStatus(0L);
		System.out.println(message);
		try {
			dao.saveMessage(message);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Messagerecord> listSendMessage(String senderName) throws MessengerServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Messagerecord> listRecieveMessage(String recieveName) throws MessengerServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Messagerecord findMessage(Long id) throws MessengerServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delRecieveMessage(Long id) throws MessengerServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delSendMessage(Long id) throws MessengerServiceException {
		// TODO Auto-generated method stub
		
	}

}
