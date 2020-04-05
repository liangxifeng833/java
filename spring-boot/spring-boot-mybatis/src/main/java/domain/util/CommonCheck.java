package domain.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 公共验证类
 *  @author : liangxifeng
 *  @date : 2018-01-25
 */
public class CommonCheck {
    /**
     * 验证名称基本信息(非空, 指定个数验证)
     * @param name 名字
     * @param nameNum 指定个数
     * @return 0: 名字为空, 1:验证通过, 2:超过指定个数
     */
    public static Integer checkNameBase(String name, Integer nameNum)
    {
        name = name.trim();
        //验证非空
        if(name == null || name.equals(""))
        {
            return 0;
        }
        //验证名称的个数
        if( name.length() > nameNum  )
        {
            return 2;
        }
        return 1;
    }
    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @param pointSum 小数点后保留的位数
     * @return true是数字, false否
     */
    public static boolean isNumeric(String str,int pointSum){
        Pattern pattern = Pattern.compile("([0-9]+)|([0-9]+.[0-9]{"+pointSum+"})");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
    /**
     * 判断多点一条直线
     * @param points 多个坐标点
     * @return true 是直线, false不是直线
     */
    public static boolean isLine(List<List<Float>> points){
        if(points == null) return false;
        //两点一线
        if(points.size() <= 2) return true;

        for (int i = 0; i < points.size(); i++){
            if(points.get(0) == null)
                return  false;
            //判断数组越界
            if( (i+2) > points.size()-1 )
                return true;

            float x1 = points.get(i).get(0);
            float x2 = points.get(i+1).get(0);
            float x3 = points.get(i+2).get(0);

            float y1 = points.get(i).get(1);
            float y2 = points.get(i+1).get(1);
            float y3 = points.get(i+2).get(1);

            //如果存在三点斜率不等,则证明不在一条直线上
            if((x1-x2)*(y1-y3) != (x1-x3)*(y1-y2)){
                return false;
            }
            //System.out.println(i);
        }
        return true;
    }
}
