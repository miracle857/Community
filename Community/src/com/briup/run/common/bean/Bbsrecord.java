package com.briup.run.common.bean;

import java.util.Date;

public class Bbsrecord  implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String author;
	private String title;
	private String content;
	private Date publishtime;
	public Bbsrecord() {}
	public Bbsrecord(int id, String author, String title, String content, Date publishtime) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.content = content;
		this.publishtime = publishtime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}
	@Override
	public String toString() {
		return "Bbsrecord [id=" + id + ", author=" + author + ", title=" + title + ", content=" + content
				+ ", publishtime=" + publishtime + "]";
	}
	
	
}
