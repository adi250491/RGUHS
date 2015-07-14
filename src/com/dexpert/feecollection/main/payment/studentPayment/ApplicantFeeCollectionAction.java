package com.dexpert.feecollection.main.payment.studentPayment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

	ApplicantFeeCollectionBean feeCollectionBean = new ApplicantFeeCollectionBean();

	AppBean appBean1 = new AppBean();
	List<ApplicantFeeCollectionBean> collectionBeanList = new ArrayList<ApplicantFeeCollectionBean>();
	ApplicantFeeCollectionDAO afc = new ApplicantFeeCollectionDAO();
	AppDAO appDAO = new AppDAO();

	// //

	public String submitParameter() {

		feeCollectionBean = afc.calculateTotalFee(feeCollectionBean);

		/*
		 * Iterator<ApplicantFeeCollectionBean> iterator =
		 * collectionBeanList.iterator(); while (iterator.hasNext()) {
		 * ApplicantFeeCollectionBean applicantFeeCollectionBean =
		 * (ApplicantFeeCollectionBean) iterator.next();
		 * 
		 * log.info("Calculated Fees Is ::" +
		 * applicantFeeCollectionBean.getFee());
		 * 
		 * }
		 */

		return SUCCESS;
	}

	// jumping to payment Gateway

	public void jumpingToPaymentGateway() throws IOException {

		String enrolId = request.getParameter("enrollId");
		String fee = request.getParameter("feeValue");

		appBean1 = appDAO.getUserDetail(enrolId);
		String user = appBean1.getAplFirstName().concat(" ").concat(appBean1.getAplLstName());

		log.info("enrollment Number ::" + enrolId);
		log.info("Total Fee CAlculated ::" + fee);
		String url = "http://localhost:8080/SabPaisa?name=" + user + "&amount=" + fee;
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

}
