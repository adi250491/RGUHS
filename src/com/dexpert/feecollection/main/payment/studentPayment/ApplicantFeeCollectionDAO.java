package com.dexpert.feecollection.main.payment.studentPayment;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.dexpert.feecollection.main.ConnectionClass;
import com.dexpert.feecollection.main.fee.lookup.LookupDAO;

public class ApplicantFeeCollectionDAO {
	// Global Variables
	public static SessionFactory factory = ConnectionClass.getFactory();
	static Logger log = Logger.getLogger(LookupDAO.class.getName());

	public List<ApplicantFeeCollectionBean> calculateTotalFee(ApplicantFeeCollectionBean fc) {

		log.info("in DAO ::" + fc.getService_type());
		log.info("in DAO ::" + fc.getCourse());
		log.info("in DAO ::" + fc.getFaculty());
		log.info("in DAO ::" + fc.getNationality());

		Session session = factory.openSession();
		try {

			Criteria criteria = session.createCriteria(ApplicantFeeCollectionBean.class);
			criteria.add(Restrictions.like("service_type", "%" + fc.getService_type() + "%"))
					.add(Restrictions.eq("course", fc.getCourse())).add(Restrictions.eq("faculty", fc.getFaculty()))
					.add(Restrictions.eq("nationality", fc.getNationality()));

			List<ApplicantFeeCollectionBean> beanlist = criteria.list();
			log.info("List Size::" + beanlist.size());
			return beanlist;
		} finally {
			session.close();
			// TODO: handle exception
		}

	}
}
