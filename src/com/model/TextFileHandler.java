package com.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TextFileHandler {

	public String load(String filename) {
		String strFile = "";
		String strLine;
	    BufferedReader loadFile;
		try {
			checkExtension(filename);
			// TODO: Check for override
			loadFile = new BufferedReader(new FileReader(filename));
			
		    while((strLine = loadFile.readLine()) != null) {
		    	strFile += strLine;
		    	strFile += "\n";
		    }
		    
		    loadFile.close();
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    return strFile;
	}
	
	public void checkExtension(String strFilename) {
		if(!strFilename.contains(".txt")) {
			strFilename += ".txt";
		}
	}
	
	public void checkFile(String strFilename) {
		String strDir = System.getProperty("user.dir");
        File myFile = new File(strDir, strFilename + ".txt");
        if (!myFile.exists()) {

            try {
				myFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}	
        }   
	}
}
