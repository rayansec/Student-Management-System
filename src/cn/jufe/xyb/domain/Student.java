package cn.jufe.xyb.domain;

import java.io.Serializable;
import java.util.Objects;

public class Student  implements Serializable{


    private static final long serialVersionID = 1l;
    private String sno;
    private String sname;
    private int sage;
    public Student(String sno, String sname, int sage){
        this.sno = sno;
        this.sname = sname;
        this.sage = sage;
    }

    public String getSno() {
        return sno;
    }

    public String getSname() {
        return sname;
    }

    public int getSage() {
        return sage;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setSage(int sage) {
        this.sage = sage;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sno='" + sno + '\'' +
                ", sname='" + sname + '\'' +
                ", sage='" + sage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(sno, student.sno) && Objects.equals(sname, student.sname) && Objects.equals(sage, student.sage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sno, sname, sage);
    }

}
