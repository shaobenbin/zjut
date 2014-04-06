package cn.edu.zjut.cfg.service;

import java.util.List;

import cn.edu.zjut.cfg.domain.Zjut_studentDomain;

public enum Zjut_studentService {
	instance;
	private List<Zjut_studentDomain> studentList = null;
	
	public void load(){
		studentList = (new Zjut_studentDomain()).selectAll();
	}
	
	public List getStudentList(){
		if(studentList == null){
			load();
		}
		return studentList;
	}
	
	public List getStudentList(int size){
		List tempStudentList = this.getStudentList();
		if(tempStudentList!=null && tempStudentList.size()>size){
			return tempStudentList.subList(0, size);
		}
		return tempStudentList;
	}
}
