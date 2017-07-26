package com.cleverdata.dataimport.fileUtils;

import com.cleverdata.dataimport.domain.Company;
import com.cleverdata.dataimport.domain.Employee;

import java.io.File;
import java.util.List;
import java.util.Set;

public interface ProcessOneFile {

	 public List<Company> readCompanies(File toRead);

	 public List<Employee> readEmployees(File toRead);


}
