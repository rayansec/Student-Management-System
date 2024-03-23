package cn.jufe.xyb.dao;

import cn.jufe.util.PropertiesUtil;
import cn.jufe.util.SerializeUtil;
import cn.jufe.xyb.domain.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    public static List<Student> studentList = new ArrayList<>();
    private String filename = PropertiesUtil.getFileName("student-path");

    @SuppressWarnings("unchecked")
    public StudentDao() throws IOException, ClassNotFoundException{
        File file = new File(filename);
        if (!file.exists()){
            file.createNewFile();
        }
        SerializeUtil.setFilename(filename);
        Object object = SerializeUtil.deSerialize();
        if (object != null){
            studentList = (List<Student>) object;
        }
    }
    public int addStudent(Student student) throws FileNotFoundException, IOException{
        if (studentList.add(student)){
            saveStudentList();
            return 1;
        }
        return 0;
    }
    public int deleteStudent(String sno) throws FileNotFoundException, IOException{
        if (studentList.remove(queryStudentBySno(sno))){
            saveStudentList();
            return 1;
        }
        return 0;

    }
    public int updateStudent(String oldSno, Student newStudent) throws FileNotFoundException, IOException{
        for (int i = 0; i < studentList.size(); i++){
            Student student = studentList.get(i);
            if (oldSno.equals(student.getSno())){
                student.setSno(newStudent.getSno());
                student.setSname(newStudent.getSname());
                student.setSage(newStudent.getSage());
                saveStudentList();
                return 1;
            }
        }
        return 0;

    }
    public Student queryStudentBySno(String sno){
        for (Student student : studentList){
            if (student.getSno().equals(sno)){
                return student;
            }
        }
        return null;
    }
    public boolean exist(String sno){
        return queryStudentBySno(sno) != null;
    }
    private void saveStudentList() throws FileNotFoundException, IOException{
        SerializeUtil.setFilename(filename);
        SerializeUtil.serialize(studentList);
    }

}
