package com.sbs.exam.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sbs.exam.demo.interceptor.BeforeActionInterceptor;
import com.sbs.exam.demo.interceptor.NeedLoginInterceptor;
//설정파일이라고 알려줘야함
@Configuration
public class MyWebConfig implements WebMvcConfigurer{
	@Autowired
	BeforeActionInterceptor beforeActionInterceptor;
	@Autowired
	NeedLoginInterceptor needLoginInterceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		
		InterceptorRegistration ir= registry.addInterceptor(beforeActionInterceptor);
		ir.addPathPatterns("/**").excludePathPatterns("/resource/**");
		registry.addInterceptor(needLoginInterceptor).addPathPatterns("/usr/article/write")
		.addPathPatterns("/usr/article/delete").addPathPatterns("/usr/article/modify")
		.addPathPatterns("/usr/article/doModify");
		
	}

}
