package com.dexpert.feecollection.main.users.applicant.template;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.dexpert.feecollection.main.fee.lookup.LookupBean;
import com.dexpert.feecollection.main.fee.lookup.LookupDAO;

public class ExcelTemplateDAO {

	public static void generateTemplate(XSSFSheet xssfSheet) {
		
		LookupDAO lpdao=new LookupDAO();
		ArrayList<LookupBean>applicantParam=lpdao.getLookupData("Scope", "Applicant", null, null);
		String[]paramStr=null;
		

		try {
			Row header = xssfSheet.createRow(0);
			
			for (int i = 0; i < applicantParam.size(); i++) {
				paramStr[i]=applicantParam.get(i).getLookupName();
			}
			
			
			
			
			
			for (int i = 0; i < paramStr.length; i++) {
				Cell monthCell = header.createCell(i);
				monthCell.setCellValue(paramStr[i]);
				xssfSheet.setColumnWidth(i, 6000);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
