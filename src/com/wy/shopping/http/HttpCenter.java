
package com.wy.shopping.http;

import org.apache.http.client.HttpClient;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import com.wy.shopping.http.request.BaseRequest;
import com.wy.shopping.http.request.Request;

public class HttpCenter {
	
	private static final HttpCenter instance = new HttpCenter();
	
	private static final BasicHttpParams httpParams;

	static {
		// 创建HttpParams以用来设置HTTP参数(这部分不是必需,手动设置最好)
		httpParams = new BasicHttpParams();
		// 设置连接超时
		HttpConnectionParams.setConnectionTimeout(httpParams, 10* 1000);
		// 设置等待数据超时25秒
		HttpConnectionParams.setSoTimeout(httpParams, 25 * 1000);
		// 设置缓存大小
		HttpConnectionParams.setSocketBufferSize(httpParams, 8192);
		// 设置重定向,缺省值为true
		HttpClientParams.setRedirecting(httpParams, true);
		// 设置字符集
		HttpProtocolParams.setContentCharset(httpParams, "UTF-8");
		// 创建一个HttpClient实例
		// 注意 HttpClient httpClient = new HttpClient(); 是Commons HttpClient
		// 中的用法，在 Android 1.5 中我们需要使用 Apache 的缺省实现 DefaultHttpClient
	}
	
	private HttpCenter(){}
	
	public static BasicHttpParams getHttpParams(){
		return httpParams;
	}
	
	public static HttpParams getParams(){
		return httpParams;
	}
	
	public HttpClient getHttpClient(){
		HttpClient client =  new DefaultHttpClient(httpParams);
		return client;
	}
	
	/**
	 * 取得请求
	 * 
	 * @return
	 */
	public static HttpCenter getInstance(){
		return instance;
	}

	/**
	 * 创建请求
	 * 
	 * @return
	 */
	public Request createRequest() {
		return new BaseRequest(instance);
	}
}

