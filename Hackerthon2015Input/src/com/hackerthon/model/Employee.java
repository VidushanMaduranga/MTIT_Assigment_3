package com.hackerthon.model;

public class Employee {

	public String idEmployee;
	public String nameFull;
	public String address;
	public String facultyName;
	public String department;
	public String designation;
	
	public String employeeIdGet() {
		return idEmployee;
	}
	public void employeeId(String employeeID) {
		idEmployee = employeeID;
	}
	public String fullNameGet() {
		return nameFull;
	}
	public void fullName(String fullName) {
		nameFull = fullName;
	}
	public String addressGet() {
		return address;
	}
	public void address(String address) {
		address = address;
	}
	public String facultyNameGet() {
		return facultyName;
	}
	public void facultyName(String facultyName) {
		facultyName = facultyName;
	}
	public String departmentGet() {
		return department;
	}
	public void department(String department) {
		department = department;
	}
	public String designationGet() {
		return designation;
	}
	public void designation(String designation) {
		designation = designation;
	}
	@Override
	public String toString() {
		
		return "Employee ID = " + idEmployee + "\n" + "FullName = " + nameFull + "\n" + "Address = " + address + "\n"
				+ "Faculty Name = " + facultyName + "\n" + "Department = " + department + "\n" + "Designation = "
				+ designation;
	}
}
