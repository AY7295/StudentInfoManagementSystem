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
            flushPanel();
        }
    };
    Action addStudentButtonAction = new AbstractAction("添加") {
        @Override
        public void actionPerformed(ActionEvent e) {


            flushPanel();
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

        // descp 组装 panel
        flushPanel();

    }


    private void flushPanel() {
        var s1 = new ArrayList<Student_I>();
        var s2 = new ArrayList<Student_II>();
        var str = Objects.requireNonNull(searchBy.getSelectedItem()).toString();
        var str1 = searchText.getText();
        var str2 = Objects.requireNonNull(sortBy.getSelectedItem()).toString();

        System.out.println(str + str1 + str2);

        JPanel p;
        if (Objects.requireNonNull(student.getSelectedItem()).toString().equals("研究生")) {
            s2 = find.Student_II(str, str1, str2, models.studentsII);
            p = fillPanelWithStudentII(s2);

        }

        s1 = find.Student_I(str, str1, str2, models.studentsI);
        p = fillPanelWithStudentI(s1);

        p.setVisible(true);
        add(p, BorderLayout.CENTER);
    }


    private JPanel fillPanelWithStudentI(ArrayList<Student_I> student) {
        var panel = new JPanel();
        System.out.println(student.size());

        for (var s : student) {
            panel.add(newRowI(s));
        }
        return panel;
    }

    private JPanel fillPanelWithStudentII(java.util.List<Student_II> student) {
        var panel = new JPanel();
        System.out.println(student.size());
        for (var s : student) {
            panel.add(newRowII(s));
        }
        return panel;
    }


    private JPanel newRowI(Student_I s) {

        var id = new JTextField(s.id);
        var name = new JTextField(s.name);
        var age = new JTextField(s.age);
        var classNum = new JTextField(s.classNum);
        var score0 = new TextField(s.scores[0]);
        var score1 = new TextField(s.scores[1]);
        var score2 = new TextField(s.scores[2]);
        var score3 = new TextField(s.scores[3]);
        var score4 = new TextField(s.scores[4]);
        var score5 = new TextField(util.totalScore(s.scores));
        var address = new TextField(util.getAddress(s.address));
        var major = new TextField(s.major);

        var jp = new JPanel();
        jp.add(id);
        jp.add(name);
        jp.add(age);
        jp.add(classNum);
        jp.add(score0);
        jp.add(score1);
        jp.add(score2);
        jp.add(score3);
        jp.add(score4);
        jp.add(address);
        jp.add(major);

        return jp;
    }

    private JPanel newRowII(Student_II s) {

        var id = new JTextField(s.id);
        var name = new JTextField(s.name);
        var age = new JTextField(s.age);
        var classNum = new JTextField(s.classNum);
        var score0 = new TextField(s.scores[0]);
        var score1 = new TextField(s.scores[1]);
        var score2 = new TextField(s.scores[2]);
        var score3 = new TextField(s.scores[3]);
        var score4 = new TextField(s.scores[4]);
        var score5 = new TextField(util.totalScore(s.scores));
        var address = new TextField(util.getAddress(s.address));
        var teacher = new TextField(s.teacher);
        var direction = new TextField(s.direction);

        var jp = new JPanel();
        jp.add(id);
        jp.add(name);
        jp.add(age);
        jp.add(classNum);
        jp.add(score0);
        jp.add(score1);
        jp.add(score2);
        jp.add(score3);
        jp.add(score4);
        jp.add(address);
        jp.add(teacher);
        jp.add(direction);

        return jp;
    }

}
