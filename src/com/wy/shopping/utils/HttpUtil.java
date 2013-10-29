/* 
 * 创建日期 2011-9-6
 *
 * 成都天和软件公司
 * 电话：028-85425861 
 * 传真：028-85425861-8008 
 * 邮编：610041 
 * 地址：成都市武侯区航空路6号丰德万瑞中心B座1001 
 * 版权所有
 */
package com.wy.shopping.utils;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import com.wy.shopping.http.HttpCenter;

public class HttpUtil {

	/**
	 * 读
	 * 
	 * @param in
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public static String read(InputStream in, String charset) throws Exception {
		InputStreamReader reader = null;
		StringWriter writer = null;
		try {
			reader = new InputStreamReader(in, charset);
			writer = new StringWriter();
			int len = -1;
			char[] buffer = new char[1024];
			while ((len = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, len);
			}
			writer.flush();
			return writer.toString();
		} finally {
			if (reader != null)
				reader.close();
			if (writer != null)
				writer.close();
		}
	}

	/**
	 * 写
	 * 
	 * @param value
	 * @param charset
	 * @param out
	 * @return
	 * @throws Exception
	 */
	public static void write(OutputStream out, String value, String charset)
			throws Exception {
		OutputStreamWriter writer = null;
		try {
			writer = new OutputStreamWriter(out, charset);
			writer.write(value);
			writer.flush();
		} finally {
			if (out != null)
				writer.close();
		}
	}

	/**
	 * 写
	 * 
	 * @param in
	 * @param out
	 * @throws Exception
	 */
	public static void write(OutputStream out, InputStream in) throws Exception {
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(out);
			int len = -1;
			byte[] buffer = new byte[1024];
			while ((len = in.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
			bos.flush();
		} finally {
			if (in != null)
				in.close();
			if (bos != null)
				bos.close();
		}
	}
	
	
	public static HttpResponse request(String url, String json) throws Exception {
		HttpClient client = new DefaultHttpClient(HttpCenter.getHttpParams());
		HttpPost httpPost = new HttpPost(url);

		StringEntity entity = new StringEntity(json, HTTP.UTF_8);
		httpPost.setEntity(entity);
		httpPost.setHeader("Accept-Encoding", "application/json,charset=UTF-8");
		httpPost.setHeader("Content-type", "application/json,charset=UTF-8");
		HttpResponse response = client.execute(httpPost);
		return response;
	}
}
