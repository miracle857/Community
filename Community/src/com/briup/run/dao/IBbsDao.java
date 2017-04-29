package com.briup.run.dao;

import java.util.List;

import com.briup.run.common.bean.Bbsrecord;
import com.briup.run.common.bean.Replyrecord;

public interface IBbsDao {
	
	// 存储贴子信息
	void saveBbsRecord(Bbsrecord bbs) throws Exception;

	// 列出N条贴子
	List<Bbsrecord> listBbsRecords(int num) throws Exception;

	// 删除帖子
	void deleteBbsRecord(Bbsrecord bbs) throws Exception;

	// 根据作者查帖子
	List<Bbsrecord> listBbsRecordsByAuthor(String author) throws Exception;
	
	void saveBbsReplyRecord(Replyrecord replyrecord) throws Exception;
	
	/**
	 * 根据ID查询帖子信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Bbsrecord findBbsrecordById(int id) throws Exception;
	
	/**
	 * 根据帖子的ID查询  回复信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<Replyrecord> listReplyByBbsid(int id) throws Exception;
}
