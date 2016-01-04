package com.xydd.controller;

import net.sf.json.JSONObject;

public class Test {

	public static void main(String[] args) {
		JSONObject jObject=new JSONObject();
		jObject.put("code", "OK");
		jObject.put("msg", "注册成功");
		System.out.println(jObject.toString());
	}
}
