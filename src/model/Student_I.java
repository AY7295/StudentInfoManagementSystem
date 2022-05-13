package model;


import java.util.Objects;

//descp 本科生类
public class Student_I extends Student {
    // tip 特有属性: 专业

    public String major; //专业

    // descp 构造方法
    public Student_I() {
    }
    public Student_I(String info) {

        if (Objects.equals(info, "")) {
            return;
        }

        //descp 以免出现空指针异常
        String[] infos = info.split(",");
        if (infos.length != 7) {
            return;
        }

        this.id = infos[0];
        this.name = infos[1];
        this.age = Integer.parseInt(infos[2]);
        this.classNum = infos[3];
        this.scores = StrScoreToIntLists(infos[4]);
        this.address.setAddress(infos[5]);
        this.major = infos[6];

    }

    public Student_I(String id, String name, int age, String classNum, int[] scores, String address ,String major) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.classNum = classNum;
        this.scores = scores;
        this.address.setAddress(address);
        this.major = major;

    }


    //descp 返回文件存储格式 tip 1111121377,AY,20,2113,100 100 100 100 100,江西 南昌 南京东路 666,计算机二类
    public String getInfo() {
        return this.id + "," + this.name + "," + this.age + "," + this.classNum + "," + getScoreStr() + "," + this.address.getAddress() + "," + this.major;
    }

    // descp 给JTable使用
    public String[] getRowInfo() {
        return (this.id+" "+this.name+" "+this.age+" "+this.classNum+" "+getScoreList()+" "+this.address.getAddressStr()+" "+this.major).split(" ");
    }

    // descp 设置和更新学生信息
    public boolean setInfo(String id, String name, int age, String classNum,int[] scores, String address, String major){

        if (util.isScoresRight(scores, 5)){ // tip 判断成绩是否合法
            return false;
        }

        this.id = id;
        this.name = name;
        this.age = age;
        this.classNum = classNum;
        this.scores = scores;
        this.address.setAddress(address);
        this.major = major;

        return true;
    }

}
