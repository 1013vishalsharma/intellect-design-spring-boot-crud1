package com.boot.model;

import org.springframework.http.HttpStatus;

public class UserResponse {

	public String resMsg;
	
	public String userId;
	
	public HttpStatus erroCode;

	public HttpStatus getErroCode() {
		return erroCode;
	}

	public void setErroCode(HttpStatus badRequest) {
		this.erroCode = badRequest;
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
