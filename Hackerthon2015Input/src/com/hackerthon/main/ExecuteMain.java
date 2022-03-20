package com.hackerthon.main;

import com.hackerthon.common.UtilTransform;
import com.hackerthon.service.GetEmpService;

public class ExecuteMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GetEmpService employeeService = new GetEmpService();
		try {
			UtilTransform.requestRansform();
			employeeService.employeesFromXml();
			employeeService.employeeTableCreate();
			employeeService.emploteesAdd();
		//employeeService.employeeGetById("EMP10004");
			//employeeService.employeeDelete("EMP10001");
			employeeService.employeeDisplay();
		} catch (Exception e) {
		}

	}

}
