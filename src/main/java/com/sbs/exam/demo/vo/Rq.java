package com.sbs.exam.demo.vo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.exam.demo.ut.Ut;

import lombok.Getter;

public class Rq {
	@Getter
	private boolean isLogined;
	@Getter
	private int loginedMemberId;
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HttpSession session;
	
	
	public Rq(HttpServletRequest req,HttpServletResponse resp) {
		
		this.session =  req.getSession();
		this.req = req;
		this.resp = resp;
		
		
		
		boolean isLogined = false;
		int loginedMemberId = 0;
		
		if(session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int)session.getAttribute("loginedMemberId");
		}
		
		this.isLogined =isLogined;
		this.loginedMemberId =loginedMemberId;
		
	}

	public void printHistoryBackJs(String msg) {
		
		resp.setContentType("text/html; charset=utf-8");
		print(Ut.jsHistoryBack(msg));
	}
	
	public void print (String msg) {
		
		try {
			resp.getWriter().append(msg);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void login(Member member) {
		session.setAttribute("loginedMemberId",member.getId());
	}

	public void logout() {
		session.removeAttribute("loginedMemberId");
		
	}

	public String historyBackOnView(String msg) {
		req.setAttribute("msg", msg);
		req.setAttribute("historyBack", true);
		return "common/js";
	}
	
	

}
