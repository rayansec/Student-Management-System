package cn.jufe.xyb.view;

import cn.jufe.util.DisplayUtil;
import cn.jufe.xyb.biz.GradeBiz;
import cn.jufe.xyb.dao.GradeDao;
import cn.jufe.xyb.domain.Grade;
import cn.jufe.xyb.exception.MyException;

import java.io.IOException;
import java.util.Scanner;

public class GradeView {
    private static Scanner input = new Scanner(System.in);
    private GradeBiz gradeBiz;
    public GradeView(){
        try {
            gradeBiz = new GradeBiz();
        } catch (ClassNotFoundException | IOException e){
            System.out.println(e.getLocalizedMessage());
        }
    }
    public void enter(){
        while (true){
            System.out.println("Please select:\n " + "1.add a grade; 2. delete a grade; 3. update a grade;" + "3. query a grade; 5. query all; 6. return");
            char selection = input.nextLine().charAt(0);
            switch (selection){
                case '1':
                    doAddGradeTask();
                    break;
                case '2':
                     doDeleteGradeTask();
                     break;
                case '3':
                     doUpdateGradeTask();
                     break;
                case '4':
                    doQueryGradeTask();
                    break;
                case '5':
                    doQueryAllGradeTask();
                    break;
                case '6':
                    return;
                default:
                    System.out.println("your input has an error");
                    break;
            }
        }
    }
    private void doQueryAllGradeTask(){
        displayGradeList();
    }
    private void doQueryGradeTask(){
        System.out.println("Please input sno and cno of a grade: ");
        String sno = input.nextLine();
        String cno = input.nextLine();
        Grade grade;
        try {
            grade = gradeBiz.queryGrade(sno, cno);
            if (grade != null){
                System.out.println(grade);
            } else {
                System.out.println("query grade failure");
            }
        } catch (MyException e){
            e.printStackTrace();
        }
    }
    private void doDeleteGradeTask(){
        System.out.println("Please input sno and cno of a grade: ");
        String sno = input.nextLine();
        String cno = input.nextLine();
        Grade grade;
        try {
            grade = gradeBiz.queryGrade(sno, cno);
            if (grade != null){
                System.out.println(grade);
            } else {
                System.out.println("query grade failure");
            }
        } catch (MyException e){
            System.out.println(e.getLocalizedMessage());
        }
    }
    private void doUpdateGradeTask(){
        System.out.println("Please input sno and cno: ");
        String sno = input.nextLine();
        String cno = input.nextLine();
        try {
            if (gradeBiz.deleteGardeBySnoCno(sno, cno) > 0){
                System.out.println("successful for deleting a course");
                displayGradeList();
            } else {
                System.out.println("delete grade failure!");
            }
        } catch (IOException | MyException e){
            System.out.println(e.getLocalizedMessage());
        }
    }
    private void displayGradeList(){
        System.out.println("----------grade list----------");
        DisplayUtil.displayList(GradeDao.gradeList);
        System.out.println("----------end----------");
    }
    private void doAddGradeTask(){
        System.out.println("please input sno, cno and score: ");
        String sno = input.nextLine();
        String cno = input.nextLine();
        int score = input.nextInt();
        input.nextLine();
        try {
            if (gradeBiz.addGrade(sno, cno, score) > 0){

                displayGradeList();
            } else {
                System.out.println("add grade failure!");
            }
        } catch (IOException | MyException e){
            System.out.println(e.getLocalizedMessage());
        }
    }
}