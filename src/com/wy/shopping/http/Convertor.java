/* 
 * 创建日期 2011-6-15
 *
 * 成都天和软件公司
 * 电话：028-85425861 
 * 传真：028-85425861-8008 
 * 邮编：610041 
 * 地址：成都市武侯区航空路6号丰德万瑞中心B座1001 
 * 版权所有
 */
package com.wy.shopping.http;

import org.json.JSONObject;

/**
 * 转换接口
 * 
 * @author 王文成
 * @version 1.0
 * @since 2011-6-15
 */
public interface Convertor {
	
	/**
	 * 数据转换
	 * @param jsonObject
	 * @return
	 */
	Object convert(JSONObject data);
}

