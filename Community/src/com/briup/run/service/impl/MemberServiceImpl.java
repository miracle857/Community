package com.briup.run.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.run.common.bean.Friendrecord;
import com.briup.run.common.bean.Graderecord;
import com.briup.run.common.bean.Memberinfo;
import com.briup.run.common.bean.Memberspace;
import com.briup.run.common.bean.Pointaction;
import com.briup.run.common.bean.Pointrecord;
import com.briup.run.common.exception.DataAccessException;
import com.briup.run.common.exception.MemberServiceException;
import com.briup.run.dao.IMemberDao;
import com.briup.run.service.IMemberService;

@Service("memberServiceImpl")
public class MemberServiceImpl implements IMemberService {

	@Autowired
	private IMemberDao dao;
	@Autowired
	private Pointrecord pointrecord;
	@Autowired
	private Friendrecord friendrecord;

	@Override
	public void registerMemberinfo(Memberinfo memberinfo) throws MemberServiceException {

		Pointaction pointAction = findPointactionByPointAction("REGISTER");
		memberinfo.setPoint(pointAction.getPoint());
		memberinfo.setRegisterdate(new java.sql.Date(new java.util.Date().getTime()));
		System.out.println("注册成功，你当前等级为："+findMemberinfoLevel(memberinfo.getPoint()));
		memberinfo.setGraderecord(findMemberinfoLevel(memberinfo.getPoint()));
		saveOrUpDate(memberinfo, memberinfo.getPassword());

		System.out.println(pointrecord);
		pointrecord.setNickname(memberinfo.getNickname());
		pointrecord.setPointaction(pointAction);
		pointrecord.setReceivedate(new java.sql.Date(new java.util.Date().getTime()));
		save(pointrecord);

		if (findMemberinfoByName(memberinfo.getRecommender()) != null) {
			pointAction = findPointactionByPointAction("RECOMMEND");
			pointrecord.setPointaction(pointAction);
			memberinfo.setPoint(memberinfo.getPoint() + pointAction.getPoint());
			memberinfo.setGraderecord(findMemberinfoLevel(memberinfo.getPoint()));
			update(memberinfo);
		}

	}

	@Override
	public Memberinfo findMemberinfoByName(String nickname) throws MemberServiceException {
		Memberinfo menberinfo = null;
		try {
			menberinfo = dao.findMemberinfoByName(nickname);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return menberinfo;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Memberinfo login(String username, String passwd) throws MemberServiceException {
		Memberinfo memberinfo = findMemberinfoByName(username);
		Date d = memberinfo.getLatestdate();
		if (memberinfo == null || !passwd.equals(memberinfo.getPassword())) {
			return null;
		}
		//上次登录日期和这次登录日期不是同一天则 增加积分点
		if (memberinfo.getLatestdate() == null || memberinfo.getLatestdate().getDate() != new Date().getDate()) {
			memberinfo.setPoint(memberinfo.getPoint() + 3L);
			memberinfo.setIsonline(1L);
			memberinfo.setGraderecord(findMemberinfoLevel(memberinfo.getPoint()));
		}
		memberinfo.setLatestdate(new Date());
		update(memberinfo);
		memberinfo.setGraderecord(findMemberinfoLevel(memberinfo.getPoint()));
		try {
			memberinfo.setMemberSpace(dao.findSpace(memberinfo.getId()));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		memberinfo.setLatestdate(d);//保存上次登录日期，为展示之
		return memberinfo;
	}

	@Override
	public void logout(String nickname) throws MemberServiceException {

	}

	@Override
	public List<Memberinfo> findMemberinfoByNum(int number) throws MemberServiceException {
		List<Memberinfo> list = null;
		try {
			list = dao.findMemberinfoByNum(number);
			Collections.sort(list, new Comparator<Memberinfo>(){
				@Override
				public int compare(Memberinfo o1, Memberinfo o2) {
					if(o1.getPoint() > o2.getPoint()){
						return -1;
					}else{
						return 1;
					}

				}
			});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int findMemberinfoOnline() throws MemberServiceException {
		return 0;
	}

	@Override
	public Graderecord findMemberinfoLevel(Long point) throws MemberServiceException {
		Graderecord graderecord = null;
		try {
			graderecord = dao.findMemberinfoLevel(point);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return graderecord;
	}

	@Override
	public Memberinfo saveOrUpDate(Memberinfo memberinfo, String oldPasswd) throws MemberServiceException {
		try {
			dao.saveMemberinfo(memberinfo);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getBackPasswd(String nickname, String pwdQuestion, String pwdAnswer) throws MemberServiceException {
		return null;
	}

	@Override
	public void update(Memberinfo memberinfo) throws MemberServiceException {
		try {
			Graderecord graderecord = dao.findMemberinfoLevel(memberinfo.getPoint());
			memberinfo.setGraderecord(graderecord);
			dao.updateMemberinfo(memberinfo);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveOrUpdate(String selfname, String friendname) throws MemberServiceException {

	}

	@Override
	public List<Memberinfo> listFriend(String selfname) throws MemberServiceException {
		List<Memberinfo> friends = null;
		try {
			friends = dao.listFriend(selfname);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return friends;
	}

	@Override
	public void moveToBlack(String selfname, String blackname) throws MemberServiceException {

	}

	@Override
	public List<Memberinfo> listBlack(String selfname) throws MemberServiceException {
		return null;
	}

	@Override
	public Friendrecord findFriend(String friend) throws MemberServiceException {
		return null;
	}

	@Override
	public Boolean isMemberspace(Long id) throws MemberServiceException {
		try {
			if(dao.findSpace(id) != null){
				return true;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void moveToFriend(String selfName, String name_searching) throws MemberServiceException {
		friendrecord.setSelfname(selfName);
		friendrecord.setFriendname(name_searching);
		try {
			dao.saveFriend(friendrecord);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteBlack(String selfName, String black) throws MemberServiceException {

	}

	@Override
	public void deleteFriend(String selfName, String friend) throws MemberServiceException {

	}

	@Override
	public void delSpace(Long id) throws MemberServiceException {

	}

	@Override
	public Pointaction findPointactionByPointAction(String actionName) throws MemberServiceException {
		Pointaction pointaction = null;
		try {
			pointaction = dao.findPointactionByPointAction(actionName);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return pointaction;
	}

	@Override
	public void save(Pointrecord pointrecord) throws MemberServiceException {
		try {
			dao.savePointrecord(pointrecord);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveSpace(Memberspace memberspace) throws MemberServiceException {
		try {
			dao.saveSpace(memberspace);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

}
