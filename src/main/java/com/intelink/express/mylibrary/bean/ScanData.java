package com.intelink.express.mylibrary.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.intelink.express.mylibrary.api.CustomDateSerializer;

import java.util.Date;

public class ScanData implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private int Id; // 本地唯一ID
	private String CompanyId; // 所属公司代码 (最大长度5)
	private String WayBillNo; // 运单号码 (最大长度40)
	private String ScanType; // 扫描类型;签收时填OK (最大长度5)
	private String ScanUser; // 扫描操作员 (最大长度20)

	private String SiteId; // 扫描站点 (最大长度20)
	private Date ScanTime; // 扫描时间
	private String Courier; // 业务员 (最大长度20)
	private String PreNextSite; // 来源去向(上/下一站) (最大长度20)
	private int Weig; // 重量 * 100 取整

	private int Pcs; // 件数
	private String BatchNo; // 批次号/袋号/包号/主单号 (最大长度30)
	private String ScanCode; // 扫描代码，仅问题件时需要填，对应问题原因基本资料中的代码 (最大长度5)
	private String Remark; // 备注 (最大长度100个汉字)
	private String DeviceId; // 设备序列号 (最大长度30)

	private byte[] ImageData; // 照片内容 (尽量控制在100K内)
	
	@JsonIgnore
	private boolean uploaded = false;  //是否已上传

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getCompanyId() {
		return CompanyId;
	}

	public void setCompanyId(String companyId) {
		CompanyId = companyId;
	}

	public String getWayBillNo() {
		return WayBillNo;
	}

	public void setWayBillNo(String wayBillNo) {
		WayBillNo = wayBillNo;
	}

	public String getScanType() {
		return ScanType;
	}

	public void setScanType(String scanType) {
		ScanType = scanType;
	}

	public String getScanUser() {
		return ScanUser;
	}

	public void setScanUser(String scanUser) {
		ScanUser = scanUser;
	}

	public String getSiteId() {
		return SiteId;
	}

	public void setSiteId(String siteId) {
		SiteId = siteId;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getScanTime() {
		return ScanTime;
	}

	public void setScanTime(Date scanTime) {
		ScanTime = scanTime;
	}

	public String getCourier() {
		return Courier;
	}

	public void setCourier(String courier) {
		Courier = courier;
	}

	public String getPreNextSite() {
		return PreNextSite;
	}

	public void setPreNextSite(String preNextSite) {
		PreNextSite = preNextSite;
	}

	public int getWeig() {
		return Weig;
	}

	public void setWeig(int weig) {
		Weig = weig;
	}

	public int getPcs() {
		return Pcs;
	}

	public void setPcs(int pcs) {
		Pcs = pcs;
	}

	public String getBatchNo() {
		return BatchNo;
	}

	public void setBatchNo(String batchNo) {
		BatchNo = batchNo;
	}

	public String getScanCode() {
		return ScanCode;
	}

	public void setScanCode(String scanCode) {
		ScanCode = scanCode;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getDeviceId() {
		return DeviceId;
	}

	public void setDeviceId(String deviceId) {
		DeviceId = deviceId;
	}

	public byte[] getImageData() {
		return ImageData;
	}

	public void setImageData(byte[] imageData) {
		ImageData = imageData;
	}

	public boolean isUploaded() {
		return uploaded;
	}

	public void setUploaded(boolean uploaded) {
		this.uploaded = uploaded;
	}
}
