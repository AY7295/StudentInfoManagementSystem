package model;

class Address {
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
    public String getAddressStr() {
        return this.province + "," + this.city + "," + this.street + "," + this.houseNmu;
    }

    public String[] getAddressStringS() {

        var addressStringLists = new String[4];
        addressStringLists[0] = this.province;
        addressStringLists[1] = this.city;
        addressStringLists[2] = this.street;
        addressStringLists[3] = this.houseNmu;
        return addressStringLists;

    }

    //descp 给 学生类 的构造方法使用
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
