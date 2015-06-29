package com.dexpert.feecollection.main.fee.lookup;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.dexpert.feecollection.main.fee.lookup.values.FvBean;
import com.opensymphony.xwork2.ActionSupport;

public class LookupAction extends ActionSupport {
	// Declare Global Variables Here
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	static Logger log = Logger.getLogger(LookupAction.class.getName());
	private LookupBean lookupdata;
	private LookupDAO lookupdao = new LookupDAO();
	private ArrayList<LookupBean> paramList = new ArrayList<LookupBean>();
	private List<LookupBean> lookupBeansList = new ArrayList<LookupBean>();

	// End of Global Variables

	// ---------------------------------------------------

	// Action Methods Here
	public String AddParameter() {
		try {
			if (lookupdata.getLookupType().contentEquals("Boolean")) {
				List<FvBean> valueList = new ArrayList<FvBean>();
				FvBean paramvalues = new FvBean();
				paramvalues.setValue("1");
				valueList.add(paramvalues);
				paramvalues = new FvBean();
				paramvalues.setValue("0");
				valueList.add(paramvalues);
				lookupdata.setFvBeansList(valueList);
			}
			lookupdata = lookupdao.saveLookupData(lookupdata);
			if (lookupdata.getLookupId() != null) {
				request.setAttribute("redirectLink", "ParamForm.jsp");
				if (lookupdata.getLookupType().contentEquals("Boolean")) {
					return "Boolean";
				} else if (lookupdata.getLookupType().contentEquals("Integer")) {
					return "Numeric";
				} else {
					return SUCCESS;
				}
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}

	}

	public String GetParameterListAll() {
		try {
			paramList = lookupdao.getLookupData("All", null, null,null);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String parameterValList() {
		lookupBeansList = lookupdao.getListOfLookUpValues();
		return SUCCESS;
	}

	// End of Action Methods

	// ---------------------------------------------------

	// Keep Getter Setter Methods Here
	public LookupBean getLookupdata() {
		return lookupdata;
	}

	public void setLookupdata(LookupBean lookupdata) {
		this.lookupdata = lookupdata;
	}

	public ArrayList<LookupBean> getParamList() {
		return paramList;
	}

	public List<LookupBean> getLookupBeansList() {
		return lookupBeansList;
	}

	public void setLookupBeansList(List<LookupBean> lookupBeansList) {
		this.lookupBeansList = lookupBeansList;
	}

}
