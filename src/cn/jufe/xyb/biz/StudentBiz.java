package cn.jufe.xyb.biz;

import cn.jufe.util.Validation;
import cn.jufe.xyb.dao.GradeDao;
import cn.jufe.xyb.dao.StudentDao;
import cn.jufe.xyb.domain.Student;
import cn.jufe.xyb.exception.MyException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class StudentBiz {
        private StudentDao studentDao;
        private GradeDao gradeDao;

        public StudentBiz() throws ClassNotFoundException, IOException {
            studentDao = new StudentDao();
            gradeDao = new GradeDao();
        }

        public int addStudent(Student student) throws FileNotFoundException, IOException, MyException {
            if (student == null || !Validation.validateStr(student.getSno())) {
                throw new MyException("exception: student is null or sno is empty");
            }
            if (studentDao.queryStudentBySno(student.getSno()) != null) {
                throw new MyException("exception: student exists in the student list");
            }
            return studentDao.addStudent(student);
        }
    public int deleteStudent(String sno) throws FileNotFoundException, IOException, MyException {
        if (!Validation.validateStr(sno)) {
            throw new MyException("exception:sno is empty");
        }
        if (!gradeDao.queryGradesBySno(sno).isEmpty()) {
            int i = studentDao.deleteStudent(sno);
            if (i > 0) {
                int j = gradeDao.deleteGradesBySno(sno);
                System.out.println("cascading delete grade for " + j + " lines");
            }
            return i;
        }
        return studentDao.deleteStudent(sno);
    }

    public int updateStudent(String oldSno, Student newStudent) throws FileNotFoundException, IOException, MyException {
        if (!Validation.validateStr(oldSno)) {
            throw new MyException("exception: oldSno is empty");
        }
        if (newStudent == null || !Validation.validateStr(newStudent.getSno())) {
            throw new MyException("exception: newStudent is null or its sno is empty");
        }
        if (studentDao.queryStudentBySno(oldSno)==null){
            throw new MyException("exception: oldSno does not exist in the student list");
        }
        if(studentDao.exist(newStudent.getSno())){
            throw new MyException("exception: newStudent sno exists in the student list");
        }
        if(!gradeDao.queryGradesBySno(oldSno).isEmpty()){
            int i = studentDao.updateStudent(oldSno, newStudent);
            if(i>0){
                int j=gradeDao.updateGradesBySno(oldSno, newStudent);
                System.out.println("cascading update grade for " + j + "lines");
            }
            return i;
        }
        return studentDao.updateStudent(oldSno, newStudent);
    }
    public Student queryStudent(String sno){
        if(!Validation.validateStr(sno)){
            return null;
        }
        return studentDao.queryStudentBySno(sno);
    }
}