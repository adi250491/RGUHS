package com.dexpert.feecollection.main.users.affiliated;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.dexpert.feecollection.main.fee.lookup.values.FvBean;

@Entity
@Table(name = "affiliated_institute_params")
public class AffParams {

	@GenericGenerator(name = "g1", strategy = "increment")
	@Id
	@GeneratedValue(generator = "g1")
	private Integer id;
	
	// one to one  relationship with parameter value
		
	@OneToOne(cascade = CascadeType.ALL ,targetEntity = FvBean.class, fetch = FetchType.EAGER)
	private FvBean valuebean;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FvBean getValuebean() {
		return valuebean;
	}

	public void setValuebean(FvBean valuebean) {
		this.valuebean = valuebean;
	}
	
	
	
	
}

