package data;

import model.Student;
import model.Student_I;
import model.Student_II;

import java.util.ArrayList;
import java.util.Comparator;

public class sort {

    public static ArrayList<Student_I> IBy(ArrayList<Student_I> s, String condition) {
        switch (condition) {
            case "学号" -> s.sort(new SortById());
            case "总成绩" -> s.sort(new SortBySumScore());
            case "成绩1" -> s.sort(new SortByScore1());
            case "成绩2" -> s.sort(new SortByScore2());
            case "成绩3" -> s.sort(new SortByScore3());
            case "成绩4" -> s.sort(new SortByScore4());
            case "成绩5" -> s.sort(new SortByScore5());

        }

        return s;
    }

    public static ArrayList<Student_II> IIBy(ArrayList<Student_II> s, String condition) {
        switch (condition) {
            case "学号" -> s.sort(new SortById());
            case "总成绩" -> s.sort(new SortBySumScore());
            case "成绩1" -> s.sort(new SortByScore1());
            case "成绩2" -> s.sort(new SortByScore2());
            case "成绩3" -> s.sort(new SortByScore3());
            case "成绩4" -> s.sort(new SortByScore4());
            case "成绩5" -> s.sort(new SortByScore5());

        }

        return s;
    }
}


class SortById implements Comparator<Student> {
    public int compare(Student a, Student b) {
        return a.id.compareTo(b.id);
    }
}

class SortBySumScore implements Comparator<Student> {
    public int compare(Student a, Student b) {
        return a.SumScore() - b.SumScore();
    }
}

class SortByScore1 implements Comparator<Student> {
    public int compare(Student a, Student b) {
        return a.scores[0] - b.scores[0];
    }
}

class SortByScore2 implements Comparator<Student> {
    public int compare(Student a, Student b) {
        return a.scores[1] - b.scores[1];
    }
}

class SortByScore3 implements Comparator<Student> {
    public int compare(Student a, Student b) {
        return a.scores[2] - b.scores[2];
    }
}

class SortByScore4 implements Comparator<Student> {
    public int compare(Student a, Student b) {
        return a.scores[3] - b.scores[3];
    }
}

class SortByScore5 implements Comparator<Student> {
    public int compare(Student a, Student b) {
        return a.scores[4] - b.scores[4];
    }
}
