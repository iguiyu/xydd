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

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xydd.service.MainService;


@Controller
public class MainController {
	
	MainService mainService;
	
	

	public MainService getMainService() {
		return mainService;
	}



	public void setMainService(MainService mainService) {
		this.mainService = mainService;
	}



	@RequestMapping("/index")
	public String index(Map<String, Object> map, Model model,
			HttpServletRequest request,HttpServletResponse response) {
		
		 
		return "redirect:/classStudentManager.shtml";
	}
	//学校班级管理列表
	@RequestMapping("/searchSchoolClass")
	public String searchSchoolClass(Map<String, Object> map, Model model,
			HttpServletRequest request,HttpServletResponse response) {
		request.setAttribute("classes", mainService.searchSchoolClass());
		return "searchSchoolClass";
	}
	//学生维护列表
	@RequestMapping("/searchStudentList")
	public String searchStudentList(Map<String, Object> map, Model model,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		String class_id = request.getParameter("classID");
		String className=new String((request.getParameter("className")).getBytes("iso-8859-1"),"utf-8");
		String schoolName = new String((request.getParameter("schoolName")).getBytes("iso-8859-1"),"utf-8");
		int CLASS_ID = Integer.parseInt(class_id);
		request.setAttribute("students", mainService.searchStudentList(CLASS_ID));
		request.setAttribute("classID", class_id);
		request.setAttribute("className", className);
		request.setAttribute("schoolName", schoolName);
		System.out.println("className:::::"+className);

		return "searchStudentList";
	}
	//删除学校班级
	@RequestMapping("/delSchoolClass")
	public String delSchoolClass(Map<String, Object> map, Model model,
			HttpServletRequest request,HttpServletResponse response) {
		String class_id = request.getParameter("classID");
		int CLASS_ID = Integer.parseInt(class_id);
		mainService.delSchoolClass(CLASS_ID);
		request.setAttribute("classStudent", mainService.classStudentManager());
		return "redirect:/classStudentManager.shtml";
	}
	//班级学生管理列表
	@RequestMapping("/classStudentManager")
	public String classStudentManager(Map<String, Object> map, Model model,
			HttpServletRequest request,HttpServletResponse response) {
		System.out.println("--------------------------------！");
		request.setAttribute("classStudent", mainService.classStudentManager());
		return "classStudentManager";
	}
	
	//新增学生
	@RequestMapping("/addStudent")
	public String addStudent(Map<String, Object> map,Model model,
			HttpServletRequest request,HttpServletResponse response,RedirectAttributes attr){
		
		String className=request.getParameter("className");
		String schoolName = request.getParameter("schoolName");	
		String stuNo=request.getParameter("stuNo");
		int studentID=Integer.parseInt(stuNo);
		System.out.println("新增学生");
		String stuNama=request.getParameter("stuName");
		String class_id = request.getParameter("classID");
		int CLASS_ID = Integer.parseInt(class_id); 
		
		HashMap param=new HashMap();
		param.put("STUDENT_NO", studentID);
		param.put("NAME", stuNama);
		param.put("CLASS_ID",CLASS_ID);
		mainService.addStudent(param);
		
		attr.addAttribute("classID",class_id);
		attr.addAttribute("className",className);
		attr.addAttribute("schoolName",schoolName);
		return "redirect:/searchStudentList.shtml";
		
	}
	
   //	班级管理-新增班级
	@RequestMapping("/addClass")
	public String addClass(Map<String, Object> map,Model model,
			HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String schoolName=request.getParameter("schoolName");
		System.out.println("------------todo-----------学校名称："+schoolName);
		String dqnj1=request.getParameter("dqnj");
		int dqnj=Integer.parseInt(dqnj1);
		String className=request.getParameter("className");
		
		HashMap param=new HashMap();
		param.put("SCHOOL_NAME", schoolName);
		param.put("CLASS_NAME", className);
		param.put("CURRENT_LEVEL",dqnj);
		mainService.addClass(param);
		
		//request.setAttribute("classStudent", mainService.classStudentManager());
		return "redirect:/classStudentManager.shtml";
		
	}
	
