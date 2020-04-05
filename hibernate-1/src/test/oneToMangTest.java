package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Grade;
import entity.Student2;
import util.HibernateUtil;

/**
 * 单向一对多关联关系测试(班级 --> 学生)
 * 建立关联关系后,可以方便的从一个对象导航到另一个对象
 * @author lxf
 */
public class oneToMangTest {
    private static Session session;
    static
    {
        session = HibernateUtil.getSession();
    }
    
    /**
     * 新增班级, 给班级的students属性赋值后
     * 在班级新增后, Hibernate会自动将students集合中的保存到数据库
     */
    public static void add()
    {
        Grade grade = new Grade("JAVA-2班", "java2班都是精英");
        Student2 st1 = new Student2("张三2", "男");
        Student2 st2 = new Student2("李四2", "女");
        Set<Student2> student2s = new HashSet<Student2>();
        student2s.add(st1);
        Student2[] sts = {st1, st2};
        //将两个学生对象添加到set中
        student2s.addAll(Arrays.asList(sts));
        grade.setStudent2(student2s);
        System.out.println(grade);
        
        //保存到数据库中
        Session session  = HibernateUtil.getSession();
        //开启事务
        Transaction tx = session.beginTransaction();
        session.save(grade);
        session.save(st1);
        session.save(st2);
        //提交事务
        tx.commit();
        HibernateUtil.closeSession();
        //查询插入后的班级以及学生信息
        oneToMangTest.getStudentByGrade(grade.getGid());
    }
    /**
     * 查询学生中包含的学生信息
     * @param args
     */
    public static void getStudentByGrade(int id)
    {
        Session session = HibernateUtil.getSession();
        //查询id=2的班级信息
        Grade grade = session.get(Grade.class, id);
        System.out.println("班级信息是:" + grade.toString());
        Set<Student2> st = grade.getStudent2();
        System.out.println("学生信息是:");
        for (Student2 student2 : st) {
            System.out.println(student2.toString());
        }
        HibernateUtil.closeSession();
    }
    
    /**
     * 修改学生信息
     * @param args
     */
    public static void update()
    {
        
        /*
        Grade g = session.get(Grade.class, 1);
        Transaction tx = session.beginTransaction();
        g.setGname("java1班修改");
        session.update(g);
        tx.commit();
        HibernateUtil.closeSession();
        */
        Grade g = new Grade("Java3班", "java3班描述描述");
        //开启事务
        Transaction tx = session.beginTransaction();
        Student2 stu = session.get(Student2.class, 1);
        g.getStudent2().add(stu);
        //数据库中新增Java3班后, 学生表会将id=1的学生gid修改为Java3班新增后的grade.gid
        session.save(g);
        tx.commit();
        HibernateUtil.closeSession();
    }
    /**
     * 删除学生信息
     * @param args
     */
    public static void delete()
    {
        Student2 stu = session.get(Student2.class, 1);
        Transaction tx = session.beginTransaction();
        session.delete(stu);
        tx.commit();
        HibernateUtil.closeSession();
    }

    
    public static void main(String[] args){
        System.out.println("123");
        //oneToMangTest.add();
        //oneToMangTest.update();
        oneToMangTest.delete();
    }
    

}
