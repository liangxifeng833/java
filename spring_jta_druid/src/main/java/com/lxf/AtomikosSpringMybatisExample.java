package com.lxf;
import com.lxf.service.JTAService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;

public class AtomikosSpringMybatisExample {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-atomikos.xml");
        JTAService jtaService = context.getBean("jtaService", JTAService.class);
        try {
            jtaService.insert();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
