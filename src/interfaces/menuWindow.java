package interfaces;

import data.find;
import model.Models;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
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
    //tip 学生类别
    JComboBox<String> student = new JComboBox<>();
    //tip 查询条件
    JComboBox<String> searchBy = new JComboBox<>();
    JTextField searchText = new JTextField(10);
    //tip 排序条件
    JComboBox<String> sortBy = new JComboBox<>();
    //tip 添加表
    JToolBar tB = new JToolBar("toolBar", SwingConstants.HORIZONTAL);
    JTable table = new JTable();
    String[] IColumnNames = {"学号", "姓名", "年龄", "班级", "成绩1", "成绩2", "成绩3", "成绩4", "成绩5", "总成绩", "地址", "专业"};
    String[] IIColumnNames = {"学号", "姓名", "年龄", "班级", "成绩1", "成绩2", "成绩3", "成绩4", "成绩5", "总成绩", "地址", "导师", "方向"};
    //tip 添加 学生人数
    JPanel bottomPanel = new JPanel();
    JLabel TotalStudentNum = new JLabel("学生总人数: ");
    JLabel IStudentNum = new JLabel("本科生学生人数: ");
    JLabel IIStudentNum = new JLabel("研究生人数: ");
    // descp toolbar actions
    Action searchButtonAction = new AbstractAction("查询") {
        @Override
        public void actionPerformed(ActionEvent e) {
            flushPanel();
        }
    };
    //tip 查询按钮
    JButton searchButton = new JButton(searchButtonAction);
    Action addStudentButtonAction = new AbstractAction("添加") {
        @Override
        public void actionPerformed(ActionEvent e) {


            flushPanel();
        }
    };
    //tip 增加学生按钮
    JButton addStudentButton = new JButton(addStudentButtonAction);
    public menuFrame(Models ms) {
        this.models = ms;
    }

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

        searchButton.setBackground(Color.CYAN);
        tB.add(searchButton);
        addStudentButton.setBackground(Color.green);
        tB.add(addStudentButton);

        tB.setFloatable(true);
        add(tB, BorderLayout.NORTH);

        // descp 组装 table
        add(new JScrollPane(this.table), BorderLayout.CENTER);

        // descp 组装 学生人数
        bottomPanel.add(TotalStudentNum);
        bottomPanel.add(IStudentNum);
        bottomPanel.add(IIStudentNum);
        bottomPanel.setLayout(new FlowLayout());
        add(bottomPanel, BorderLayout.SOUTH);

        flushPanel();

    }


    private void flushPanel() {
        var data = new String[][]{};
        var columnNames = new String[]{};

        var str = Objects.requireNonNull(searchBy.getSelectedItem()).toString();
        var str1 = searchText.getText();
        var str2 = Objects.requireNonNull(sortBy.getSelectedItem()).toString();

        if (Objects.equals(student.getSelectedItem(), "本科生")) {

            data = find.Student_I(str, str1, str2, models.studentsI);
            columnNames = IColumnNames;

        } else {
            data = find.Student_II(str, str1, str2, models.studentsII);
            columnNames = IIColumnNames;

        }

        this.table.setModel(new DefaultTableModel(data, columnNames));

        TotalStudentNum.setText("学生总人数: " + (models.studentsI.size() + models.studentsII.size()) + "人;    ");
        IStudentNum.setText("本科生学生人数: " + models.studentsI.size() + "人;   ");
        IIStudentNum.setText("研究生人数: " + models.studentsII.size() + "人;");

    }
}
