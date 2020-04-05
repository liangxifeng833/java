package domain.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.soap.Text;
import java.io.Serializable;
import java.util.Date;

/**
 * 物业资源表
 * @author 宁亚超 2018-02-07
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Estate implements Serializable {

    /**
     * 主键
     */
    private long  id;

    /**
     * '物业资源面积'
     */
    private float estateProportion;

    /**
     * 该物业资源的地图坐标点，序列化保存
     */
    private String estateMap;

    /**
     * 状态：0未签 1已签 2过期
     */
    private int state;

    /**
     * '指定物业资源年份'
     */
    private String estateYear;

    /**
     * '楼层': 1一层  2二层  3三层  -1(地下一层)
     */
    private int estateFloor;


}
