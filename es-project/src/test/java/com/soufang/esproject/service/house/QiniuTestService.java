package com.soufang.esproject.service.house;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.soufang.esproject.ApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * Description: es-project
 * Create by liangxifeng on 19-4-29
 */
public class QiniuTestService extends ApplicationTests {

    @Autowired
    private IQiNiuService iQiNiuService;

    /**
     * 测试图片上传
     */
    @Test
    public void testUploadFile(){
        String fileName = "/home/lxf/git/user_liangxifeng833/my_program/java/es-project/tmp/dayin-kache.jpg";
        File file = new File(fileName);
        Assert.assertTrue(file.exists());
        try {
            Response response = iQiNiuService.uploadFile(file);
            Assert.assertTrue(response.isOK());
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单元测试删除七牛云的图片
     * @throws QiniuException
     */
    @Test
    public void testDelImg() throws QiniuException {
        String key = "Fpa3PiTAQ3uxaGpqF27lujMqpFAJ";
        Response response = iQiNiuService.delete(key);
        Assert.assertTrue(response.isOK());
    }
}
