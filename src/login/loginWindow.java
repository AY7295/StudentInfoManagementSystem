package login;

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
        lF.setSize(400, 400);
        lF.setLocationRelativeTo(null);// descp set the window in the middle of the screen
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
        setTitle("StudentInfoSystem Login Interface");

        var p0 = new JPanel();
        title = new JLabel("StudentInfoSystem");
        title.setFont(new Font("Serif", Font.BOLD, 30));
        p0.add(title,BorderLayout.CENTER);

        var p1 = new JPanel();
        id = new TextField(20);
        p1.add(new JLabel("Username: "), BorderLayout.WEST);
        p1.add(id, BorderLayout.CENTER);
        p1.setSize(200,50);

        var p2 = new JPanel();
        pass = new TextField(20);
        p2.add(new JLabel("Password: "), BorderLayout.WEST);
        p2.add(pass, BorderLayout.EAST);
        p2.setSize(200,50);



        login = new Button("Login");
        login.setFont(new Font("Serif", Font.BOLD, 15));
        login.setSize(50, 50);
        login.addActionListener(new LoginAction());
        var p3 = new JPanel();
        p3.add(login);

        setLayout(new GridLayout(4, 1));


        add(p0, BorderLayout.CENTER);
        add(p1, BorderLayout.CENTER);
        add(p2, BorderLayout.CENTER);
        add(p3, BorderLayout.CENTER);


        setVisible(true);
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private class LoginAction implements ActionListener { // descp 设置监听器
        public void actionPerformed(ActionEvent e) {

            var u  = new User(id.getText(), pass.getText());

            switch (util.isRightUser(models.users , u)) {
                case -1 ->   JOptionPane.showMessageDialog(null, "User not found","Error", JOptionPane.ERROR_MESSAGE);
                case 0 ->    JOptionPane.showMessageDialog(null, "Password is wrong","Error", JOptionPane.ERROR_MESSAGE);
                case 1 ->    new service(models).menu();
            }
        }
    }

}