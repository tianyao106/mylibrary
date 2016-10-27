package com.intelink.express.mylibrary.api;

import com.intelink.express.mylibrary.bean.LoginReqBody;
import com.intelink.express.mylibrary.bean.LoginRespBody;
import com.intelink.express.mylibrary.bean.Request;
import com.intelink.express.mylibrary.bean.Response;
import com.intelink.express.mylibrary.bean.Result;
import com.intelink.express.mylibrary.bean.ScanData;
import com.intelink.express.mylibrary.bean.ScanUploadBody;
import com.intelink.express.mylibrary.bean.User;
import com.intelink.express.mylibrary.exception.AppException;
import com.intelink.express.mylibrary.http.Http;
import com.intelink.express.mylibrary.utils.BsonUtils;

import java.util.List;

public class ApiClient {
	public static final String BASE = "http://intelink.onecod.com:8800/";
	public static final String APIURL = BASE + "api.ashx?p=B";

	/**
	 * 登录
	 * 
	 * @return
	 */
	public static Result login(final String Token, final LoginReqBody reqBody) {
		Result result = new Result();
		final Request request = new Request();
		request.setCommand(Command.LOGIN_COMMAND);
		request.setBodyType("LoginEntity");
		request.setEncryptType(Constant.ENCRYP_TTYPE);
		request.setToken(Token);

		Response response = null;
		try {
			request.setBody(BsonUtils.encode(reqBody));
			byte[] datas = Http.post(APIURL, request);
			response = BsonUtils.decode(datas, Response.class);
		} catch (AppException e) {
			result.setMessage(e.getMessage());
			return result;
		}

		if (response == null) {
			result.setMessage("Command=" + request.getCommand() + ",无法解析响应");
			return result;
		}
		if (response.isSuccess()) {
			LoginRespBody respBody = null;
			try {
				respBody = BsonUtils.decode(response.getBody(), LoginRespBody.class, response.getEncryptType());
				if (respBody != null) {
					result.setSuccess(true);
					result.setObject(respBody);
				} else {
					result.setMessage("无法解析用户信息");
				}
			} catch (AppException e) {
				e.printStackTrace();
				result.setMessage("无法解析用户信息");
			}
		} else {
			result.setMessage(response.getMessage());
		}
		return result;
	}

	/**
	 * 解析登录响应
	 * @param respBody
	 * @return
	 */
	public static User parseLoginRespBody(LoginRespBody respBody) {
		User user = new User();
		user.setSiteId(respBody.getSiteId());
		user.setSiteName(respBody.getSiteName());
		user.setToken(respBody.getToken());
		user.setUserId(respBody.getUserId());
		user.setUserName(respBody.getUserName());
		user.setUserType(respBody.getUserType());
		user.setCompanyName(respBody.getCompanyName());
		user.setDataInfo(respBody.getDataInfo());
		return user;
	}

	/**
	 * 上传扫描数据
	 * 
	 * @param scanDatas
	 * @return
	 */
	public static Result uploadScanData(String Token, List<ScanData> scanDatas) {
		Result result = new Result();
		final Request request = new Request();
		request.setCommand(Command.SCAN_COMMAND);
		request.setBodyType("ScanDataEntity[]");
		request.setEncryptType(Constant.ENCRYP_TTYPE);
		request.setToken(Token);

		ScanData[] reqBody = new ScanData[scanDatas.size()];
		scanDatas.toArray(reqBody);

		Response response = null;
		try {
			request.setBody(BsonUtils.encode(reqBody));
			byte[] datas = Http.post(APIURL, request);
			response = BsonUtils.decode(datas, Response.class);
		} catch (AppException e) {
			result.setMessage(e.getMessage());
			return result;
		}

		if (response == null) {
			result.setMessage("Command=" + request.getCommand() + ",无法解析响应");
			return result;
		}
		if (response.isSuccess()) {
			ScanUploadBody uploadBody = null;
			try {
				uploadBody = BsonUtils.decode(response.getBody(), ScanUploadBody.class, response.getEncryptType());
				if (uploadBody != null) {
					result.setSuccess(true);
					result.setObject(uploadBody);
				}
			} catch (AppException e) {
				e.printStackTrace();
			}
			if (uploadBody == null) {
				result.setMessage("Command=" + request.getCommand() + ",无法解析响应Body");
			}
		} else {
			result.setMessage(response.getMessage());
		}
		return result;
	}

