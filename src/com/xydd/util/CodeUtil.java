package com.xydd.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * code生成工具类
 * 
 * @author vincent
 * @date 2013-05-18
 */
public class CodeUtil {
	// 起头

	
	public static String genCode() {
		String result="";
		int temp=(int)(Math.random()*9999);
		if(temp<1000)result=result+"0";
		if(temp<100)result=result+"0";
		if(temp<10)result=result+"0";
		result=result+temp;
		
		temp=(int)(Math.random()*9999);
		if(temp<1000)result=result+"0";
		if(temp<100)result=result+"0";
		if(temp<10)result=result+"0";
		result=result+temp;
		
		
		System.out.println(result);
		return result;
	}
	
	
	
	
	public static void main(String[] args) {
		CodeUtil.genCode();
	}
}
