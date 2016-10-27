package com.intelink.express.mylibrary.http;

import android.util.Log;


import com.intelink.express.mylibrary.bean.Request;
import com.intelink.express.mylibrary.exception.AppException;
import com.intelink.express.mylibrary.utils.BsonUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Http {

	private static final OkHttpClient mOkHttpClient = new OkHttpClient();

	public static byte[] post(String url, Request request) throws AppException {
		Map<String, String> headers = new HashMap<String, String>();
		if(request.getToken()!=null && !"".equals(request.getToken())){
			headers.put("Cookie", "Token=" + request.getToken());
		}
		return post(url, "d", BsonUtils.encode(request), headers);
	}
	
	public static byte[] InputStreamToByte(InputStream is) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		int ch;
		while ((ch = is.read()) != -1) {
			outputStream.write(ch);
		}
		byte datas[] = outputStream.toByteArray();
		outputStream.close();
		return datas;
	}

	public static byte[] post(String url, String name, byte[] datas, Map<String, String> headers) throws AppException {

		RequestBody formBody = new MultipartBody.Builder()
				.setType(MultipartBody.FORM)
				.addFormDataPart(name, "c:\\a.txt",
						RequestBody.create(MediaType.parse("application/octet-stream"), datas))
				.build();
		okhttp3.Request request=new okhttp3.Request.Builder().url(url).post(formBody).headers(SetHeaders(headers)).build();

		try {
			Response response=mOkHttpClient.newCall(request).execute();
			if(response.isSuccessful()){
				InputStream in =response.body().byteStream();
				return InputStreamToByte(in);
			}
		}catch (Exception e){
			throw new AppException("错误", e);
		}
		return new byte[] {};
	}

	private static Headers SetHeaders(Map<String, String> headersParams){
		Headers headers=null;
		Headers.Builder headersbuilder=new Headers.Builder();

		if(headersParams != null)
		{
			Iterator<String> iterator = headersParams.keySet().iterator();
			String key = "";
			while (iterator.hasNext()) {
				key = iterator.next().toString();
				headersbuilder.add(key, headersParams.get(key));
				Log.d("get http", "get_headers===" + key + "====" + headersParams.get(key));
			}
		}
		headers=headersbuilder.build();
		return headers;
	}

}