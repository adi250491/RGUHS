package com.dexpert.feecollection.main.fee.config;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "fee_config_master")
public class FcBean {

	@GenericGenerator(name = "g11", strategy = "increment")
	@Id
	@GeneratedValue(generator = "g11")
	private Integer feeConfigId;
	private String feeName;
	
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
