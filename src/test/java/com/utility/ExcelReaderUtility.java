package com.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojo.User;

public class ExcelReaderUtility {

	public static Iterator<User> readExcelFile(String fileName) {

		File xssfFile = new File(
				System.getProperty("user.dir") + "//testData//"+ fileName);
		XSSFWorkbook xssfWorkbook = null;
		List<User> userList = null;
		Row row;
		Cell emailAddressCell;
		Cell passwordCell;
		Iterator<Row> rowIterator;
		XSSFSheet xssfsheet;
		User user;
		try {
			xssfWorkbook = new XSSFWorkbook(xssfFile);
			xssfsheet = xssfWorkbook.getSheet("LoginTestData");
			rowIterator = xssfsheet.rowIterator();
			rowIterator.next();
			userList = new ArrayList<User>();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				emailAddressCell = row.getCell(0);
				passwordCell = row.getCell(1);
				user = new User(emailAddressCell.toString(),
						passwordCell.toString());
				userList.add(user);
				xssfWorkbook.close();
			}
		} catch (IOException | InvalidFormatException e) {
			e.printStackTrace();
		}

		return userList.iterator();

	}
	
	
}
