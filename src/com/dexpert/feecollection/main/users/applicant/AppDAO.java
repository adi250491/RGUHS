package com.dexpert.feecollection.main.users.applicant;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.dexpert.feecollection.main.ConnectionClass;
import com.dexpert.feecollection.main.users.affiliated.AffBean;
import com.dexpert.feecollection.main.users.affiliated.AffDAO;

public class AppDAO {

	// Declare Global Variables Here
	public static SessionFactory factory = ConnectionClass.getFactory();
	static Logger log = Logger.getLogger(AppDAO.class.getName());
	AffDAO aff = new AffDAO();

	// End of Global Variables

	// ---------------------------------------------------

	// DAO Methods Here

	public AppBean saveOrUpdate(AppBean appBean) {
		// Declarations
		// Open session from session factory
		Session session = factory.openSession();
		AffBean affBean = new AffBean();

		// to get college record based on id to create relationship
		affBean = aff.viewInstDetail(appBean.getAplInstId());
		// one to one bidirectional

		appBean.setAffBean(affBean);

		affBean.setAppBean(appBean);

		try {
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(appBean);
			tx.commit();

		} finally {

			session.close();
		}
		return appBean;

	}

	public List<AppBean> getAllStudentList() {
		Session session = factory.openSession();
		try {
			Criteria criteria = session.createCriteria(AppBean.class);
			List<AppBean> list = criteria.list();
			return list;
		} finally {
			session.close();
		}

	}

	public AppBean viewApplicantDetail(Integer appId) {
		Session session = factory.openSession();
		try {

			Criteria criteria = session.createCriteria(AppBean.class);
			criteria.add(Restrictions.eq("aplId", appId));
			AppBean appBean = (AppBean) criteria.list().iterator().next();
			return appBean;

		} finally {
			session.close();
			// TODO: handle exception
		}

	}

	public List<String> existingEnrollNum(AppBean appBean) {
		Session session = factory.openSession();
		try {
			Criteria criteria = session.createCriteria(AppBean.class);
			criteria.setProjection(Projections.property("enrollmentNumber"));
			criteria.add(Restrictions.eq("enrollmentNumber", appBean.getEnrollmentNumber()));
			List<String> list = criteria.list();
			return list;

		} finally {
			session.close();
			// TODO: handle exception
		}

	}

}
