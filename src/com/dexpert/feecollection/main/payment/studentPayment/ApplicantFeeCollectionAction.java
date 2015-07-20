package com.dexpert.feecollection.main.payment.studentPayment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.dexpert.feecollection.main.fee.lookup.LookupAction;
import com.dexpert.feecollection.main.users.applicant.AppBean;
import com.dexpert.feecollection.main.users.applicant.AppDAO;
import com.opensymphony.xwork2.ActionSupport;

public class ApplicantFeeCollectionAction extends ActionSupport {

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession httpSession = request.getSession();
	static Logger log = Logger.getLogger(LookupAction.class.getName());

	Map<String, String> serviceList = new HashMap<String, String>();
	Map<String, String> serviceListSelected = new HashMap<String, String>();
	Map<String, String> courseList = new HashMap<String, String>();
	Map<String, String> nationalityList = new HashMap<String, String>();
	Map<String, String> facultyList = new HashMap<String, String>();

	ApplicantFeeCollectionBean feeCollectionBean = new ApplicantFeeCollectionBean();
	ApplicantFeeCollectionDAO dao = new ApplicantFeeCollectionDAO();
	AppBean appBean1 = new AppBean();
	List<ApplicantFeeCollectionBean> collectionBeanList = new ArrayList<ApplicantFeeCollectionBean>();
	ApplicantFeeCollectionDAO afc = new ApplicantFeeCollectionDAO();
	AppDAO appDAO = new AppDAO();

	// //

	// get Student Service Detail

	public String studentServiceDetail() {

		serviceList = dao.serviceTypeList();
		courseList = dao.courseList();
		facultyList = dao.facultyList();
		nationalityList = dao.nationalityList();

		return SUCCESS;
	}

	public String submitParameter() {
		ApplicantFeeCollectionBean tempBean=feeCollectionBean;
		feeCollectionBean = afc.calculateTotalFee(feeCollectionBean);
		feeCollectionBean.setService_type(tempBean.getService_type());
		log.info("enroll ment  number ::" + appBean1.getEnrollmentNumber());
		appBean1 = appDAO.getUserDetail(appBean1.getEnrollmentNumber());
		serviceListSelected.put(feeCollectionBean.getService_type(), serviceList.get(feeCollectionBean.getService_type()));
		serviceList = dao.serviceTypeList();
		courseList = dao.courseList();
		facultyList = dao.facultyList();
		nationalityList = dao.nationalityList();

		return SUCCESS;
	}

	// jumping to payment Gateway

	public void studentToPaymentGateway() throws IOException {

		String enrolId = request.getParameter("enrollId");
		String fee = request.getParameter("feeValue");
		log.info("Enroll ment Id is ::" + enrolId);

		appBean1 = appDAO.getUserDetail(enrolId);
		String user = appBean1.getAplFirstName().concat(" ").concat(appBean1.getAplLstName());

		log.info("enrollment Number ::" + enrolId);
		log.info("Total Fee CAlculated ::" + fee);
		String url = "http://localhost:8083/SabPaisa/?name=" + user + "&amount=" + fee;
		response.sendRedirect(url);

	}

	// jumping to payment Gateway

	public void instPaymentGateway() throws IOException {

		String user = request.getParameter("feeName");
		String fee = request.getParameter("amt");

		String url = "http://localhost:8083/SabPaisa/?name=" + user + "&amount=" + fee;
		response.sendRedirect(url);

	}

	// /
	public ApplicantFeeCollectionBean getFeeCollectionBean() {
		return feeCollectionBean;
	}

	public void setFeeCollectionBean(ApplicantFeeCollectionBean feeCollectionBean) {
		this.feeCollectionBean = feeCollectionBean;
	}

	public List<ApplicantFeeCollectionBean> getCollectionBeanList() {
		return collectionBeanList;
	}

	public void setCollectionBeanList(List<ApplicantFeeCollectionBean> collectionBeanList) {
		this.collectionBeanList = collectionBeanList;
	}

	public AppBean getAppBean1() {
		return appBean1;
	}

	public void setAppBean1(AppBean appBean1) {
		this.appBean1 = appBean1;
	}

	public Map<String, String> getServiceList() {
		return serviceList;
	}

	public void setServiceList(Map<String, String> serviceList) {
		this.serviceList = serviceList;
	}

	public Map<String, String> getCourseList() {
		return courseList;
	}

	public void setCourseList(Map<String, String> courseList) {
		this.courseList = courseList;
	}

	public Map<String, String> getNationalityList() {
		return nationalityList;
	}

	public void setNationalityList(Map<String, String> nationalityList) {
		this.nationalityList = nationalityList;
	}

	public Map<String, String> getFacultyList() {
		return facultyList;
	}

	public void setFacultyList(Map<String, String> facultyList) {
		this.facultyList = facultyList;
	}

	public Map<String, String> getServiceListSelected() {
		return serviceListSelected;
	}

	public void setServiceListSelected(Map<String, String> serviceListSelected) {
		this.serviceListSelected = serviceListSelected;
	}

	
}
