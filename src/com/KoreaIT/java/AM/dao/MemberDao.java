package com.KoreaIT.java.AM.dao;

import java.util.ArrayList;
import java.util.List;

import com.KoreaIT.java.AM.dto.Member;

public class MemberDao extends Dao{
	public static List<Member> members;
	
	public MemberDao() {
		members = new ArrayList<>();
	}
	
	public int setNewId() {
		return lastId + 1;
	}

	public void add(Member member) {
		members.add(member);
		lastId++;
	}
	
	public Member getMemberByloginId(String loginId) {
		int i = 0;
		for(Member member : members) {
			if(member.loginId.equals(loginId)) {
				return members.get(i);
			}
			i++;
		}
		return null;
	}

	public boolean isJoinableByLoginId(String loginId) {
		for(Member member : members) {
			if(member.loginId.equals(loginId)) {
				return false;
			}
		}
		return true;
	}

	public Member getMemberBymemberId(int memberId) {
		int i = 0;
		for(Member member : members) {
			if(member.id == memberId) {
				return members.get(i);
			}
			i++;
		}
		return null;
	}

}
