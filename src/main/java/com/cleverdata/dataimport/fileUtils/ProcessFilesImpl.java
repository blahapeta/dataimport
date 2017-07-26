package com.cleverdata.dataimport.fileUtils;

import com.cleverdata.dataimport.domain.Company;
import com.cleverdata.dataimport.domain.Employee;
import com.cleverdata.dataimport.dto.CompanyEmplStructure;
import com.cleverdata.dataimport.dto.builder.CompanyEmplStructureBuilder;
import org.springframework.stereotype.Service;
import com.cleverdata.dataimport.fileUtils.factory.FileUtilsFactory;
import java.io.File;
import java.util.*;

@Service
public class ProcessFilesImpl implements ProcessFiles {



	@Override public CompanyEmplStructure TranslateData(Collection<File> files) {
		List<Company> companies = new ArrayList<>();
		List<Employee> employees = new ArrayList<>();


		for(File file:files){

			ProcessOneFile processOneFile = FileUtilsFactory.getOneFileProcessor(getFileExtension(file));
			if(processOneFile !=null){
				List<Company> companySet = processOneFile.readCompanies(file);
				List<Employee> employeeSet = processOneFile.readEmployees(file);

				companies.addAll(companySet);
				employees.addAll(employeeSet);
			}

		}

		return new CompanyEmplStructureBuilder().setCompanyList(companies).setEmployeeList(employees).createCompanyEmplStructure();
	}


	private  String getFileExtension(File file) {
		String fileName = file.getName();
		if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
		else return "";
	}
}
