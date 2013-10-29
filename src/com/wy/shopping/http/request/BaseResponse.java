package com.wy.shopping.http.request;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.wy.shopping.utils.HttpUtil;

public class BaseResponse implements Response {

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 消息
     */
    private String message;

    /**
     * 服务器返回数据
     */
    private Object data;

    public BaseResponse(HttpResponse response) {
        try {
            init(response);
        } catch (Exception e) {
            Log.e("Response.init", "数据接收错误!Message[" + e.getMessage() + "]", e);
            throw new DataException(e);
        }
    }

    public BaseResponse(Exception e) {
        init(e);
    }

    private void init(HttpResponse response) throws Exception {
        HttpEntity entity = response.getEntity();
        // 如果返回输出流
        if (isInputStream(entity)) {
            File file = File.createTempFile("Download-", ".temp");
            FileOutputStream out = new FileOutputStream(file);
            HttpUtil.write(out, entity.getContent());
            data = new FileInputStream(file);
            message = "";
            success = true;
        } else {
            data = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
            message = "";
            success = true;
        }
    }

    @SuppressWarnings("all")
    private void init(Exception e) {
        message = e.getMessage();
        success = false;
        data = "";
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    /**
     * 是输出流?
     * 
     * @param response
     * @return
     */
    private boolean isInputStream(HttpEntity entity) {
        String contentType = entity.getContentType().toString();
        return contentType.toLowerCase().contains("stream");
    }
}