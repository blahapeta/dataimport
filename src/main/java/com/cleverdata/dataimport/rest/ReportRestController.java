package com.cleverdata.dataimport.rest;

import com.cleverdata.dataimport.dto.CompanyEmplStructure;
import com.cleverdata.dataimport.scheduler.ReadFilesFromDirectoryScheduler;
import com.cleverdata.dataimport.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

@RestController
@RequestMapping(path = "/rest/report")
public class ReportRestController {

	@Autowired
	DataService dataService;

	@Autowired
	ReadFilesFromDirectoryScheduler readFilesFromDirectoryScheduler;

//	@ResponseBody
//	@RequestMapping(path = "/data",method = RequestMethod.GET)
//	public CompanyEmplStructure getAllData(){
//		return dataService.getAllData();
//	}


	@ResponseBody
	@RequestMapping(path = "/soucetZaznamuVeVsechTabulkach",method = RequestMethod.GET)
	public Long getStatistics(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return dataService.getStatistics();
	}

	@ResponseBody
	@RequestMapping(path = "/dirInfo",method = RequestMethod.GET)
	public String getdirInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "Adresar pro nahrani csv je "+readFilesFromDirectoryScheduler.DIR;
	}
}
