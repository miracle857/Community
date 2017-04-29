package com.briup.run.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.run.common.bean.Bbsrecord;
import com.briup.run.common.bean.Replyrecord;
import com.briup.run.dao.IBbsDao;
import com.briup.run.service.IBbsService;

@Service("bbsServiceImpl")
public class BbsServiceImpl implements IBbsService {

	@Autowired
	private IBbsDao dao;

	@Override
	public void saveBbsRecord(Bbsrecord bbs) {
		try {
			System.out.println(bbs);
			dao.saveBbsRecord(bbs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Bbsrecord> listBbsRecords(int num) {
		List<Bbsrecord> list = null;
		try {
			list = dao.listBbsRecords(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void deleteBbsRecord(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Bbsrecord> listBbsRecordsByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bbsrecord findBbsrecordById(int id) throws Exception {
		Bbsrecord bbsrecord = dao.findBbsrecordById(id);
		return bbsrecord;
	}

	@Override
	public List<Replyrecord> listReplyByBbsid(int id) throws Exception {
		List<Replyrecord> list = dao.listReplyByBbsid(id);
		return list;
	}

	@Override
	public void saveReply(Replyrecord replycord) throws Exception {
		dao.saveBbsReplyRecord(replycord);
	}

}
