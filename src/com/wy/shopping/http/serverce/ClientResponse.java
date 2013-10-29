package com.wy.shopping.http.serverce;

import com.wy.shopping.http.ServiceException;
import com.wy.shopping.http.ServiceResponse;
import com.wy.shopping.http.request.Response;

public class ClientResponse implements ServiceResponse {

    private boolean success;

    private String message;

    private Object content;

    public ClientResponse(Response response) {
        this.init(response);
    }

    private void init(Response response) {
        if (response.isSuccess()) {
            ServiceResult result = new ServiceResult(response.getData());
            this.content = result.getContent();
            this.success = result.isSuccess();
            this.message = result.getMessage();
        } else {
            ServiceResult result = new ServiceResult(response.getData());
            throw new ServiceException("服务调用异常 " + result.getMessage());
        }
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public Object getContent() {
        return content;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ClientResponse \n[success=").append(success).append(";\nmessage=").append(message)
                .append(";\ncontent=").append(content).append("]");
        return builder.toString();
    }
}
