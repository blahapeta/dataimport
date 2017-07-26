package com.cleverdata.dataimport.dto.builder;

import com.cleverdata.dataimport.domain.Company;
import com.cleverdata.dataimport.domain.Employee;
import com.cleverdata.dataimport.dto.CompanyEmplStructure;

import java.util.List;
import java.util.Set;

public class CompanyEmplStructureBuilder {
	private List<Company> companyList;
	private List<Employee> employeeList;

	public CompanyEmplStructureBuilder setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
		return this;
	}

	public CompanyEmplStructureBuilder setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
		return this;
	}

	public CompanyEmplStructure createCompanyEmplStructure() {
		return new CompanyEmplStructure(companyList, employeeList);
	}
}