package com.whsxyelf.social.service.impl;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.whsxyelf.social.service.FileUpLoadService;
import com.whsxyelf.social.util.FileUploadUtil;

@Service
public class FileUpLoadServiceImpl implements FileUpLoadService {
	String ACCESS_KEY = "uWWlMUZfz7gvT0ZZlyoQ2624A4uCPbpaDkvlzZxI";
	String SECRT_KEY = "mnNrHp1yjkH6YtgWl-W_uZ8NzXEhFkEXCeaDNRQt";
	String bucketName = "whsxyelf";
	
	Auth auth = Auth.create(ACCESS_KEY, SECRT_KEY);
	//地区
	Configuration cfg = new Configuration(Zone.zone2());
	UploadManager uploadManager = new UploadManager(cfg);
	
	private static String QINIU_IMAGE_DOMAIN = "http://pqxkane0w.bkt.clouddn.com/";

	//简单上传
	@Override
	public String getUpToken() {
		return auth.uploadToken(bucketName);
	}

	@Override
	public String saveImage(MultipartFile file) throws IOException {
		try {
			String originName = file.getOriginalFilename();
			int dot = originName.lastIndexOf(".");
			String extName = originName.substring(dot+1).toLowerCase();
			if(!FileUploadUtil.isImageAllowed(extName)) {
				return null;
			}
			
			String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + extName;
			
			Response res = uploadManager.put(file.getBytes(), fileName, getUpToken());
			
			if(res.isOK() && res.isJson()) {
				return QINIU_IMAGE_DOMAIN + JSONObject.parseObject(res.bodyString()).getString("key");
			} else {
				System.out.println("上传组件异常:" + res.bodyString());
				return null;
			}
		} catch (QiniuException e) {
			System.out.println("七牛云异常:" + e.getMessage());
			return null;
		}
	}

	@Override
	public String saveImageList(MultipartFile[] fileList) throws IOException {
		String fileName = UUID.randomUUID().toString().replaceAll("-", "");
		try {
			int count = 1;
			for(MultipartFile file : fileList) {
				String originName = file.getOriginalFilename();
				int dot = originName.lastIndexOf(".");
				String extName = originName.substring(dot+1).toLowerCase();
				if(!FileUploadUtil.isImageAllowed(extName)) {
					continue;
				}
				
				Response res = uploadManager.put(file.getBytes(),fileName+"_"+fileList.length+"_"+count+".jpg",getUpToken());
				count = count + 1;
				if(!res.isOK()) {
					System.out.println("上传组件异常:" + res.bodyString());
					return null;
				}
			}
		} catch (QiniuException e) {
			System.out.println("七牛云异常:" + e.getMessage());
			return null;
		}
		return QINIU_IMAGE_DOMAIN + fileName + "_" + fileList.length;
	}
	
	
}
