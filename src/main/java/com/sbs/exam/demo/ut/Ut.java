package com.sbs.exam.demo.ut;

public class Ut {

	public static boolean empty(Object obj) {
		
		if (obj == null) {
			return true;
		}
		//instanceof 객체타입을 확인하는 연산자 String 이냐 맞으면 true 틀리면false
		if(obj instanceof String == false) {
			return true;
		}
		String str = (String) obj;
		
		return str.trim().length() == 0;
	}
	
	public static String f(String format, Object... args) {
		
		return String.format(format,args);
	}

}
