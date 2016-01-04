package com.xydd.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xydd.dao.MainDao;
import com.xydd.service.MainService;


public class MainServiceImpl implements MainService{
	
	MainDao mainDao;

	public MainDao getMainDao() {
		return mainDao;
	}

	public void setMainDao(MainDao mainDao) {
		this.mainDao = mainDao;
	}

	@Override
	public List<Map> test() {
		// TODO Auto-generated method stub
		return mainDao.test();
	}
	
	@Override
	public List<Map> searchSchoolClass() {
		// TODO Auto-generated method stub
		return mainDao.searchSchoolClass();
	}

	@Override
	public List<Map> searchStudentList(int CLASS_ID) {
		// TODO Auto-generated method stub
		return mainDao.searchStudentList(CLASS_ID);
	}

	@Override
	public List<Map> classStudentManager() {
		// TODO Auto-generated method stub
		return mainDao.classStudentManager();
	}

	@Override
	public void delSchoolClass(int CLASS_ID) {
		// TODO Auto-generated method stub
		mainDao.delSchoolClass(CLASS_ID);
	}
	
	@Override
	public void addStudent(Map map){
		mainDao.addStudent(map);
	}

	@Override
	public void delStudent(int STUDENT_ID,String NAME) {
		// TODO Auto-generated method stub
		mainDao.delStudent(STUDENT_ID, NAME);
	}
	@Override
	public void addClass(Map map){
		mainDao.addClass(map);
	}

	@Override
	public List<Map> perfomanceManager(){
		return mainDao.perfomanceManager();
	}
	
	@Override
	public List<Map> searchPerfomanceList(Map map){
		return mainDao.searchPerfomanceList(map);
	}
	
	@Override
	public void delPerfomance(Map map){
		mainDao.delPerfomance(map);
	}
	@Override
	public void addPerfomance(Map map){
		mainDao.addPerfomance(map);
	}

	@Override
	public List<Map> searchTeacherByOpenId(String openid) {
		// TODO Auto-generated method stub
		return mainDao.searchTeacherByOpenId(openid);
	}

	@Override
	public List<Map> searchSchoolByType(String type) {
		// TODO Auto-generated method stub
		return mainDao.searchSchoolByType(type);
	}

	@Override
	public List<Map> searchSchoolByParam(Map map) {
		// TODO Auto-generated method stub
		return mainDao.searchSchoolByParam(map);
	}

	@Override
	public List<Map> searchClassByParam(Map map) {
		// TODO Auto-generated method stub
		return mainDao.searchClassByParam(map);
	}

	@Override
	public void addTeacher(Map map) {
		// TODO Auto-generated method stub
		mainDao.addTeacher(map);
	}

	@Override
	public void addTeacherClass(Map map) {
		// TODO Auto-generated method stub
		mainDao.addTeacherClass(map);
	}

	@Override
	public void addWeiXinUser(Map map) {
		// TODO Auto-generated method stub
		mainDao.addWeiXinUser(map);
	}
	

}
