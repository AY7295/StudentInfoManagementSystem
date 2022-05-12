package interfaces;

import data.find;
import model.Models;
import model.Student_I;
import model.Student_II;
import model.util;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Objects;


public class menuWindow {
    Models models;

    public menuWindow(Models ms) {
        this.models = ms;
    }

    public void displayMenu() {

        var frame = new menuFrame(models);

        frame.setSize(500, 500);
        frame.setResizable(true);
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
            flushPanel(panel);
        }
    };
    Action addStudentButtonAction = new AbstractAction("添加") {
        @Override
        public void actionPerformed(ActionEvent e) {


            flushPanel(panel);
        }
    };

    //tip 学生类别
    JComboBox<String> student = new JComboBox<>();

    //tip 查询条件
    JComboBox<String> searchBy = new JComboBox<>();
    JTextField searchText = new JTextField(10);

    //tip 排序条件
    JComboBox<String> sortBy = new JComboBox<>();

    //tip 查询按钮
    JButton searchButton = new JButton(searchButtonAction);

    //tip 增加学生按钮
    JButton addStudentButton = new JButton(addStudentButtonAction);

    JToolBar tB = new JToolBar("toolBar", SwingConstants.HORIZONTAL);
    JPanel panel = new JPanel();


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

        // descp 查询文本框
        tB.add(searchText);


        //descp 排序条件
        sortBy.addItem("学号");
        sortBy.addItem("总成绩");
        sortBy.addItem("成绩1");
        sortBy.addItem("成绩2");
        sortBy.addItem("成绩3");
        sortBy.addItem("成绩4");
        sortBy.addItem("成绩5");
        tB.add(sortBy);

        tB.add(searchButton);
        tB.add(addStudentButton);

        tB.setFloatable(true);
        add(tB, BorderLayout.NORTH);

        // descp 组装 table
        flushPanel(panel);

    }


    private void flushPanel(JPanel p) {
        var s1 = new ArrayList<Student_I>();
        var s2 = new ArrayList<Student_II>();
        var str = Objects.requireNonNull(searchBy.getSelectedItem()).toString();
        var str1 = searchText.getText();
        var str2 = Objects.requireNonNull(sortBy.getSelectedItem()).toString();


        add(p, BorderLayout.CENTER);

    }


}
