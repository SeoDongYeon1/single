package com.KoreaIT.java.AM.controller;

import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.service.ArticleService;
import com.KoreaIT.java.AM.service.MemberService;
import com.KoreaIT.java.AM.util.Util;
import com.KoreaIT.java.container.Container;

public class ArticleController extends Controller{
	private Scanner sc;
	String actionMethodName;
	String cmd;
	
	ArticleService articleService;
	MemberService memberService;
	
	public ArticleController(Scanner sc) {
		articleService = Container.articleService;
		memberService = Container.memberService;
		this.sc = sc;
	}
	
	@Override
	public void doAction(String actionMethodName, String cmd) {
		this.actionMethodName = actionMethodName;
		this.cmd = cmd;
		
		switch (actionMethodName) {
		case "write":
			doWrite();
			break;
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "delete":
			doDelete();
			break;
		case "modify":
			doModify();
			break;
		default:
			System.out.println("존재하지 않는 명령어입니다.");
			break;
		}
	}

	public void doWrite() {
		if(isLogined()==false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		int id = articleService.setNewId();
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		String regDate = Util.getNowDateStr();
		String updateDate= "";
		int memberId = loginedMember.id;
		
		Article article = new Article(id, regDate, updateDate, title, body, memberId);
		articleService.add(article);
		System.out.printf("%d번 게시글이 생성되었습니다.\n", id);
	}
	
	public void showList() {
		if(Container.articleDao.articles.size()==0) {
			System.out.println("게시글이 존재하지 않습니다.");
			return;
		}
		
		System.out.println("번호  /  제목  /  조회수  /  작성자 ");
		String writer = null;
		for(int i = Container.articleDao.articles.size()-1; i>=0; i--) {
			Article article = Container.articleDao.articles.get(i);
			Member member = memberService.getMemberBymemberId(article.memberId);
			writer = member.name;
			System.out.printf("%d  /  %s   /   %s  / %s\n", article.id, article.title, article.hit, writer);
		}
	}
	
	public void showDetail() {
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length < 3) {
			System.out.println("명령어를 확인해주세요.");
			return;
		}
		
		int id = Integer.parseInt(cmdBits[2]);
		
		Article foundArticle = articleService.getArticleById(id);
		
		if(foundArticle==null) {
			System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
			return;
		}
		
		String writer = null;
		Member member = memberService.getMemberBymemberId(foundArticle.memberId);
		writer = member.name;
		
		foundArticle.IncreaseHit();
		System.out.printf("번호 : %d \n", foundArticle.id);
		System.out.printf("제목 : %s \n", foundArticle.title);
		System.out.printf("내용 : %s \n", foundArticle.body);
		System.out.printf("작성자 : %s \n", writer);
		System.out.printf("작성 날짜 : %s \n", foundArticle.regDate);
		System.out.printf("수정 날짜 : %s \n", foundArticle.updateDate);
		System.out.printf("조회수 : %d \n", foundArticle.hit);
	}

	public void doDelete() {
		if(isLogined()==false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length < 3) {
			System.out.println("명령어를 확인해주세요.");
			return;
		}
		
		int id = Integer.parseInt(cmdBits[2]);
		
		Article foundArticle = articleService.getArticleById(id);
		
		if(foundArticle==null) {
			System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
			return;
		}
		
		if(loginedMember.id!=foundArticle.memberId) {
			System.out.println("권한이 없습니다.");
			return;
		}
		
		System.out.printf("%d번 게시글이 삭제되었습니다\n", foundArticle.id);
		articleService.remove(foundArticle);
	}

	public void doModify() {
		if(isLogined()==false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length < 3) {
			System.out.println("명령어를 확인해주세요.");
			return;
		}
		
		int id = Integer.parseInt(cmdBits[2]);
		
		Article foundArticle = articleService.getArticleById(id);
		
		if(foundArticle==null) {
			System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
			return;
		}
		
		if(loginedMember.id!=foundArticle.memberId) {
			System.out.println("권한이 없습니다.");
			return;
		}
		
		System.out.printf("새 제목 : ");
		String title = sc.nextLine();
		System.out.printf("새 내용 : ");
		String body = sc.nextLine();
		String updateDate = Util.getNowDateStr();
		
		foundArticle.title = title;
		foundArticle.body = body;
		foundArticle.updateDate = updateDate;
		
		System.out.printf("%d번 게시글이 수정되었습니다.\n", foundArticle.id);
	}
	
	public void maketestData() {
		System.out.println("테스트를 위해 게시글 데이터를 생성합니다.");
		articleService.add(new Article(1, Util.getNowDateStr(), "", "제목1", "내용1", 11, 1));
		articleService.add(new Article(2, Util.getNowDateStr(), "", "제목2", "내용2", 22, 2));
		articleService.add(new Article(3, Util.getNowDateStr(), "", "제목3", "내용3", 33, 3));
	}
}
