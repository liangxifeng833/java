package com.lxf.service;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.lxf.bean.Account;
import com.lxf.bean.User;
import com.lxf.mappers.db_account.AccountMapper;
import com.lxf.mappers.db_user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;

/**
 * spring+automikos方式实现分布式事务servier demo
 */
public class JTAService {
    @Autowired
    private UserMapper userMapper;//操作db_user库
    @Autowired
    private AccountMapper accountMapper;//操作db_account库
    //@Transactional
    public void insert() throws Exception {
        //UserTransactionImp tx = new UserTransactionImp();
        UserTransactionManager tx = new UserTransactionManager();
        tx.begin();
        /*
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-atomikos.xml");
        UserTransactionManager tx = context.getBean("atomikosTransactionManager",UserTransactionManager.class);
        tx.begin();
        */
        User user = new User();
        user.setName("zhaoliu-2");
        userMapper.insert(user);
        //    int i = 1 / 0;//模拟异常，spring回滚后，db_user库中user表中也不会插入记录
        Account account = new Account();
        account.setUserId(user.getId());
        account.setMoney(700);
        accountMapper.insert(account);
        tx.commit();
    }
}
