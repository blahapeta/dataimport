package com.cleverdata.dataimport.fileUtils.factory;

import com.cleverdata.dataimport.fileUtils.ProcessCSV;
import com.cleverdata.dataimport.fileUtils.ProcessJSON;
import com.cleverdata.dataimport.fileUtils.ProcessOneFile;
import com.cleverdata.dataimport.fileUtils.ProcessXML;

public class FileUtilsFactory {

	private static final String XML = "xml";
	private static final String CSV = "csv";
	private static final String JSON = "json";
	public static ProcessOneFile getOneFileProcessor(String criteria)
	{
		if ( criteria.equals(XML) )
			return new ProcessXML();
		else if ( criteria.equals(JSON) )
			return new ProcessJSON();
		else if ( criteria.equals(CSV) )
			return new ProcessCSV();


		return null;
	}
}
