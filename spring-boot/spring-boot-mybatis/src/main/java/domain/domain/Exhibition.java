package domain.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 展位主表
 * @author 宁亚超 2018-02-07
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Exhibition implements Serializable {
    /**
     * 主键
     */
    private String exhibitionName;
    /*
     *  状态：0未签 1已签
     */
    private int state;
}
