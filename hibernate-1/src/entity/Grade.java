package entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**
 * 班级实体类
 * @author lxf
 *
 */

public class Grade implements Serializable {
    private int gid;
    private String gname;
    private String gdesc;
    private int score;
    //在班级(一方)定义学生(多方)的集合
    private Set<Student2> student2 = new HashSet<Student2>();
    
    
    public Grade() {
        super();
    }

    public Grade(String gname, String gdesc) {
        super();
        this.gname = gname;
        this.gdesc = gdesc;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGid() {
        return gid;
    }
    public void setGid(int gid) {
        this.gid = gid;
    }
    public String getGname() {
        return gname;
    }
    public void setGname(String gname) {
        this.gname = gname;
    }
    public String getGdesc() {
        return gdesc;
    }
    public void setGdesc(String gdesc) {
        this.gdesc = gdesc;
    }
    public Set<Student2> getStudent2() {
        return student2;
    }
    public void setStudent2(Set<Student2> student2) {
        this.student2 = student2;
    }

    @Override
    public String toString() {
        return "Grade [gid=" + gid + ", gname=" + gname + ", gdesc=" + gdesc + ", score=" + score + ", student2="
                + student2 + "]";
    }
    
}
