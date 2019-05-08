package com.whsxyelf.social.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileUpLoadService {
	public String getUpToken();
	public String saveImage(MultipartFile file) throws IOException;
	public String saveImageList(MultipartFile[] fileList) throws IOException;
}
