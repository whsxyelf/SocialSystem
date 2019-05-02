package com.whsxyelf.social.exception;

public class UserException extends RuntimeException {
	private String msg;
	
	public UserException(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
