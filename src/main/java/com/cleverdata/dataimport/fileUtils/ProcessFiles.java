package com.cleverdata.dataimport.fileUtils;

import com.cleverdata.dataimport.dto.CompanyEmplStructure;

import java.io.File;
import java.util.Collection;

public interface ProcessFiles {

	public CompanyEmplStructure TranslateData(Collection<File> files);
}
