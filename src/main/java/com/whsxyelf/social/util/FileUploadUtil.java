package com.whsxyelf.social.util;

public class FileUploadUtil {
	//允许上传的图片格式
	public static String[] IMAGE_FILE_EXTD = new String[] {"png", "bmp","jpg","jpeg","pdf"};
	
	public static boolean isImageAllowed(String extName) {
		for(String ext : IMAGE_FILE_EXTD) {
			if(extName.equals(ext)) {
				return true;
			}
		}
		return false;
	}
}
