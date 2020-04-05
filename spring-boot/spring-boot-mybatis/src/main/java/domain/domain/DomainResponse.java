package domain.domain;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * 领域层返回类
 * @author  liangxifeng 2018-1-20
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DomainResponse<T> {
    //操作码, 默认=1 为正常返回应用层所需数据
    private int code = 1;
    //响应描述信息
    private String message;
    //响应数据
    private T data;

    public DomainResponse(int code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public DomainResponse(String message,T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
