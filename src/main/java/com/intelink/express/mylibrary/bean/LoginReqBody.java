package com.intelink.express.mylibrary.bean;

public class LoginReqBody {
	private String UserId; // 登录用户名
	private String Passwd; // 登录密码(16位小写MD5密码)
	private String SiteId; // 登录网点代码
	private String CompanyId; // 所属公司代码
	private String DeviceId; // 硬件序列号
	private String DataInfo; // 客户端数据表时间戳 表名1:最大时间戳1|表名2:最大时间戳2

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getPasswd() {
		return Passwd;
	}

	public void setPasswd(String passwd) {
		Passwd = passwd;
	}

	public String getSiteId() {
		return SiteId;
	}

	public void setSiteId(String siteId) {
		SiteId = siteId;
	}

	public String getCompanyId() {
		return CompanyId;
	}

	public void setCompanyId(String companyId) {
		CompanyId = companyId;
	}

	public String getDeviceId() {
		return DeviceId;
	}

	public void setDeviceId(String deviceId) {
		DeviceId = deviceId;
	}

	public String getDataInfo() {
		return DataInfo;
	}

	public void setDataInfo(String dataInfo) {
		DataInfo = dataInfo;
	}
}
