package model;

import java.util.Objects;

//descp 研究生类
public class Student_II extends Student {
    //tip 特有属性: 导师、研究方向
    public String teacher; //导师
    public String direction; //研究方向


    //descp 构造方法
    public Student_II() {
    }

    public Student_II(String info) {

        if (Objects.equals(info, "")) {
            return;
        }

        //descp 以免出现空指针异常
        String[] infos = info.split(",");
        if (infos.length != 8) {
            return;
        }

        this.id = infos[0];
        this.name = infos[1];
        this.age = Integer.parseInt(infos[2]);
        this.classNum = infos[3];
        this.scores = StrScoreToIntLists(infos[4]);
        this.address.setAddress(infos[5]);
        this.teacher = infos[6];
        this.direction = infos[7];

    }

    public Student_II(String[] ss) {
        this.id = ss[0];
        this.name = ss[1];
        this.age = Integer.parseInt(ss[2]);
        this.classNum = ss[3];
        int[] is = new int[5];
        for (int i = 0; i < 5; i++) {
            is[i] = Integer.parseInt(ss[4 + i]);
        }
        this.scores = is;
        this.address.setAddress(ss[9]);
        this.teacher = ss[10];
        this.direction = ss[11];

    }

    //descp 返回文件存储格式 tip 1111121377,AY,20,2113,100 100 100 100 100,江西 南昌 南京东路 666,胡军,Java
    public String getInfo() {
        return this.id + "," + this.name + "," + this.age + "," + this.classNum + "," + getScoreStr() + "," + this.address.getAddress() + "," + this.teacher + "," + this.direction;
    }

    // descp 给JTable使用
    public String[] getRowInfo() {
        return (this.id + " " + this.name + " " + this.age + " " + this.classNum + " " + getScoreList() + " " + this.address.getAddressStr() + " " + this.teacher + " " + this.direction).split(" ");
    }

    // descp 设置和更新学生信息
    public boolean setInfo(String id, String name, int age, String classNum, int[] scores, String address, String teacher, String direction) {

        if (util.isScoresRight(scores, 5)) { // tip 判断成绩是否合法
            return false;
        }

        this.id = id;
        this.name = name;
        this.age = age;
        this.classNum = classNum;
        this.scores = scores;
        this.address.setAddress(address);
        this.teacher = teacher;
        this.direction = direction;

        return true;
    }

}
