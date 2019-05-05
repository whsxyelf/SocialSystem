package com.whsxyelf.social.util;

import java.util.Random;

public class CheckCodeUtil {
	public static String createCheckCode() {
		Random random = new Random();
		return String.valueOf(100000 + random.nextInt(899999));
	}
}
