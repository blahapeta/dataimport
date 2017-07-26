package com.cleverdata.dataimport.scheduler;

import com.cleverdata.dataimport.dto.CompanyEmplStructure;
import com.cleverdata.dataimport.fileUtils.ProcessFiles;
import com.cleverdata.dataimport.service.DataService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;

@Component
public class ReadFilesFromDirectoryScheduler {

	private final Logger logger = LoggerFactory.getLogger(ReadFilesFromDirectoryScheduler.class);
	public final String DIR;


	@Autowired
	DataService saveLoadedDataService;

	@Autowired
	ProcessFiles processFiles;

	public ReadFilesFromDirectoryScheduler() {
		DIR = System.getProperty("java.io.tmpdir") + "Company";
		new File(DIR+"/done").mkdirs();
	}

	@Scheduled(fixedRate = 1000 * 3 * 10, initialDelay = 3000) public void readFilesFromDirectory() {
		try {
			//read all files from directory
			Collection<File> files = FileUtils.listFiles(FileUtils.getFile(DIR), new String[] { "csv", "json", "xml" }, false);
			//read content
			CompanyEmplStructure companyEmplStructure = processFiles.TranslateData(files);
			//write content
			saveLoadedDataService.save(companyEmplStructure);

			//move files
			for (File file : files) {
				//FileUtils.moveFileToDirectory(file, new File(DIR + "\\done"), true);
				Files.move(file.toPath(), Paths.get(DIR + "/done/"+file.getName()),StandardCopyOption.REPLACE_EXISTING);
			}

		} catch (Exception e) {
			//TODO:
			logger.error("Nastala chyba ve zpracovani", e);
		}
	}

}




