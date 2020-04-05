package hqlTest;

import static org.junit.Assert.*;
/*
 * query查询数据库练习
 */

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entity.Grade;
import util.HibernateUtil;

public class queryTest {
    private Session session = null;
    @Before
    public void  init()
    {
        session = HibernateUtil.getSession();
    }
    /**
     * 使用query.uniqueResult()返回一个对象（只返回一条记录）
     * 如果结果有多条则会报错
     */
    @Test
    public void testGetOne()
    {
        String hql = "SELECT g FROM Grade AS g where g.gid = 1 order by id asc";
        Query query = session.createQuery(hql);
        Grade grade = (Grade)query.uniqueResult();
        System.out.println("name = " + grade.getGname());   
    }
    /**
     * 四则运算在where中使用
     */
    @Test
    public void testSizeyunsuan()
    {
        String hql = "SELECT g FROM Grade AS g where g.score*2 > 800";
        Query query = session.createQuery(hql);
        List<Object> list = query.list();
        System.out.println("name = " + list.get(0));        
    }
    /**
     * 测试集合运算
     */
    @Test
    public void testJihe()
    {
        //查询班级属性中学生属性补位空的班级信息
        //String hql = "SELECT new map(g.gid as id , g.gname as name ) FROM Grade AS g WHERE g.student2 is not empty";
       //String hql = "SELECT new map(g.gid as id , g.gname as name ) FROM Grade AS g WHERE student2 member of  g.student2";
       String hql = "SELECT new map(s) FROM Student2 AS s WHERE s member of  grade.student2";
        Query query = session.createQuery(hql);
        List<Map> maps = query.list();
        
        for (Map map : maps) {
            //定义属性别名的获取方式
            System.out.println("id = " + map.get("id") + "name = " + map.get("name"));
        }
        
    }
    /**
     * 以自定义方式返回查询属性，需要在查询的实体类中定义构造方法
     * select语句中指定new 实体类名
     */
    @Test
    public void testSelectColumnCustomer()
    {
        String hql = "SELECT new Grade( g.gname , g.gdesc) FROM Grade AS g WHERE g.gid > 5 AND g.gid in (8,10) AND g.gid  BETWEEN 1 and 10";
        Query query = session.createQuery(hql);
        List<Grade> grades = query.list();
        for (Grade grade : grades) {
            //定义属性别名的获取方式
            System.out.println("name = " + grade.getGname()+ "desc = " + grade.getGdesc());
        }  
    }
    /**
     * 以Map集合形式返回查询属性, select语句中指定new map
     * 如果指定属性未定义AS别名，在获取的时候要使用get("0")字符串下标方式获取
     * 如果指定属性定义AS别名，在获取的时候可以使用属性别名作为key查询
     */
    @Test
    public void testSelectColumnMap()
    {
        //以List集合形式返回查询属性 SELECT new map(g.gid, g.gname) 
        //String hql = "SELECT new map(g.gid AS id, g.gname AS name) FROM Grade AS g";
        String hql = "SELECT new map(g.gid , g.gname ) FROM Grade AS g";
        Query query = session.createQuery(hql);
        List<Map> maps = query.list();
        for (Map map : maps) {
            //未定义属性别名的获取方式
            System.out.println("id = " + map.get("0") + "name = " + map.get("1"));
            //定义属性别名的获取方式
            System.out.println("id = " + map.get("id") + "name = " + map.get("name"));
        }  
    }
    /**
     * 以List集合形式返回查询属性, select语句中指定new list
     */
    @Test
    public void testSelectColumnList()
    {
        //以List集合形式返回查询属性 SELECT new list(g.gid, g.gname) 
        String hql = "SELECT new list(g.gid, g.gname) FROM Grade AS g";
        Query query = session.createQuery(hql);
        List<List> lists = query.list();
        for (List list : lists) {
            System.out.println("id = " + list.get(0) + "name = " + list.get(1));
        }
    }
    /**
     * 以Object数组形式返回查询属性
     */
    @Test
    public void testSelectColumnObjectArray()
    {
        /*
        //使用select查询班级id和名称，返回对象数组
        String hql = "SELECT g.gid, g.gname FROM Grade AS g";
        Query query = session.createQuery(hql);
        List<Object[]> list = query.list();
        for (Object[] objects : list) {
            System.out.println("id =  " + objects[0] + "name: " + objects[1]);
        }     */
        //当只查询一个属性的时候，返回的是对象，而不是对象数组
        String hql1 = "SELECT g.gname FROM Grade AS g";
        Query query1 = session.createQuery(hql1);
        List<Object> list1 = query1.list();
        System.out.println("name = " + list1.get(0));     
    }

    /**
     * 查询班级信息
     */
    @Test
    public void testGrade() {
            //定义hql语句，Grade类不需要写全限定名：entity.Grade, 默认情况会auto-import
            String hql = "from Grade";
            Query query = session.createQuery(hql);
            List<Grade> res = query.list();
            for (Grade grade : res) {
                //只输出班级名称，则hibernate只查询grade表，不产生关联查询
                System.out.println(grade.getGname());
                //当输出班级包含的学生属性时候，hibernate会根据关联关系查询student2表数据
                System.out.println(grade.getStudent2());
            }
    }
    
    @After
    public void destroy()
    {
        HibernateUtil.closeSession();
    }

}
