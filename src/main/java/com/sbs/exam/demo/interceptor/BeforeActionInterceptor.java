package com.sbs.exam.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sbs.exam.demo.vo.Rq;
@Component
public class BeforeActionInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
			throws Exception {
		//requst,response = 디스패처서블릿이 넣어주는것
		Rq rq = new Rq(req,resp);
		req.setAttribute("rq", rq);
		System.out.println("완료");
		
		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
	
}
