package interfaces;

import data.find;
import data.selectI;
import data.selectII;
import model.Models;
import model.Student_I;
import model.Student_II;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    JTextField searchText = new JTextField("2113", 10);

    //tip 排序条件
    JComboBox<String> sortBy = new JComboBox<>();

    //tip 添加toolbar
    JToolBar tB = new JToolBar("toolBar", SwingConstants.HORIZONTAL);


    ActionListener jPopupMenuListener = e -> {

        String action = e.getActionCommand();
        try {
            Info(action);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    };
    //tip 添加右击菜单
    JPopupMenu jPopupMenu = new JPopupMenu();
    ButtonGroup buttonGroup = new ButtonGroup();
    JButton update = new JButton("修改");
    JButton delete = new JButton("删除");

    Student_I student_i = new Student_I();
    Student_II student_ii = new Student_II();


    //tip 添加表
    JTable table = new JTable();
    MouseAdapter tableMouseListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) {

                // descp 组装右击菜单
                update.addActionListener(jPopupMenuListener);
                delete.addActionListener(jPopupMenuListener);
                buttonGroup.add(update);
                buttonGroup.add(delete);
                jPopupMenu.add(update);
                jPopupMenu.add(delete);

                int row = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(row, row);    // descp 选中行
                findObject(table.getValueAt(row, 0).toString()); // descp 查找 表格中选择对象

                jPopupMenu.show(table, e.getX(), e.getY());

            }
        }
    };
    String[] IColumnNames = {"学号", "姓名", "年龄", "班级", "成绩1", "成绩2", "成绩3", "成绩4", "成绩5", "总成绩", "地址", "专业"};
    String[] IIColumnNames = {"学号", "姓名", "年龄", "班级", "成绩1", "成绩2", "成绩3", "成绩4", "成绩5", "总成绩", "地址", "导师", "方向"};


    //tip 添加 学生人数的底部显示
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
        searchBy.addItem("班级");
        searchBy.addItem("学号");
        searchBy.addItem("姓名");
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

        //descp 添加功能按钮
        searchButton.setBackground(Color.CYAN);
        tB.add(searchButton);
        addStudentButton.setBackground(Color.green);
        tB.add(addStudentButton);

        tB.setFloatable(true);
        add(tB, BorderLayout.NORTH);


        // descp 组装 table
        this.table.add(jPopupMenu); // descp 右击菜单
        this.table.addMouseListener(tableMouseListener);
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

    public void Info(String acton) {
        if (acton.equals("修改")) {
            JOptionPane.showMessageDialog(null, "修改成功！");

        } else if (acton.equals("删除")) {
            JOptionPane.showMessageDialog(null, "删除成功！");
        }
    }

    private void findObject(String id) {
        if (Objects.equals(student.getSelectedItem(), "本科生")) {
            student_i = selectI.ById(id, models.studentsI).get(0);
        } else {
            student_ii = selectII.ById(id, models.studentsII).get(0);
        }

    }

    static class updateInfoDialog extends JDialog {


    }

    static class deleteInfoDialog extends JDialog {

    }

    static class addInfoDialog extends JDialog {

    }
}
