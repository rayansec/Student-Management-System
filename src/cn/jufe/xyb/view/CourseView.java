package cn.jufe.xyb.view;

import cn.jufe.xyb.biz.CourseBiz;
import cn.jufe.xyb.dao.CourseDao;
import cn.jufe.xyb.domain.Course;
import cn.jufe.xyb.exception.MyException;
import cn.jufe.util.DisplayUtil;
import java.io.IOException;
import java.util.Scanner;

public class CourseView {
        private static Scanner input = new Scanner(System.in);
        private CourseBiz courseBiz;

        public CourseView(){
            try{
                courseBiz = new CourseBiz();
            } catch(ClassNotFoundException | IOException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        public void enter(){
            while(true){
                System.out.println("please select:\n" + "1.add a course; 2. delete a course; 3. update a course; " + "4. query a course; 5. query all; 6. return");
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
            displayCourseList();
        }
        private void doQueryStudentTask(){
            System.out.println("please input cno:");
            String cno = input.nextLine();
            Course course;
            try{
                course = courseBiz.queryCourse(cno);
                if(course != null){
                    System.out.println(course);
                }else {
                    System.out.println(cno + " not exists, operation failure!");
                }
            }catch (MyException e){
                System.out.println(e.getLocalizedMessage());
            }
        }
        private void doUpdateStudentTask(){
            System.out.println("please input cno needed to update:");
            String oldCno = input.nextLine();
            System.out.println("please input new information: cno, cname, ccredit:");
            String newCno = input.next();
            String newCname = input.next();
            int newCcredit = input.nextInt();
            input.nextLine();
            Course newCourse = new Course(newCno, newCname, newCcredit);
            try{
                if(courseBiz.updateCourse(oldCno, newCourse) > 0){
                    System.out.println("successful for up updating a course");
                    displayCourseList();
                }else{
                    System.out.println("operation failure!");
                }
            }catch (MyException | IOException e){
                System.out.println(e.getLocalizedMessage());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        private void doDeleteStudentTask() {
            System.out.println("Please input cno: ");
            String cno = input.nextLine();
            try {
                if (courseBiz.deleteCourse(cno) > 0) {
                    System.out.println("successful for deleting a course");
                    displayCourseList();
                } else {
                    System.out.println("operation failure!");
                }
            } catch (MyException | IOException e) {
                System.out.println(e.getLocalizedMessage());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    private void displayCourseList(){
        System.out.println("----------------course list ------------------");
        DisplayUtil.displayList(CourseDao.courseList);
        System.out.println("--------------------end----------------");
    }
    private void doAddStudentTask(){
        System.out.println("please input cno, cname and ccredit");
        String cno = input.next();
        String cname = input.next();
        int ccredit = input.nextInt();
        input.nextLine();
        try{
            if(courseBiz.addCourse(new Course(cno, cname, ccredit)) >0){
                System.out.println("successful for adding a course");
                displayCourseList();
            }else{
                System.out.println("operation failure!");
            }
        }catch (IOException | MyException e){
            System.out.println(e.getLocalizedMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
