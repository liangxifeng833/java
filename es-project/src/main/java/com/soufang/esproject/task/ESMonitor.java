package com.soufang.esproject.task;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by liangxifeng
 * 定时任务检测es健康值.
 */
//@Component
public class ESMonitor {
    private static final String HEALTH_CHECK_API = "http://127.0.0.1:8888/_cluster/health";

    private static final String GREEN = "green";    //健康
    private static final String YELLOW = "yellow";  //警告
    private static final String RED = "red";        //错误

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    protected JavaMailSender mailSender;

//    @Scheduled(fixedDelay = 60 * 1000)
    //定时任务5000毫秒=5秒,该项目启动后每隔5秒执行一次
    @Scheduled(fixedDelay = 5000)
    public void healthCheck() {
        HttpClient httpClient = HttpClients.createDefault();

        HttpGet get = new HttpGet(HEALTH_CHECK_API);

        try {
            HttpResponse response = httpClient.execute(get);
            if (response.getStatusLine().getStatusCode() != HttpServletResponse.SC_OK) {
                System.out.println("Can not access ES Service normally! Please check the server.");
            } else {
                String body = EntityUtils.toString(response.getEntity(), "UTF-8");
                JsonNode result = objectMapper.readTree(body);
                String status = result.get("status").asText();

                String message = "";
                boolean isNormal = false;
                switch (status) {
                    case GREEN:
                        message = "ES server run normally.";
                        isNormal = true;
                        break;
                    case YELLOW:
                        message = "ES server gets status yellow! Please check the ES server!";
                        break;
                    case RED:
                        message = "ES ser get status RED!!! Must Check ES Server!!!";
                        break;
                    default:
                        message = "Unknown ES status from server for: " + status + ". Please check it.";
                        break;
                }
                if (!isNormal) {
                    sendAlertMessage(message);
                }

                // 获取集群节点
                int totalNodes = result.get("number_of_nodes").asInt();
                if (totalNodes < 5) {
                    sendAlertMessage("集群节点数<5！");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送邮件信息
     * @param message
     */
    private void sendAlertMessage(String message) {
        System.out.println("邮件内容message == "+message);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //发件人邮箱,注意要和application.yml中配置的mail.username一致
        mailMessage.setFrom("786285076@qq.com");
        //收件人邮箱
        mailMessage.setTo("liangxifeng@163.com");
        //标题
        mailMessage.setSubject("【警告】ES服务监控");
        //内容
        mailMessage.setText(message);
        //发送邮件
        mailSender.send(mailMessage);
    }
}
