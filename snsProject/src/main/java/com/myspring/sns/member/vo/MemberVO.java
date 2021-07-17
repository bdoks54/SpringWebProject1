package com.myspring.sns.member.vo;

public class MemberVO {
	private int member_num;
	private String member_id;
	private String member_pw;
	private byte[] member_passwd;
	private String member_name;
	private String member_email;
	private String member_home;
	private String member_friend;
	public int getMember_num() {
		return member_num;
	}
	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_home() {
		return member_home;
	}
	public void setMember_home(String member_home) {
		this.member_home = member_home;
	}
	public String getMember_friend() {
		return member_friend;
	}
	public void setMember_friend(String member_friend) {
		this.member_friend = member_friend;
	}
	public byte[] getMember_passwd() {
		return member_passwd;
	}
	public void setMember_passwd(byte[] member_passwd) {
		this.member_passwd = member_passwd;
	}
	
	
}
