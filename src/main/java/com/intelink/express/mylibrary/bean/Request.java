package com.intelink.express.mylibrary.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value={"Token"})
public class Request {
	private String Token;

	private String Command; // 请求的命令(远程方法名称)，如登录为Login

	private String BodyType; // 请求的参数实体类型名称(不包含命名空间/包名的类名)

	private int EncryptType; // 请求的参数实体加密方式

	private byte[] Body; // 请求的参数实体序列化(或压缩过)后的字节数组
	
	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

	public String getCommand() {
		return Command;
	}

	public void setCommand(String command) {
		Command = command;
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
