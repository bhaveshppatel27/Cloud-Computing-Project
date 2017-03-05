package com.neu.edu.wtp.utility;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {

	public String upImage(String imageName, MultipartFile file) {

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "images");

				// File dir = new File(path + File.separator + "images");
				if (!dir.exists()) {
					dir.mkdirs();
				}
				String timeStamp=Long.toString(new DateTime().getMillis());
				String newName = imageName +"_"+timeStamp;
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + newName);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				return newName;
			} catch (Exception e) {

				return "You failed to upload " + imageName + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + imageName + " because the file was empty.";
		}

	}
}
