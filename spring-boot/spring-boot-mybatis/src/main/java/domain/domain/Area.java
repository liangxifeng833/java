package domain.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 区域主表
 * @author 宁亚超 2018-02-07
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Area implements Serializable {

    /**
     * 主键
     */
    private long id;

    /**
     * 区域名称
     */
    private String areaName;

}
