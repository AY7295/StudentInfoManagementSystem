package data;

import model.Student_II;

import java.util.ArrayList;
import java.util.List;

public class selectII {
    public static ArrayList<Student_II> ById(String condition, List<Student_II> students) {
        var result = new ArrayList<Student_II>();

        students.forEach(student -> {
            if (student.id.equals(condition)) {
                result.add(student);
            }
        });

        return result;
    }

    public static ArrayList<Student_II> ByName(String condition, List<Student_II> students) {
        var result = new ArrayList<Student_II>();

        students.forEach(student -> {
            if (student.name.equals(condition)) {
                result.add(student);
            }
        });

        return result;
    }

    public static ArrayList<Student_II> ByClassNum(String condition, List<Student_II> students) {
        var result = new ArrayList<Student_II>();

        students.forEach(student -> {
            if (student.classNum.equals(condition)) {
                result.add(student);
            }
        });

        return result;
    }
}
