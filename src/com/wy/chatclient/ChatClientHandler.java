package com.wy.chatclient;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import android.widget.ListView;

import com.wy.shopping.adapter.ChatAdapter;
import com.wy.vo.Content;

public class ChatClientHandler extends SimpleChannelInboundHandler<Content> {

    private ListView list;

    private ChatDeatailAct act;

    public ChatClientHandler(ListView list, ChatDeatailAct act) {
        this.list =list;
        this.act = act;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext arg0, Content arg1) throws Exception {
        
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
        ctx.close();
    }

    @Override
    public boolean acceptInboundMessage(Object msg) throws Exception {
        return super.acceptInboundMessage(msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        final Content content = (Content) msg;
        content.setTo(1);
        act.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ChatAdapter adapter=(ChatAdapter) list.getAdapter();
                adapter.addItem(content, adapter.getCount());
                list.setSelection(adapter.getCount()-1);
            }
        });
    }
}
