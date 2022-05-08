package model;

import java.util.Objects;

public class Student {
    //tip 公共属性: 学号、姓名、年龄、班级、各科成绩、地址、
    public String id; //学号
    public String name; //姓名
    public int age; //年龄
    public int classNum; //班级
    public int[] scores; //各科成绩 假设为5科
    public Address address = new Address(); //地址
    public String getScoreStr() {
        return this.scores[0] + " " + this.scores[1] + " " + this.scores[2] + " " + this.scores[3] + " " + this.scores[4];
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


    static class Address {
        public String street;//街道
        public String city;//城市
        public String province; //省份
        public String houseNmu;//门牌号

        public void setAddress(String street, String city, String province, String houseNum) {

            this.street = street;
            this.city = city;
            this.province = province;
            this.houseNmu = houseNum;

        }

        // descp 返回文件存储格式
        public String getAddress() {
            return this.province + " " + this.city + " " + this.street + " " + this.houseNmu;
        }

        public String[] getAddressStringS() {

            var addressStringLists = new String[4];
            addressStringLists[0] = this.province;
            addressStringLists[1] = this.city;
            addressStringLists[2] = this.street;
            addressStringLists[3] = this.houseNmu;
            return addressStringLists;

        }

        //descp 给 学生类的构造方法使用
        public void setAddress(String address) {
            String[] addressArray = address.split(" ");

            // tip : 在这里将addressArray的长度补全为4; 避免后面数组越界
            for (int i = 0; addressArray.length < 4; i++) {
                addressArray[i] = "";
            }

            this.street = addressArray[2];
            this.city = addressArray[1];
            this.province = addressArray[0];
            this.houseNmu = addressArray[3];

        }
    }

}
