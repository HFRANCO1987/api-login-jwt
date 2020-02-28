package br.com.techsti.util;


import java.io.Serializable;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author Henrique Santiago henriquesantiagofranco@gmail.com
 * @date 25/11/2018 - 01:20:20
 * 
 * Utilizado para padronizar as respostas da API
 *
 */
public class ResponseData implements Serializable {

	private static final long serialVersionUID = 4073401720356788332L;
	
	private Object data;
	private int status;
	private String message;
	private boolean success;
	private String token;

	public ResponseData(int status, String message) {
		super();
		this.status = status;
		this.message = message;
		this.success = HttpStatus.OK.value() == status || HttpStatus.CREATED.value() == status;
	}
	
	public ResponseData(int status, String message, String token) {
		super();
		this.status = status;
		this.message = message;
		this.success = HttpStatus.OK.value() == status || HttpStatus.CREATED.value() == status;
		this.token = token;
	}

	public ResponseData(Object data) {
		super();
		this.data = data;
		this.status = 202;
		this.success = true;
	}

	public ResponseData(Object data, String message) {
		super();
		this.data = data;
		this.status = 202;
		this.message = message;
		this.success = true;
	}

	public ResponseData(Object data, String message, HttpStatus http) {
		super();
		this.data = data;
		this.status = http.value();
		this.message = message;
		this.success = true;
	}
	
	public ResponseData(Object data, String message, int httpCode) {
		super();
		this.data = data;
		this.status = httpCode;
		this.message = message;
		this.success = true;
	}

	public ResponseData(Object data, HttpStatus http) {
		super();
		this.data = data;
		this.status = http.value();
		this.success = true;
	}
	
	public ResponseData(Object data, String message, HttpStatus http, String token) {
		super();
		this.data = data;
		this.status = http.value();
		this.message = message;
		this.success = true;
		this.token = token;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}