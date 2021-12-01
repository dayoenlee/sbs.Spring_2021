package com.sbs.exam.demo.vo;

import lombok.Getter;
//양식의 메타데이터
// return 해줄때 문자와 객체 동시에 리턴해주기위해 만들어줌
public class ResultData<DT> {
	//S - 1, S - 2
	//F - 1, F - 2
	@Getter
	//getResultCode
	private String resultCode;
	@Getter
	private String msg;
	@Getter
	private String data1Name;
	@Getter
	private DT data1;
	@Getter
	private String data2Name;
	@Getter
	private Object data2;
	
	
	
	private ResultData() {
		
	}
	public static <DT>ResultData from(String resultCode,String msg) {
		return from(resultCode,msg,null,null);
	}
	
	public static <DT>ResultData<DT> from(String resultCode,String msg,String data1Name, DT data1) {
		ResultData<DT> rd = new ResultData<DT>();
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.data1Name = data1Name;
		rd.data1 =data1;
		
		return rd;
		
	}
	//String 함수 startswith();
	public boolean isSuccess() {
		return resultCode.startsWith("S-");
	}
	public boolean isFail() {
		return isSuccess() == false;
	}
	
	public static<DT> ResultData<DT> newData(ResultData oldRd,String data1Name, DT Data1) {
		
		return from(oldRd.getResultCode(),oldRd.getMsg(),data1Name,Data1);
	}
	
	public void setData2(String dataName, Object data) {
		data2Name = dataName;
		data2 = data;
		
	}
	
	
}