	/*public static Result getTraceStatus(final String Token, final String CompanyId, final String WayBillNo) {
		Result result = new Result();
		final Request request = new Request();
		request.setCommand(Command.TRACE_COMMAND);
		request.setBodyType("TraceEntity");
		request.setEncryptType(Constant.ENCRYP_TTYPE);
		request.setToken(Token);

		TraceReqBody reqBody = new TraceReqBody();
		reqBody.setCompanyId(CompanyId);
		reqBody.setWayBillNo(WayBillNo);
		Response response = null;
		try {
			request.setBody(BsonUtils.encode(reqBody));
			byte[] datas = Http.post(APIURL, request);
			response = BsonUtils.decode(datas, Response.class);
		} catch (AppException e) {
			result.setMessage(e.getMessage());
			return result;
		}

		if (response == null) {
			result.setMessage("Command=" + request.getCommand() + ",无法解析响应");
			return result;
		}
		if (response.isSuccess()) {
			List<TraceRespBody> respBody = null;
			try {
				respBody = BsonUtils.decode(response.getBody(), new TypeReference<List<TraceRespBody>>() {
				}, response.getEncryptType());
				if (respBody != null) {
					result.setSuccess(true);
					result.setObject(respBody);
				} else {
					result.setMessage("无法解析追踪结果");
				}
			} catch (AppException e) {
				e.printStackTrace();
				result.setMessage("无法解析追踪结果");
			}
		} else {
			result.setMessage(response.getMessage());
		}
		return result;
	}*/

	/**
	 * 检查更新
	 * @param context
	 * @param Token
	 * @param CompanyId
	 * @return

	public static Result checkVersion(AppContext context, String Token, String CompanyId) {
		Result result = new Result();
		final Request request = new Request();
		request.setCommand(Command.CHECKVERSION_COMMAND);
		request.setBodyType("VersionEntity");
		request.setEncryptType(Constant.ENCRYP_TTYPE);
		request.setToken(Token);
		VersionEntity versionEntity = new VersionEntity();
		versionEntity.setAppVerCode(context.getCurrentVersionCode());
		versionEntity.setAppVerName(context.getCurrentVersionName());
		versionEntity.setCompanyId(CompanyId);
		versionEntity.setOSType(Constant.OS_TYPE);
		versionEntity.setOSVer(android.os.Build.MODEL+"/"+android.os.Build.VERSION.RELEASE);
		try {
			Response response = null;
			request.setBody(BsonUtils.encode(versionEntity));
			byte[] datas = Http.post(APIURL, request);
			response = BsonUtils.decode(datas, Response.class);
			if (response == null) {
				result.setMessage("Command=" + request.getCommand() + ",无法解析响应");
				return result;
			}
			if (response.isSuccess()) {
				VersionInfo info = BsonUtils.decode(response.getBody(), VersionInfo.class, response.getEncryptType());
				if (info != null) {
					result.setSuccess(true);
					result.setObject(info);
				} else {
					result.setMessage("无法解析版本信息");
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("无法获取到版本信息");
		}
		return result;
	}*/
	

	
	/**
	 * 更新站点资料
	 * @param token
	 * @param companyId
	 * @param lastModifyStamp
	 * @return

	public static Result UpdateSite(String token, String companyId, long lastModifyStamp) {
		Result result = new Result();
		final Request request = new Request();
		request.setCommand(Command.UPDATE_SITE_COMMAND);
		request.setBodyType("BaseDataReqEntity");
		request.setEncryptType(Constant.ENCRYP_TTYPE);
		request.setToken(token);	
		BaseDataReqEntity dataReqEntity = new BaseDataReqEntity();
		dataReqEntity.setCompanyId(companyId);
		dataReqEntity.setLastModifyStamp(lastModifyStamp);
		try {
			Response response = null;
			request.setBody(BsonUtils.encode(dataReqEntity));
			byte[] datas = Http.post(APIURL, request);
			response = BsonUtils.decode(datas, Response.class);
			if (response == null) {
				result.setMessage("Command=" + request.getCommand() + ",无法解析响应");
				return result;
			}
			if (response.isSuccess()) {
				List<SiteEntity> siteList = BsonUtils.decode(response.getBody(), new TypeReference<List<SiteEntity>>() {
				}, response.getEncryptType());				
				if (siteList != null) {
					result.setSuccess(true);
					result.setObject(siteList);
				} else {
					result.setMessage("无法解析站点资料");
				}				
			} else {
				result.setMessage(response.getErrCode() +":" + response.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("无法获取到站点资料");
		}		
		return result;
	}*/
	
