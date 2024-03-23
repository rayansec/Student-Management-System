package cn.jufe.xyb.dao;

import cn.jufe.util.PropertiesUtil;
import cn.jufe.util.SerializeUtil;
import cn.jufe.xyb.domain.Course;
import cn.jufe.xyb.domain.Grade;
import cn.jufe.xyb.domain.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GradeDao {
    public static List<Grade> gradeList = new ArrayList<Grade>();
    private String filename = PropertiesUtil.getFileName("grade-path");

    @SuppressWarnings("unchecked")
    public GradeDao() throws IOException, ClassNotFoundException{
        File file = new File(filename);
        if (!file.exists()){
            file.createNewFile();
        }
        SerializeUtil.setFilename(filename);
        Object object = SerializeUtil.deSerialize();
        if (object != null){
            gradeList = (List<Grade>) object;
        }
    }
    public int addGrade(Grade grade) throws FileNotFoundException, IOException{
        if (gradeList.add(grade)){
            saveGradeList();
            return 1;
        }
        return 0;
    }
    public int deleteGrade(Grade grade) throws FileNotFoundException, IOException{
        if (gradeList.remove(grade)){
            saveGradeList();
            return 1;
        }
        return 0;
    }
    public int updateGradeBySnoCno(String sno, String cno, int newScore) throws FileNotFoundException, IOException{
        Grade grade = queryGradeBySnoCno(sno, cno);
        if (grade != null){
            grade.setScore(newScore);
            saveGradeList();
            return 1;
        }
        return 0;
    }
    public Grade queryGradeBySnoCno(String sno, String cno){
        for (Grade grade : gradeList){
            if (grade.getStudent().getSno().equals(sno) && grade.getCourse().getCno().equals(cno)){
                return grade;
            }
        }
        return null;
    }
    public List<Grade> queryGradesBySno(String sno){
        List<Grade> grades = new ArrayList<>();
        for (Grade grade : gradeList){
            if (grade.getStudent().getSno().equals(sno)){
                grades.add(grade);
            }
        }
        return grades;
    }
    public List<Grade> queryGradesByCno(String cno){
        List<Grade> grades = new ArrayList<>();
        for (Grade grade : gradeList){
            if (grade.getCourse().getCno().equals(cno)){
                grades.add(grade);
            }
        }
        return grades;
    }
    public List<Grade> queryAll(){
        return gradeList;
    }
    public int updateGradesBySno(String oldSno, Student newStudent) throws FileNotFoundException, IOException{
        int count = 0;
        for (Grade grade : gradeList){
            if (grade.getStudent().getSno().equals(oldSno)){
                grade.setStudent(newStudent);
                count++;
            }
        }
        if (count > 0){
            saveGradeList();
        }
        return count;
    }
    public int updateGradesByCno(String oldCno, Course newCourse) throws FileNotFoundException, IOException{
        int count  = 0;
        for (Grade grade : gradeList){
            if (grade.getCourse().getCno().equals(oldCno)){
                grade.setCourse(newCourse);
                count++;
            }
        }
        if (count > 0){
            saveGradeList();
        }
        return count;
    }
    public int deleteGradesBySno(String sno) throws FileNotFoundException, IOException{
        int count = 0;
        for (int i = 0; i < gradeList.size(); i++){
            if (gradeList.get(i).getStudent().getSno().equals(sno)){
                gradeList.remove(i);
                count++;
            }
        }
        if (count > 0){
            saveGradeList();
        }
        return count;
    }
    public int deleteGradesByCno(String cno) throws FileNotFoundException, IOException{
        int count = 0;
        for (int i = 0; i < gradeList.size(); i++) {
            if (gradeList.get(i).getCourse().getCno().equals(cno)) {
                gradeList.remove(i);
                count++;
            }
        }
        if (count > 0) {
            saveGradeList();
        }
        return count;
    }
    private void saveGradeList() throws FileNotFoundException, IOException {
        SerializeUtil.setFilename(filename);
        SerializeUtil.serialize(gradeList);
    }
}