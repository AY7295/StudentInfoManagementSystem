package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Models {
    public String[] paths = new String[]{"src/file/user.txt", "src/file/I_student.txt", "src/file/II_student.txt"};

    public List<Student_I> studentsI = new ArrayList<>();
    public List<Student_II> studentsII = new ArrayList<>();
    public User[] users = new User[0];


    public Models() {

        try {
            this.users = InitUsers(paths[0]);
            this.studentsI  = InitStudent_I(paths[1]);
            this.studentsII = InitStudent_II(paths[2]);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //  descp  更新List内容, 并保存到文件
    public boolean UpdateStuI(Student_I student_I, int b) throws IOException {
        if (student_I == null) {
            return false;
        }
        switch (b) {
            case -1 -> this.studentsI.removeIf(student -> student.getInfo().equals(student_I.getInfo()));
            case 0 -> {
                for (Student_I student : this.studentsI) {
                    if (Objects.equals(student.id, student_I.id)){
                        this.studentsI.set(this.studentsI.indexOf(student), student_I);
                        break;
                    }
                }
            }
            case 1 -> this.studentsI.add(student_I);
            default -> {
                return false;
            }
        }
        return saveStuI();
    }
    public boolean UpdateStuII(Student_II student_II, int b) throws IOException {
        if (student_II == null) {
            return false;
        }

        switch (b) {
            case -1 -> this.studentsII.removeIf(student -> student.getInfo().equals(student_II.getInfo()));
            case 0 -> {
                for (Student_II student : this.studentsII) {
                    if (Objects.equals(student.id, student_II.id)){
                        this.studentsII.set(this.studentsII.indexOf(student), student_II);
                        break;
                    }
                }
            }
            case 1 -> this.studentsII.add(student_II);
            default -> {
                return false;
            }
        }
        return saveStuII();
    }

    //  descp  保存到文件
    public boolean saveStuI() throws IOException {

        File file = new File("src/file/I_student.txt");

        try (FileWriter writer = new FileWriter(file);
             BufferedWriter out = new BufferedWriter(writer)
        ) {

            for (Student_I student : this.studentsI) {
                out.write(student.getInfo() + "\r\n");
            }
            out.flush(); // 注:必须flush tip 把缓存区内容压入文件
        }

        return true;
    }
    public boolean saveStuII() throws IOException {

        File file = new File("src/file/II_student.txt");

        try (FileWriter writer = new FileWriter(file);
             BufferedWriter out = new BufferedWriter(writer)
        ) {
            for (Student_II student : studentsII) {
                out.write(student.getInfo() + "\r\n");
            }
            out.flush(); // 注:必须flush tip 把缓存区内容压入文件
        }

        return true;
    }

    //  descp  获取文件内容
    private static String[] linesToStringS(String path) throws IOException {
        return new BufferedReader(new FileReader(path)).lines().toArray(String[]::new);
    }

    //  descp  初始化model
    private static User[] InitUsers(String path) throws IOException {
        String[] ss = linesToStringS(path); // 注:文件中不能有空行, 否则会出现空指针异常

        var users = new User[ss.length];

        for (int i = 0; i < ss.length; i++) {
            String[] s = ss[i].split(" ");

            if (s.length != 2 || Objects.equals(s[0], "") || Objects.equals(s[1], "")) {
                continue;
            }
            users[i] = new User(s[0], s[1]);

        }

        return users;
    }
    private static List<Student_I> InitStudent_I(String path) throws IOException {

        var students = new ArrayList<Student_I>();

        for (String line : linesToStringS(path)) {
            students.add(new Student_I(line));
        }
        return students;
    }
    private static List<Student_II> InitStudent_II(String path) throws IOException {

        var students = new ArrayList<Student_II>();

        for (String line : linesToStringS(path)) {
            students.add(new Student_II(line));
        }

        return students;

    }
}
