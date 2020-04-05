package entity;

import java.io.Serializable;
/**
 * 学生实体( 主要应用在一对多的映射中 )
 * @author lxf
 *
 */

public class Student2 implements Serializable {
    private int sid;
    private String sname;
    private String sex;
    // 在多方定义一个一方的引用, 该学生所属的班级
    private Grade grade;
    
    
    public Grade getGrade() {
        return grade;
    }
    public void setGrade(Grade grade) {
        this.grade = grade;
    }
    public Student2(String sname, String sex) {
        super();
        this.sname = sname;
        this.sex = sex;
    }
    public Student2() {
        super();
    }
    public int getSid() {
        return sid;
    }
    public void setSid(int sid) {
        this.sid = sid;
    }
    public String getSname() {
        return sname;
    }
    public void setSname(String sname) {
        this.sname = sname;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    @Override
    public String toString() {
        return "Student2 [sid=" + sid + ", sname=" + sname + ", sex=" + sex + "]";
    }
    
    

}
