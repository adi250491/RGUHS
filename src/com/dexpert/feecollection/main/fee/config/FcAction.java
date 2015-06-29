package com.dexpert.feecollection.main.fee.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.dexpert.feecollection.main.fee.lookup.LookupBean;
import com.dexpert.feecollection.main.fee.lookup.LookupDAO;
import com.dexpert.feecollection.main.fee.lookup.values.FvBean;
import com.dexpert.feecollection.main.fee.lookup.values.FvDAO;
import com.opensymphony.xwork2.ActionSupport;

public class FcAction extends ActionSupport {
	// Global Declarations Start
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	static Logger log = Logger.getLogger(FcAction.class.getName());
	HttpSession ses = ServletActionContext.getRequest().getSession();
	private ArrayList<LookupBean> CourseParamList = new ArrayList<LookupBean>();
	private ArrayList<LookupBean> InstituteParamList = new ArrayList<LookupBean>();
	private ArrayList<LookupBean> ApplicantParamList = new ArrayList<LookupBean>();
	private ArrayList<LookupBean> ServiceParamList = new ArrayList<LookupBean>();
	private ArrayList<String> SelectedCourseParam = new ArrayList<String>();
	private ArrayList<String> SelectedInstParam = new ArrayList<String>();
	private ArrayList<String> SelectedAppParam = new ArrayList<String>();
	private ArrayList<String> SelectedSerParam = new ArrayList<String>();
	LookupDAO lpDao = new LookupDAO();
	FvDAO fvdao = new FvDAO();

	// Global Declarations End
	// ----------------------
	// Action Methods Start
	public String populateFeeForm() {
		CourseParamList = lpDao.getLookupData("Scope", "Course", null, null);
		InstituteParamList = lpDao.getLookupData("Scope", "Institute", null, null);
		ApplicantParamList = lpDao.getLookupData("Scope", "Applicant", null, null);
		ServiceParamList = lpDao.getLookupData("Scope", "Service", null, null);
		ses.setAttribute("CourseParams", CourseParamList);
		ses.setAttribute("InsParams", InstituteParamList);
		ses.setAttribute("AppParams", ApplicantParamList);
		ses.setAttribute("SerParams", ServiceParamList);

		return SUCCESS;
	}

	public String GenerateCombination() {
		ArrayList<String> SelCouParVal = new ArrayList<String>();
		ArrayList<String> SelInsParVal = new ArrayList<String>();
		ArrayList<String> SelAppParVal = new ArrayList<String>();
		ArrayList<String> SelSerParVal = new ArrayList<String>();

		SelCouParVal = GetValueList(SelectedCourseParam);
		SelInsParVal = GetValueList(SelectedInstParam);
		SelAppParVal = GetValueList(SelectedAppParam);
		SelSerParVal = GetValueList(SelectedSerParam);

		HashMap<String, ArrayList<String>> paramMap = new HashMap<String, ArrayList<String>>();
		paramMap.put(SelCouParVal.size() + ":Course Size", SelCouParVal);
		paramMap.put(SelInsParVal.size() + ":Inst Size", SelInsParVal);
		paramMap.put(SelAppParVal.size() + ":App Size", SelAppParVal);
		paramMap.put(SelSerParVal.size() + ":Ser Size", SelSerParVal);
		ArrayList<String> sortedKeys = new ArrayList<String>(paramMap.keySet());

		// Integer[] lengths = {
		// SelectedCourseParam.size(),SelectedInstParam.size(),SelectedAppParam.size(),SelectedSerParam.size()};
		Collections.sort(sortedKeys, Collections.reverseOrder());
		log.info("sorted keys are " + sortedKeys.toString());
		int count = 0;
		for (int i = 0; i < 4; i++) {
			if (paramMap.get(sortedKeys.get(i)).size() != 0) {
				count++;
			} else {
				break;
			}
		}

		log.info("Coount is " + count);

		FindCombinations(count, paramMap.get(sortedKeys.get(0)), paramMap.get(sortedKeys.get(1)),
				paramMap.get(sortedKeys.get(2)), paramMap.get(sortedKeys.get(3)));
		// End of Commbination Code

		return SUCCESS;
	}

	// Action Methods End
	// ----------------------

