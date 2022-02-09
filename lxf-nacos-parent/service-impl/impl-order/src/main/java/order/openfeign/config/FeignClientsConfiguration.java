package order.openfeign.config;


import com.netflix.hystrix.HystrixCommand;
import feign.Feign;
import feign.hystrix.HystrixFeign;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * feign 默认开启熔断配置类
 * 注意：该配置类有在application.yml中配置feign.hystrix.enabled=true时，才会自动注册bean
 * author:liangxifeng
 * date:2021-12-29
 */
//@Configuration
public class FeignClientsConfiguration {
    @Configuration
    //当本项目下已经存在 HystrixCommand.class, HystrixFeign.class 类时 注册该bean
    @ConditionalOnClass({ HystrixCommand.class, HystrixFeign.class})
    protected static class HystrixFeignConfiguration {
        @Bean
        @Scope("prototype") //prototype 为非单例模式注册bean
        @ConditionalOnMissingBean //当给定的在bean不存在时,则实例化当前Bean
        //当配置配置文件中配置feign.hystrix.enabled 属性时，该配置生效，也就是才会出则该bean
        @ConditionalOnProperty(name = "feign.hystrix.enabled", matchIfMissing = true)
        public Feign.Builder feignHystrixBuilder() {
            return HystrixFeign.builder();
        }
    }
}