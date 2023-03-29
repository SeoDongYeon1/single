package com.KoreaIT.java.AM.dto;

public class Article extends Dto{
	public String title;
	public String body;
	public int memberId;
	public int hit;
	
	public Article(int id, String regDate, String updateDate, String title, String body, int memberId) {
		this(id, regDate, updateDate, title, body, 0, memberId);
	}
	
	public Article(int id, String regDate, String updateDate, String title, String body, int hit, int memberId) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.title = title;
		this.body = body;
		this.hit = hit;
		this.memberId = memberId;
	}

	public void IncreaseHit() {
		this.hit++;
	}
}
