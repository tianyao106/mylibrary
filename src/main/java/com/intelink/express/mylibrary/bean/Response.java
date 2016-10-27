package com.intelink.express.mylibrary.bean;

public class Response {
	private boolean Success = false; // 是否成功
	private int ErrCode;
	private String Message; // 失败时的提示信息
	private String BodyType; // 返回的实体类型名称(不包含命名空间/包名)，如果不返回实体时为空字符串
	private int EncryptType; // 返回的实体加密方式,如果不需要返回实体的命令此值无效
	private byte[] Body; // 返回的实体序列化(或压缩过)后的字节数组，如果不需要返回实体的命令此值为空

	public boolean isSuccess() {
		return Success;
	}

	public void setSuccess(boolean success) {
		Success = success;
	}

	public int getErrCode() {
		return ErrCode;
	}

	public void setErrCode(int errCode) {
		ErrCode = errCode;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getBodyType() {
		return BodyType;
	}

	public void setBodyType(String bodyType) {
		BodyType = bodyType;
	}

	public int getEncryptType() {
		return EncryptType;
	}

	public void setEncryptType(int encryptType) {
		EncryptType = encryptType;
	}

	public byte[] getBody() {
		return Body;
	}

	public void setBody(byte[] body) {
		Body = body;
	}
}
