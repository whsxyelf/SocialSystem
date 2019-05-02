package com.whsxyelf.social.exception;

public class CollectException extends Exception {
	private String msg;
	
	public CollectException(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
