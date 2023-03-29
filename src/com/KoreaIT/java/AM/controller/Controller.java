package com.KoreaIT.java.AM.controller;

import com.KoreaIT.java.AM.dto.Member;

public abstract class Controller {
	public static Member loginedMember = null;
	
	public boolean isLogined() {
		if(loginedMember!=null) {
			return true;
		}
		return false;
	}

	public abstract void doAction(String actionMethodName, String cmd);
}
