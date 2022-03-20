package com.hackerthon.service;

import org.xml.sax.SAXException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.hackerthon.common.UtilTransform;
import java.sql.ResultSet;
import java.sql.Statement;
import com.hackerthon.common.UtilQ;
import com.hackerthon.model.Employee;
import java.util.ArrayList;
import java.util.Map;
import com.hackerthon.common.UtilC;

public class GetEmpService extends UtilC {

	private final ArrayList<Employee> emp = new ArrayList<Employee>();

	private static Connection conn;

	private static Statement s;

	private PreparedStatement ps;

	public GetEmpService() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"),
					properties.getProperty("password"));
		} catch (Exception e) {
		} 
	}

	public void employeesFromXml() {

		try {
			int s = UtilTransform.xmlPaths().size();
			for (int i = 0; i < s; i++) {
				Map<String, String> l = UtilTransform.xmlPaths().get(i);
				Employee EMPLOYEE = new Employee();
				EMPLOYEE.employeeId(l.get("XpathEmployeeIDKey"));
				EMPLOYEE.fullName(l.get("XpathEmployeeNameKey"));
				EMPLOYEE.address(l.get("XpathEmployeeAddressKey"));
				EMPLOYEE.facultyName(l.get("XpathFacultyNameKey"));
				EMPLOYEE.department(l.get("XpathDepartmentKey"));
				EMPLOYEE.designation(l.get("XpathDesignationKey"));
				emp.add(EMPLOYEE);
				System.out.println(EMPLOYEE.toString() + "\n");
			}
		} catch (Exception e) {
		}
	}

	
	/*Create new table 
	 * Drop Table
	 */
	public void employeeTableCreate() {
		try {
			s = conn.createStatement();
			s.executeUpdate(UtilQ.Q("dropTable"));
			s.executeUpdate(UtilQ.Q("createTable"));
		} catch (Exception e) {
		}
	}

	//Inserte employees to the table
	public void emploteesAdd() {
		try {
			ps = conn.prepareStatement(UtilQ.Q("insertEmployee"));
			conn.setAutoCommit(false);
			for(int i = 0; i < emp.size(); i++){
				Employee employee = emp.get(i);
				ps.setString(1, employee.employeeIdGet());
				ps.setString(2, employee.facultyNameGet());
				ps.setString(3, employee.addressGet());
				ps.setString(4, employee.facultyNameGet());
				ps.setString(5, employee.departmentGet());
				ps.setString(6, employee.designationGet());
				ps.addBatch();
			}
			ps.executeBatch();
			conn.commit();
		} catch (Exception e) {
		}
	}

	//Select employee using employee id in table
	public void employeeGetById(String eId) {

		Employee employee = new Employee();
		try {
			ps = conn.prepareStatement(UtilQ.Q("selectEmployee"));
			ps.setString(1, eId);
			ResultSet R = ps.executeQuery();
			while (R.next()) {
				employee.employeeId(R.getString(1));
				employee.fullName(R.getString(2));
				employee.address(R.getString(3));
				employee.facultyName(R.getString(4));
				employee.department(R.getString(5));
				employee.designation(R.getString(6));
			}
			ArrayList<Employee> l = new ArrayList<Employee>();
			l.add(employee);
			employeeOutPut(l);
		} catch (Exception ex) {
		}
	}

	//Delete employees
	public void employeeDelete(String eId) {

		try {
			ps = conn.prepareStatement(UtilQ.Q("deleteEmployee"));
			ps.setString(1, eId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//See All employees details
	public void employeeDisplay() {

		ArrayList<Employee> l = new ArrayList<Employee>();
		try {
			ps = conn.prepareStatement(UtilQ.Q("selectEmployees"));
			ResultSet r = ps.executeQuery();
			while (r.next()) {
				Employee employee = new Employee();
				employee.employeeId(r.getString(1));
				employee.fullName(r.getString(2));
				employee.address(r.getString(3));
				employee.facultyName(r.getString(4));
				employee.department(r.getString(5));
				employee.designation(r.getString(6));
				l.add(employee);
			}
		} catch (Exception e) {
		}
		employeeOutPut(l);
	}
	
	public void employeeOutPut(ArrayList<Employee> l){
		
		System.out.println("Employee ID" + "\t\t" + "Full Name" + "\t\t" + "Address" + "\t\t" + "Faculty Name" + "\t\t"
				+ "Department" + "\t\t" + "Designation" + "\n");
		System.out
				.println("================================================================================================================");
		for(int i = 0; i < l.size(); i++){
			Employee employee = l.get(i);
			System.out.println(employee.employeeIdGet() + "\t" + employee.facultyNameGet() + "\t\t"
					+ employee.addressGet() + "\t" + employee.facultyNameGet() + "\t" + employee.departmentGet() + "\t"
					+ employee.designationGet() + "\n");
			System.out
			.println("----------------------------------------------------------------------------------------------------------------");
		}
		
	}
}
