package com.briup.run.common.bean;

public class Replyrecord  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8747916804145046319L;
	private int id;
	private String people;
	private String content;
	private int bbsid;
	public Replyrecord() {}
	public Replyrecord(int id, String people, String content, int bbsid) {
		super();
		this.id = id;
		this.people = people;
		this.content = content;
		this.bbsid = bbsid;
	}
	@Override
	public String toString() {
		return "Replyrecord [id=" + id + ", people=" + people + ", content=" + content + ", bbsid=" + bbsid + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getBbsid() {
		return bbsid;
	}
	public void setBbsid(int bbsid) {
		this.bbsid = bbsid;
	}
	
	
}
