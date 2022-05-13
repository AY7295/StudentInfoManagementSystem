package data;

import model.Student_I;
import model.Student_II;

import java.util.ArrayList;
import java.util.List;

public class find {
    public static String[][] Student_I(String str, String condition, String str1, List<Student_I> stu) {
        var s = new ArrayList<Student_I>(stu.size());
        switch (str) {

            case "学号" -> s = selectI.ById(condition, stu);
            case "姓名" -> s = selectI.ByName(condition, stu);
            case "班级" -> s = selectI.ByClassNum(condition, stu);

        }
        var s1 = sort.IBy(s, str1);
//        var s1 = s;

        if (s1 == null) {
            return new String[][]{};    // descp 没有找到
        }
        var ss = new String[s1.size()][12];
        for (int i = 0; i < s1.size(); i++) {
            ss[i] = s1.get(i).getRowInfo();
        }

        return ss;
    }


    public static String[][] Student_II(String str, String condition, String str1, List<Student_II> stu) {
        var s = new ArrayList<Student_II>(stu.size());
        switch (str) {

            case "学号" -> s = selectII.ById(condition, stu);
            case "姓名" -> s = selectII.ByName(condition, stu);
            case "班级" -> s = selectII.ByClassNum(condition, stu);

        }
        var s1 = sort.IIBy(s, str1);
//        var s1 = s;

        if (s1 == null) {
            return new String[][]{};    // descp 没有找到
        }
        var ss = new String[s1.size()][13];
        for (int i = 0; i < s1.size(); i++) {
            ss[i] = s1.get(i).getRowInfo();
        }

        return ss;
    }

}
