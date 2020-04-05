package com.lxf.config;

import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

//ServerApplicationConfig接口的实现类会在web容器启动的时候自动执行
public class SocketConfig  implements ServerApplicationConfig{

    /**
     * @param scaned 代表是由web服务器启动的时候，扫码本项目中带有@ServerEndpoint注解的类，
     * 　将这些类实例放到该接合中，并传入到本方法中（也称依赖注入，被动式编程）
     */
    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scaned) {
        // TODO Auto-generated method stub
        System.out.println("websocket config---------------扫描到有websocket的endPoint服务个数："+scaned.size());
        //在这里我们可以做一些过滤操作和一些自己的业务逻辑
        
        //最后给服务器返回,服务器端就会注册这些socket server，工前台访问
        return scaned;
    }

    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> arg0) {
        // TODO Auto-generated method stub
        return null;
    }

}
