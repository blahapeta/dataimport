package com.cleverdata.dataimport.fileUtils;

import com.cleverdata.dataimport.domain.Company;
import com.cleverdata.dataimport.domain.Employee;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProcessXML implements ProcessOneFile {
	@Override public List<Company> readCompanies(File toRead) {
		return new ArrayList<Company>();
	}

	@Override public List<Employee> readEmployees(File toRead) {
		return new ArrayList<Employee>();
	}


}
