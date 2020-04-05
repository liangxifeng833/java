package com.soufang.esproject.service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * Created by 瓦力.
 */
@Service
public class SmsServiceImpl implements ISmsService, InitializingBean {
    @Value("${aliyun.sms.accessKey}")
    private String accessKey;

    @Value("${aliyun.sms.accessKeySecret}")
    private String secertKey;

    @Value("${aliyun.sms.template.code}")
    private String templateCode;

    private IAcsClient acsClient;

    private final static String SMS_CODE_CONTENT_PREFIX = "SMS::CODE::CONTENT";

    private static final String[] NUMS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static final Random random = new Random();

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 发送短信接口
     * @param telephone
     * @return
     */
    @Override
    public ServiceResult<String> sendSms(String telephone) {
        String gapKey = "SMS::CODE::INTERVAL::" + telephone;
        String result = redisTemplate.opsForValue().get(gapKey);
        if (result != null) {
            return new ServiceResult<String>(false, "请求次数太频繁");
        }

        String code = generateRandomSmsCode();
        //aliyun短信模板内容
        String templateParam = String.format("{\"code\": \"%s\"}", code);

        // 组装请求对象
        SendSmsRequest request = new SendSmsRequest();

        // 使用post提交
        request.setMethod(MethodType.POST);
        request.setPhoneNumbers(telephone);
        request.setTemplateParam(templateParam);
        request.setTemplateCode(templateCode);
        request.setSignName("蓝景商城");

        boolean success = false;
        try {
            //发送短信操作
            SendSmsResponse response = acsClient.getAcsResponse(request);
            if ("OK".equals(response.getCode())) { //短信发送成功
                success = true;
            } else {
                // TODO log this question
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if (success) {
            //设置redis　60秒内不能重复请求
            redisTemplate.opsForValue().set(gapKey, code, 60, TimeUnit.SECONDS);
            //验证码内容存储在redis中，　１０分钟过期
            redisTemplate.opsForValue().set(SMS_CODE_CONTENT_PREFIX + telephone, code, 10, TimeUnit.MINUTES);
            return ServiceResult.of(code);
        } else {
            return new ServiceResult<String>(false, "服务忙，请稍后重试");
        }
    }

    @Override
    public String getSmsCode(String telephone) {
        return this.redisTemplate.opsForValue().get(SMS_CODE_CONTENT_PREFIX + telephone);
    }

    @Override
    public void remove(String telephone) {
        this.redisTemplate.delete(SMS_CODE_CONTENT_PREFIX + telephone);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 设置超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        IClientProfile profile = DefaultProfile.getProfile("cn-beijing", accessKey, secertKey);

        String product = "Dysmsapi";
        String domain = "dysmsapi.aliyuncs.com";

        DefaultProfile.addEndpoint("cn-beijing", "cn-beijing", product, domain);
        this.acsClient = new DefaultAcsClient(profile);

    }

    /**
     * 6位验证码生成器
     * @return
     */
    private static String generateRandomSmsCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(10);
            sb.append(NUMS[index]);
        }
        return sb.toString();
    }
}
