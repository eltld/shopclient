package com.wy.shopping.http;

public interface ServiceResponse {

    /**
     * 是否成功
     * 
     * @return
     */
    boolean isSuccess();

    /**
     * 返回内容
     * 
     * @return
     */
    Object getContent();

    /**
     * 返回信息
     * 
     * @return
     */
    String getMessage();

}
