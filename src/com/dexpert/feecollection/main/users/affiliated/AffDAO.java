package com.dexpert.feecollection.main.users.affiliated;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.dexpert.feecollection.main.ConnectionClass;

public class AffDAO {

	// Declare Global Variables Here
	public static SessionFactory factory = ConnectionClass.getFactory();
	static Logger log = Logger.getLogger(AffDAO.class.getName());

	// End of Global Variables

	// ---------------------------------------------------

	// DAO Methods Here
	// saveOrUpdate()
	@SuppressWarnings("resource")
	public AffBean saveOrUpdate(AffBean saveData, String path) {
		// Declarations
		// Open session from session factory
		Session session = factory.openSession();
		try {

			// file input Stream is use to save file in to DataBase

			FileInputStream fileInputStream = null;

			// to create new file with actual name with extension
			File dstfile = new File(path, saveData.getFileUploadFileName());

			// to copy files at specified destination path
			FileUtils.copyFile(saveData.getFileUpload(), dstfile);

			// convert file into array of bytes
			byte[] bFile = new byte[(int) dstfile.length()];
			fileInputStream = new FileInputStream(dstfile);

			int fileSize = fileInputStream.read(bFile);

			// fileinputStream must be close
			fileInputStream.close();

			saveData.setFilesByteSize(bFile);
			saveData.setFileSize(fileSize);
			session.beginTransaction();
			session.saveOrUpdate(saveData);
			session.getTransaction().commit();
			return saveData;

		} catch (Exception e) {

			e.printStackTrace();
			return saveData;
		} finally {

			// close session
			session.close();

		}

	}

	public ArrayList<AffBean> getInstitutes(String filterKey, String filterVal, ArrayList<Integer> idList,
			Date fromDate, Date toDate) {
		// Declarations
		ArrayList<AffBean> instList = new ArrayList<AffBean>();
		// Open session from session factory
		Session session = factory.openSession();
		try {
			Criteria InstSearchCr = session.createCriteria(AffBean.class);
			if (filterKey.contentEquals("NONE")) {
				log.info("Showing All Affiliated Institutes");
			}
			Iterator<AffBean> instIter = InstSearchCr.list().iterator();
			while (instIter.hasNext()) {
				instList.add(instIter.next());
			}

			return instList;
		} finally {
			// close session
			session.close();
		}

	}

	// delete()
	// getList()

	public Integer getRowCount() {
		// Declarations

		// Open session from session factory
		Session session = factory.openSession();
		try {
			Criteria c = session.createCriteria(AffBean.class);
			c.addOrder(Order.desc("instId"));
			c.setMaxResults(1);
			AffBean temp = (AffBean) c.uniqueResult();
			return temp.getInstId() + 1;

		} finally {
			// close session
			session.close();
		}

	}

	public ArrayList<AffBean> getCollegesList() {

		Session session = factory.openSession();

		Criteria criteria = session.createCriteria(AffBean.class);

		ArrayList<AffBean> affBeansList = (ArrayList<AffBean>) criteria.list();
		session.close();
		return affBeansList;
	}

	// End of DAO Methods

	public AffBean viewInstDetail(Integer instId) {
		Session session = factory.openSession();

		Criteria criteria = session.createCriteria(AffBean.class);

		criteria.add(Restrictions.eq("instId", instId));
		AffBean affBean = (AffBean) criteria.list().iterator().next();
		session.close();
		return affBean;
	}

	public AffBean getOneCollegeRecord(Integer instId) {

		Session session = factory.openSession();

		AffBean affBean = (AffBean) session.get(AffBean.class, instId);
		session.close();
		return affBean;
	}

	public void updateCollege(AffBean newAffInstBean) {
		// TODO Auto-generated method stub

		Session session = factory.openSession();

		try {
			Transaction tx = session.beginTransaction();
			AffBean oldBean = (AffBean) session.get(AffBean.class, newAffInstBean.getInstId());

			oldBean.setPlace(newAffInstBean.getPlace());
			oldBean.setInstName(newAffInstBean.getInstName());
			oldBean.setContactPerson(newAffInstBean.getContactPerson());
			oldBean.setContactNumber(newAffInstBean.getContactNumber());
			oldBean.setMobileNum(newAffInstBean.getMobileNum());
			oldBean.setEmail(newAffInstBean.getEmail());
			oldBean.setInstAddress(newAffInstBean.getInstAddress());

			session.merge(oldBean);

			tx.commit();
		} finally {
			session.close();
		}

	}

	public void updateCollegeDoc(AffBean newAffInstBean, String path) {

		Session session = factory.openSession();
		try {
			AffBean oldBean = (AffBean) session.get(AffBean.class, newAffInstBean.getInstId());
			// file input Stream is use to save file in to DataBase

			FileInputStream fileInputStream = null;

			// to create new file with actual name with extension
			File dstfile = new File(path, newAffInstBean.getFileUploadFileName());

			// to copy files at specified destination path
			FileUtils.copyFile(newAffInstBean.getFileUpload(), dstfile);

			// convert file into array of bytes
			byte[] bFile = new byte[(int) dstfile.length()];
			fileInputStream = new FileInputStream(dstfile);

			int fileSize = fileInputStream.read(bFile);

			// fileinputStream must be close
			fileInputStream.close();

			newAffInstBean.setFilesByteSize(bFile);
			newAffInstBean.setFileSize(fileSize);
			session.beginTransaction();

			oldBean.setFilesByteSize(newAffInstBean.getFilesByteSize());
			oldBean.setFileSize(newAffInstBean.getFileSize());
			oldBean.setFileUploadFileName(newAffInstBean.getFileUploadFileName());

			session.saveOrUpdate(oldBean);
			session.getTransaction().commit();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			// close session
			session.close();

		}
	}

	// to check whether record is already exist or New
	public List<String> getCollegeNameList(String instName) {

		Session session = factory.openSession();

		Criteria criteria = session.createCriteria(AffBean.class);
		criteria.add(Restrictions.eq("instName", instName));
		criteria.setProjection(Projections.property("instName"));

		List<String> affList = criteria.list();

		return affList;
	}

	// ---------------------------------------------------

	// Keep Getter Setter Methods Here

	// End of Getter Setter Methods

}
