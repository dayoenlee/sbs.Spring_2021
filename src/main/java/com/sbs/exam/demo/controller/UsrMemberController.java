package com.sbs.exam.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.demo.service.MemberService;



@Controller
public class UsrMemberController {
	@Autowired
	private MemberService memberService;
	
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId,String loginPw,String name,String nickname, String cellphoneNO,String email) {
		memberService.join(loginId,loginPw,name,nickname,cellphoneNO,email);
		return "성공";
	}
	
	
}	

