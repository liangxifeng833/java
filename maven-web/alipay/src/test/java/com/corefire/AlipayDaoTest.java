package com.corefire;
/**
 * 支付宝收款表Dao单元测试
 */

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import com.corefire.config.CorefireConfig;
import com.corefire.dao.AlipayAnnalDao;
import com.corefire.model.AlipayAnnal;
import com.corefire.util.JdbcConnect;

/**
 * 单元测试mysql数据库操作
 * @author liangxifeng
 * @date 2017-04-15
 *
 */

public class AlipayDaoTest {
    @Test
    public void testAdd() throws SQLException 
    {      
        //实例支付宝收款实体
        AlipayAnnal pay = new AlipayAnnal();
        pay.setAppid("alipay-appid");
        pay.setValue(new BigDecimal("999.99"));
        pay.setCharge(new BigDecimal("0.99"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            pay.setAdd_time(sdf.parse("2016-12-12"));
            pay.setModify_time(sdf.parse("2016-12-31"));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        AlipayAnnalDao payDao = new AlipayAnnalDao();
        int res = payDao.add(pay);
        System.out.println("单元测试AlipayDao.add测试"+payDao.add(pay));
        Assert.assertEquals(1, res);
    }
}
