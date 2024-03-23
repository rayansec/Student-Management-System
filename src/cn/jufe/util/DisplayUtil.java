package cn.jufe.util;

import java.util.List;

public class DisplayUtil {
    public static void displayList(List<?> studentList){
        for (Object object : studentList){
            System.out.println(object);
        }
    }
}
