package com.cleverdata.dataimport.dto;

import com.cleverdata.dataimport.domain.Company;
import com.cleverdata.dataimport.domain.Employee;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class CompanyEmplStructure {

	public CompanyEmplStructure(List<Company> companyList, List<Employee> employeeList) {
		this.companyList = companyList;
		this.employeeList = employeeList;
	}



	List<Company> companyList;
	List<Employee> employeeList;
}
