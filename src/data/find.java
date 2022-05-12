package data;

import model.Models;
import model.Student_I;
import model.Student_II;

import java.util.ArrayList;
import java.util.List;

public class find {
    public static ArrayList<Student_I> Student_I(String str, String condition, String str1, List<Student_I> stu) {
        var s= new ArrayList<Student_I>(stu.size());
        switch (str) {
            case "学号" -> {
                    s = selectI.ById(condition,stu);
            }
            case "姓名" -> {
                    s = selectI.ByName(condition,stu);
            }
            case "班级" -> {
                    s = selectI.ByClassNum(condition,stu);
            }
        }
//        return sort.IBy(s, str1);
        return s;
    }


    public static ArrayList<Student_II> Student_II(String str, String condition, String str1, List<Student_II> stu) {
        var s= new ArrayList<Student_II>(stu.size());
        switch (str) {
            case "学号" -> {
                s = selectII.ById(condition,stu);
            }
            case "姓名" -> {
                s = selectII.ByName(condition,stu);
            }
            case "班级" -> {
                s = selectII.ByClassNum(condition,stu);
            }

        }
//        return sort.IIBy(s, str1);
        return s;
    }



}
