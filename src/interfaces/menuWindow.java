package interfaces;

import model.Models;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class menuWindow {
    Models models;
    public menuWindow(Models ms) {
        this.models = ms;
    }

    public void displayMenu() {

        var frame = new menuFrame(models);

        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); //descp  center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.showFrame();
    }


}

class menuFrame extends JFrame {
    Models models;
    public menuFrame(Models ms) {
        this.models = ms;
    }


    // descp toolbar actions
    Action searchButtonAction = new AbstractAction("查询") {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };
    Action addStudentButtonAction = new AbstractAction("添加") {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };

    //tip 学生类别
    JComboBox<String> student = new JComboBox<>();

    //tip 查询条件
    JComboBox<String> searchBy = new JComboBox<>();

    //tip 排序条件
    JComboBox<String> sortBy = new JComboBox<>();

    //tip 查询按钮
    JButton searchButton = new JButton(searchButtonAction);

    //tip 增加学生按钮
    JButton addStudentButton = new JButton(addStudentButtonAction);


    //tip 总人数
    JLabel totalNum = new JLabel();
    JTextField totalNumText = new JTextField();

    JToolBar tB = new JToolBar("toolBar", SwingConstants.HORIZONTAL);


    public void showFrame() {
        // 组装 toolbar
        // descp 学生类别
        student.addItem("本科生");
        student.addItem("研究生");
        tB.add(student);

        // descp 查询条件
        searchBy.addItem("学号");
        searchBy.addItem("姓名");
        searchBy.addItem("班级");
        tB.add(searchBy);

        tB.add(searchButton);

        //descp 排序条件
        sortBy.addItem("学号");
        sortBy.addItem("总成绩");
        sortBy.addItem("成绩1");
        sortBy.addItem("成绩2");
        sortBy.addItem("成绩3");
        sortBy.addItem("成绩4");
        sortBy.addItem("成绩5");
        tB.add(sortBy);

        totalNum.add(totalNumText);
        tB.add(totalNum);

        tB.add(addStudentButton);

        tB.setFloatable(true);
        add(tB, BorderLayout.NORTH);

        // 组装 panel


    }

}
