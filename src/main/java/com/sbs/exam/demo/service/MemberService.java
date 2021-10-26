package com.sbs.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.exam.demo.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;
	
	public void join(String loginId, String loginPw, String name, String nickname, String cellphoneNO,
			String email) {
		//memberRepository.join(loginId,loginPw,name,nickname,cellphoneNO,email);
		
	}

}
