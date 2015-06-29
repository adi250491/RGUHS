package com.dexpert.feecollection.main.users.affiliated;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.dexpert.feecollection.main.communication.email.EmailSessionBean;
import com.dexpert.feecollection.main.users.LoginBean;
import com.dexpert.feecollection.main.users.PasswordEncryption;
import com.dexpert.feecollection.main.users.RandomPasswordGenerator;
import com.opensymphony.xwork2.ActionSupport;

public class AffAction extends ActionSupport {

	// Declare Global Variables Here
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	public AffBean affInstBean;
	AffDAO affDao = new AffDAO();
	static Logger log = Logger.getLogger(AffAction.class.getName());
	ArrayList<AffBean> affInstList = new ArrayList<AffBean>();

	private File fileUpload;

	private String fileUploadFileName;
	private Integer fileSize;
	private String contentType;

	// End of Global Variables

	// ---------------------------------------------------

	// Action Methods Here

	// registerInstitute()
	public String registerInstitute() throws Exception {

		String path = request.getServletContext().getRealPath("/");
		path = path + File.separator;
		File f = new File(path + "/RGUHS/");
		f.mkdir();

		// add parent institute details to affInstBean

		// forward to save method in dao
		log.info("file Name  is ::" + fileUploadFileName);

		log.info("file temp  is ::" + fileUpload);

		affInstBean.setFileUpload(fileUpload);
		affInstBean.setFileUploadFileName(fileUploadFileName);

		// affInstBean = affDao.saveOrUpdate(affInstBean);
		log.info("The ID after saving is is " + affInstBean.getInstId());
		// if saved successfully generate credentials and forward success
		String username;
		// generate credentials for admin login
		try {
			username = "Inst".concat(affInstBean.getInstName().replaceAll("\\s+", "").substring(0, 4)
					.concat(affDao.getRowCount().toString()));

		} catch (java.lang.NullPointerException e) {
			username = "Inst".concat(affInstBean.getInstName().replaceAll("\\s+", "").substring(0, 4).concat("1"));
			// TODO: handle exception
		}

		String password = RandomPasswordGenerator.generatePswd(6, 8, 1, 2, 0);
		log.info("Password Generated is " + password);

		PasswordEncryption.encrypt(password);
		String encryptedPwd = PasswordEncryption.encStr;

		LoginBean creds = new LoginBean();
		creds.setPassword(encryptedPwd);
		creds.setUserName(username);

		log.info("User Name is ::" + username);
		log.info("Password is ::" + password);

		log.info("User Profile is  ::" + affInstBean.getLoginBean().getProfile());
		creds.setProfile(affInstBean.getLoginBean().getProfile());

		// for bidirectional relationship ,set parent record to child
		// record
		creds.setAffBean(affInstBean);
		if (creds.getProfile().equals("Admin")) {

			// for bidirectional relationship ,set child record to Parent
			// record
			affInstBean.setLoginBean(creds);

		}

		affInstBean = affDao.saveOrUpdate(affInstBean, f + File.separator);
		// -----Code for sending email//--------------------
		EmailSessionBean email = new EmailSessionBean();
		email.sendEmail(affInstBean.getEmail(), "Welcome To Fee Collection Portal!", username, password,
				affInstBean.getInstName());
		request.setAttribute("redirectLink", "CollegeForm.jsp");
		return SUCCESS;

		// else forward error message on input page

	}

	// getInstituteDetails()
	public String getInstituteDetails() {
		String filterKey = null, filterVal = null;
		Date fromDate = new Date(), toDate = new Date();
		ArrayList<Integer> idList = new ArrayList<Integer>();
		try {

			try {
				filterKey = request.getParameter("fKey").toString();
				filterVal = request.getParameter("fVal").toString();
			} catch (java.lang.NullPointerException e) {
				log.info("filter key is Null");
				log.info("filter value is Null");
			}

		} catch (Exception e) {
			e.printStackTrace();
			filterKey = "NONE";
		}
		affInstList = affDao.getInstitutes(filterKey, filterVal, idList, fromDate, toDate);
		return SUCCESS;
	}

	// get institute Details list

	public String getCollegeList() {

		affInstList = affDao.getCollegesList();

		return SUCCESS;
	}

	// get one college Detail to edit

	public String viewCollegeDetail() {

		String instituteId = request.getParameter("instId");
		Integer instId = Integer.parseInt(instituteId);
		affInstBean = affDao.viewInstDetail(instId);

		return SUCCESS;
	}

	// edit college Details

	public String editCollegeDetail() {

		String id = request.getParameter("instId");

		Integer instId = Integer.parseInt(id);

		affInstBean = affDao.getOneCollegeRecord(instId);

		return SUCCESS;

	}

	// update COllege Record

	public String updateCollegeDetail() {

		affDao.updateCollege(affInstBean);

		return SUCCESS;
	}

	// deleteInstitute()
	// getAssociatedFees()
	// getAssociatedApplicants()
	//

	// End of Action Methods

	// ---------------------------------------------------

	// Keep Getter Setter Methods Here
	public AffBean getAffInstBean() {
		return affInstBean;
	}

	public void setAffInstBean(AffBean affInstBean) {
		this.affInstBean = affInstBean;
	}

	public ArrayList<AffBean> getAffInstList() {
		return affInstList;
	}

	public void setAffInstList(ArrayList<AffBean> affInstList) {
		this.affInstList = affInstList;
	}

	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	// End of Getter Setter Methods
}
