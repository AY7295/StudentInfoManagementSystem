package data;

import model.Student_I;

import java.util.ArrayList;
import java.util.List;

public class selectI {
    public static ArrayList<Student_I> ById(String condition , List<Student_I> students) {
        var result = new ArrayList<Student_I>();

        students.forEach(student -> {
            if (student.id.equals(condition)) {
                result.add(student);
            }
        });

        return result;
    }
    public static ArrayList<Student_I> ByName(String condition , List<Student_I> students) {
        var result = new ArrayList<Student_I>();

        students.forEach(student -> {
            if (student.name.equals(condition)) {
                result.add(student);
            }
        });

        return result;
    }

    public static ArrayList<Student_I> ByClassNum(String condition , List<Student_I> students) {
        var result = new ArrayList<Student_I>();

        students.forEach(student -> {
            if (student.classNum.equals(condition)) {
                result.add(student);
            }
        });

        return result;
    }
}

