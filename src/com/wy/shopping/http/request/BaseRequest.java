package com.wy.shopping.http.request;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;

import android.util.Log;

import com.wy.shopping.http.HttpCenter;

public class BaseRequest implements Request {
	
	private HttpCenter httpCenter;
	
	public BaseRequest(HttpCenter httpCenter) {
		this.httpCenter = httpCenter;
	}

	@Override
	public Response post(String url,Object value) {
//		if(ScpipApplication.NetworkCheck.check()){ 
		HttpClient httpClient = httpCenter.getHttpClient();
		HttpPost httpPost = new HttpPost(url);
		HttpResponse response = null;
		try {
			StringEntity entity = new StringEntity(value.toString(), HTTP.UTF_8);
			httpPost.setEntity(entity);
			httpPost.setHeader("Accept-Encoding",
					"application/json,charset=UTF-8");
			httpPost
					.setHeader("Content-type", "application/json,charset=UTF-8");
			response = httpClient.execute(httpPost);
			return ResponseFactory.getResponse(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseFactory.getResponse(e);
		} finally {
			httpPost.abort();
		}
//		}else{
//			throw new ServiceException("无网络连接");
//		}
	}

	@Override
	public Response get(String url) {
		HttpClient httpClient = httpCenter.getHttpClient();
		HttpResponse response = null;
		HttpGet httpGet = null;
		try {
			String uri = url;
			Log.i("URL", uri);
			httpGet = new HttpGet(uri);
			httpGet.setHeader("Content-type",
					"application/x-www-form-urlencoded;charset=" + HTTP.UTF_8);
			response = httpClient.execute(httpGet);
			return ResponseFactory.getResponse(response);
		} catch (Exception e) {
			Log.v("HttpRequest.post:", "HttpRequest Exception[message="
					+ e.getMessage() + "]", e);
			return ResponseFactory.getResponse(e);
		} finally {
			if (httpGet != null)
				httpGet.abort();
		}
	}
}
