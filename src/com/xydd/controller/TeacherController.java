package com.xydd.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.axis.utils.StringUtils;
import org.apache.jasper.tagplugins.jstl.core.Out;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xydd.service.MainService;
import com.xydd.util.UserInfo;
import com.xydd.util.WeixinUtil;
import com.xydd.weixin.pojo.WeiXinStatic;


@Controller
public class TeacherController {
	
	MainService mainService;
	
	

	public MainService getMainService() {
		return mainService;
	}



	public void setMainService(MainService mainService) {
		this.mainService = mainService;
	}



	@RequestMapping("/teacher/index")
	public String index(Map<String, Object> map, Model model,
			HttpServletRequest request,HttpServletResponse response) {
		String openid=(String)request.getSession().getAttribute("openid");
		
		request.setAttribute("openid", openid);
		
		List<Map> teachers=mainService.searchTeacherByOpenId(openid);
		
		if(teachers!=null&&teachers.size()>0)
		{
			return "";
		}
		else
			return "teacher/index";
	}
	
	
	//注册所在学校
	@RequestMapping("/teacher/regSchool")
	public String regSchool(Map<String, Object> map, Model model,
			HttpServletRequest request,HttpServletResponse response) {
		
		String type=request.getParameter("type");
		if(StringUtils.isEmpty(type))type="X";
		String name=request.getParameter("name");
		if(StringUtils.isEmpty(name))
		{
			request.setAttribute("schools", mainService.searchSchoolByType(type));
			request.setAttribute("type", type);
		}
		else
		{
			HashMap param=new HashMap();
			param.put("SCHOOL_NAME", name);
			request.setAttribute("schools", mainService.searchSchoolByParam(param));
		}
		
		return "teacher/regSchool";
	}
	
	//注册所在班级
	@RequestMapping("/teacher/regClass")
	public String regClass(Map<String, Object> map, Model model,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		
		
		String SCHOOL_NAME=request.getParameter("SCHOOL_NAME");
		String SCHOOL_TYPE=request.getParameter("SCHOOL_TYPE");
		String CURRENT_LEVEL=request.getParameter("CURRENT_LEVEL");
		if(StringUtils.isEmpty(CURRENT_LEVEL))CURRENT_LEVEL="1";
		
		
		if(!StringUtils.isEmpty(SCHOOL_NAME))SCHOOL_NAME=new String(SCHOOL_NAME.getBytes("ISO-8859-1"), "UTF-8");  
		
		HashMap param=new HashMap();
		param.put("SCHOOL_NAME", SCHOOL_NAME);
		param.put("SCHOOL_TYPE", SCHOOL_TYPE);
		param.put("CURRENT_LEVEL", CURRENT_LEVEL);
		
		request.setAttribute("classes", mainService.searchClassByParam(param));
		request.setAttribute("SCHOOL_NAME", SCHOOL_NAME);
		request.setAttribute("SCHOOL_TYPE", SCHOOL_TYPE);
		request.setAttribute("CURRENT_LEVEL", CURRENT_LEVEL);
		
		return "teacher/regClass";
	}
	
	//注册身份类型
	@RequestMapping("/teacher/regType")
	public String regType(Map<String, Object> map, Model model,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		
		String CLASS_ID=request.getParameter("CLASS_ID");
		String CLASS_NAME=request.getParameter("CLASS_NAME");
		String SCHOOL_NAME=request.getParameter("SCHOOL_NAME");
		String SCHOOL_TYPE=request.getParameter("SCHOOL_TYPE");
		String CURRENT_LEVEL=request.getParameter("CURRENT_LEVEL");
		
		if(!StringUtils.isEmpty(SCHOOL_NAME))SCHOOL_NAME=new String(SCHOOL_NAME.getBytes("ISO-8859-1"), "UTF-8");  
		if(!StringUtils.isEmpty(CLASS_NAME))CLASS_NAME=new String(CLASS_NAME.getBytes("ISO-8859-1"), "UTF-8");  
		
		
		HashMap param=new HashMap();
		param.put("SCHOOL_NAME", SCHOOL_NAME);
		param.put("SCHOOL_TYPE", SCHOOL_TYPE);
		param.put("CURRENT_LEVEL", CURRENT_LEVEL);
		
		request.setAttribute("classes", mainService.searchClassByParam(param));
		request.setAttribute("CLASS_ID", CLASS_ID);
		request.setAttribute("CLASS_NAME", CLASS_NAME);
		request.setAttribute("SCHOOL_NAME", SCHOOL_NAME);
		request.setAttribute("SCHOOL_TYPE", SCHOOL_TYPE);
		request.setAttribute("CURRENT_LEVEL", CURRENT_LEVEL);
		
		return "teacher/regType";
	}
	
