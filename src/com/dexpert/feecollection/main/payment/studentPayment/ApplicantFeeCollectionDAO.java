package com.dexpert.feecollection.main.payment.studentPayment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.dexpert.feecollection.main.ConnectionClass;
import com.dexpert.feecollection.main.fee.lookup.LookupDAO;

public class ApplicantFeeCollectionDAO {
	// Global Variables
	public static SessionFactory factory = ConnectionClass.getFactory();
	static Logger log = Logger.getLogger(LookupDAO.class.getName());

	public ApplicantFeeCollectionBean calculateTotalFee(ApplicantFeeCollectionBean fc) {

		log.info("in DAO ::" + fc.getService_type());
		log.info("in DAO ::" + fc.getCourse());
		log.info("in DAO ::" + fc.getFaculty());
		log.info("in DAO ::" + fc.getNationality());
		ApplicantFeeCollectionBean beanlist = new ApplicantFeeCollectionBean();
		Session session = factory.openSession();
		try {

			Criteria criteria = session.createCriteria(ApplicantFeeCollectionBean.class);
			criteria.add(Restrictions.eq("service_type", fc.getService_type()))
					.add(Restrictions.eq("course", fc.getCourse())).add(Restrictions.eq("faculty", fc.getFaculty()))
					.add(Restrictions.eq("nationality", fc.getNationality()));

			// List<ApplicantFeeCollectionBean> beanlist = criteria.list();

			try {
				beanlist = (ApplicantFeeCollectionBean) criteria.list().iterator().next();

			} catch (java.util.NoSuchElementException e) {
				log.info("Combination Not Available");
			}
			// log.info("List Size::" + beanlist.size());
			return beanlist;
		} finally {
			session.close();
			// TODO: handle exception
		}

	}

	public Map<String, String> serviceTypeList() {

		Map<String, String> map = new HashMap<String, String>();
		List<ServiceTypeBean> list = new ArrayList<ServiceTypeBean>();
		map.put("EC", "ELIGIBILITY CRITERIA");
		map.put("ECR", "ELIGIBILITY CERTIFICATE RENEWAL");
		map.put("CONAIE", "CHANGE OF NAME & INITIAL EXPANSION");
		map.put("MC", "MIGRATION CERTIFICATE");
		map.put("MT", "MIGRATION TRANSFER 1ST TO 2ND YEAR ");
		map.put("NOCIT", "NOC(for Internship Transfer)");
		map.put("CMCL", "CONSOLIDATED MARKS CARD (LAMINATED)");
		map.put("NCIMC", "NAME CORRECTION IN MARKS CARD");
		map.put("PDC", "PDC");
		map.put("RC", "RANK CERTIFICATE");
		map.put("MPC", "MEDAL/PRIZE CERTIFICATE");
		map.put("MPCD", "MEDAL/PRIZE sCERTIFICATE-Duplicate");
		map.put("DDC", "DUPLICATE DEGREE CERTIFICATE");
		map.put("PGETS", "PGET Superspeciality");
		map.put("PGETF", "PGET Fee");
		map.put("PGETA", "PGET Application");
		map.put("VO", "VERIFICATION ONLY");
		map.put("VACOC", "VERIFICATION AND CERTIFICATION OF COPY'S");
		map.put("CCOTSO", "CERTIFIED COPY OF THE SYLLABUS/ORINANCE");
		map.put("DMC", "DUPLICATE MARKS CARD");
		map.put("PPC", "PROVISIONAL PASS CERTIFICATE");
		map.put("DC", "DEGREE CERTIFICATE");
		map.put("CC", "CERTIFICATE COURSE");
		map.put("NCIDC", "NAME CORRECTION IN DEGREE CERTIFICATE");
		map.put("COTOTP", "CHANGE OF TITLE OF THESIS-PHD");
		map.put("RRP", "RE-REGISTRATION-PH.D");
		map.put("EOR", "EXTENSION OF REGISTRATION");
		map.put("PE", "PRE EXAMINATION");
		map.put("RF", "REGISTRATION FEE");
		map.put("FSSAFPE", "FINAL SYNOPSIS SUBMISSION AND FINAL PH.D. EXAMINATION");
		map.put("DF", "DISSERTATION FEE");
		map.put("DFR", "DISSERTATION FEE-RESUBMISSION");
		map.put("CVOTAOTDF", "CREDENTIAL VERIFICATION / OFFICIAL TRANSCRIPT/ ATTESTATION OF THE DOCUMENTS FEE");
		map.put("MOIEC", "MEDIUM OF INSTRUCTION IN ENGLISH CERTIFICATION");
		map.put("MLCPFR2500", "MALPRACTICE LAPSES COMMITTEE - PENALTY FEE-RS. 2500");
		map.put("MLCPFR5000", "MALPRACTICE LAPSES COMMITTEE - PENALTY FEE-RS. 5000");
		map.put("MLCPFR7500", "MALPRACTICE LAPSES COMMITTEE - PENALTY FEE-RS. 7500");
		map.put("MLCPFR10000", "MALPRACTICE LAPSES COMMITTEE - PENALTY FEE-RS. 10000");
		map.put("MLCPFR12500", "MALPRACTICE LAPSES COMMITTEE - PENALTY FEE-RS. 12500");
		map.put("MLCPFR17500", "MALPRACTICE LAPSES COMMITTEE - PENALTY FEE-RS. 17500");
		map.put("MLCPFR20000", "MALPRACTICE LAPSES COMMITTEE - PENALTY FEE-RS. 20000");
		map.put("MLCPFR25000", "MALPRACTICE LAPSES COMMITTEE - PENALTY FEE-RS. 25000");
		map.put("RTFS1", "RE-TOTALING FOR 1 SUBJECT");
		map.put("RTFS2", "RE-TOTALING FOR 2 SUBJECT");
		map.put("RTFS3", "RE-TOTALING FOR 3 SUBJECT");
		map.put("RTFS4", "RE-TOTALING FOR 4 SUBJECT");
		map.put("RTFS5", "RE-TOTALING FOR 5 SUBJECT");
		map.put("PMCFFR5000", "PROFESSIONAL MISCONDUCT COMMITTEE FINE FEES-RS. 5000");
		map.put("PMCFFR1000", "PROFESSIONAL MISCONDUCT COMMITTEE FINE FEES-RS. 10000");
		map.put("PMCFFR20000", "PROFESSIONAL MISCONDUCT COMMITTEE FINE FEES-RS. 20000");
		map.put("PMCFFR30000", "PROFESSIONAL MISCONDUCT COMMITTEE FINE FEES-RS. 30000");
		map.put("PMCFFR50000", "PROFESSIONAL MISCONDUCT COMMITTEE FINE FEES-RS. 50000");
		map.put("PMCFFR100000", "PROFESSIONAL MISCONDUCT COMMITTEE FINE FEES-RS. 100000");
		/*Session session = factory.openSession();
		Criteria criteria = session.createCriteria(ServiceTypeBean.class);
		list = criteria.list();
		Iterator<ServiceTypeBean> itr = list.iterator();
		while (itr.hasNext()) {
			ServiceTypeBean serviceTypeBean = (ServiceTypeBean) itr.next();

			map.put(serviceTypeBean.getServiceKey(), serviceTypeBean.getServiceValue());

		}*/

		/*
		 * for (Map.Entry<String, String> entry : map.entrySet()) { Session
		 * session = factory.openSession(); Transaction tx =
		 * session.beginTransaction();
		 * serviceTypeBean.setServiceKey(entry.getKey());
		 * serviceTypeBean.setServiceValue(entry.getValue());
		 * 
		 * System.out.println("Key is ::" + entry.getKey() + "/" + "value is" +
		 * entry.getValue()); session.save(serviceTypeBean); tx.commit();
		 * 
		 * }
		 */
		return map;

	}

