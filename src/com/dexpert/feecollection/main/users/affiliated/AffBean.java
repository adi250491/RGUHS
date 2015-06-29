package com.dexpert.feecollection.main.users.affiliated;

import java.io.File;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.dexpert.feecollection.main.users.LoginBean;
import com.dexpert.feecollection.main.users.applicant.AppBean;

@Entity
@Table(name = "affiliated_institute_details")
public class AffBean {

	@GenericGenerator(name = "g4", strategy = "increment")
	@Id
	@GeneratedValue(generator = "g4")
	private Integer instId;
	private String instName, contactPerson, place, email, contactNumber, mobileNum;
	private String instAddress;

	// --------------------------------------
	// to upload file
	private File fileUpload;

	private String fileUploadFileName;
	private Integer fileSize;

	@Lob
	@Column(name = "filesByteSize", nullable = false, columnDefinition = "mediumblob")
	byte[] filesByteSize;

	// ------------------------------------

	// one to one bidirectional relationship with login

	@OneToOne(cascade = CascadeType.ALL)
	LoginBean loginBean;

	/*
	 * // one to many Bidirectional Relationship with LoginBean
	 * 
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "affBean") private
	 * Set<LoginBean> loginBeanSet;
	 */
	// one to many relationship with Applicants (Students)
	@OneToMany(cascade = CascadeType.ALL, targetEntity = AppBean.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "InstituteId_Fk", referencedColumnName = "instId")
	Set<AppBean> aplBeanSet;

	// one to one bidirectional relationship with student and college
	@OneToOne(cascade = CascadeType.ALL, targetEntity = AppBean.class)
	@JoinColumn(name = "instituteId_Fk", referencedColumnName = "instId")
	public Set<AppBean> getAplBeanSet() {
		return aplBeanSet;
	}

	public void setAplBeanSet(Set<AppBean> aplBeanSet) {
		this.aplBeanSet = aplBeanSet;
	}

	/*
	 * public Set<LoginBean> getLoginBeanSet() { return loginBeanSet; }
	 * 
	 * public void setLoginBeanSet(Set<LoginBean> loginBeanSet) {
	 * this.loginBeanSet = loginBeanSet; }
	 */

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public Integer getInstId() {
		return instId;
	}

	public void setInstId(Integer instId) {
		this.instId = instId;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getInstAddress() {
		return instAddress;
	}

	public void setInstAddress(String instAddress) {
		this.instAddress = instAddress;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
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

	public byte[] getFilesByteSize() {
		return filesByteSize;
	}

	public void setFilesByteSize(byte[] filesByteSize) {
		this.filesByteSize = filesByteSize;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

}
