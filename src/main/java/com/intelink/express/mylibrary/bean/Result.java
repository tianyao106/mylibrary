package com.intelink.express.mylibrary.bean;

public class Result {
	private boolean Success = false; // 是否成功
	private String Message = "[未知原因]"; // 失败时的提示信息
	private Object object;

	public Result(boolean success, String message) {
		super();
		Success = success;
		Message = message;
	}
	
	public Result() {
	}

	public boolean isSuccess() {
		return Success;
	}

	public void setSuccess(boolean success) {
		Success = success;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
}
