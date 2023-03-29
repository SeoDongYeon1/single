package com.KoreaIT.java.AM;

import java.util.Scanner;

import com.KoreaIT.java.AM.controller.ArticleController;
import com.KoreaIT.java.AM.controller.Controller;
import com.KoreaIT.java.AM.controller.MemberController;

public class App {
	public void start() {
		Scanner sc = new Scanner(System.in);
		
		ArticleController articleController = new ArticleController(sc);
		MemberController memberController = new MemberController(sc);
		
		System.out.println("==프로그램 시작==");
		articleController.maketestData();
		memberController.maketestData();
		while(true) {
			System.out.printf("명령어 > ");
			String cmd = sc.nextLine().trim();
			
			if(cmd.length()==0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			
			if(cmd.equals("exit")) {
				break;
			}
			
			String[] cmdDiv = cmd.split(" ");
			
			if(cmdDiv.length == 1) {
				System.out.println("명령어를 확인해주세요.");
				continue;
			}
			
			Controller controller = null;
			String ControllerName = cmdDiv[0];
			
			if(ControllerName.equals("article")) {
				controller = articleController;
			}
			else if(ControllerName.equals("member")) {
				controller = memberController;
			}
			else {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}
			
			String actionMethodName = cmdDiv[1];
			
			controller.doAction(actionMethodName, cmd);
		}
		System.out.println("==프로그램 종료==");
		sc.close();
	}

}
