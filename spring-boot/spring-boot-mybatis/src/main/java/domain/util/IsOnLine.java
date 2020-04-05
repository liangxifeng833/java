package domain.util;

import domain.domain.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证坐标点是否在一条直线上
 * @author  liangxifeng 2018-01-11
 */
public class IsOnLine {
    //主方法
    @SuppressWarnings("static-access")
    public static void main(String args[]){
        List points = new ArrayList();
        List point1 = new ArrayList();
        point1.add(1f);
        point1.add(1f);
        points.add(point1);

        List point2 = new ArrayList();
        point2.add(2f);
        point2.add(4f);
        points.add(point2);

        List point3 = new ArrayList();
        point3.add(12f);
        point3.add(1f);
        points.add(point3);

        List point4 = new ArrayList();
        point4.add(7f);
        point4.add(5f);
        points.add(point4);

        System.out.println( "坐标点是:"+points );
        System.out.println("坐标点是否共线:"+CommonCheck.isLine(points));

    }

}
