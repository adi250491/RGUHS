package com.dexpert.feecollection.main.users.superadmin;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

import com.dexpert.feecollection.main.ConnectionClass;
import com.dexpert.feecollection.main.users.affiliated.AffDAO;
import com.dexpert.feecollection.main.users.parent.ParBean;

public class SaDAO {
	// Declare Global Variables Here
	public static SessionFactory factory = ConnectionClass.getFactory();
	static Logger log = Logger.getLogger(AffDAO.class.getName());

	// End of Global Variables
	public static Object getRowCount() {
		// Declarations

		// Open session from session factory
		Session session = factory.openSession();
		try {
			Criteria c = session.createCriteria(ParBean.class);
			c.addOrder(Order.desc("parInstId"));
			c.setMaxResults(1);
			ParBean temp = (ParBean) c.uniqueResult();

			return temp.getParInstId() + 1;

		} finally {
			// close session
			session.close();
		}
	}

	public static SaBean saveOrUpdate(SaBean superAdmin) {
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(superAdmin);
			session.getTransaction().commit();
			return superAdmin;

		} catch (Exception e) {

			e.printStackTrace();
			return superAdmin;
		} finally {

			// close session
			session.close();

		}
	}

}
