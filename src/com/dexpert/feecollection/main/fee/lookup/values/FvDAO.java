package com.dexpert.feecollection.main.fee.lookup.values;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.dexpert.feecollection.main.ConnectionClass;

public class FvDAO {
	//Global Declarations
	public static SessionFactory factory = ConnectionClass.getFactory();
	static Logger log = Logger.getLogger(FvDAO.class.getName());	
	//End of Global Declarations
	
	//DAO Methods Start
	public FvBean saveParamValues(FvBean valuesData)
	{
		//Declarations

		//Open session from session factory
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(valuesData);
			session.getTransaction().commit();
			return valuesData;
		} finally {
			//close session
			session.close();
		}
	}
	//DAO Methods End
	
}
