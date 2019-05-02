package com.whsxyelf.social.exception;

public class CommentException extends Exception {

	private String msg;
	
	public CommentException(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
