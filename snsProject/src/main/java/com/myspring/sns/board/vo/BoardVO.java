package com.myspring.sns.board.vo;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class BoardVO {
	
	private int board_number;
	private String board_title;
	private String board_content;
	private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private String board_date = sdf.format(timestamp);
	private String board_name;
	private int board_control;
	private int board_img;
	private String board_imgname;
	private int board_like;
	private String board_id;
	
	public BoardVO() {}
	
	
	public String getBoard_id() {
		return board_id;
	}


	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}


	public String getBoard_imgname() {
		return board_imgname;
	}
	public void setBoard_imgname(String board_imgname) {
		this.board_imgname = board_imgname;
	}
	public int getBoard_like() {
		return board_like;
	}
	public void setBoard_like(int board_like) {
		this.board_like = board_like;
	}
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	public int getBoard_number() {
		return board_number;
	}
	public void setBoard_number(int board_number) {
		this.board_number = board_number;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getBoard_date() {
		return board_date;
	}
	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	public int getBoard_control() {
		return board_control;
	}
	public void setBoard_control(int board_control) {
		this.board_control = board_control;
	}
	public int getBoard_img() {
		return board_img;
	}
	public void setBoard_img(int board_img) {
		this.board_img = board_img;
	}
	
	
}
