package com.whsxyelf.social.exception;

public class ConcernException extends Exception{

	private String msg;
	
	public ConcernException(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
