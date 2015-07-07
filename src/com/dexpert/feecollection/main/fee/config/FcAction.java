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
		ArrayList<String> SelCouParVal1 = new ArrayList<String>();
		ArrayList<String> SelCouParVal2 = new ArrayList<String>();
		ArrayList<String> SelCouParVal3 = new ArrayList<String>();
		ArrayList<String> SelCouParVal4 = new ArrayList<String>();

		ArrayList<String> SelInsParVal1 = new ArrayList<String>();
		ArrayList<String> SelInsParVal2 = new ArrayList<String>();
		ArrayList<String> SelInsParVal3 = new ArrayList<String>();
		ArrayList<String> SelInsParVal4 = new ArrayList<String>();

		ArrayList<String> SelAppParVal1 = new ArrayList<String>();
		ArrayList<String> SelAppParVal2 = new ArrayList<String>();
		ArrayList<String> SelAppParVal3 = new ArrayList<String>();
		ArrayList<String> SelAppParVal4 = new ArrayList<String>();

		ArrayList<String> SelSerParVal1 = new ArrayList<String>();
		ArrayList<String> SelSerParVal2 = new ArrayList<String>();
		ArrayList<String> SelSerParVal3 = new ArrayList<String>();
		ArrayList<String> SelSerParVal4 = new ArrayList<String>();

		ArrayList<Integer[]> Combos = new ArrayList<Integer[]>();

		switch (SelectedCourseParam.size()) {
		case 0:

			break;
		case 1:
			SelCouParVal1 = GetValueList(SelectedCourseParam.get(0));
			break;
		case 2:
			SelCouParVal1 = GetValueList(SelectedCourseParam.get(0));
			SelCouParVal2 = GetValueList(SelectedCourseParam.get(1));
			break;
		case 3:
			SelCouParVal1 = GetValueList(SelectedCourseParam.get(0));
			SelCouParVal2 = GetValueList(SelectedCourseParam.get(1));
			SelCouParVal3 = GetValueList(SelectedCourseParam.get(2));
			break;
		case 4:
			SelCouParVal1 = GetValueList(SelectedCourseParam.get(0));
			SelCouParVal2 = GetValueList(SelectedCourseParam.get(1));
			SelCouParVal3 = GetValueList(SelectedCourseParam.get(2));
			SelCouParVal4 = GetValueList(SelectedCourseParam.get(3));
			break;

		default:
			break;
		}

		switch (SelectedAppParam.size()) {
		case 0:

			break;
		case 1:
			SelAppParVal1 = GetValueList(SelectedAppParam.get(0));
			break;
		case 2:
			SelAppParVal1 = GetValueList(SelectedAppParam.get(0));
			SelAppParVal2 = GetValueList(SelectedAppParam.get(1));
			break;
		case 3:
			SelAppParVal1 = GetValueList(SelectedAppParam.get(0));
			SelAppParVal2 = GetValueList(SelectedAppParam.get(1));
			SelAppParVal3 = GetValueList(SelectedAppParam.get(2));
			break;
		case 4:
			SelAppParVal1 = GetValueList(SelectedAppParam.get(0));
			SelAppParVal2 = GetValueList(SelectedAppParam.get(1));
			SelAppParVal3 = GetValueList(SelectedAppParam.get(2));
			SelAppParVal4 = GetValueList(SelectedAppParam.get(3));
			break;

		default:
			break;
		}

		switch (SelectedInstParam.size()) {
		case 0:

			break;
		case 1:
			SelInsParVal1 = GetValueList(SelectedInstParam.get(0));
			break;
		case 2:
			SelInsParVal1 = GetValueList(SelectedInstParam.get(0));
			SelInsParVal2 = GetValueList(SelectedInstParam.get(1));
			break;
		case 3:
			SelInsParVal1 = GetValueList(SelectedInstParam.get(0));
			SelInsParVal2 = GetValueList(SelectedInstParam.get(1));
			SelInsParVal3 = GetValueList(SelectedInstParam.get(2));
			break;
		case 4:
			SelInsParVal1 = GetValueList(SelectedInstParam.get(0));
			SelInsParVal2 = GetValueList(SelectedInstParam.get(1));
			SelInsParVal3 = GetValueList(SelectedInstParam.get(2));
			SelInsParVal4 = GetValueList(SelectedInstParam.get(3));
			break;

		default:
			break;
		}

		switch (SelectedSerParam.size()) {
		case 0:

			break;
		case 1:
			SelSerParVal1 = GetValueList(SelectedSerParam.get(0));
			break;
		case 2:
			SelSerParVal1 = GetValueList(SelectedSerParam.get(0));
			SelSerParVal2 = GetValueList(SelectedSerParam.get(1));
			break;
		case 3:
			SelSerParVal1 = GetValueList(SelectedSerParam.get(0));
			SelSerParVal2 = GetValueList(SelectedSerParam.get(1));
			SelSerParVal3 = GetValueList(SelectedSerParam.get(2));
			break;
		case 4:
			SelSerParVal1 = GetValueList(SelectedSerParam.get(0));
			SelSerParVal2 = GetValueList(SelectedSerParam.get(1));
			SelSerParVal3 = GetValueList(SelectedSerParam.get(2));
			SelSerParVal4 = GetValueList(SelectedSerParam.get(3));
			break;

		default:
			break;
		}

		// SelInsParVal1 = GetValueList(SelectedInstParam);
		// SelAppParVal1 = GetValueList(SelectedAppParam);
		// SelSerParVal1 = GetValueList(SelectedSerParam);

		HashMap<String, ArrayList<String>> paramMap = new HashMap<String, ArrayList<String>>();
		paramMap.put(SelCouParVal1.size() + ":Course 1 Size", SelCouParVal1);
		paramMap.put(SelInsParVal1.size() + ":Inst 1 Size", SelInsParVal1);
		paramMap.put(SelAppParVal1.size() + ":App 1 Size", SelAppParVal1);
		paramMap.put(SelSerParVal1.size() + ":Ser 1 Size", SelSerParVal1);

		paramMap.put(SelCouParVal2.size() + ":Course 2 Size", SelCouParVal2);
		paramMap.put(SelInsParVal2.size() + ":Inst 2 Size", SelInsParVal2);
		paramMap.put(SelAppParVal2.size() + ":App 2 Size", SelAppParVal2);
		paramMap.put(SelSerParVal2.size() + ":Ser 2 Size", SelSerParVal2);

		paramMap.put(SelCouParVal3.size() + ":Course 3 Size", SelCouParVal3);
		paramMap.put(SelInsParVal3.size() + ":Inst 3 Size", SelInsParVal3);
		paramMap.put(SelAppParVal3.size() + ":App 3 Size", SelAppParVal3);
		paramMap.put(SelSerParVal3.size() + ":Ser 3 Size", SelSerParVal3);

		paramMap.put(SelCouParVal4.size() + ":Course 4 Size", SelCouParVal4);
		paramMap.put(SelInsParVal4.size() + ":Inst 4 Size", SelInsParVal4);
		paramMap.put(SelAppParVal4.size() + ":App 4 Size", SelAppParVal4);
		paramMap.put(SelSerParVal4.size() + ":Ser 4 Size", SelSerParVal4);
		ArrayList<String> sortedKeys = new ArrayList<String>(paramMap.keySet());

		// Integer[] lengths = {
		// SelectedCourseParam.size(),SelectedInstParam.size(),SelectedAppParam.size(),SelectedSerParam.size()};
		Collections.sort(sortedKeys, Collections.reverseOrder());
		log.info("sorted keys are " + sortedKeys.toString());
		int count = 0;
		for (int i = 0; i < 16; i++) {
			if (paramMap.get(sortedKeys.get(i)).size() != 0) {
				count++;
			} else {
				break;
			}
		}

		log.info("Coount is " + count);

		Combos = FindCombinations(count, paramMap.get(sortedKeys.get(0)), paramMap.get(sortedKeys.get(1)),
				paramMap.get(sortedKeys.get(2)), paramMap.get(sortedKeys.get(3)), paramMap.get(sortedKeys.get(4)),
				paramMap.get(sortedKeys.get(5)), paramMap.get(sortedKeys.get(6)), paramMap.get(sortedKeys.get(7)),
				paramMap.get(sortedKeys.get(8)), paramMap.get(sortedKeys.get(9)), paramMap.get(sortedKeys.get(10)),
				paramMap.get(sortedKeys.get(11)), paramMap.get(sortedKeys.get(12)), paramMap.get(sortedKeys.get(13)),
				paramMap.get(sortedKeys.get(14)), paramMap.get(sortedKeys.get(15)));

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
			ArrayList<String> C, ArrayList<String> D, ArrayList<String> E, ArrayList<String> F, ArrayList<String> G,
			ArrayList<String> H, ArrayList<String> I, ArrayList<String> J, ArrayList<String> K, ArrayList<String> L,
			ArrayList<String> M, ArrayList<String> N, ArrayList<String> O, ArrayList<String> P)
	// Method
	// to
	// find
	// all
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

		if (No_of_Arrays == 16) {
			for (int p = 0; p < P.size(); p++) {
				for (int o = 0; o < O.size(); o++) {
					for (int n = 0; n < N.size(); n++) {
						for (int m = 0; m < M.size(); m++) {
							for (int l = 0; l < L.size(); l++) {
								for (int k = 0; k < K.size(); k++) {
									for (int j = 0; j < J.size(); j++) {
										for (int i = 0; i < I.size(); i++) {
											for (int h = 0; h < H.size(); h++) {
												for (int g = 0; g < G.size(); g++) {
													for (int f = 0; f < F.size(); f++) {
														for (int e = 0; e < E.size(); e++) {
															for (int d = 0; d < D.size(); d++) {
																for (int c = 0; c < C.size(); c++) {
																	for (int b = 0; b < B.size(); b++) {
																		for (int a = 0; a < A.size(); a++) {
																			Integer[] temp = {
																					Integer.parseInt(A.get(a)),
																					Integer.parseInt(B.get(b)),
																					Integer.parseInt(C.get(c)),
																					Integer.parseInt(D.get(d)),
																					Integer.parseInt(E.get(e)),
																					Integer.parseInt(F.get(f)),
																					Integer.parseInt(G.get(g)),
																					Integer.parseInt(H.get(h)),
																					Integer.parseInt(I.get(i)),
																					Integer.parseInt(J.get(j)),
																					Integer.parseInt(K.get(k)),
																					Integer.parseInt(L.get(l)),
																					Integer.parseInt(M.get(m)),
																					Integer.parseInt(N.get(n)),
																					Integer.parseInt(O.get(o)),
																					Integer.parseInt(P.get(p)) };
																			res.add(temp);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		if (No_of_Arrays == 15) {
			for (int o = 0; o < O.size(); o++) {
				for (int n = 0; n < N.size(); n++) {
					for (int m = 0; m < M.size(); m++) {
						for (int l = 0; l < L.size(); l++) {
							for (int k = 0; k < K.size(); k++) {
								for (int j = 0; j < J.size(); j++) {
									for (int i = 0; i < I.size(); i++) {
										for (int h = 0; h < H.size(); h++) {
											for (int g = 0; g < G.size(); g++) {
												for (int f = 0; f < F.size(); f++) {
													for (int e = 0; e < E.size(); e++) {
														for (int d = 0; d < D.size(); d++) {
															for (int c = 0; c < C.size(); c++) {
																for (int b = 0; b < B.size(); b++) {
																	for (int a = 0; a < A.size(); a++) {
																		Integer[] temp = { Integer.parseInt(A.get(a)),
																				Integer.parseInt(B.get(b)),
																				Integer.parseInt(C.get(c)),
																				Integer.parseInt(D.get(d)),
																				Integer.parseInt(E.get(e)),
																				Integer.parseInt(F.get(f)),
																				Integer.parseInt(G.get(g)),
																				Integer.parseInt(H.get(h)),
																				Integer.parseInt(I.get(i)),
																				Integer.parseInt(J.get(j)),
																				Integer.parseInt(K.get(k)),
																				Integer.parseInt(L.get(l)),
																				Integer.parseInt(M.get(m)),
																				Integer.parseInt(N.get(n)),
																				Integer.parseInt(O.get(o)) };
																		res.add(temp);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

		}
		if (No_of_Arrays == 14) {

			for (int n = 0; n < N.size(); n++) {
				for (int m = 0; m < M.size(); m++) {
					for (int l = 0; l < L.size(); l++) {
						for (int k = 0; k < K.size(); k++) {
							for (int j = 0; j < J.size(); j++) {
								for (int i = 0; i < I.size(); i++) {
									for (int h = 0; h < H.size(); h++) {
										for (int g = 0; g < G.size(); g++) {
											for (int f = 0; f < F.size(); f++) {
												for (int e = 0; e < E.size(); e++) {
													for (int d = 0; d < D.size(); d++) {
														for (int c = 0; c < C.size(); c++) {
															for (int b = 0; b < B.size(); b++) {
																for (int a = 0; a < A.size(); a++) {
																	Integer[] temp = { Integer.parseInt(A.get(a)),
																			Integer.parseInt(B.get(b)),
																			Integer.parseInt(C.get(c)),
																			Integer.parseInt(D.get(d)),
																			Integer.parseInt(E.get(e)),
																			Integer.parseInt(F.get(f)),
																			Integer.parseInt(G.get(g)),
																			Integer.parseInt(H.get(h)),
																			Integer.parseInt(I.get(i)),
																			Integer.parseInt(J.get(j)),
																			Integer.parseInt(K.get(k)),
																			Integer.parseInt(L.get(l)),
																			Integer.parseInt(M.get(m)),
																			Integer.parseInt(N.get(n))

																	};
																	res.add(temp);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

		}
		if (No_of_Arrays == 13) {

			for (int m = 0; m < M.size(); m++) {
				for (int l = 0; l < L.size(); l++) {
					for (int k = 0; k < K.size(); k++) {
						for (int j = 0; j < J.size(); j++) {
							for (int i = 0; i < I.size(); i++) {
								for (int h = 0; h < H.size(); h++) {
									for (int g = 0; g < G.size(); g++) {
										for (int f = 0; f < F.size(); f++) {
											for (int e = 0; e < E.size(); e++) {
												for (int d = 0; d < D.size(); d++) {
													for (int c = 0; c < C.size(); c++) {
														for (int b = 0; b < B.size(); b++) {
															for (int a = 0; a < A.size(); a++) {
																Integer[] temp = { Integer.parseInt(A.get(a)),
																		Integer.parseInt(B.get(b)),
																		Integer.parseInt(C.get(c)),
																		Integer.parseInt(D.get(d)),
																		Integer.parseInt(E.get(e)),
																		Integer.parseInt(F.get(f)),
																		Integer.parseInt(G.get(g)),
																		Integer.parseInt(H.get(h)),
																		Integer.parseInt(I.get(i)),
																		Integer.parseInt(J.get(j)),
																		Integer.parseInt(K.get(k)),
																		Integer.parseInt(L.get(l)),
																		Integer.parseInt(M.get(m))

																};
																res.add(temp);
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		if (No_of_Arrays == 12) {

			for (int l = 0; l < L.size(); l++) {
				for (int k = 0; k < K.size(); k++) {
					for (int j = 0; j < J.size(); j++) {
						for (int i = 0; i < I.size(); i++) {
							for (int h = 0; h < H.size(); h++) {
								for (int g = 0; g < G.size(); g++) {
									for (int f = 0; f < F.size(); f++) {
										for (int e = 0; e < E.size(); e++) {
											for (int d = 0; d < D.size(); d++) {
												for (int c = 0; c < C.size(); c++) {
													for (int b = 0; b < B.size(); b++) {
														for (int a = 0; a < A.size(); a++) {
															Integer[] temp = { Integer.parseInt(A.get(a)),
																	Integer.parseInt(B.get(b)),
																	Integer.parseInt(C.get(c)),
																	Integer.parseInt(D.get(d)),
																	Integer.parseInt(E.get(e)),
																	Integer.parseInt(F.get(f)),
																	Integer.parseInt(G.get(g)),
																	Integer.parseInt(H.get(h)),
																	Integer.parseInt(I.get(i)),
																	Integer.parseInt(J.get(j)),
																	Integer.parseInt(K.get(k)),
																	Integer.parseInt(L.get(l))

															};
															res.add(temp);

														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		if (No_of_Arrays == 11) {

			for (int k = 0; k < K.size(); k++) {
				for (int j = 0; j < J.size(); j++) {
					for (int i = 0; i < I.size(); i++) {
						for (int h = 0; h < H.size(); h++) {
							for (int g = 0; g < G.size(); g++) {
								for (int f = 0; f < F.size(); f++) {
									for (int e = 0; e < E.size(); e++) {
										for (int d = 0; d < D.size(); d++) {
											for (int c = 0; c < C.size(); c++) {
												for (int b = 0; b < B.size(); b++) {
													for (int a = 0; a < A.size(); a++) {
														Integer[] temp = { Integer.parseInt(A.get(a)),
																Integer.parseInt(B.get(b)), Integer.parseInt(C.get(c)),
																Integer.parseInt(D.get(d)), Integer.parseInt(E.get(e)),
																Integer.parseInt(F.get(f)), Integer.parseInt(G.get(g)),
																Integer.parseInt(H.get(h)), Integer.parseInt(I.get(i)),
																Integer.parseInt(J.get(j)), Integer.parseInt(K.get(k))

														};
														res.add(temp);

													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		if (No_of_Arrays == 10) {

			for (int j = 0; j < J.size(); j++) {
				for (int i = 0; i < I.size(); i++) {
					for (int h = 0; h < H.size(); h++) {
						for (int g = 0; g < G.size(); g++) {
							for (int f = 0; f < F.size(); f++) {
								for (int e = 0; e < E.size(); e++) {
									for (int d = 0; d < D.size(); d++) {
										for (int c = 0; c < C.size(); c++) {
											for (int b = 0; b < B.size(); b++) {
												for (int a = 0; a < A.size(); a++) {
													Integer[] temp = { Integer.parseInt(A.get(a)),
															Integer.parseInt(B.get(b)), Integer.parseInt(C.get(c)),
															Integer.parseInt(D.get(d)), Integer.parseInt(E.get(e)),
															Integer.parseInt(F.get(f)), Integer.parseInt(G.get(g)),
															Integer.parseInt(H.get(h)), Integer.parseInt(I.get(i)),
															Integer.parseInt(J.get(j))

													};
													res.add(temp);

												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		if (No_of_Arrays == 9) {

			for (int i = 0; i < I.size(); i++) {
				for (int h = 0; h < H.size(); h++) {
					for (int g = 0; g < G.size(); g++) {
						for (int f = 0; f < F.size(); f++) {
							for (int e = 0; e < E.size(); e++) {
								for (int d = 0; d < D.size(); d++) {
									for (int c = 0; c < C.size(); c++) {
										for (int b = 0; b < B.size(); b++) {
											for (int a = 0; a < A.size(); a++) {
												Integer[] temp = { Integer.parseInt(A.get(a)),
														Integer.parseInt(B.get(b)), Integer.parseInt(C.get(c)),
														Integer.parseInt(D.get(d)), Integer.parseInt(E.get(e)),
														Integer.parseInt(F.get(f)), Integer.parseInt(G.get(g)),
														Integer.parseInt(H.get(h)), Integer.parseInt(I.get(i))

												};
												res.add(temp);

											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		if (No_of_Arrays == 8) {

			for (int h = 0; h < H.size(); h++) {
				for (int g = 0; g < G.size(); g++) {
					for (int f = 0; f < F.size(); f++) {
						for (int e = 0; e < E.size(); e++) {
							for (int d = 0; d < D.size(); d++) {
								for (int c = 0; c < C.size(); c++) {
									for (int b = 0; b < B.size(); b++) {
										for (int a = 0; a < A.size(); a++) {
											Integer[] temp = { Integer.parseInt(A.get(a)), Integer.parseInt(B.get(b)),
													Integer.parseInt(C.get(c)), Integer.parseInt(D.get(d)),
													Integer.parseInt(E.get(e)), Integer.parseInt(F.get(f)),
													Integer.parseInt(G.get(g)), Integer.parseInt(H.get(h))

											};
											res.add(temp);

										}
									}
								}
							}
						}
					}

				}
			}
		}
		if (No_of_Arrays == 7) {
			for (int h = 0; h < H.size(); h++) {
				for (int g = 0; g < G.size(); g++) {
					for (int f = 0; f < F.size(); f++) {
						for (int e = 0; e < E.size(); e++) {
							for (int d = 0; d < D.size(); d++) {
								for (int c = 0; c < C.size(); c++) {
									for (int b = 0; b < B.size(); b++) {
										for (int a = 0; a < A.size(); a++) {
											Integer[] temp = { Integer.parseInt(A.get(a)), Integer.parseInt(B.get(b)),
													Integer.parseInt(C.get(c)), Integer.parseInt(D.get(d)),
													Integer.parseInt(E.get(e)), Integer.parseInt(F.get(f)),
													Integer.parseInt(G.get(g))

											};
											res.add(temp);

										}
									}
								}
							}
						}
					}

				}
			}
		}
		if (No_of_Arrays == 6) {

			for (int f = 0; f < F.size(); f++) {
				for (int e = 0; e < E.size(); e++) {
					for (int d = 0; d < D.size(); d++) {
						for (int c = 0; c < C.size(); c++) {
							for (int b = 0; b < B.size(); b++) {
								for (int a = 0; a < A.size(); a++) {
									Integer[] temp = { Integer.parseInt(A.get(a)), Integer.parseInt(B.get(b)),
											Integer.parseInt(C.get(c)), Integer.parseInt(D.get(d)),
											Integer.parseInt(E.get(e)), Integer.parseInt(F.get(f)) };
									res.add(temp);

								}
							}
						}
					}
				}
			}

		}
		if (No_of_Arrays == 5) {

			for (int e = 0; e < E.size(); e++) {
				for (int d = 0; d < D.size(); d++) {
					for (int c = 0; c < C.size(); c++) {
						for (int b = 0; b < B.size(); b++) {
							for (int a = 0; a < A.size(); a++) {
								Integer[] temp = { Integer.parseInt(A.get(a)), Integer.parseInt(B.get(b)),
										Integer.parseInt(C.get(c)), Integer.parseInt(D.get(d)),
										Integer.parseInt(E.get(e)) };
								res.add(temp);

							}
						}
					}
				}
			}
		}

		if (No_of_Arrays == 4) {

			for (int d = 0; d < D.size(); d++) {
				for (int c = 0; c < C.size(); c++) {
					for (int b = 0; b < B.size(); b++) {
						for (int a = 0; a < A.size(); a++) {
							Integer[] temp = { Integer.parseInt(A.get(a)), Integer.parseInt(B.get(b)),
									Integer.parseInt(C.get(c)), Integer.parseInt(D.get(d))

							};
							res.add(temp);

						}
					}
				}
			}
		}
		if (No_of_Arrays == 3) {

			for (int c = 0; c < C.size(); c++) {
				for (int b = 0; b < B.size(); b++) {
					for (int a = 0; a < A.size(); a++) {
						Integer[] temp = { Integer.parseInt(A.get(a)), Integer.parseInt(B.get(b)),
								Integer.parseInt(C.get(c)) };
						res.add(temp);
					}
				}
			}
		}
		if (No_of_Arrays == 2) {

			for (int b = 0; b < B.size(); b++) {
				for (int a = 0; a < A.size(); a++) {
					Integer[] temp = { Integer.parseInt(A.get(a)), Integer.parseInt(B.get(b)) };
					res.add(temp);
				}
			}

		}

		if (No_of_Arrays == 1) {

			for (int a = 0; a < A.size(); a++) {
				Integer[] temp = { Integer.parseInt(A.get(a)) };
				res.add(temp);
			}

		}
		for (int i = 0; i < res.size(); i++) {
			log.info("Index " + i + " Combo: " + res.get(i));

		}
		return res;
	}

	private ArrayList<String> GetValueList(String Id)
	// Method to get the values of the selected Parameters, extract
	// their Ids and convert them to string
	{
		ArrayList<String> resList = new ArrayList<String>();
		ArrayList<Integer> IdList = new ArrayList<Integer>();

		IdList.add(Integer.parseInt(Id));

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
		ArrayList<FvBean> FvBeanList = new ArrayList<FvBean>();
		FvBeanList = fvdao.getValues("ALL", null,null);
		HashMap<Integer,FvBean>ValueMap=new HashMap<Integer, FvBean>();
		ValueMap=CreateMap(FvBeanList);
		log.info("Value Map keyset "+ValueMap.keySet().toString());
		Integer Uid = 0;
		ArrayList<ArrayList<String>> ResultList = new ArrayList<ArrayList<String>>();
		
		log.info("FvBeanList size is "+FvBeanList.size());
		Iterator<Integer[]> comboIt = ComboIds.iterator();
		while (comboIt.hasNext()) {
			ArrayList<String> comboList = new ArrayList<String>();
			comboList.add(Uid.toString());
			Integer[] tempArr = comboIt.next();

			ArrayList<Integer> tempids = new ArrayList<Integer>();
			for (int i = 0; i < tempArr.length; i++) {
				tempids.add(tempArr[i]);
			}
			
			log.info("Temp Array of combo ids is " + tempids);
			ArrayList<FvBean>tempList=new ArrayList<FvBean>();
	
			for(int j=0;j<tempids.size();j++)
			{
				
				tempList.add(ValueMap.get(tempids.get(j)));
			}
			Iterator<FvBean> beanIt = tempList.iterator();
		
			while (beanIt.hasNext()) {
				
				comboList.add(beanIt.next().getValue());

			}
			log.info("Temp Array of combolist ids is " + comboList);
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
	
	private HashMap<Integer,FvBean> CreateMap(ArrayList<FvBean>AllValues)
	{
		HashMap<Integer,FvBean> resultMap=new HashMap<Integer, FvBean>();
		if(AllValues.size()>0)
		{
			Iterator<FvBean>it=AllValues.iterator();
			while(it.hasNext())
			{
				FvBean tempbean=new FvBean();
				tempbean=it.next();
				log.info("Putting bean no "+tempbean.getFeeValueId());
				resultMap.put(tempbean.getFeeValueId(), tempbean);
			}
		}
		return resultMap;
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
