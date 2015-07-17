package com.dexpert.feecollection.main.fee.config;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.dexpert.feecollection.main.fee.lookup.LookupBean;


@Entity
@Table(name = "fee_details")
public class FeeDetailsBean {

	@GenericGenerator(name = "g11", strategy = "increment")
	@Id
	@GeneratedValue(generator = "g11")
	private Integer feeId;
	
	@Column(unique = true)
	private String feeName;
	private String ins_param,cou_param,app_param,ser_param;
	//private Boolean forApplicant=false,forInstitute=false,cal_mode=false;
	private Integer forApplicant,forInstitute,cal_mode;

	
	// one to many relationship with FeeDetails (Students)
		@OneToMany(cascade = CascadeType.ALL, targetEntity = FcBean.class, fetch = FetchType.EAGER )
		@JoinColumn(name = "feeId_fk", referencedColumnName = "feeId")
		private List<FcBean>configs;
		
	
	/*//one to many Unidirectional relationship
	
	@OneToMany(cascade=CascadeType.ALL,targetEntity=LookupBean.class)
	@JoinColumn(name="lookupId",referencedColumnName="feeId")
	private List<LookupBean> lookupBeanList;
	*/
	//you can use Set also in place of List
	
	
	/*public List<LookupBean> getLookupBeanList() {
		return lookupBeanList;
	}

	public void setLookupBeanList(List<LookupBean> lookupBeanList) {
		this.lookupBeanList = lookupBeanList;
	}*/

	public Integer getFeeId() {
		return feeId;
	}

	public void setFeeId(Integer feeId) {
		this.feeId = feeId;
	}

	public String getIns_param() {
		return ins_param;
	}

	public void setIns_param(String ins_param) {
		this.ins_param = ins_param;
	}

	public String getCou_param() {
		return cou_param;
	}

	public void setCou_param(String cou_param) {
		this.cou_param = cou_param;
	}

	public String getApp_param() {
		return app_param;
	}

	public void setApp_param(String app_param) {
		this.app_param = app_param;
	}

	public String getSer_param() {
		return ser_param;
	}

	public void setSer_param(String ser_param) {
		this.ser_param = ser_param;
	}

	

	public Integer getForApplicant() {
		return forApplicant;
	}

	public void setForApplicant(Integer forApplicant) {
		this.forApplicant = forApplicant;
	}

	public Integer getForInstitute() {
		return forInstitute;
	}

	public void setForInstitute(Integer forInstitute) {
		this.forInstitute = forInstitute;
	}

	public Integer getCal_mode() {
		return cal_mode;
	}

	public void setCal_mode(Integer cal_mode) {
		this.cal_mode = cal_mode;
	}

	public String getFeeName() {
		return feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	public List<FcBean> getConfigs() {
		return configs;
	}

	public void setConfigs(List<FcBean> configs) {
		this.configs = configs;
	}
	

}
