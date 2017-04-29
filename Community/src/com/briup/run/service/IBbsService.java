package com.briup.run.service;

import java.util.List;

import com.briup.run.common.bean.Bbsrecord;
import com.briup.run.common.bean.Replyrecord;

public interface IBbsService {
	
	/**
	 * 存储贴子信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	void saveBbsRecord(Bbsrecord bbs) throws Exception;

	/**
	 * 列出N条贴子
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<Bbsrecord> listBbsRecords(int num) throws Exception;

	/**
	 * 删除帖子
	 * @param id
	 * @return
	 * @throws Exception
	 */
	void deleteBbsRecord(int id) throws Exception;

	/**
	 * 根据作者查帖子
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<Bbsrecord> listBbsRecordsByAuthor(String author) throws Exception;
	
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
	
	/**
	 * 保存帖子中 的回复信息
	 * @param replycord
	 * @throws Exception
	 */
	void saveReply(Replyrecord replycord) throws Exception;
	
}
