package com.wy.shopping.http.request;

import org.apache.http.HttpResponse;

public class ResponseFactory {

    /**
     * 取得服务器响应
     * 
     * @param httpResponse
     * @return
     */
    public static Response getResponse(HttpResponse response) {
        return new BaseResponse(response);
    }

    public static Response getResponse(Exception e) {
        return new BaseResponse(e);
    }
}
