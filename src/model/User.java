package model;


// descp 系统管理人员
public class User {

    public String username;	//用户名
    public String password;	//密码

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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