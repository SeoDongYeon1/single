package com.KoreaIT.java.AM.controller;

import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.service.MemberService;
import com.KoreaIT.java.AM.util.Util;
import com.KoreaIT.java.container.Container;

public class MemberController extends Controller{
	private MemberService memberService;
	private Scanner sc;
	String actionMethodName;
	String cmd;
	
	public MemberController(Scanner sc) {
		memberService = Container.memberService;
		this.sc = sc;
	}
	
	@Override
	public void doAction(String actionMethodName, String cmd) {
		this.actionMethodName = actionMethodName;
		this.cmd = cmd;
		
		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		case "profile":
			showProfile();
			break;
		default:
			System.out.println("존재하지 않는 명령어입니다.");
			break;
		}
	}

	public void doJoin() {
		if(isLogined()) {
			System.out.println("로그아웃 후 이용해주세요.");
			return;
		}
		int id = memberService.setNewId();
		String loginId = null;
		String loginPw = null;
		String loginPwConfirm = null;
		String name = null;
		
		while(true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();
			
			if(loginId.length()==0) {
				System.out.println("필수 정보입니다.");
				continue;
			}
			
			if(memberService.isJoinableByLoginId(loginId)==false) {
				System.out.println(loginId + " 은(는) 이미 사용중인 아이디입니다.");
				continue;
			}
			System.out.println(loginId + " 은(는)사용 가능한 아이디입니다.");
			break;
		}
		
		while(true) {
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
			
			if(loginPw.length()==0) {
				System.out.println("필수 정보입니다.");
				continue;
			}
			
			while(true) {
				System.out.printf("로그인 비밀번호 확인 : ");
				loginPwConfirm = sc.nextLine();
				
				if(loginPwConfirm.length()==0) {
					System.out.println("필수 정보입니다.");
					continue;
				}
				break;
			}
			if(loginPw.equals(loginPwConfirm)==false) {
				System.out.println("비밀번호가 일치하지 않습니다.");
				continue;
			}
			System.out.println("비밀번호가 일치합니다.");
			break;
		}
		while(true) {
			System.out.printf("이름 : ");
			name = sc.nextLine();
			
			if(name.length()==0) {
				System.out.println("필수 정보입니다.");
				continue;
			}
			break;
		}
		String regDate = Util.getNowDateStr();
		String updateDate = "";
		
		Member member = new Member(id, regDate, updateDate, loginId, loginPw, name);
		memberService.add(member);
		
		System.out.printf("%s님 회원가입 되었습니다. \n", name);
	}

	public void doLogin() {
		if(isLogined()) {
			System.out.println("로그아웃 후 이용해주세요.");
			return;
		}
		System.out.printf("로그인 아이디 : ");
		String loginId = sc.nextLine();
		System.out.printf("로그인 비밀번호 : ");
		String loginPw = sc.nextLine();
		
		Member member = memberService.getMemberByloginId(loginId);
		
		if(member == null) {
			System.out.println("아이디 또는 비밀번호를 확인해주세요.");
			return;
		}
		
		if(member.loginPw.equals(loginPw)==false) {
			System.out.println("아이디 또는 비밀번호를 확인해주세요.");
			return;
		}
		
		System.out.printf("로그인 성공! %s님 반갑습니다.\n", member.name);
		loginedMember = member;
	}

	public void doLogout() {
		if(isLogined()==false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		System.out.printf("%s님 로그아웃 되었습니다.\n", loginedMember.name);
		loginedMember = null;
	}

	public void showProfile() {
		if(isLogined()==false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		System.out.printf("회원 번호 : %d \n", loginedMember.id);
		System.out.printf("아이디 : %s \n", loginedMember.loginId);
		System.out.printf("이름 : %s \n", loginedMember.name);
	}
	
	public void maketestData() {
		System.out.println("테스트를 위해 게시글 데이터를 생성합니다.");
		memberService.add(new Member(1, Util.getNowDateStr(), "", "a", "a", "이름a"));
		memberService.add(new Member(2, Util.getNowDateStr(), "", "b", "c", "이름b"));
		memberService.add(new Member(3, Util.getNowDateStr(), "", "c", "c", "이름c"));
	}

}
