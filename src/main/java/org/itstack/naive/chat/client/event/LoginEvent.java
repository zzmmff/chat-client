package org.itstack.naive.chat.client.event;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.itstack.naive.chat.client.infrastructure.util.BeanUtil;
import org.itstack.naive.chat.client.infrastructure.util.CacheUtil;
import org.itstack.naive.chat.protocol.login.LoginRequest;
import org.mufanz.chat.ui.view.login.ILoginEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 * <p>
 * 事件实现；登陆窗口
 */
public class LoginEvent implements ILoginEvent {

    private Logger logger = LoggerFactory.getLogger(LoginEvent.class);

    @Override
    public void doLoginCheck(String userId, String userPassword) {
        System.out.println("点击 登录！");
        Channel channel = BeanUtil.getBean("channel", Channel.class);
        //ChannelFuture future = channel.writeAndFlush(MsgUtil.buildMsg(channel.id().toString(),"你好，使用 protobuf 通信格式的服务端，我是 https://bugstack.cn"));
        ChannelFuture future = channel.writeAndFlush(new LoginRequest(userId, userPassword));
        future.addListener((ChannelFutureListener) channelFuture -> {
            if (future.isSuccess()){
                System.out.println("write成功");
            }else {
                System.out.println("write失败");
            }
        });
        CacheUtil.userId = userId;
    }
}
