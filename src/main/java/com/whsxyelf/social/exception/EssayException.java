package com.whsxyelf.social.exception;

public class EssayException extends RuntimeException {

	private String msg;
	
	public EssayException(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
