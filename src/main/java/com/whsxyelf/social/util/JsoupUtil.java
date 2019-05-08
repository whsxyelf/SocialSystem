package com.whsxyelf.social.util;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupUtil {
	public static String get163News(String url) throws IOException {
		Connection conn = Jsoup.connect(url);
		Document doc = conn.get();
		
		String detail = doc.select(".post_text").toString();
		return detail;
	}
	
	public static String getSinaNews(String url) throws IOException {
		Connection conn = Jsoup.connect(url);
		Document doc = conn.get();
		
		String detail = doc.select(".article").toString();
		return detail;
	}
	
	public static String getPeopleNews(String url) throws IOException {
		Connection conn = Jsoup.connect(url);
		Document doc = conn.get();
		
		String detail = doc.select(".box_con").toString();
		return detail;
	}
	
	public static void main(String[] args) throws IOException {
		
		System.out.println(JsoupUtil.get163News("http://localhost:8080/social/newsDetail?newsId=122"));
	}
}
