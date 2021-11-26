package com.sbs.exam.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sbs.exam.demo.vo.Rq;
@Component
public class BeforeActionInterceptor implements HandlerInterceptor{
	private Rq rq;
	
	public BeforeActionInterceptor (Rq rq) {
		this.rq = rq;
	}
	
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
			throws Exception {
		//requst,response = 디스패처서블릿이 넣어주는것
		rq.initOnbeforeActionInterceptor();// 로그인 상태를 알게됨
		System.out.println("완료");
		
		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
	
}
