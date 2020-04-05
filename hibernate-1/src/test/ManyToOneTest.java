package test;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Grade;
import entity.Student2;
import util.HibernateUtil;

/**
 * 多对一测试(学生-->班级)
 * 单向和双向测试
 * @author lxf
 *
 */

public class ManyToOneTest {
    private static Session session;
    static
    {
        session = HibernateUtil.getSession();
    }
    /**
     * 新增班级和学生( 学生-->班级　多对一 )单向关联关系
     */
    public static void add()
    {
        //实例班级以及学生对象
        Grade grade = new Grade("JAVA-1班", "java1班都是精英");
        Student2 st1 = new Student2("张三1", "男");
        Student2 st2 = new Student2("李四1", "女");        
        //设置多对一关联关系
        st1.setGrade(grade); //学生张三1属于JAVA-1班
        st2.setGrade(grade);//学生李四1属于JAVA-1班
        //保存数据库
        Transaction tx = session.beginTransaction();
        session.save(grade);
        session.save(st1);
        session.save(st2);
        tx.commit();
        HibernateUtil.closeSession();
    }
    /**
     * 新增班级和学生( 班级-->学生一对多，学生-->班级多对一 )双向关联关系
     */
    public static void add2()
    {
        //实例班级以及学生对象
        Grade grade = new Grade("JAVA-1班", "java1班都是精英");
        Student2 st1 = new Student2("张三1", "男");
        Student2 st2 = new Student2("李四1", "女");   
        //设置关联关系一对多
        grade.getStudent2().add(st1);
        grade.getStudent2().add(st2);
        //设置多对一关联关系
        st1.setGrade(grade); //学生张三1属于JAVA-1班
        st2.setGrade(grade);//学生李四1属于JAVA-1班
        //保存数据库
        Transaction tx = session.beginTransaction();
        //session.save(grade);
        session.save(st1);
        session.save(st2);
        tx.commit();
        HibernateUtil.closeSession();
    }
    /**
     * 通过查询学生获取班级信息
     * @param args
     */
    public static void getGradeByStudent(int id)
    {
        //查询主键id = 参数的学生信息
       Student2 st =  session.get(Student2.class, id);
       //查询该学生所属班级
       Grade g = st.getGrade();
       System.out.println("查询id = " + id + " 的学生是" + st.toString());
       System.out.println("查询id = " + id + " 的学生所属班级：" + g.toString());
    }
    public static void main(String[] args)
    {
        //ManyToOneTest.add();
        ManyToOneTest.add2();
        //ManyToOneTest.getGradeByStudent(1);
    }
}
