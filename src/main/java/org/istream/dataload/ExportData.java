package org.istream.dataload;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ExportData {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstm = null;
		FileInputStream input = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Connection con = (Connection)
			// DriverManager.getConnection("jdbc:mysql://localhost/ihr","root","");
			con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306", "root", "1qazZAQ!");
			con.setAutoCommit(false);

			input = new FileInputStream(
					"/Users/dsheth/work/RND/Istream/I-HR/src/main/java/org/istream/dataload/TestData.xls");
			POIFSFileSystem fs = new POIFSFileSystem(input);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			Row row;
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);
				int id = 0;
				if (null != row.getCell(0)) {
					id = (int) row.getCell(0).getNumericCellValue();
				}

				String name = "";
				if (null != row.getCell(1)) {
					name = row.getCell(1).getStringCellValue();
				}
				String lastname = "";
				if (null != row.getCell(2)) {
					lastname = row.getCell(2).getStringCellValue();
				}
				String gender = "";
				if (null != row.getCell(3)) {
					gender = row.getCell(3).getStringCellValue();
				}
				String estatus = "";
				if (null != row.getCell(4)) {
					estatus = row.getCell(4).getStringCellValue();
				}
				String res_adreess = "";
				if (null != row.getCell(5)) {
					res_adreess = row.getCell(5).getStringCellValue();
				}
				String imgr_status ="";
				if (null != row.getCell(6)) {
					imgr_status= row.getCell(6).getStringCellValue();
				}
				String wk_adress = "";
				if (null != row.getCell(7)) {
					wk_adress = row.getCell(7).getStringCellValue();
				}

				int salary=0;
				if (null != row.getCell(9)) {
					salary = (int) row.getCell(9).getNumericCellValue();
				}
				int lca_rate=0;
				if (null != row.getCell(10)) {
					lca_rate = (int) row.getCell(10).getNumericCellValue();
				}

				String job_title = "";
				if (null != row.getCell(11)) {
					job_title = row.getCell(11).getStringCellValue();
				}

				String job_amdent = "";
				if (null != row.getCell(12)) {
					job_amdent = row.getCell(12).getStringCellValue();
				}

				String client_name = "";
				if (null != row.getCell(13)) {
					client_name = row.getCell(13).getStringCellValue();
				}

				String vendor_name = "";
				if (null != row.getCell(14)) {
					vendor_name = row.getCell(14).getStringCellValue();
				}

				String wrk_site_LCA = "";
				if (null != row.getCell(15)) {
					wrk_site_LCA = row.getCell(15).getStringCellValue();
				}

				String project_name = "";
				if (null != row.getCell(16)) {
					project_name = row.getCell(16).getStringCellValue();
				}

				// Date sdate_emploument = row.getCell(8).getDateCellValue();

				System.out.println("job_title" + job_title);

				String sql = "INSERT INTO ihr.employee ('First_name','Last_Name') VALUES('"
						+ name + "','" + lastname + "')";

				StringBuffer sql1 = new StringBuffer();
				sql1.append(
						"INSERT INTO   IHR.EMPLOYEE  (employee_id, First_name, Last_name, Middle_name, Email, Mobile_phone, SSN, Birth_date, Base_salary_amt, Bonus_amt, Designation,Start_date) VALUES (")
						.append("'").append(id).append("',")
						.append("'").append(name).append("',")
						.append("'").append(lastname).append("',")
						.append("'").append("").append("',")
						.append("'").append("jigartrivedi@gmail.com").append("',")
						.append("'").append("9999999999").append("',")
						.append("'").append("****1111").append("',")
						.append("").append("STR_TO_DATE('1-01-1981', '%d-%m-%Y')").append(",")
						.append("'").append(salary).append("',")
						.append("'").append("10000").append("',")
						.append("'").append("Architect").append("',")
						.append("").append("STR_TO_DATE('1-01-2006', '%d-%m-%Y')").append("").append(")");

				// '1', 'Devarpi', 'Sheth', 'Y', 'devarpi@gmail.com',
				// '7035080119', '****1111', STR_TO_DATE('09-11-1981',
				// '%d-%m-%Y'), '100000', '1000',
				// 'Architect',STR_TO_DATE('1-01-2016', '%d-%m-%Y'));
				// ")
				
				
				
				System.out.println(sql1.toString());

				pstm = (PreparedStatement) con.prepareStatement(sql1.toString());
				pstm.execute();
				con.commit();
				System.out.println("Import rows " + i);
			}

			System.out.println("Success import excel to mysql table");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
		} catch (IOException ioe) {
			System.out.println(ioe);
		} finally {
			try {
				if (null != pstm) {
					pstm.close();
				}

				if (null != input) {
					input.close();
				}
				
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
