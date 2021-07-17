package com.myspring.sns.comment.vo;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CommentVO {
	private int comment_num;
	private String comment_id;
	private String comment_name;
	private int comment_boardNum;
	private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private String comment_date = sdf.format(timestamp);
	private int comment_parent;
	private String comment_content;
	
	
	public String getComment_id() {
		return comment_id;
	}
	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public String getComment_name() {
		return comment_name;
	}
	public void setComment_name(String comment_name) {
		this.comment_name = comment_name;
	}
	public int getComment_boardNum() {
		return comment_boardNum;
	}
	public void setComment_boardNum(int comment_boardNum) {
		this.comment_boardNum = comment_boardNum;
	}
	public String getComment_date() {
		return comment_date;
	}
	public void setComment_date(String comment_date) {
		this.comment_date = comment_date;
	}
	public int getComment_parent() {
		return comment_parent;
	}
	public void setComment_parent(int comment_parent) {
		this.comment_parent = comment_parent;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	
	
}
