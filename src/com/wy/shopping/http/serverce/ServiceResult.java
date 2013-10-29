
package com.wy.shopping.http.serverce;

import java.io.InputStream;

import org.json.JSONObject;

import android.util.Log;

import com.wy.shopping.http.Param;
import com.wy.shopping.http.request.DataException;


public class ServiceResult {

	private boolean success;

	private String message;

	private Object content;

	public ServiceResult() {
	}

	public ServiceResult(Object data) {
		try {
			if( data instanceof InputStream){
				init((InputStream)data);
			}else{
				init(data.toString());
			}
		} catch (Exception e) {
			Log.e("ServiceResult", "数据格式异常:" + e.getMessage(), e);
			throw new DataException(e);
		}
	}

	private void init(String json) throws Exception {
		if (isJSONFormat(json)) {
			JSONObject obj = new JSONObject(json);
			success = obj.getBoolean(Param.SUCCESS);
			message = obj.getString(Param.MESSAGE);
			content = obj.getString(Param.CONTENT);
		} else {
			success = false;
			message = json;
			content = "";
		}
	}
	
	private void init(InputStream in) throws Exception {
		success = true;
		message = "文件输出流";
		content = in;
	}

	/**
	 * 是否是JSON格式
	 * 
	 * @param json
	 * @return
	 */
	private boolean isJSONFormat(String json) {
		return json.startsWith("{") && json.endsWith("}");
	}

	/**
	 * 是否成功
	 * 
	 * @return
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * 是否失败
	 * 
	 * @return
	 */
	public boolean isFail() {
		return !isSuccess();
	}

	/**
	 * 取得信息
	 * 
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 取得服务器端数据
	 * 
	 * @return
	 */
	public Object getContent() {
		return content;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServiceResult \n[success=").append(success).append(";\nmessage=").append(message).append(";\ncontent=").append(content).append("]");
		return builder.toString();
	}
}
