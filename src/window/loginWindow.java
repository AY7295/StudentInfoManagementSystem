package window;

import model.Models;
import model.User;
import model.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class loginWindow {
    Models models;

    public loginWindow(Models ms){
        this.models = ms;
    }

    public void displayWindow(){
        loginFrame lF = new loginFrame(models);
        lF.ShowFrame();
    }

}

class loginFrame extends JFrame {
    JLabel title ;
    TextField id ;
    TextField pass;
    Button login;
    Models models;

    loginFrame(Models ms) {
        this.models = ms;
    }

    public void ShowFrame() {
        setTitle("Login");

        title = new JLabel("StudentInfoSystem");
        id = new TextField(20);
        pass = new TextField(20);
        login = new Button("Login");

        login.addActionListener(new LoginAction(this, models));

        setLayout(new FlowLayout());
        add(title);
        add(id);
        add(pass);
        add(login);
        setSize(300, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

class LoginAction implements ActionListener {
    loginFrame loginFrame;
    Models models;

    public LoginAction(loginFrame loginFrame, Models ms) {
        this.loginFrame = loginFrame;
        this.models = ms;
    }

    public void actionPerformed(ActionEvent e) {
        var u  = new User(loginFrame.id.getText(), loginFrame.pass.getText());
       switch (util.isRightUser(models.users , u)) {
           case -1 ->   JOptionPane.showMessageDialog(null, "User not found","Error", JOptionPane.ERROR_MESSAGE);
           case 0 ->    JOptionPane.showMessageDialog(null, "Password is wrong","Error", JOptionPane.ERROR_MESSAGE);
           case 1 -> {
                //JOptionPane.showMessageDialog(null, "Login Successful","Success", JOptionPane.INFORMATION_MESSAGE);
                // todo set the dialog auto close

                var ds = new studentInfoWindow(models);
                ds.displayWindow();
           }
       }
    }
}
