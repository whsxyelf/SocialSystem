package com.whsxyelf.social.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.springframework.stereotype.Component;

@Component
public class Serializer {
	public static byte[] serializeKey(String key) {
		return key.getBytes();
	}
	
	public static byte[] serializeValue(Object object) {
		return serialize(object);
	}
	
	public static Object deserializeValue(byte[] bytes) {
		return deSerialize(bytes);
	}
	
	public static String deserializeKey(byte[] bytes) {
		return new String(bytes);
	}
	
	
	public static byte[] serialize(Object obj) {
		byte[] bytes = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			bytes = baos.toByteArray();
			baos.close();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}
 
	public static Object deSerialize(byte[] bytes) {
		Object obj = null;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			obj = ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
 
}
