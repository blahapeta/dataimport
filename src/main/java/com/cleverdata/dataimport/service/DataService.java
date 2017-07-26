package com.cleverdata.dataimport.service;

import com.cleverdata.dataimport.domain.Company;
import com.cleverdata.dataimport.domain.Employee;
import com.cleverdata.dataimport.dto.CompanyEmplStructure;
import com.cleverdata.dataimport.dto.builder.CompanyEmplStructureBuilder;
import com.cleverdata.dataimport.repository.CompanyRepository;
import com.cleverdata.dataimport.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class DataService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Transactional
	public void save(CompanyEmplStructure companyEmplStructure){


		companyRepository.save(companyEmplStructure.getCompanyList());
		employeeRepository.save(companyEmplStructure.getEmployeeList());

	}

	@Transactional(readOnly = true)
	public CompanyEmplStructure getAllData(){


		CompanyEmplStructure ces = new CompanyEmplStructureBuilder().setCompanyList(companyRepository.findAll()).setEmployeeList(employeeRepository.findAll()).createCompanyEmplStructure();
		return ces;
	}

	@Transactional(readOnly = true)
	public Long getStatistics(){

		Long sum = companyRepository.count() + employeeRepository.count();
		return sum;
	}

}
