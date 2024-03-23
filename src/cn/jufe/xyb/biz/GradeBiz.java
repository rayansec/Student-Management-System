package cn.jufe.xyb.biz;

import cn.jufe.util.Validation;
import cn.jufe.xyb.dao.CourseDao;
import cn.jufe.xyb.dao.GradeDao;
import cn.jufe.xyb.dao.StudentDao;
import cn.jufe.xyb.domain.Course;
import cn.jufe.xyb.domain.Grade;
import cn.jufe.xyb.domain.Student;
import cn.jufe.xyb.exception.MyException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class GradeBiz {
        private GradeDao gradeDao;
        private StudentDao studentDao;
        private CourseDao courseDao;

        public GradeBiz() throws ClassNotFoundException, IOException{
            gradeDao = new GradeDao();
            studentDao = new StudentDao();
            courseDao = new CourseDao();
        }
        public int addGrade(String sno, String cno, double score)throws FileNotFoundException, IOException, MyException{
            if(!Validation.validateStr(sno) || !Validation.validateStr(cno)){
                throw new MyException("exception: sno is empty or cno is empty");
            }
            if (gradeDao.queryGradeBySnoCno(sno,cno)!= null){
                throw new MyException("exception: grade of such sno and cno does not exist in the grade list");

            }
            Student student = studentDao.queryStudentBySno(sno);
            if(student == null){
                throw new MyException(" exception: student of such sno does not exist in the student list");
            }
            Course course = courseDao.queryCourseByCno(cno);
            if(course == null){
                throw new MyException("exception: course of such cno does not exist in the course list");

            }
            Grade grade = new Grade(student, course, score);
            return gradeDao.addGrade(grade);
        }
    public int deleteGardeBySnoCno(String sno, String cno) throws FileNotFoundException, IOException,MyException{
        if (!Validation.validateStr(sno)|| !Validation.validateStr(cno)){
            throw new MyException("exception: sno is empty or cno is empty");
        }
        Student student = studentDao.queryStudentBySno(sno);
        if(student == null){
            throw new MyException("exception: student of such sno does not exist in the student list");
        }
        Course course= courseDao.queryCourseByCno(cno);
        if(course == null){
            throw new MyException("exception: course of such cno does not exist in the course list");
        }
        Grade grade = gradeDao.queryGradeBySnoCno(sno, cno);
        return gradeDao.deleteGrade(grade);
    }

    public List<Grade> queryAll(){
        return gradeDao.queryAll();
    }
    public List<Grade> queryAllBySno(String sno) throws MyException{
        if(!Validation.validateStr(sno)){
            throw new MyException("exception: sno is empty ");
        }
        return gradeDao.queryGradesBySno(sno);
    }

    public List<Grade> queryAllByCno(String cno) throws MyException {
        if(!Validation.validateStr(cno)){
            throw new MyException("exception: cno is empty ");
        }
        return gradeDao.queryGradesBySno(cno);
    }

    public Grade queryGrade(String sno, String cno) throws MyException {
        if (!Validation.validateStr(sno)|| !Validation.validateStr(cno)) {
            throw new MyException("exception: sno is empty or cno is empty");
        }
        return gradeDao.queryGradeBySnoCno(sno,cno);
    }
}