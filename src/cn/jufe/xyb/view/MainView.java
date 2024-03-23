package cn.jufe.xyb.view;

import java.util.Scanner;

public class MainView {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        while (true){
            System.out.println("our program has started, please select an operation");
            System.out.println("1. Student operation; 2. Course operation; " + "3. Grade operation; 4. exit");
            char selection =  input.nextLine().charAt(0);
            switch (selection){
                case '1':
                    new StudentView().enter();
                    break;
                case '2':
                    new CourseView().enter();
                    break;
                case '3':
                    new GradeView().enter();
                    break;
                case '4':
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}