	public Map<String, String> facultyList() {

		Map<String, String> map = new HashMap<String, String>();
		List<FacultyBean> list = new ArrayList<FacultyBean>();
		map.put("MED", "MEDICAL");
		map.put("DENT", "DENTAL");
		map.put("NursPHYSIOPHARM", "NURSING,PHYSIOTHERAPY,PHARMACY");
		map.put("AYAVRDHOMEOUNANYOGA", "AYUSH,AYURVEDA,HOMEOPATHY,UNANI,YOGA");
		map.put("PMEDIAOTHERS", "PARAMEDICAL AND Others");

		/*Session session = factory.openSession();
		Criteria criteria = session.createCriteria(FacultyBean.class);
		list = criteria.list();
		Iterator<FacultyBean> itr = list.iterator();
		while (itr.hasNext()) {
			FacultyBean serviceTypeBean = (FacultyBean) itr.next();

			map.put(serviceTypeBean.getFacultyKey(), serviceTypeBean.getFacultyValue());

		}*/
		return map;

	}

	public Map<String, String> nationalityList() {

		Map<String, String> map = new HashMap<String, String>();

		map.put("INDIAN", "INDIAN");
		map.put("NRISAARC", "NRI/SAARC");
		map.put("FOREIGN", "FOREIGN");

		/*Session session = factory.openSession();
		List<NationalityBean> list = new ArrayList<NationalityBean>();
		Criteria criteria = session.createCriteria(NationalityBean.class);
		list = criteria.list();
		Iterator<NationalityBean> itr = list.iterator();
		while (itr.hasNext()) {
			NationalityBean serviceTypeBean = (NationalityBean) itr.next();

			map.put(serviceTypeBean.getNationalityKey(), serviceTypeBean.getNationalityValue());

		}*/
		return map;

	}

	public Map<String, String> courseList() {

		Session session = factory.openSession();
		CourseBean facultyBean = new CourseBean();
		Map<String, String> map = new HashMap<String, String>();

		map.put("PG", "PG");
		map.put("UG", "UG");
		map.put("PGDIPLOMA", "PG DIPLOMA");
		map.put("PHD", "PHD");

		/*List<CourseBean> list = new ArrayList<CourseBean>();
		Criteria criteria = session.createCriteria(CourseBean.class);
		list = criteria.list();
		Iterator<CourseBean> itr = list.iterator();
		while (itr.hasNext()) {
			CourseBean serviceTypeBean = (CourseBean) itr.next();

			map.put(serviceTypeBean.getCourseKey(), serviceTypeBean.getCourseValue());

		}*/

		return map;

	}

}
