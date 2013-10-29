package com.wy.shopping.http.request;

public interface Request {

    /**
     * POST请求
     * 
     * @return
     * @throws RequestException
     */
    Response post(String url, Object value);

    /**
     * GET请求
     * 
     * @return
     * @throws RequestException
     */
    Response get(String url);
}
