package cn.jufe.xyb.domain;

import java.io.Serializable;
import java.util.Objects;

public class Course  implements Serializable{


    private static final long serialVersionID = 1L;
    private String cno;
    private String cname;
    private int ccredit;

    public Course(String cno, String cname, int ccredit){
        super();
        this.cno = cno;
        this.cname = cname;
        this.ccredit = ccredit;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getCcredit() {
        return ccredit;
    }

    public void setCcredit(int ccredit) {
        this.ccredit = ccredit;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cno='" + cno + '\'' +
                ", cname='" + cname + '\'' +
                ", ccredit=" + ccredit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return ccredit == course.ccredit && Objects.equals(cno, course.cno) && Objects.equals(cname, course.cname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cno, cname, ccredit);
    }
}