	/**
	 * 更新问题件
	 * @param token
	 * @param companyId
	 * @param lastModifyStamp
	 * @return

	public static Result updateProblem(String token, String companyId, long lastModifyStamp) {
		Result result = new Result();
		final Request request = new Request();
		request.setCommand(Command.UPDATE_PROBLEM_COMMAND);
		request.setBodyType("BaseDataReqEntity");
		request.setEncryptType(Constant.ENCRYP_TTYPE);
		request.setToken(token);	
		BaseDataReqEntity dataReqEntity = new BaseDataReqEntity();
		dataReqEntity.setCompanyId(companyId);
		dataReqEntity.setLastModifyStamp(lastModifyStamp);
		try {
			Response response = null;
			request.setBody(BsonUtils.encode(dataReqEntity));
			byte[] datas = Http.post(APIURL, request);
			response = BsonUtils.decode(datas, Response.class);
			if (response == null) {
				result.setMessage("Command=" + request.getCommand() + ",无法解析响应");
				return result;
			}
			if (response.isSuccess()) {
				List<ProblemEntity> problemList = BsonUtils.decode(response.getBody(), new TypeReference<List<ProblemEntity>>() {
				}, response.getEncryptType());
				if (problemList != null) {
					result.setSuccess(true);
					result.setObject(problemList);
				} else {
					result.setMessage("无法解析问题状态");
				}				
			} else {
				result.setMessage(response.getErrCode() +":" + response.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("无法解析问题状态");
		}		
		return result;		
	}*/
	
	/**
	 * 更新外务员资料
	 * @param token
	 * @param companyId
	 * @param lastModifyStamp
	 * @return

	public static Result UpdateCourier(String siteid,String token, String companyId, long lastModifyStamp) {
		Result result = new Result();
		final Request request = new Request();
		request.setCommand(Command.UPDATE_COURIER);
		request.setBodyType("UpdateBySiteReqEntity");
		request.setEncryptType(Constant.ENCRYP_TTYPE);
		request.setToken(token);
		BaseDataReqEntity dataReqEntity = new BaseDataReqEntity();
		dataReqEntity.setCompanyId(companyId);
		dataReqEntity.setLastModifyStamp(lastModifyStamp);
		dataReqEntity.setSiteid(siteid);
		try {
			Response response = null;
			request.setBody(BsonUtils.encode(dataReqEntity));
			byte[] datas = Http.post(APIURL, request);
			response = BsonUtils.decode(datas, Response.class);
			if (response == null) {
				result.setMessage("Command=" + request.getCommand() + ",无法解析响应");
				return result;
			}
			if (response.isSuccess()) {
				List<CourierEntity> siteList = BsonUtils.decode(response.getBody(), new TypeReference<List<CourierEntity>>() {
				}, response.getEncryptType());				
				if (siteList != null) {
					result.setSuccess(true);
					result.setObject(siteList);
				} else {
					result.setMessage("无法解析站点资料");
				}				
			} else {
				result.setMessage(response.getErrCode() +":" + response.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("无法获取到站点资料");
		}		
		return result;
	}*/
}
