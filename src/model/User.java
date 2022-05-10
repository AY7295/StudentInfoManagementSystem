package model;


// descp 系统管理人员
public class User {

    public String id;	//用户名
    public String pass;	//密码

    public User(String id, String pass) {
        this.id = id;
        this.pass = pass;
    }

    //tip 待进一步开发
    public boolean isLogin = false;	//用户是否登陆
    public boolean getIsLogin() {
        return isLogin;
    }
    public void setIsLogin() {
        this.isLogin = !this.isLogin;
    }

}