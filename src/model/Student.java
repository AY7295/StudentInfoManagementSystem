package model;

import java.util.Objects;

public class Student {
    //tip 共有属性: 学号、姓名、年龄、班级、各科成绩、地址、
    public String id; //学号
    public String name; //姓名
    public int age; //年龄
    public String classNum; //班级
    public int[] scores; //各科成绩 假设为5科
    public Address address = new Address(); //地址
    public String getScoreStr() {
        return this.scores[0] + " " + this.scores[1] + " " + this.scores[2] + " " + this.scores[3] + " " + this.scores[4];
    }

    public String getScoreList() {
        int total = 0;
        for (int score : scores) {
            total += score;
        }

        return getScoreStr()+" "+total;
    }

    public boolean setScore(int[] nums){
        if (util.isScoresRight(scores, 5)){ // tip 判断成绩是否合法
            return false;
        }
        this.scores = nums;
        return true;
    }

    //descp 给子类构造方法 public Student_(String info) { 使用
    public int[] StrScoreToIntLists(String score) {
        var scoreList = score.split(" ");
        var intList = new int[5];

        // tip : 在这里将Array的长度补全为5; 避免后面数组越界
        for (int i = 0; scoreList.length >i; i++) {
            if (Objects.equals(scoreList[i], "")){
                intList[i] = 0;
            }
            intList[i] = Integer.parseInt(scoreList[i]);

        }

        return intList;
    }

}