	private void FindCombinations(Integer arrayCount, ArrayList<String> A, ArrayList<String> B, ArrayList<String> C,
			ArrayList<String> D)// Method to find all possible unique
								// combinations of selected parameters' values
	{
		Integer No_of_Arrays = arrayCount;// Arranged in descending order of
											// elements

		ArrayList<String> combos = new ArrayList<String>();
		String res = null;

		// Integer no_of_unique_cominations = A.size() * B.le * C.length *
		// D.length;
		if (No_of_Arrays == 4) {
			for (int i = 0; i < A.size(); i++) {
				for (int j = 0; j < B.size(); j++) {
					for (int j2 = 0; j2 < C.size(); j2++) {
						for (int k = 0; k < D.size(); k++) {
							res = A.get(i).toString().concat(B.get(j).toString()).concat(C.get(j2).toString())
									.concat(D.get(k).toString());
							combos.add(res);
						}
					}
				}
			}
		}
		if (No_of_Arrays == 3) {

			for (int j = 0; j < A.size(); j++) {
				for (int j2 = 0; j2 < B.size(); j2++) {
					for (int k = 0; k < C.size(); k++) {
						res = A.get(j).toString().concat(B.get(j2).toString()).concat(C.get(k).toString());
						combos.add(res);
					}
				}
			}

		}
		if (No_of_Arrays == 2) {
			for (int j2 = 0; j2 < A.size(); j2++) {
				for (int k = 0; k < B.size(); k++) {
					res = A.get(j2).toString().concat(B.get(k).toString());
					combos.add(res);
				}
			}
		}

		if (No_of_Arrays == 1) {
			for (int k = 0; k < A.size(); k++) {
				res = A.get(k).toString();
				combos.add(res);
			}
		}
		log.info("All Possible combos are " + combos.toString());
	}

	private ArrayList<String> GetValueList(ArrayList<String> inputList)
	// Method to get the values of the selected Parameters, extract
	// their Ids and convert them to string
	{
		ArrayList<String> resList = new ArrayList<String>();
		ArrayList<Integer> IdList = new ArrayList<Integer>();
		if (inputList.size() != 0) {
			Iterator<String> tempIt = inputList.iterator();

			while (tempIt.hasNext()) {
				IdList.add(Integer.parseInt(tempIt.next()));
			}
			ArrayList<LookupBean> tempList = lpDao.getLookupData("Ids", null, null, IdList);
			Iterator<LookupBean> beanIt = tempList.iterator();

			while (beanIt.hasNext()) {
				List<FvBean> valList = new ArrayList<FvBean>();
				valList = beanIt.next().getFvBeansList();
				Iterator<FvBean> valIt = valList.iterator();
				while (valIt.hasNext()) {
					resList.add(valIt.next().getFeeValueId().toString());
				}
			}

		}
		return resList;
	}

	// Getter Setters Start
	public ArrayList<LookupBean> getCourseParamList() {
		return CourseParamList;
	}

	public ArrayList<LookupBean> getInstituteParamList() {
		return InstituteParamList;
	}

	public ArrayList<LookupBean> getApplicantParamList() {
		return ApplicantParamList;
	}

	public ArrayList<LookupBean> getServiceParamList() {
		return ServiceParamList;
	}

	public ArrayList<String> getSelectedCourseParam() {
		return SelectedCourseParam;
	}

	public void setSelectedCourseParam(ArrayList<String> selectedCourseParam) {
		SelectedCourseParam = selectedCourseParam;
	}

	public ArrayList<String> getSelectedInstParam() {
		return SelectedInstParam;
	}

	public void setSelectedInstParam(ArrayList<String> selectedInstParam) {
		SelectedInstParam = selectedInstParam;
	}

	public ArrayList<String> getSelectedAppParam() {
		return SelectedAppParam;
	}

	public void setSelectedAppParam(ArrayList<String> selectedAppParam) {
		SelectedAppParam = selectedAppParam;
	}

	public ArrayList<String> getSelectedSerParam() {
		return SelectedSerParam;
	}

	public void setSelectedSerParam(ArrayList<String> selectedSerParam) {
		SelectedSerParam = selectedSerParam;
	}

	// Getter Setters End

}
