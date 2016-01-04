package com.xydd.service;

import java.util.List;
import java.util.Map;

public interface MainService {

	public List<Map> test();
	public List<Map> searchTeacherByOpenId(String openid);
	public List<Map> searchSchoolByType(String type);
	public List<Map> searchSchoolByParam(Map map);
	public List<Map> searchClassByParam(Map map);
	public void addWeiXinUser(Map map);
	public void addTeacher(Map map);
	public void addTeacherClass(Map map);
	public List<Map> searchSchoolClass();
	public List<Map> searchStudentList(int CLASS_ID);
	public void delSchoolClass(int CLASS_ID);
	public void addClass(Map map);
	public List<Map> classStudentManager();
	public void addStudent(Map map);
	public void delStudent(int STUDENT_ID,String NAME);
	public List<Map> perfomanceManager();
	public List<Map> searchPerfomanceList(Map map);
	public void delPerfomance(Map map);
	public void addPerfomance(Map map);
}
