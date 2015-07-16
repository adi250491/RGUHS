package com.dexpert.feecollection.main.users.affiliated;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.dexpert.feecollection.main.communication.email.EmailSessionBean;
import com.dexpert.feecollection.main.fee.PaymentDuesBean;
import com.dexpert.feecollection.main.fee.config.FcDAO;
import com.dexpert.feecollection.main.fee.config.FeeDetailsBean;
import com.dexpert.feecollection.main.fee.lookup.LookupBean;
import com.dexpert.feecollection.main.fee.lookup.LookupDAO;
import com.dexpert.feecollection.main.fee.lookup.values.FvBean;
import com.dexpert.feecollection.main.users.LoginBean;
import com.dexpert.feecollection.main.users.PasswordEncryption;
import com.dexpert.feecollection.main.users.RandomPasswordGenerator;
import com.opensymphony.xwork2.ActionSupport;

public class AffAction extends ActionSupport {

	// Declare Global Variables Here
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession ses = request.getSession();
	ArrayList<AffBean> affBeansList = new ArrayList<AffBean>();
	Boolean saved = true;
	private LookupDAO lookupdao = new LookupDAO();
	public AffBean affInstBean;

	private AffFeePropBean propbean;
	ArrayList<AffFeePropBean> dueList = new ArrayList<AffFeePropBean>();
	AffDAO affDao = new AffDAO();
	FcDAO feeDAO = new FcDAO();
	static Logger log = Logger.getLogger(AffAction.class.getName());
	ArrayList<AffBean> affInstList = new ArrayList<AffBean>();
	private ArrayList<Integer> paramIds = new ArrayList<Integer>();;
	ArrayList<AffBean> failureAffBeanList = new ArrayList<AffBean>();
	ArrayList<AffBean> existingInstitureRecordList = new ArrayList<AffBean>();
	private ArrayList<LookupBean> paramList2 = new ArrayList<LookupBean>();
	String fileFileName;
	FileInputStream inputStream;
	private File fileUpload;
	private ArrayList<FeeDetailsBean> feeList = new ArrayList<FeeDetailsBean>();
	private String fileUploadFileName;
	private Integer fileSize;
	private String contentType;

	// End of Global Variables

	// ---------------------------------------------------

	// Action Methods Here

	// registerInstitute()
	public String registerInstitute() throws Exception {
		// log.info("paramset is "+affInstBean.getParamvalues().toString());
		List<String> instNameList = affDao.getCollegeNameList(affInstBean.getInstName());
		log.info("List Size is ::" + instNameList.size());

		if (instNameList.isEmpty()) {

			String path = request.getServletContext().getRealPath("/");
			path = path + File.separator;
			File f = new File(path + "/RGUHS/");
			f.mkdir();

			// add parent institute details to affInstBean

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

			request.setAttribute("msg", "Institute Added Successfully");
			request.setAttribute("redirectLink", "Success.jsp");
			return SUCCESS;

		}

		// else forward error message on input page

		else {
			log.info("College NAME ALREADY AVAILABLE");

			request.setAttribute("msg", "Institute Name is Already Registered");
			return "failure";
		}

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
		ses.setAttribute("sesAffBean", affInstBean);

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
		//log.info("paramlist is " + affInstBean.getParamvalues().toString());
		List<String> instNameList = affDao.getCollegeNameList(affInstBean.getInstName());
		log.info("list Size is ::" + instNameList.size());
		if (instNameList.isEmpty()) {

			affDao.updateCollege(affInstBean);

			request.setAttribute("msg", "Institute Updated Successfully");

			return SUCCESS;
		} else {

			log.info("College NaME ALREADY AVAILABLE");

			request.setAttribute("msg", "Institute Name is Already Registered");
			return "failure";

		}

	}

	public String configureCollegeParam() {

		log.info("parameter ids are " + paramIds.toString());
		HashMap<Integer, FvBean> valueMap = (HashMap<Integer, FvBean>) ses.getAttribute("sesParamMap");
		AffBean savedata = (AffBean) ses.getAttribute("sesAffBean");
		log.info("keys are " + valueMap.keySet().toString());
		for (int i = 0; i < paramIds.size(); i++) {
			savedata.getParamvalues().add(valueMap.get(paramIds.get(i)));
		}

		affDao.saveOrUpdate(savedata, null);
		ses.removeAttribute("sesAffBean");
		ses.removeAttribute("sesParamMap");
		request.setAttribute("msg", "Institute Updated Successfully");

		return SUCCESS;

	}

