package com.wy.shopping.tasks.business;

import android.content.Context;

import com.wy.shopping.http.ServiceResponse;
import com.wy.shopping.http.serverce.ServiceFactory;
import com.wy.shopping.serverce.PostService;
import com.wy.shopping.tasks.BaseTask;

public class TestConnectTask extends BaseTask<Integer, Void, ServiceResponse> {

    PostService service = ServiceFactory.getService(PostService.class);
    
    public TestConnectTask(Context context) {
        super(context);
    }

    @Override
    public ServiceResponse doExecute(Integer param) throws Exception {
        ServiceResponse resp = service.testConnectServer(param);
        return resp;
    }

    @Override
    public void doResult(ServiceResponse result) throws Exception {
        if(result.isSuccess()){
            System.out.println(result.getContent());
        }else{
            System.out.println("连接失败");
        }
    }

}
