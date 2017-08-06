package com.cleverdata.dataimport.fileUtils;

import com.cleverdata.dataimport.config.ServiceConfig;
import com.cleverdata.dataimport.domain.Company;
import com.cleverdata.dataimport.domain.Employee;
import com.cleverdata.dataimport.dto.CompanyEmplStructure;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) @ContextConfiguration(classes = { ServiceConfig.class }) public class ProcessFilesTest {

	@Autowired
	ProcessFiles processFiles;


	@Test
	public void processCSV() throws IOException {
		File src = new File(ProcessFilesTest.class.getClassLoader().getResource("samples/test1").getFile());
		Collection<File>  file = FileUtils.listFiles(src,new String[] { "csv", "json", "xml" }, false);
		CompanyEmplStructure companyEmplStructure = processFiles.TranslateData(file);
		Assert.assertEquals(companyEmplStructure.getCompanyList().size(),1);
		Assert.assertEquals(companyEmplStructure.getEmployeeList().size(),1);
		Company company = companyEmplStructure.getCompanyList().get(0);
		Employee employee = companyEmplStructure.getEmployeeList().get(0);
		Assert.assertTrue(company.getAdresa().equals(" Adresa"));
		Assert.assertTrue(company.getIco().equals(123456789));

		Assert.assertTrue(employee.getCompanyname().equals("Moje firma"));
		Assert.assertTrue(employee.getEmail().equals(" email@zamestnanec.ls"));
	}



}