	//考试成绩管理列表
		@RequestMapping("/perfomanceManager")
		public String perfomanceManager(Map<String, Object> map, Model model,
				HttpServletRequest request,HttpServletResponse response) {
			request.setAttribute("pm", mainService.perfomanceManager());
			return "perfomanceManager";
		}
	
		//学生成绩列表
		@RequestMapping("/searchPerfomanceList")
		public String searchPerfomanceList(Map<String, Object> map, Model model,
				HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
			String class_id = request.getParameter("CLASS_ID");
			String COURSE_NAME=new String((request.getParameter("COURSE_NAME")).getBytes("iso-8859-1"),"utf-8");
			String EXAM_TIME = new String((request.getParameter("EXAM_TIME")).getBytes("iso-8859-1"),"utf-8");
			String EXAM_NAME=new String((request.getParameter("EXAM_NAME")).getBytes("iso-8859-1"),"utf-8");
			String CLASS_NAME = new String((request.getParameter("CLASS_NAME")).getBytes("iso-8859-1"),"utf-8");
			String SCHOOL_NAME = new String((request.getParameter("SCHOOL_NAME")).getBytes("iso-8859-1"),"utf-8");
			System.out.println("  "+class_id+"  "+COURSE_NAME+"  "+EXAM_TIME+"  "+EXAM_NAME+"  "+CLASS_NAME+" "+SCHOOL_NAME+" ");
			int CLASS_ID = Integer.parseInt(class_id);
			request.setAttribute("COURSE_NAME", COURSE_NAME);
			request.setAttribute("EXAM_TIME", EXAM_TIME);
			request.setAttribute("EXAM_NAME", EXAM_NAME);
			request.setAttribute("CLASS_NAME", CLASS_NAME);
			request.setAttribute("SCHOOL_NAME", SCHOOL_NAME);
			request.setAttribute("CLASS_ID", class_id);
			
			
			Map<Object,Object> params = new HashMap<Object, Object>();
			params.put("CLASS_ID", CLASS_ID);
			params.put("COURSE_NAME", COURSE_NAME);
			params.put("EXAM_NAME", EXAM_NAME);
			
			
			request.setAttribute("pfList", mainService.searchPerfomanceList(params));
			List<Map> pfList=(List<Map>)request.getAttribute("pfList");
			return "searchPerfomanceList";
		}	
		
		//删除考试成绩
		@RequestMapping("/delPerfomance")
		public String delPerfomance(Map<String, Object> map, Model model,
				HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
			String class_id = request.getParameter("CLASS_ID");
			String COURSE_NAME=new String((request.getParameter("COURSE_NAME")).getBytes("iso-8859-1"),"utf-8");
			String EXAM_NAME=new String((request.getParameter("EXAM_NAME")).getBytes("iso-8859-1"),"utf-8");
			int CLASS_ID = Integer.parseInt(class_id);
			Map<Object,Object> params = new HashMap<Object, Object>();
			params.put("CLASS_ID", CLASS_ID);
			params.put("COURSE_NAME", COURSE_NAME);
			params.put("EXAM_NAME", EXAM_NAME);
			mainService.delPerfomance(params);
			request.setAttribute("classStudent", mainService.classStudentManager());
			return "redirect:/perfomanceManager.shtml";
		}
		
		@RequestMapping("/addPerfomance")
		public String addPerfomance(Map<String, Object> map, Model model,
				HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
			
			String class_id = request.getParameter("CLASS_ID");
			String COURSE_NAME=request.getParameter("COURSE_NAME");
			String EXAM_TIME = request.getParameter("EXAM_TIME");
			String EXAM_NAME=request.getParameter("EXAM_NAME");
			String SCHOOL_NAME =request.getParameter("SCHOOL_NAME");
			
			int CLASS_ID = Integer.parseInt(class_id);
			Map<Object,Object> params = new HashMap<Object, Object>();
			params.put("CLASS_ID", CLASS_ID);
			params.put("COURSE_NAME", COURSE_NAME);
			params.put("EXAM_NAME", EXAM_NAME);
			params.put("SCHOOL_NAME", SCHOOL_NAME);
			params.put("EXAM_TIME", EXAM_TIME);
			mainService.addPerfomance(params);
			return "redirect:/perfomanceManager.shtml";
		}
		
}
