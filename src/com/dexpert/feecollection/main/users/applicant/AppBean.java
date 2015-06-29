package com.dexpert.feecollection.main.users.applicant;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.dexpert.feecollection.main.payment.transaction.PayBean;
import com.dexpert.feecollection.main.users.LoginBean;
import com.dexpert.feecollection.main.users.affiliated.AffBean;

@Entity
@Table(name = "applicant_details")
public class AppBean {

	@GenericGenerator(name = "g3", strategy = "increment")
	@Id
	@GeneratedValue(generator = "g3")
	private Integer aplId;
	private String aplName;
	private String aplMidName;
	private String aplLstName;
	private String aplAddress;
	private Integer aplAge;

	// one to one bidirectional relationship with login
	@OneToOne(cascade = CascadeType.ALL)
	LoginBean loginBean;

	// one to many relationship of applicant with Payment
	@OneToMany(cascade = CascadeType.ALL, targetEntity = PayBean.class)
	@JoinColumn(name = "aplicantId_Fk", referencedColumnName = "aplId")
	private Set<PayBean> payBeansSet;

	// one to one bidirectional  relationship with student and college
	@OneToOne(cascade = CascadeType.ALL)
	AffBean affBean;

	public AffBean getAffBean() {
		return affBean;
	}

	public void setAffBean(AffBean affBean) {
		this.affBean = affBean;
	}

	public Set<PayBean> getPayBeansSet() {
		return payBeansSet;
	}

	public void setPayBeansSet(Set<PayBean> payBeansSet) {
		this.payBeansSet = payBeansSet;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public Integer getAplId() {
		return aplId;
	}

	public void setAplId(Integer aplId) {
		this.aplId = aplId;
	}

	public String getAplName() {
		return aplName;
	}

	public void setAplName(String aplName) {
		this.aplName = aplName;
	}

	public String getAplMidName() {
		return aplMidName;
	}

	public void setAplMidName(String aplMidName) {
		this.aplMidName = aplMidName;
	}

	public String getAplLstName() {
		return aplLstName;
	}

	public void setAplLstName(String aplLstName) {
		this.aplLstName = aplLstName;
	}

	public String getAplAddress() {
		return aplAddress;
	}

	public void setAplAddress(String aplAddress) {
		this.aplAddress = aplAddress;
	}

	public Integer getAplAge() {
		return aplAge;
	}

	public void setAplAge(Integer aplAge) {
		this.aplAge = aplAge;
	}

}
