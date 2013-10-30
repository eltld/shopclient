
package com.wy.shopping.http.serverce;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

import com.wy.shopping.constant.Const;
import com.wy.shopping.http.HttpCenter;
import com.wy.shopping.http.Param;
import com.wy.shopping.http.request.Request;
import com.wy.shopping.http.request.Response;
import com.wy.shopping.pojo.UserInfo;


public class ServiceInvocationHandler implements InvocationHandler {
	
	/**
	 * Http调用
	 */
	private HttpCenter httpCenter;

	public ServiceInvocationHandler(HttpCenter httpCenter) {
		this.httpCenter = httpCenter;
	}

	/**
	 * {class:'com.th.phone.server.service.PeopleReportService'
	 * ,method:'getPeopleAgeReport' ,types:['java.lang.String'] ,values:['10']}
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		JSONObject param = new JSONObject();
		// 设置请求类
		param.put(Param.CLASS, method.getDeclaringClass().getName());
		// 设置请求方法
		param.put(Param.METHOD, method.getName());
		// 设置参数类型 
		Class<?>[] typeArray = method.getParameterTypes();
		JSONArray types = new JSONArray();
		for(Class<?> type : typeArray){
			types.put(type.getName());
		}
		param.put(Param.TYPES, types);
		// 设置参数值
		JSONArray values = new JSONArray(Arrays.asList(args));
		param.put(Param.VALUES, values);
		UserInfo user = null;
		//第一次启动程序，此时数据库文件不存在
//		try{
//			user = cache.getCacheObject(CacheKey.USER, UserInfo.class);
//		}catch (Exception e) {
//			user = null;
//		}
		String sessionId = (user == null) ? "" : user.getSessionId();
		param.put(Param.SESSION_ID, "1111111111");
		param.put(Param.APK_VERSION, "0.1");
		Request request = httpCenter.createRequest();
		Response response = request.post(Const.SERVER_URL, param);
		return new ClientResponse(response);
	}
}
