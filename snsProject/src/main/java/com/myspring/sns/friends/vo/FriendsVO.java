package com.myspring.sns.friends.vo;

public class FriendsVO {
	private String id_1;
	private String id_2;
	
	public FriendsVO(String id_1,String id_2) {
		this.id_1 = id_1;
		this.id_2 = id_2;
	}

	public String getId_1() {
		return id_1;
	}

	public void setId_1(String id_1) {
		this.id_1 = id_1;
	}

	public String getId_2() {
		return id_2;
	}

	public void setId_2(String id_2) {
		this.id_2 = id_2;
	}
	public FriendsVO() {
		// TODO Auto-generated constructor stub
	}

}
