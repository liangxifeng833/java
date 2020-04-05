/*
########################################################################/
# File Name: OfficeTest.java
# Author: Liangxifeng
# Mail: liangxifeng833@163.com 
# Created Time: 2017年02月17日 星期五 10时25分21秒
########################################################################/
*/

class OfficeTest
{
    public static void main(String args[])
    {
        try
        {
            //无法通过参数来进行实例化,在编译的时候会出错
            //Word w = new args[1]();
            System.out.println("=========================");
            //动态加载类在运行时加载
            Class c = Class.forName(args[0]);
            //通过类类型创建该类对象
            Office oa = (Office)c.newInstance();
            oa.start();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
