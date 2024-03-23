package cn.jufe.xyb.dao;

import cn.jufe.util.PropertiesUtil;
import cn.jufe.util.SerializeUtil;
import cn.jufe.xyb.domain.Course;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseDao {
    public static List<Course> courseList = new ArrayList<>();
    private String filename = PropertiesUtil.getFileName("course-path");

    @SuppressWarnings("unchecked")
    public CourseDao() throws IOException, ClassNotFoundException {
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
        }
        SerializeUtil.setFilename(filename);
        Object object = SerializeUtil.deSerialize();
        if (object != null) {
            courseList = (List<Course>) object;
        }
    }

    public int addCourse(Course course) throws IOException, ClassNotFoundException {
        if (courseList.add(course)) {
            saveCourseList();
            return 1;
        }
        return 0;
    }

    public int deleteCourse(String cno) throws IOException, ClassNotFoundException {
        if (courseList.remove(queryCourseByCno(cno))) {
            saveCourseList();
            return 1;
        }
        return 0;
    }

    public int updateCourse(String onldCno, Course newCourse) throws IOException, ClassNotFoundException {
        for (int i = 0; i < courseList.size(); i++) {
            Course course = courseList.get(i);
            if (onldCno.equals(course.getCno())) {
                course.setCno(newCourse.getCno());
                course.setCname(newCourse.getCname());
                course.setCcredit(newCourse.getCcredit());
                saveCourseList();
                return 1;
            }
        }
        return 0;
    }

    public Course queryCourseByCno(String cno) {
        for (Course course : courseList) {
            if (course.getCno().equals(cno)) {
                return course;
            }
        }
        return null;
    }

    public boolean exist(String cno) {
        return queryCourseByCno(cno) != null;
    }
    private void saveCourseList() throws FileNotFoundException, IOException{
        SerializeUtil.setFilename(filename);
        SerializeUtil.serialize(courseList);
    }
}