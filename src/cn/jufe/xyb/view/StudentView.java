package cn.jufe.xyb.view;

import cn.jufe.xyb.biz.StudentBiz;
import cn.jufe.xyb.dao.StudentDao;
import cn.jufe.xyb.domain.Student;
import cn.jufe.xyb.exception.MyException;
import cn.jufe.util.DisplayUtil;
import java.io.IOException;
import java.util.Scanner;

public class StudentView {

    private static Scanner input = new Scanner(System.in);
    private StudentBiz studentBiz;

    public StudentView() {
        try {
            studentBiz = new StudentBiz();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void enter() {
        while (true) {
            System.out.println("please select:\n" + " 1.add a student; 2. delete a student; 3. update a student " + "4. query a student; 5. query all; 6. return");
            char selection = input.nextLine().charAt(0);
            switch (selection) {
                case '1':
                    doAddStudentTask();
                    break;
                    case '2':
                        doDeleteStudentTask();
                        break;
                    case '3':
                        doUpdateStudentTask();
                        break;
                    case '4':
                        doQueryStudentTask();
                        break;
                    case '5':
                        doQueryAllStudentTask();
                        break;
                    case '6':
                        return;
                    default:
                        System.out.println("your input has an error");
                        break;
                }
            }
        }
        private void doQueryAllStudentTask(){
        displayStudentList();
        }
        private void doQueryStudentTask(){
            System.out.println("Please input sno: ");
            String sno = input.nextLine();
            Student student = studentBiz.queryStudent(sno);
            if (student != null){
                System.out.println(student);
            } else {
                System.out.println("query student failure!");
            }
    }
    private void doUpdateStudentTask(){
        System.out.println("Please input sno needed to update: ");
        String oldsno = input.nextLine();
        System.out.println("Please input new information: sno, sname, sage");
        String newSno = input.next();
        String newSname = input.next();
        int newSage = input.nextInt();
        input.nextLine();
        Student newStudent = new Student(newSno, newSname, newSage);
        try {
            if (studentBiz.updateStudent(oldsno, newStudent) > 0){
                System.out.println("Successful for updating a student");
                displayStudentList();
            } else {
                System.out.println("Update student failure!");
            }
        } catch (IOException | MyException e){
            System.out.println(e.getLocalizedMessage());
        }
    }
    private void doDeleteStudentTask(){
        System.out.println("Please input sno: ");
        String sno = input.nextLine();
        try {
            if (studentBiz.deleteStudent(sno) > 0){
                System.out.println("successful for deleting a student");
                displayStudentList();
            } else {
                System.out.println("delete student failure!");
            }
        } catch (IOException | MyException e){
            System.out.println(e.getLocalizedMessage());
        }
    }
    private void displayStudentList(){
        System.out.println("----------student list----------");
        DisplayUtil.displayList(StudentDao.studentList);
        System.out.println("----------end----------");
    }
    private void doAddStudentTask(){
        System.out.println("Please input sno, sname, and sage: ");
        String sno = input.nextLine();
        String sname = input.nextLine();
        int sage = input.nextInt();
        input.nextLine();
        try {
            if (studentBiz.addStudent(new Student(sno, sname, sage)) > 0){
                System.out.println("successful fr adding a student");
                displayStudentList();
            } else {
                System.out.println("add student failure!");
            }
        } catch (IOException | MyException e){
            System.out.println(e.getLocalizedMessage());
        }
    }
}