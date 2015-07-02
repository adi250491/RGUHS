package com.dexpert.feecollection.main.users.affiliated;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.dexpert.feecollection.main.ConnectionClass;
import com.dexpert.feecollection.main.communication.email.EmailSessionBean;
import com.dexpert.feecollection.main.users.LoginBean;
import com.dexpert.feecollection.main.users.PasswordEncryption;
import com.dexpert.feecollection.main.users.RandomPasswordGenerator;

public class AffDAO {

	// Declare Global Variables Here
	public static SessionFactory factory = ConnectionClass.getFactory();
	static Logger log = Logger.getLogger(AffDAO.class.getName());
	static Boolean isExist = false;

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
			byte[] bFile = null;
			Integer fileSize = null;
			// file input Stream is use to save file in to DataBase

			try {
				FileInputStream fileInputStream = null;

				// to create new file with actual name with extension
				File dstfile = new File(path, saveData.getFileUploadFileName());

				// to copy files at specified destination path
				FileUtils.copyFile(saveData.getFileUpload(), dstfile);

				// convert file into array of bytes
				bFile = new byte[(int) dstfile.length()];
				fileInputStream = new FileInputStream(dstfile);

				fileSize = fileInputStream.read(bFile);

				// fileinputStream must be close
				fileInputStream.close();

			} catch (java.lang.NullPointerException e) {

			}

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

	// to read excel file

	@SuppressWarnings("resource")
	public AffBean importExcelFileToDatabase(String fileUploadFileName, File fileUpload, String path) throws Exception {

		String instName, email, ContactPerson, instAddress, place;
		Integer contactNum, mobileNum;

		AffBean affBean = new AffBean();

		FileInputStream fileInputStream = new FileInputStream(fileUpload);

		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);

		XSSFSheet hssfSheet = xssfWorkbook.getSheetAt(0);

		Iterator<Row> rowIterator = hssfSheet.iterator();

		while (rowIterator.hasNext()) {
			Row row = (Row) rowIterator.next();

			if (row.getRowNum() == 0) {
				continue;
			}

			Iterator<Cell> cellIterator = row.cellIterator();

			while (cellIterator.hasNext()) {
				Cell cell = (Cell) cellIterator.next();

				switch (cell.getCellType()) {

				case Cell.CELL_TYPE_NUMERIC:

					break;

				case Cell.CELL_TYPE_STRING:

					break;

				}
			}

			Cell r = row.getCell(0);
			instName = r.getStringCellValue();
			r = row.getCell(1);
			contactNum = (int) r.getNumericCellValue();
			r = row.getCell(2);
			mobileNum = (int) r.getNumericCellValue();
			r = row.getCell(3);
			email = r.getStringCellValue();
			r = row.getCell(4);
			ContactPerson = r.getStringCellValue();
			r = row.getCell(5);
			instAddress = r.getStringCellValue();
			r = row.getCell(6);
			place = r.getStringCellValue();

			affBean.setInstName(instName);
			affBean.setContactNumber(contactNum.toString());
			affBean.setMobileNum(mobileNum.toString());
			affBean.setEmail(email);
			affBean.setContactPerson(ContactPerson);
			affBean.setInstAddress(instAddress);
			affBean.setPlace(place);

			addBulkData(affBean);

		}
		return affBean;

	}

	// ---------------------------------------------------

	// to save record into Database
	private void addBulkData(AffBean affBean) throws InvalidKeyException, NoSuchAlgorithmException,
			InvalidKeySpecException, InvalidAlgorithmParameterException, UnsupportedEncodingException,
			IllegalBlockSizeException, BadPaddingException {
		HttpServletRequest request = ServletActionContext.getRequest();

		List<String> instNameList = getCollegeNameList(affBean.getInstName());
		log.info("List Size is ::" + instNameList.size());

		if (instNameList.isEmpty()) {
			isExist = false;
			log.info("College List is Empty...");

			String username;

			// generate credentials for admin login
			try {
				username = "Inst".concat(affBean.getInstName().replaceAll("\\s+", "").substring(0, 4)
						.concat(getRowCount().toString()));

			} catch (java.lang.NullPointerException e) {
				username = "Inst".concat(affBean.getInstName().replaceAll("\\s+", "").substring(0, 4).concat("1"));

			}

			String password = RandomPasswordGenerator.generatePswd(6, 8, 1, 2, 0);
			PasswordEncryption.encrypt(password);
			String encryptedPwd = PasswordEncryption.encStr;

			LoginBean creds = new LoginBean();
			creds.setPassword(encryptedPwd);
			creds.setUserName(username);

			String profile = "Admin";
			creds.setProfile(profile);
			affBean.setLoginBean(creds);
			creds.setProfile(profile);

			// for bidirectional relationship ,set parent record to child //
			// record
			creds.setAffBean(affBean);
			if (creds.getProfile().equals("Admin")) {

				// for bidirectional relationship ,set child record to Parent
				// record
				affBean.setLoginBean(creds);
			}
			// -----Code for sending email

			EmailSessionBean email = new EmailSessionBean();
			email.sendEmail(affBean.getEmail(), "Welcome To Fee Collection Portal!", username, password,
					affBean.getInstName());
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(affBean);
			tx.commit();
			session.close();

		} else {
			isExist = true;
			log.info("College List is Not Empty...");

		}

	}
}
// Keep Getter Setter Methods Here

// End of Getter Setter Methods

