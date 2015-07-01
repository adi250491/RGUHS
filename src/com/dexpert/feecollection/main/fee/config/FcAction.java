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

	public class ComboObject {
		private Integer comboId;

	}

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
	private ArrayList<ArrayList<String>> ComboList = new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<String>> BodyList = new ArrayList<ArrayList<String>>();
	private ArrayList<String> HeaderList = new ArrayList<String>();

	LookupDAO lpDao = new LookupDAO();
	FvDAO fvdao = new FvDAO();

	// Global Declarations End
	// ----------------------
	// Action Methods Start
	public String populateFeeForm() {

		// Only Testing Something New !
		ArrayList<String> valuesArray = new ArrayList<String>();
		valuesArray.add("Indian");
		valuesArray.add("Open");
		valuesArray.add("LC");
		valuesArray.add("No");
		ComboList.add(valuesArray);
		valuesArray = new ArrayList<String>();
		valuesArray.add("Indian");
		valuesArray.add("SC");
		valuesArray.add("LC");
		valuesArray.add("No");
		ComboList.add(valuesArray);
		valuesArray = new ArrayList<String>();
		valuesArray.add("Indian");
		valuesArray.add("ST");
		valuesArray.add("LC");
		valuesArray.add("No");
		ComboList.add(valuesArray);
		valuesArray = new ArrayList<String>();
		valuesArray.add("Foreign");
		valuesArray.add("Open");
		valuesArray.add("LC");
		valuesArray.add("No");
		ComboList.add(valuesArray);
		valuesArray = new ArrayList<String>();
		valuesArray.add("Foreign");
		valuesArray.add("SC");
		valuesArray.add("LC");
		valuesArray.add("No");
		ComboList.add(valuesArray);
		valuesArray = new ArrayList<String>();
		valuesArray.add("Foreign");
		valuesArray.add("ST");
		valuesArray.add("LC");
		valuesArray.add("No");
		ComboList.add(valuesArray);

		log.info("combolist is " + ComboList.toString());

		// Done with testing

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

		ArrayList<Integer[]> Combos = new ArrayList<Integer[]>();

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

		Combos = FindCombinations(count, paramMap.get(sortedKeys.get(0)), paramMap.get(sortedKeys.get(1)),
				paramMap.get(sortedKeys.get(2)), paramMap.get(sortedKeys.get(3)));

		// Populate Header List with Strings of Parameters
		HeaderList = GetHeaders(SelectedInstParam, SelectedCourseParam, SelectedAppParam, SelectedSerParam);
		// Populate the BodyList with Strings of Parameter Values and a unique
		// id for each Combo
		BodyList = GetBodyContent(Combos);

		// End of Commbination Code

		return SUCCESS;
	}

	// Action Methods End
	// ----------------------

	private ArrayList<Integer[]> FindCombinations(Integer arrayCount, ArrayList<String> A, ArrayList<String> B,
			ArrayList<String> C, ArrayList<String> D)// Method to find all
														// possible unique
														// combinations of
														// selected parameters'
														// values
	{
		Integer No_of_Arrays = arrayCount;// Arranged in descending order of
											// elements

		ArrayList<ArrayList<String>> combos = new ArrayList<ArrayList<String>>();
		ArrayList<Integer[]> res = new ArrayList<Integer[]>();

		// Integer no_of_unique_cominations = A.size() * B.le * C.length *
		// D.length;
		if (No_of_Arrays == 4) {
			for (int i = 0; i < A.size(); i++) {
				for (int j = 0; j < B.size(); j++) {
					for (int j2 = 0; j2 < C.size(); j2++) {
						for (int k = 0; k < D.size(); k++) {
							Integer[] temp = { Integer.parseInt(A.get(i)), Integer.parseInt(B.get(j)),
									Integer.parseInt(C.get(j2)), Integer.parseInt(D.get(k)) };
							res.add(temp);
							// res.add(A.get(i).concat(",").concat(B.get(j)).concat(",").concat(C.get(j2)).concat(",")
							// .concat(D.get(k)));
							// combos.add(res);
						}
					}
				}
			}
		}
		if (No_of_Arrays == 3) {

			for (int j = 0; j < A.size(); j++) {
				for (int j2 = 0; j2 < B.size(); j2++) {
					for (int k = 0; k < C.size(); k++) {
						Integer[] temp = { Integer.parseInt(A.get(j)), Integer.parseInt(B.get(j2)),
								Integer.parseInt(C.get(k)) };
						res.add(temp);
						// res.add(A.get(j).concat(",").concat(B.get(j2).concat(",")).concat(C.get(k)));
						// combos.add(res);
					}
				}
			}

		}
		if (No_of_Arrays == 2) {
			for (int j2 = 0; j2 < A.size(); j2++) {
				for (int k = 0; k < B.size(); k++) {
					Integer[] temp = { Integer.parseInt(A.get(j2)), Integer.parseInt(B.get(k)) };
					res.add(temp);
					// res.add(A.get(j2).concat(",").concat(B.get(k)));
					// /combos.add(res);
				}
			}
		}

		if (No_of_Arrays == 1) {
			for (int k = 0; k < A.size(); k++) {
				Integer[] temp = { Integer.parseInt(A.get(k)) };
				res.add(temp);
				// res.add(A.get(k));

				// combos.add(res);
			}
		}
		for (int i = 0; i < res.size(); i++) {
			log.info("Index "+i+" Combo: "+res.get(i));

		}
		return res;
	}

	private ArrayList<String> GetValueList(ArrayList<String> inputList)
	// Method to get the values of the selected Parameters, extract
	// their Ids and convert them to string
	{
		ArrayList<String> resList = new ArrayList<String>();
		ArrayList<Integer> IdList = new ArrayList<Integer>();
		if (inputList.size() != 0) {
			IdList = GetIds(inputList);

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

	private ArrayList<String> GetHeaders(ArrayList<String> Inst, ArrayList<String> Cour, ArrayList<String> Appl,
			ArrayList<String> Serv) {
		
		ArrayList<String> ResultList = new ArrayList<String>();
		if (Inst.size() > 0) {
			ArrayList<Integer> IDList = new ArrayList<Integer>();
			IDList = GetIds(Inst);
			ArrayList<LookupBean> tempList = lpDao.getLookupData("Ids", null, null, IDList);
			Iterator<LookupBean> beanIt1 = tempList.iterator();
			while (beanIt1.hasNext()) {
				String tempStr = beanIt1.next().getLookupName();
				ResultList.add(tempStr);
			}

		}
		if (Cour.size() > 0) {
			ArrayList<Integer> IDList = new ArrayList<Integer>();
			IDList = GetIds(Cour);
			ArrayList<LookupBean> tempList = lpDao.getLookupData("Ids", null, null, IDList);
			Iterator<LookupBean> beanIt2 = tempList.iterator();
			while (beanIt2.hasNext()) {
				String tempStr = beanIt2.next().getLookupName();
				ResultList.add(tempStr);
			}

		}
		if (Appl.size() > 0) {
			ArrayList<Integer> IDList = new ArrayList<Integer>();
			IDList = GetIds(Appl);
			ArrayList<LookupBean> tempList = lpDao.getLookupData("Ids", null, null, IDList);
			Iterator<LookupBean> beanIt3 = tempList.iterator();
			while (beanIt3.hasNext()) {
				String tempStr = beanIt3.next().getLookupName();
				ResultList.add(tempStr);
			}

		}
		if (Serv.size() > 0) {
			ArrayList<Integer> IDList = new ArrayList<Integer>();
			IDList = GetIds(Serv);
			ArrayList<LookupBean> tempList = lpDao.getLookupData("Ids", null, null, IDList);
			Iterator<LookupBean> beanIt4 = tempList.iterator();
			while (beanIt4.hasNext()) {
				String tempStr = beanIt4.next().getLookupName();
				ResultList.add(tempStr);
			}

		}
		return ResultList;
	}

	private ArrayList<ArrayList<String>> GetBodyContent(ArrayList<Integer[]> ComboIds) {

		Integer Uid = 0;
		ArrayList<ArrayList<String>> ResultList = new ArrayList<ArrayList<String>>();
		
		Iterator<Integer[]> comboIt = ComboIds.iterator();
		while (comboIt.hasNext()) {
			ArrayList<String> comboList = new ArrayList<String>();
			comboList.add(Uid.toString());
			Integer[] tempArr = comboIt.next();
			
			ArrayList<Integer> tempids = new ArrayList<Integer>();
			for (int i = 0; i < tempArr.length; i++) {
				tempids.add(tempArr[i]);
			}
			ArrayList<FvBean> tempList = new ArrayList<FvBean>();
			log.info("Temp Array of combo ids is "+tempids);
			tempList = fvdao.getValues("Ids", null, tempids);
			Iterator<FvBean> beanIt = tempList.iterator();
			
			while (beanIt.hasNext()) {
				comboList.add(beanIt.next().getValue());
				
			}
			log.info("Temp Array of combolist ids is "+comboList);
			ResultList.add(comboList);
			Uid++;
		}
		return ResultList;
	}

	private ArrayList<Integer> GetIds(ArrayList<String> inputList) {
		ArrayList<Integer> IdList = new ArrayList<Integer>();
		if (inputList.size() != 0) {
			Iterator<String> tempIt = inputList.iterator();

			while (tempIt.hasNext()) {
				IdList.add(Integer.parseInt(tempIt.next()));
			}
		}
		return IdList;
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

	public ArrayList<ArrayList<String>> getComboList() {
		return ComboList;
	}

	public void setComboList(ArrayList<ArrayList<String>> comboList) {
		ComboList = comboList;
	}

	public ArrayList<ArrayList<String>> getBodyList() {
		return BodyList;
	}

	public void setBodyList(ArrayList<ArrayList<String>> bodyList) {
		BodyList = bodyList;
	}

	public ArrayList<String> getHeaderList() {
		return HeaderList;
	}

	public void setHeaderList(ArrayList<String> headerList) {
		HeaderList = headerList;
	}

	// Getter Setters End

}
