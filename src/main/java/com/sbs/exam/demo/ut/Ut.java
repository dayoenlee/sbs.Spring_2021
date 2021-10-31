package com.sbs.exam.demo.ut;

public class Ut {

	public static boolean empty(Object obj) {
		
		if (obj == null) {
			return true;
		}
		//instanceof 객체타입을 확인하는 연산자 String 이냐 맞으면 true 틀리면false
		//들어온 데이터가 스트링이 아니라면 스트링으로 바꾸어주고 그문장이 길이가 0인지 확인
		if(obj instanceof String == false) {
			return true;
		}
		String str = (String) obj;
		
		return str.trim().length() == 0;
	}
	// 들어온 데이터타입을 문장으로
	public static String f(String format, Object... args) {
		
		return String.format(format,args);
	}

}
