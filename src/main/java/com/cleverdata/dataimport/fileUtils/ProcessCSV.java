package com.cleverdata.dataimport.fileUtils;

import com.cleverdata.dataimport.domain.Company;
import com.cleverdata.dataimport.domain.Employee;
import com.cleverdata.dataimport.scheduler.ReadFilesFromDirectoryScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProcessCSV implements ProcessOneFile {
	private final Logger logger = LoggerFactory.getLogger(ProcessCSV.class);

	@Override public List<Company> readCompanies(File toRead) {
		List<Company> companySet = new ArrayList<>();

		String cvsSplitBy = ";";
		Boolean header = true;
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyy HH:mm");

		try {
			try (InputStream is = Files.newInputStream(toRead.toPath(), StandardOpenOption.READ)) {
				InputStreamReader reader = new InputStreamReader(is, "UTF-8");
				BufferedReader lineReader = new BufferedReader(reader);
				// Process all lines.
				String line;
				while ((line = lineReader.readLine()) != null) {
					// Line content content is in variable line.
					if(header){
						header = !header;
						continue;
					}

					String[] company = line.split(cvsSplitBy);
					Company comp = new Company();
					comp.setIco(Integer.parseInt(company[0]));
					comp.setName(company[1]);
					comp.setAdresa(company[2]);
					comp.setDatumPoslednihoUpdate(sdf.parse(company[6]));

					companySet.add(comp);
				}
			}






		}catch (Exception e){
			logger.error("Chyba pri ziskavani dat o firme",e);
		}
		return companySet;
	}

	@Override public List<Employee> readEmployees(File toRead) {
		List<Employee> employeeSet = new ArrayList<>();
		BufferedReader br = null;
		String cvsSplitBy = ";";
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyy HH:mm");
		Boolean header = true;

		try {
			try (InputStream is = Files.newInputStream(toRead.toPath(), StandardOpenOption.READ)) {
				InputStreamReader reader = new InputStreamReader(is, "UTF-8");
				BufferedReader lineReader = new BufferedReader(reader);
				// Process all lines.
				String line;
				while ((line = lineReader.readLine()) != null) {
					// Line content content is in variable line.
					if(header){
						header = !header;
						continue;
					}

					// use comma as separator
					String[] employee = line.split(cvsSplitBy);
					Employee emp = new Employee();
					emp.setEmail(employee[3]);
					emp.setName(employee[4]);
					emp.setSurname(employee[5]);
					emp.setDatumPoslednihoUpdate(sdf.parse(employee[6]));
					emp.setCompanyname(employee[1]);

					employeeSet.add(emp);
				}
			}


		}catch (Exception e){
			logger.error("Chyba pri ziskavani dat o Zamestnanci",e);
		}
		return employeeSet;
	}


}
