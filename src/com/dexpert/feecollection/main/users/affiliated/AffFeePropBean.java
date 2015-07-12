package com.dexpert.feecollection.main.users.affiliated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "institute_fee_properties")
public class AffFeePropBean {

	@GenericGenerator(name = "g1", strategy = "increment")
	@Id
	@GeneratedValue(generator = "g1")
	private Integer propId;
	private Integer feeId;
	private String timeFactor;
	private String factorValue;
	public Integer getPropId() {
		return propId;
	}
	public void setPropId(Integer propId) {
		this.propId = propId;
	}
	public Integer getFeeId() {
		return feeId;
	}
	public void setFeeId(Integer feeId) {
		this.feeId = feeId;
	}
	public String getTimeFactor() {
		return timeFactor;
	}
	public void setTimeFactor(String timeFactor) {
		this.timeFactor = timeFactor;
	}
	public String getFactorValue() {
		return factorValue;
	}
	public void setFactorValue(String factorValue) {
		this.factorValue = factorValue;
	}
	
	

}
