package com.sbs.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.exam.demo.vo.Member;
import com.sbs.exam.demo.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;
	
	public int join(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {
		// 아이디 중복체크
		Member oldmember = getMemberByLoginId(loginId);
		if(oldmember != null) {
			return -1;
		}
		//이름+이메일 중복체크
		oldmember = getMemberByNameAndEmail(name,email);
		
		if (oldmember != null) {
			return -2;
		}
		memberRepository.join(loginId,loginPw,name,nickname,cellphoneNo,email);
		
		return memberRepository.getLastInertId();
		
		
	}	
	private Member getMemberByNameAndEmail(String name, String email) {
		
		return memberRepository.getMemberByNameAndEmail(name,email);
	}
	private Member getMemberByLoginId(String loginId) {
		
		return memberRepository.getMemberByLoginId(loginId);
	}
	
	public Member getMemberById(int id) {
		
		return memberRepository.getMemberById(id);
	}
}
