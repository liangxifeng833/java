package com.corefire;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import com.corefire.config.CorefireConfig;
import com.corefire.util.JdbcConnect;

/**
 * 单元测试mysql数据库操作
 * @author liangxifeng
 * @date 2017-04-15
 *
 */

public class MysqlTest {
    @Test
    public void testConnect() throws IOException, ClassNotFoundException
    {
        System.out.println("单元测试Mysql获取mysql连接"+JdbcConnect.getConnect());
        Assert.assertNotNull(JdbcConnect.getConnect());
    }
}
