package com.sbs.exam.demo.vo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.sbs.exam.demo.service.MemberService;
import com.sbs.exam.demo.ut.Ut;

import lombok.Getter;


@Component
@Scope(value = "request",  proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Rq {
	@Getter
	private boolean isLogined;
	@Getter
	private int loginedMemberId;
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HttpSession session;
	@Getter
	private Member loginedMember;
	
	
	public Rq(HttpServletRequest req,HttpServletResponse resp,MemberService memberService) {
		
		this.session =  req.getSession();
		this.req = req;
		this.resp = resp;
		
		
		
		boolean isLogined = false;
		int loginedMemberId = 0;
		Member loginedMember = null;
		
		if(session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int)session.getAttribute("loginedMemberId");
			loginedMember = memberService.getMemberById(loginedMemberId);
			
		}
		
		this.isLogined =isLogined;
		this.loginedMemberId =loginedMemberId;
		this.loginedMember = loginedMember;
		this.req.setAttribute("rq", this);
		
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

	public String jsHistoryBack(String msg) {
		
		return Ut.jsHistoryBack(msg);
	}

	public String jsReplace(String msg, String uri) {
		
		return Ut.jsReplace(msg, uri);
	}
	
	
	//Rq ????????? ??????????????? ??????????????? ???????????? ?????????
	//????????? ??????
	//???????????? ????????? ?????? BeforeActionInterceptor ?????? ?????? ??????
	public void initOnbeforeActionInterceptor() {
	
		
	}
	
	

}
