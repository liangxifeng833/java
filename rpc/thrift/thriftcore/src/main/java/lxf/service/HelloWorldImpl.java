package lxf.service;

public class HelloWorldImpl implements HelloWorldService.Iface {

    public HelloWorldImpl() {
    }

    @Override
    public String sayHello(String username) {
        return "Hi," + username + " welcome to my blog www.micmiu.com";
    }

}
