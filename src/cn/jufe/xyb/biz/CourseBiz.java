package cn.jufe.xyb.biz;

import cn.jufe.util.Validation;
import cn.jufe.xyb.dao.CourseDao;
import cn.jufe.xyb.dao.GradeDao;
import cn.jufe.xyb.domain.Course;
import cn.jufe.xyb.exception.MyException;
import java.io.FileNotFoundException;
import java.io.IOException;


public class CourseBiz {
        private CourseDao courseDao;
        private GradeDao gradeDao;

        public CourseBiz() throws ClassNotFoundException, IOException {
            courseDao = new CourseDao();
            gradeDao = new GradeDao();
        }

        public int addCourse(Course course) throws FileNotFoundException, IOException, MyException, ClassNotFoundException {
            if (course == null || !Validation.validateStr(course.getCno())) {
                throw new MyException("exception: course is null or cno is empty");
            }
            if (courseDao.queryCourseByCno(course.getCno()) != null) {
                throw new MyException("exception: course exists in the course list.");
            }
            return courseDao.addCourse(course);
        }

        public int deleteCourse(String cno) throws FileNotFoundException, IOException, MyException, ClassNotFoundException {
            if (!Validation.validateStr(cno)) {
                throw new MyException("exception: cno is empty");
            }
            if (!gradeDao.queryGradesByCno(cno).isEmpty()) {
                int i = courseDao.deleteCourse(cno);
                if (i > 0) {
                    int j = gradeDao.deleteGradesByCno(cno);
                    System.out.println("cascading delete grade for" + j + "lines");
                }
                return i;
            }
            return courseDao.deleteCourse(cno);
        }
    public int updateCourse(String oldCno, Course newCourse) throws FileNotFoundException, IOException, MyException, ClassNotFoundException {
        if (!Validation.validateStr(oldCno)) {
            throw new MyException("exception: oldCno is empty");
        }
        if(newCourse == null|| !Validation.validateStr(newCourse.getCno())){
            throw new MyException("exception: newCourse is null or its cno is empty");
        }
        if(courseDao.queryCourseByCno(oldCno) == null){
            throw new MyException("exception: oldCno does not exist in the course list");
        }
        if(!gradeDao.queryGradesByCno(oldCno).isEmpty()){
            int i = courseDao.updateCourse(oldCno, newCourse);
            if(i>0){
                int j= gradeDao.updateGradesByCno(oldCno, newCourse);
                System.out.println("cascading update grade for" + j + "lines");
            }
            return i;
        }
        return courseDao.updateCourse(oldCno,newCourse);

    }
    public Course queryCourse(String cno) throws MyException{
        if(!Validation.validateStr(cno)){
            throw new MyException("exception: cno is empty");
        }
        return courseDao.queryCourseByCno(cno);
    }
}
