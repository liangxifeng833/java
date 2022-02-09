package order.openfeign.config;

import feign.Logger;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

/**
 * fegin 客户端的自定义配置
 * @author liangxifeng
 * @date: 2021-12-31
 */
@Slf4j
public class FeignConfiguration {
    /**
     * 自定义重试机制,我们这里不需要重试，所以再次去掉Bean的注册
     * @return
     */
    //@Bean
    public Retryer feignRetryer() {
        //最大请求次数为5，初始间隔时间为100ms，下次间隔时间1.5倍递增，重试间最大间隔时间为1s
        return new Retryer.Default();
    }


    /**
     * Feign需要打印的出来的日志信息
     * NONE:没有记录（DEFAULT）
     * BASIC:仅记录请求方法和URL以及响应状态代码和执行时间
     * HEADERS:记录基本信息以及请求和响应headers
     * FULL:记录请求和响应的headers、body和元数据
     * 注意：这些日志大部分都是debug级别的日志，所以系统的配置文件中，必须定义debug级别的日志输出
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        //这里记录所有，根据实际情况选择合适的日志level
        return Logger.Level.FULL; //记录基本信息以及请求和响应headers。;
    }

    /**
     * 响应异常解码处理，正常响应不触发
     * 该方法只记录error日志
     * @return
     */
    @Bean
    public ErrorDecoder feignError() {
        return (key, response) -> {
            /**
            if (response.status() == 400) {
                log.error("请求xxx服务400参数错误,返回:{}", response.body());
            }
            if (response.status() == 409) {
                log.error("请求xxx服务409异常,返回:{}", response.body());
            }
            if (response.status() == 404) {
                log.error("请求xxx服务404异常,返回:{}", response.body());
            }*/
            log.error("---aaaaaa--feign请求出现异常，异常code={},message={}", response.status(),response.body());
            // 其他异常交给Default去解码处理
            // 这里使用单例即可，Default不用每次都去new
            return new ErrorDecoder.Default().decode(key, response);
        };
    }
}
