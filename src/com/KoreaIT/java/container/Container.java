package com.KoreaIT.java.container;

import com.KoreaIT.java.AM.dao.ArticleDao;
import com.KoreaIT.java.AM.dao.MemberDao;
import com.KoreaIT.java.AM.service.ArticleService;
import com.KoreaIT.java.AM.service.MemberService;

public class Container {
	public static MemberDao memberDao;
	public static ArticleDao articleDao;
	
	public static MemberService memberService;
	public static ArticleService articleService;
	
	static {
		memberDao = new MemberDao();
		articleDao = new ArticleDao();
		memberService = new MemberService();
		articleService = new ArticleService();
	}
}
