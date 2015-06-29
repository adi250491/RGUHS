package com.dexpert.feecollection.main.fee.config;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.dexpert.feecollection.main.fee.lookup.LookupBean;
import com.dexpert.feecollection.main.fee.lookup.LookupDAO;
import com.opensymphony.xwork2.ActionSupport;

public class FcAction extends ActionSupport{
    //Global Declarations Start
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	static Logger log = Logger.getLogger(FcAction.class.getName());
	ArrayList<LookupBean>CourseParamList=new ArrayList<LookupBean>();
	ArrayList<LookupBean>InstituteParamList=new ArrayList<LookupBean>();
	ArrayList<LookupBean>ApplicantParamList=new ArrayList<LookupBean>();
	ArrayList<LookupBean>ServiceParamList=new ArrayList<LookupBean>();
	LookupDAO lpDao=new LookupDAO();
	//Global Declarations End
	//----------------------
	//Action Methods Start
	public String populateFeeForm()
	{
		CourseParamList=lpDao.getLookupData("Scope", "Course", null);
		InstituteParamList=lpDao.getLookupData("Scope", "Institute", null);
		ApplicantParamList=lpDao.getLookupData("Scope", "Applicant", null);
		ServiceParamList=lpDao.getLookupData("Scope", "Service", null);
		
		return SUCCESS;
	}
	//Action Methods End
	//----------------------
	//Getter Setters Start
	
	//Getter Setters End
	
}