	// edit college doc

	public String editCollegeDoc() {
		String id = request.getParameter("instId");

		Integer instId = Integer.parseInt(id);

		affInstBean = affDao.getOneCollegeRecord(instId);
		return SUCCESS;

	}

	// update College Doc

	public String updateCollegeDoc() {

		String path = request.getServletContext().getRealPath("/");
		path = path + File.separator;
		File f = new File(path + "/RGUHS/");
		f.mkdir();

		affInstBean.setFileUpload(fileUpload);
		affInstBean.setFileUploadFileName(fileUploadFileName);
		affDao.updateCollegeDoc(affInstBean, f + File.separator);

		String msg = "File Successfully Updated";
		request.setAttribute("msg", msg);

		return SUCCESS;
	}

	// download File

	public String downloadFile() throws IOException {
		String docuId = request.getParameter("documentId");
		log.info("Document ID ::" + docuId);
		Integer id = Integer.parseInt(docuId);
		affInstBean = affDao.getOneCollegeRecord(id);
		FileOutputStream fileOuputStream = new FileOutputStream(affInstBean.getFileUploadFileName());
		fileOuputStream.write(affInstBean.getFilesByteSize());
		fileOuputStream.close();

		fileFileName = affInstBean.getFileUploadFileName();
		inputStream = new FileInputStream(affInstBean.getFileUploadFileName());

		return SUCCESS;
	}

	// add bulk colleges

	public String bulkCollegesAdd() throws Exception {
		log.info("file Name ::" + fileUploadFileName);
		log.info("file IS  ::" + fileUpload);

		// if loop to check format of file
		if (fileUploadFileName.endsWith(".xlsx")) {

			String path = request.getServletContext().getRealPath("/");
			path = path + File.separator;
			File f = new File(path + "/RGUHS/");
			f.mkdir();

			affBeansList = affDao.importExcelFileToDatabase(fileUploadFileName, fileUpload, f + File.separator);

			Iterator<AffBean> iterator = affBeansList.iterator();
			while (iterator.hasNext()) {
				AffBean affBean = (AffBean) iterator.next();
				log.info("Colleges in action class are ::" + affBean.getInstName());

			}

			return SUCCESS;

		}

		else {

			log.info("file Format not Match");
			String msg = "Please Select Proper File Format";
			request.setAttribute("msg", msg);
			return "failure";
		}

	}

	public String GetFees() {
		feeList = feeDAO.GetFees("payee", "institute", null, null);

		return SUCCESS;
	}

	public String AddFees() {
		ArrayList<FeeDetailsBean> feelist = new ArrayList<FeeDetailsBean>();

		AffBean collegedata = new AffBean();
		try {
			// Get College id from request
			Integer id = Integer.parseInt(request.getParameter("collId").trim());
			// Get Fee ids from request
			String feeidstr = request.getParameter("reqFeeIds").trim();
			log.info(feeidstr);
			List<String> FeeIds = Arrays.asList(feeidstr.split(","));
			ArrayList<Integer> FeeIdsInt = new ArrayList<Integer>();
			Iterator<String> idIt = FeeIds.iterator();
			log.info(FeeIds.toString());
			while (idIt.hasNext()) {
				FeeIdsInt.add(Integer.parseInt(idIt.next()));
			}
			// Get College Data
			collegedata = affDao.getOneCollegeRecord(id);
			// Get Fees in Set
			feelist = feeDAO.GetFees("ids", null, null, FeeIdsInt);
			Set<FeeDetailsBean> feeset = collegedata.getFeeSet();
			Set<AffFeePropBean> propSet = collegedata.getFeeProps();
			for (int i = 0; i < feelist.size(); i++) {
				AffFeePropBean tempbean = new AffFeePropBean();
				tempbean.setFeeId(feelist.get(i).getFeeId());
				tempbean.setFeeName(feelist.get(i).getFeeName());
				tempbean.setDueBean(new PaymentDuesBean());
				propSet.add(tempbean);

			}

			feeset.addAll(feelist);
			// Add Fees to College Beans' FeeSet
			collegedata.setFeeSet(feeset);
			collegedata.setFeeProps(propSet);
			affDao.saveOrUpdate(collegedata, null);
			request.setAttribute("msg", "Fees Updated Successfully");
			// Save College Bean
			return SUCCESS;
		} catch (java.lang.NumberFormatException e) {
			e.printStackTrace();

			return ERROR;
		}
	}

