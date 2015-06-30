package com.dexpert.feecollection.main.fee.config;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.dexpert.feecollection.main.fee.lookup.LookupBean;

@Entity
@Table(name = "fee_config_master")
public class FcBean {

	@GenericGenerator(name = "g11", strategy = "increment")
	@Id
	@GeneratedValue(generator = "g11")
	private Integer feeConfigId;
	private String feeName;

	
	//one to many Unidirectional relationship
	
	@OneToMany(cascade=CascadeType.ALL,targetEntity=LookupBean.class)
	@JoinColumn(name="feeConfig_id_fk",referencedColumnName="feeConfigId")
	private List<LookupBean> lookupBeanList;

	//you can use Set also in place of List
	
	
	public List<LookupBean> getLookupBeanList() {
		return lookupBeanList;
	}

	public void setLookupBeanList(List<LookupBean> lookupBeanList) {
		this.lookupBeanList = lookupBeanList;
	}

	public Integer getFeeConfigId() {
		return feeConfigId;
	}

	public void setFeeConfigId(Integer feeConfigId) {
		this.feeConfigId = feeConfigId;
	}

	public String getFeeName() {
		return feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

}
