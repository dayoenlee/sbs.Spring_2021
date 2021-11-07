package com.sbs.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.exam.demo.vo.Member;
import com.sbs.exam.demo.vo.ResultData;
import com.sbs.exam.demo.repository.MemberRepository;
import com.sbs.exam.demo.ut.Ut;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;
	
	public ResultData<Integer> join(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {
		// 아이디 중복체크
		Member oldmember = getMemberByLoginId(loginId);
		if(oldmember != null) {
			return ResultData.from("F-7",Ut.f("해당로그인아이디(%s)는 이미 사용중입니다.",loginId));
		}
		//이름+이메일 중복체크
		oldmember = getMemberByNameAndEmail(name,email);
		
		if (oldmember != null) {
			return ResultData.from("F-8",Ut.f("(%s)와(%s)(은)는 이미 가입 된 회원의 이름과 이메일입니다.",name,email));
		}
	
		memberRepository.join(loginId,loginPw,name,nickname,cellphoneNo,email);
		
		int id = memberRepository.getLastInertId();
		
		return ResultData.from("S-1","회원가입이 완료되었습니다.","id",id);
	}	
	private Member getMemberByNameAndEmail(String name, String email) {
		
		return memberRepository.getMemberByNameAndEmail(name,email);
	}
	public Member getMemberByLoginId(String loginId) {
		
		return memberRepository.getMemberByLoginId(loginId);
	}
	
	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}
}