	public String GetParameterListInstitute() {
		try {

			paramList2 = lookupdao.getLookupData("Scope", "Institute", null, null);
			HashMap<Integer, FvBean> paramMap = new HashMap<Integer, FvBean>();
			Iterator<LookupBean> lIt = paramList2.iterator();
			while (lIt.hasNext()) {
				LookupBean temp = lIt.next();
				List<FvBean> valueList = new ArrayList<FvBean>();
				valueList = temp.getFvBeansList();
				Iterator<FvBean> pIt = valueList.iterator();
				while (pIt.hasNext()) {
					FvBean temp2 = pIt.next();
					paramMap.put(temp2.getFeeValueId(), temp2);
				}
			}

			ses.setAttribute("sesParamMap", paramMap);
			String instituteId = request.getParameter("instId");
			Integer instId = Integer.parseInt(instituteId);
			affInstBean = affDao.viewInstDetail(instId);
			ses.setAttribute("sesAffBean", affInstBean);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	// Method to get Fee Properties' Details
	public String viewFeeProps() {
		// get institute id from request
		Integer reqinstId = Integer.parseInt(request.getParameter("instId").trim());
		// get fee id from request
		Integer reqfeeId = Integer.parseInt(request.getParameter("reqfeeId").trim());
		// get inst bean from database
		AffBean tempbean = affDao.getOneCollegeRecord(reqinstId);
		// populate feeprop bean with correct object from set
		Set<AffFeePropBean> feePropsSet = tempbean.getFeeProps();
		log.info("set size is " + feePropsSet.size());
		Iterator<AffFeePropBean> setIt = feePropsSet.iterator();
		while (setIt.hasNext()) {
			AffFeePropBean tempbean2 = new AffFeePropBean();
			tempbean2 = setIt.next();
			if (tempbean2.getFeeId() == reqfeeId) {
				propbean = tempbean2;
				return SUCCESS;
			}

		}
		return ERROR;
	}

	public String getDues() {
		// Get id from session
		Integer id = (Integer) ses.getAttribute("sesId");
		// get bean from db
		AffBean collbean = affDao.getOneCollegeRecord(id);
		// get feeprops set in list
		dueList = new ArrayList<AffFeePropBean>(collbean.getFeeProps());
		return SUCCESS;
	}

	// to get College Due Report

	public String collegeDueReport() {
		log.info("COllege Due Report");

		return SUCCESS;
	}

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

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public FileInputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(FileInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public ArrayList<FeeDetailsBean> getFeeList() {
		return feeList;
	}

	public void setFeeList(ArrayList<FeeDetailsBean> feeList) {
		this.feeList = feeList;
	}

	public Boolean getSaved() {
		return saved;
	}

	public void setSaved(Boolean saved) {
		this.saved = saved;
	}

	public ArrayList<AffBean> getFailureAffBeanList() {
		return failureAffBeanList;
	}

	public void setFailureAffBeanList(ArrayList<AffBean> failureAffBeanList) {
		this.failureAffBeanList = failureAffBeanList;
	}

	public ArrayList<AffBean> getExistingInstitureRecordList() {
		return existingInstitureRecordList;
	}

	public void setExistingInstitureRecordList(ArrayList<AffBean> existingInstitureRecordList) {
		this.existingInstitureRecordList = existingInstitureRecordList;
	}

	public ArrayList<Integer> getParamIds() {
		return paramIds;
	}

	public void setParamIds(ArrayList<Integer> paramIds) {
		this.paramIds = paramIds;
	}

	public ArrayList<LookupBean> getParamList2() {
		return paramList2;
	}

	public void setParamList2(ArrayList<LookupBean> paramList2) {
		this.paramList2 = paramList2;
	}

	public AffFeePropBean getPropbean() {
		return propbean;
	}

	public void setPropbean(AffFeePropBean propbean) {
		this.propbean = propbean;
	}

	public ArrayList<AffFeePropBean> getDueList() {
		return dueList;
	}

	public void setDueList(ArrayList<AffFeePropBean> dueList) {
		this.dueList = dueList;
	}

	public ArrayList<AffBean> getAffBeansList() {
		return affBeansList;
	}

	public void setAffBeansList(ArrayList<AffBean> affBeansList) {
		this.affBeansList = affBeansList;
	}

	// End of Getter Setter Methods
}
