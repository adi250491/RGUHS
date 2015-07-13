package com.dexpert.feecollection.main.payment.studentPayment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.dexpert.feecollection.main.fee.lookup.LookupAction;
import com.opensymphony.xwork2.ActionSupport;

public class ApplicantFeeCollectionAction extends ActionSupport {

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	static Logger log = Logger.getLogger(LookupAction.class.getName());

	ApplicantFeeCollectionBean feeCollectionBean = new ApplicantFeeCollectionBean();
	List<ApplicantFeeCollectionBean> collectionBeanList = new ArrayList<ApplicantFeeCollectionBean>();
	ApplicantFeeCollectionDAO afc = new ApplicantFeeCollectionDAO();

	// //

	public String submitParameter() {

		log.info("Service  ::" + feeCollectionBean.getService_type());
		log.info("Course  ::" + feeCollectionBean.getCourse());
		log.info("Faculty  ::" + feeCollectionBean.getFaculty());
		log.info("Nationality  ::" + feeCollectionBean.getNationality());

		collectionBeanList = afc.calculateTotalFee(feeCollectionBean);

		Iterator<ApplicantFeeCollectionBean> iterator = collectionBeanList.iterator();
		while (iterator.hasNext()) {
			ApplicantFeeCollectionBean applicantFeeCollectionBean = (ApplicantFeeCollectionBean) iterator.next();

			log.info("Calculated Fees Is ::" + applicantFeeCollectionBean.getFee());

		}

		return SUCCESS;
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

}
