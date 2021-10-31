package com.sbs.exam.demo.vo;

import lombok.Getter;
//양식의 메타데이터
// return 해줄때 문자와 객체 동시에 리턴해주기위해 만들어줌
public class ResultData {
	//S - 1, S - 2
	//F - 1, F - 2
	@Getter
	//getResultCode
	private String resultCode;
	@Getter
	private String msg;
	@Getter
	private Object data1;
	
	
	
	private ResultData() {
		
	}
	public static ResultData from(String resultCode,String msg) {
		return from(resultCode,msg,null);
	}
	
	public static ResultData from(String resultCode,String msg, Object data1) {
		ResultData rd = new ResultData();
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.data1 =data1;
		
		return rd;
		
	}
	
	public boolean isSuccess() {
		return resultCode.startsWith("S-");
	}
	public boolean isFail() {
		return isSuccess() == false;
	}
}
