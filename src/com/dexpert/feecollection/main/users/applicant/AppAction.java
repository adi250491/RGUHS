package com.dexpert.feecollection.main.users.applicant;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.dexpert.feecollection.main.users.affiliated.AffAction;
import com.dexpert.feecollection.main.users.affiliated.AffBean;
import com.opensymphony.xwork2.ActionSupport;

public class AppAction extends ActionSupport {

	// Declare Global Variables Here
	AppBean appBean1;
	List<AppBean> appBeansList = new ArrayList<AppBean>();
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	static Logger log = Logger.getLogger(AffAction.class.getName());

	public AppDAO aplDAO = new AppDAO();

	// End of Global Variables

	// ---------------------------------------------------

	// Action Methods Here

	public String registerStudent() {

		log.info("Applicant Name  ::" + appBean1.getAplFirstName());
		List<String> existEnrollmentList = aplDAO.existingEnrollNum();
		if (existEnrollmentList.isEmpty()) {

			appBean1 = aplDAO.saveOrUpdate(appBean1);
			return SUCCESS;
		} else {
			request.setAttribute("msg", "Enrollment Number Already Registered");

			return "failure";
		}

	}

	// to get list of All Students

	public String getStudentList() {

		appBeansList = aplDAO.getAllStudentList();

		return SUCCESS;

	}

	// to view Applicant Detail
	public String viewApplicant() {

		String apId = request.getParameter("applicantId");
		Integer appId = Integer.parseInt(apId);

		appBean1 = aplDAO.viewApplicantDetail(appId);
		// log.info("College Name ::" + appBean1.getAffBean().getInstName());
		return SUCCESS;
	}

	// End of Action Methods

	// ---------------------------------------------------

	// Keep Getter Setter Methods Here
	public AppBean getAppBean1() {
		return appBean1;
	}

	public void setAppBean1(AppBean appBean1) {
		this.appBean1 = appBean1;
	}

	public List<AppBean> getAppBeansList() {
		return appBeansList;
	}

	public void setAppBeansList(List<AppBean> appBeansList) {
		this.appBeansList = appBeansList;
	}

}
