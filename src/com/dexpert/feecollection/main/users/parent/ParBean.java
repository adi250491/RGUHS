package com.dexpert.feecollection.main.users.parent;

import java.io.File;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.dexpert.feecollection.main.users.LoginBean;

@Entity
@Table(name = "parent_inst_detail")
public class ParBean {

	@GenericGenerator(name = "g9", strategy = "increment")
	@Id
	@GeneratedValue(generator = "g9")
	private Integer parInstId;
	private String parInstName, parInstAddress, parInstContPerson, parInstEmail, parInstContact;
	// --------------------------------------
	// to upload file
	private File fileUpload;

	private String fileUploadFileName;
	private Integer fileSize;

	@Lob
	@Column(name = "filesByteSize", columnDefinition = "mediumblob")
	byte[] filesByteSize;

	// ------------------------------------

	@OneToOne(cascade = CascadeType.ALL)
	LoginBean loginBean;

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public Integer getParInstId() {
		return parInstId;
	}

	public void setParInstId(Integer parInstId) {
		this.parInstId = parInstId;
	}

	public String getParInstName() {
		return parInstName;
	}

	public void setParInstName(String parInstName) {
		this.parInstName = parInstName;
	}

	public String getParInstAddress() {
		return parInstAddress;
	}

	public void setParInstAddress(String parInstAddress) {
		this.parInstAddress = parInstAddress;
	}

	public String getParInstContPerson() {
		return parInstContPerson;
	}

	public void setParInstContPerson(String parInstContPerson) {
		this.parInstContPerson = parInstContPerson;
	}

	public String getParInstEmail() {
		return parInstEmail;
	}

	public void setParInstEmail(String parInstEmail) {
		this.parInstEmail = parInstEmail;
	}

	public String getParInstContact() {
		return parInstContact;
	}

	public void setParInstContact(String parInstContact) {
		this.parInstContact = parInstContact;
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

}
