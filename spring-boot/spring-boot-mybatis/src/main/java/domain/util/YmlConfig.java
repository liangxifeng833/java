package domain.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * yml配置文件中自定义变量映射类
 * @author ningyachao
 * @date 2018-02-02
 */
@Component
@ConfigurationProperties(prefix = "myYml")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class YmlConfig {

    /**
     * 模块接口域名地址
     */
    private String moduleApiHost;
    /**
     * 展位号最大数量
     */
    private int totalNumber;
}