	//注册任课
	@RequestMapping("/teacher/regCourse")
	public String regCourse(Map<String, Object> map, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {

		String CLASS_ID=request.getParameter("CLASS_ID");
		String CLASS_NAME = request.getParameter("CLASS_NAME");
		String SCHOOL_NAME = request.getParameter("SCHOOL_NAME");
		String SCHOOL_TYPE = request.getParameter("SCHOOL_TYPE");
		String CURRENT_LEVEL = request.getParameter("CURRENT_LEVEL");
		String TEACHER_TYPE = request.getParameter("TEACHER_TYPE");

		if (!StringUtils.isEmpty(SCHOOL_NAME))
			SCHOOL_NAME = new String(SCHOOL_NAME.getBytes("ISO-8859-1"),
					"UTF-8");
		if (!StringUtils.isEmpty(CLASS_NAME))
			CLASS_NAME = new String(CLASS_NAME.getBytes("ISO-8859-1"), "UTF-8");

		request.setAttribute("CLASS_ID", CLASS_ID);
		request.setAttribute("CLASS_NAME", CLASS_NAME);
		request.setAttribute("SCHOOL_NAME", SCHOOL_NAME);
		request.setAttribute("SCHOOL_TYPE", SCHOOL_TYPE);
		request.setAttribute("CURRENT_LEVEL", CURRENT_LEVEL);
		request.setAttribute("TEACHER_TYPE", TEACHER_TYPE);

		return "teacher/regCourse";
	}
	
	//注册姓名、手机
	@RequestMapping("/teacher/regMobile")
	public String regMobile(Map<String, Object> map, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {

		String CLASS_ID=request.getParameter("CLASS_ID");
		String CLASS_NAME = request.getParameter("CLASS_NAME");
		String SCHOOL_NAME = request.getParameter("SCHOOL_NAME");
		String SCHOOL_TYPE = request.getParameter("SCHOOL_TYPE");
		String CURRENT_LEVEL = request.getParameter("CURRENT_LEVEL");
		String TEACHER_TYPE = request.getParameter("TEACHER_TYPE");
		String TEACH_LESSON = request.getParameter("TEACH_LESSON");

		if (!StringUtils.isEmpty(SCHOOL_NAME))
			SCHOOL_NAME = new String(SCHOOL_NAME.getBytes("ISO-8859-1"),
					"UTF-8");
		if (!StringUtils.isEmpty(CLASS_NAME))
			CLASS_NAME = new String(CLASS_NAME.getBytes("ISO-8859-1"), "UTF-8");
		if (!StringUtils.isEmpty(TEACH_LESSON))
			TEACH_LESSON = new String(TEACH_LESSON.getBytes("ISO-8859-1"), "UTF-8");

		request.setAttribute("CLASS_ID", CLASS_ID);
		request.setAttribute("CLASS_NAME", CLASS_NAME);
		request.setAttribute("SCHOOL_NAME", SCHOOL_NAME);
		request.setAttribute("SCHOOL_TYPE", SCHOOL_TYPE);
		request.setAttribute("CURRENT_LEVEL", CURRENT_LEVEL);
		request.setAttribute("TEACHER_TYPE", TEACHER_TYPE);
		request.setAttribute("TEACH_LESSON", TEACH_LESSON);

		return "teacher/regMobile";
	}
	
	//注册姓名、手机
		@RequestMapping("/teacher/regTeacher")
		@ResponseBody
		public String regTeacher(Map<String, Object> map, Model model,
				HttpServletRequest request, HttpServletResponse response)
				throws UnsupportedEncodingException {
			
			System.out.println("regteacher");

			String name=request.getParameter("name");
			String mobile=request.getParameter("mobile");
			String verCode=request.getParameter("verCode");
			String CLASS_ID = request.getParameter("CLASS_ID");
			String TEACHER_TYPE = request.getParameter("TEACHER_TYPE");
			String TEACH_LESSON = request.getParameter("TEACH_LESSON");
			String openid=(String)request.getSession().getAttribute("openid");
			
			System.out.println(openid);
			JSONObject jObject=new JSONObject();
			
			if(verCode!=null&&verCode.equals("1111"))
			{
				WeixinUtil util=new WeixinUtil();
				
				UserInfo user=util.getUserInfo(WeiXinStatic.appId, WeiXinStatic.appSecret, openid);
				
				if(user!=null)
				{
					HashMap param=new HashMap();
					param.put("OPENID", openid);
					param.put("NICKNAME", user.getNickname());
					param.put("SEX", user.getSex());
					param.put("CITY", user.getCity());
					param.put("COUNTRY", user.getCountry());
					param.put("PROVINCE", user.getProvince());
					param.put("HEADIMGURL", user.getHeadimgurl());
					mainService.addWeiXinUser(param);
				
					HashMap param1=new HashMap();
					param1.put("OPENID", openid);
					param1.put("NAME", name);
					param1.put("MOBILE", mobile);
					mainService.addTeacher(param1);
					
					HashMap param2=new HashMap();
					param2.put("TEACHER_OPENID", openid);
					param2.put("CLASS_ID", CLASS_ID);
					param2.put("TEACHER_TYPE", TEACHER_TYPE);
					param2.put("TEACH_LESSON",TEACH_LESSON );
					mainService.addTeacherClass(param2);
					
					jObject.put("code", "OK");
					jObject.put("msg", "注册成功");
				}
				else
				{
					jObject.put("code", "400");
					jObject.put("msg", "获取用户信息失败");
				}
			}
			else
			{
				jObject.put("code", "200");
				jObject.put("msg", "验证码错误");
				
			}
			

			return jObject.toString();
		}
	
		
}
