package com.dexpert.feecollection.main.fee.config;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.dexpert.feecollection.main.ConnectionClass;
import com.dexpert.feecollection.main.fee.lookup.LookupDAO;

public class FcDAO {
	// Global Declarations
	public static SessionFactory factory = ConnectionClass.getFactory();
	static Logger log = Logger.getLogger(LookupDAO.class.getName());

	// Global Declarations End
	// DAO Methods
	public void insertFeeBulk(ArrayList<FcBean> savelist) {
		// Declarations

		// Open session from session factory
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			for (int i = 0; i < savelist.size(); i++) {
				FcBean savebean = new FcBean();
				savebean=savelist.get(i);
				session.save(savebean);
				if (i % 20 == 0) { // 20, same as the JDBC batch size
					// flush a batch of inserts and release memory:
					session.flush();
					session.clear();
				}
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// close session
			session.close();
		}
	}

	// DAO Methods End
}
