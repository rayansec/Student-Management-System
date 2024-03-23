package cn.jufe.xyb.domain;

import java.io.Serializable;
import java.util.Objects;

public class Grade  implements Serializable{
    private static final long serialVersionID = 1L;
    private Student student;
    private Course course;
    private double score;
    public Grade(Student student, Course course, double score){
        super();
        this.student = student;
        this.course = course;
        this.score = score;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "student=" + student +
                ", course=" + course +
                ", score=" + score +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grade)) return false;
        Grade grade = (Grade) o;
        return Double.compare(grade.score, score) == 0 && Objects.equals(student, grade.student) && Objects.equals(course, grade.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, course, score);
    }
}
