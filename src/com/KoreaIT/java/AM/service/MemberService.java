package com.KoreaIT.java.AM.service;

import com.KoreaIT.java.AM.dao.MemberDao;
import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.container.Container;

public class MemberService {
	MemberDao memberDao;
	
	public MemberService() {
		memberDao = Container.memberDao;
	}

	public void add(Member member) {
		memberDao.add(member);
	}

	public boolean isJoinableByLoginId(String loginId) {
		return memberDao.isJoinableByLoginId(loginId);
	}

	public Member getMemberByloginId(String loginId) {
		return memberDao.getMemberByloginId(loginId);
	}

	public Member getMemberBymemberId(int memberId) {
		return memberDao.getMemberBymemberId(memberId);
	}

	public int setNewId() {
		return memberDao.setNewId();
	}
}
