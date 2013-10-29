package com.wy.shopping.http.request;

public interface Response {

    /**
     * 是否成功
     * 
     * @return
     */
    boolean isSuccess();

    /**
     * 信息
     * 
     * @return
     */
    String getMessage();

    /**
     * 返回数据
     * 
     * @return
     */
    Object getData();
}