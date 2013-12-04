package com.wy.shopping.tasks.business;

import java.io.Serializable;
import java.util.List;

import org.json.JSONObject;

import android.content.Context;

import com.wy.chatclient.ChatMainAct;
import com.wy.chatclient.LoginAct;
import com.wy.shopping.http.ServiceResponse;
import com.wy.shopping.http.serverce.ServiceFactory;
import com.wy.shopping.service.LoginService;
import com.wy.shopping.tasks.BaseTask;
import com.wy.shopping.utils.Util;
import com.wy.vo.Info;
import com.wy.vo.User;

public class LoginTask extends BaseTask<Info, Void, ServiceResponse> {

    LoginService service = ServiceFactory.getService(LoginService.class);

    private LoginAct act;
    
    private Info logininfo;

    public LoginTask(Context context) {
        super(context);
        act = (LoginAct) context;
    }

    @Override
    public ServiceResponse doExecute(Info info) throws Exception {
        logininfo=info;
        JSONObject obj = new JSONObject();
        obj.put("name", info.getName());
        obj.put("pass", info.getPass());
        ServiceResponse resp = service.login(obj);
        return resp;
    }

    @Override
    public void doResult(ServiceResponse result) throws Exception {
        if (result.isSuccess()) {
            List<User> data = Util.StringToList(result.getContent().toString());
            User myInfo=new User();
            myInfo.setChannelId(LoginAct.getChannel().hashCode());
            myInfo.setName(logininfo.getName());
            LoginAct.getChannel().writeAndFlush(myInfo).sync();
            act.skip(ChatMainAct.class,(Serializable)data);
        } else {
            act.toast(result.getContent());
        }
    }

}
