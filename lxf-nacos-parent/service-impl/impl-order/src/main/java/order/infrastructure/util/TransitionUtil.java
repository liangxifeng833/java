package order.infrastructure.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * 本项目数据库均为Latin1编码，存放的是gbk数据，因此查出来的时候需要转换为utf8编码
 * @author zhaoyong
 * @date 2021-12-26
 */

@Component
@Slf4j
public class TransitionUtil {

    /**
     * 负责处理　utf8编码向latin1编码转换
     * @param x utf8 编码数据
     * @return　latin1　编码数据
     */
     public static String utf8ToLatin1(String x) {
        try {
            return new String(x.getBytes("UTF-8"),"ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            log.error("utf8=>latin1,编码转换异常,原因：{}", e.getMessage());
        }
        return null;
    }
    /**
     * 负责处理　latin1编码向utf8编码转换
     * @param x latin1编码数据
     * @return　utf8　编码数据
     */
    public static String latin1ToUtf8(String x) {
        try {
            return new String(x.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("latin1=>utf8,编码转换异常,原因：{}", e.getMessage());
        }
        return null;
    }

    /**
     * 判断某个字符串是否是latin1编码
     * @param str
     * @return
     */
    public static boolean isLatin1(String str) {
        try {
            return str.equals(new String(str.getBytes("ISO-8859-1"),"ISO-8859-1") );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断某个字符串是否是utf8编码
     * @param str
     * @return
     */
    public static boolean isUTF8(String str) {
        try {
            return str.equals(new String(str.getBytes("UTF-8"),"UTF-8") );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }


}
