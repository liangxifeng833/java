package com.soufang.esproject.entity;

import com.soufang.esproject.ApplicationTests;
import com.soufang.esproject.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


/**
 * Created by 瓦力.
 */
public class UserRepositoryTest extends ApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindOne() {
        //userRepository.findById(1L);
        User user = userRepository.findOne(1L);
        Assert.assertEquals("wali", user.getName());
    }

    @Test
    public void  testAdd(){
        User user = new User();
        //user.setId(3L);
        user.setName("liangxifeng-test-1");
        user.setCreateTime(new Date());
        user.setLastLoginTime(new Date());
        user.setLastUpdateTime(new Date());
        user.setEmail("liangxifeng833@165.com");
        user.setPassword("1234562");
        user.setPhoneNumber("13301223035");
        User user1 = userRepository.save(user);
        User user2 = userRepository.findOne(user1.getId());
        System.out.println(user1);
        Assert.assertEquals(user2.getName(), user1.getName());
    }
}
