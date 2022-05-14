package interfaces;

import data.find;
import data.selectI;
import data.selectII;
import model.Models;
import model.Student_I;
import model.Student_II;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;
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
    static Student_I student_i = new Student_I();
    static Student_II student_ii = new Student_II();
    static Models models;
    //tip 学生类别
    JComboBox<String> student = new JComboBox<>();
    //tip 查询条件
    JComboBox<String> searchBy = new JComboBox<>();
    JTextField searchText = new JTextField("2113", 10);
    //tip 排序条件
    JComboBox<String> sortBy = new JComboBox<>();
    //tip 添加toolbar
    JToolBar tB = new JToolBar("toolBar", SwingConstants.HORIZONTAL);
    //tip 添加右击菜单
    JPopupMenu jPopupMenu = new JPopupMenu();
    ButtonGroup buttonGroup = new ButtonGroup();
    JButton updateInfo = new JButton("修改");
    JButton deleteInfo = new JButton("删除");
    JButton addInfo = new JButton("添加");
    //tip 添加表
    JTable table = new JTable();
    String[] IColumnNames = {"学号", "姓名", "年龄", "班级", "成绩1", "成绩2", "成绩3", "成绩4", "成绩5", "总成绩", "地址", "专业"};
    String[] IIColumnNames = {"学号", "姓名", "年龄", "班级", "成绩1", "成绩2", "成绩3", "成绩4", "成绩5", "总成绩", "地址", "导师", "方向"};

    //tip 添加 学生人数的底部显示
    JPanel bottomPanel = new JPanel();
    JLabel TotalStudentNum = new JLabel("学生总人数: ");
    JLabel IStudentNum = new JLabel("本科生学生人数: ");
    JLabel IIStudentNum = new JLabel("研究生人数: ");


    ActionListener jPopupMenuListener = e -> {

        String action = e.getActionCommand();
        try {
            Info(action);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    };
    MouseAdapter tableMouseListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) {

                // descp 组装右击菜单
                updateInfo.setBackground(Color.ORANGE);
                deleteInfo.setBackground(Color.RED);
                addInfo.setBackground(Color.GREEN);
                updateInfo.addActionListener(jPopupMenuListener);
                deleteInfo.addActionListener(jPopupMenuListener);
                addInfo.addActionListener(jPopupMenuListener);
                buttonGroup.add(updateInfo);
                buttonGroup.add(deleteInfo);
                buttonGroup.add(addInfo);
                jPopupMenu.add(updateInfo);
                jPopupMenu.add(deleteInfo);
                jPopupMenu.add(addInfo);

                int row = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(row, row);    // descp 选中行
                findObject(table.getValueAt(row, 0).toString()); // descp 查找 表格中选择对象

                jPopupMenu.show(table, e.getX(), e.getY());

            }
        }
    };

    // descp toolbar actions
    Action searchButtonAction = new AbstractAction("查询") {
        @Override
        public void actionPerformed(ActionEvent e) {
            flushTable();
        }
    };
    //tip 查询按钮
    JButton searchButton = new JButton(searchButtonAction);


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

        flushTable();

    }


    private void flushTable() {
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

        switch (acton) {

            case "修改" -> new dialogWindow().updateInfo();
            case "删除" -> new dialogWindow().deleteInfo();
            case "添加" -> new dialogWindow().addInfo();

        }
        flushTable();
    }

    private void findObject(String id) {
        if (Objects.equals(student.getSelectedItem(), "本科生")) {
            student_i = selectI.ById(id, models.studentsI).get(0);
            student_ii = null;
        } else {
            student_ii = selectII.ById(id, models.studentsII).get(0);
            student_i = null;
        }

    }

    static class dialogWindow extends JDialog {
        JLabel title;
        JTable table = new JTable();
        JButton doIt = new JButton("确定");
        JButton cansel = new JButton("取消");
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();

        String[] cloumnNames = new String[]{"", ""};
        String[] rowI = {"学号", "姓名", "年龄", "班级", "成绩1", "成绩2", "成绩3", "成绩4", "成绩5", "总成绩", "地址", "专业"};
        String[] rowII = {"学号", "姓名", "年龄", "班级", "成绩1", "成绩2", "成绩3", "成绩4", "成绩5", "总成绩", "地址", "导师", "方向"};

        public void updateInfo() {
            title = new JLabel("修改学生信息");
            title.setFont(new Font("宋体", Font.BOLD, 20));


            if (Objects.equals(student_i, null)) {
                this.table.setModel(tableModelII());
            } else {
                this.table.setModel(tableModelI());
            }


            r.setHorizontalAlignment(JLabel.CENTER);
            r.setVerticalAlignment(JLabel.CENTER);
            this.table.setDefaultRenderer(Object.class, r);

            setLayout(new BorderLayout());
            add(title, BorderLayout.NORTH);
            add(table, BorderLayout.CENTER);
            var jp = new JPanel();
            cansel.addActionListener(e -> dispose());
            doIt.addActionListener(new okAction(getColumn(), 0));
            jp.add(cansel);
            jp.add(doIt);
            add(jp, BorderLayout.SOUTH);
            setSize(500, 500);
            setVisible(true);
            setLocationRelativeTo(null);

        }

        public void deleteInfo() {
            title = new JLabel("删除学生信息");
            title.setFont(new Font("宋体", Font.BOLD, 20));

            if (Objects.equals(student_i, null)) {
                table.setModel(tableModelII());
            } else {
                table.setModel(tableModelI());
            }
            r.setHorizontalAlignment(JLabel.CENTER);
            r.setVerticalAlignment(JLabel.CENTER);
            this.table.setDefaultRenderer(Object.class, r);

            setLayout(new BorderLayout());
            add(title, BorderLayout.NORTH);
            add(table, BorderLayout.CENTER);
            var jp = new JPanel();
            cansel.addActionListener(e -> dispose());
            doIt.addActionListener(new okAction(getColumn(), -1));
            jp.add(cansel);
            jp.add(doIt);
            add(jp, BorderLayout.SOUTH);
            setSize(500, 500);
            setVisible(true);
            setLocationRelativeTo(null);

        }

        public void addInfo() {

            title = new JLabel("增加学生信息");
            title.setFont(new Font("宋体", Font.BOLD, 20));

            if (Objects.equals(student_i, null)) {
                table.setModel(AddTableModelII());
            } else {
                table.setModel(AddTableModelI());
            }
            r.setHorizontalAlignment(JLabel.CENTER);
            r.setVerticalAlignment(JLabel.CENTER);
            this.table.setDefaultRenderer(Object.class, r);

            setLayout(new BorderLayout());
            add(title, BorderLayout.NORTH);
            add(table, BorderLayout.CENTER);
            var jp = new JPanel();
            cansel.addActionListener(e -> dispose());
            doIt.addActionListener(new okAction(getColumn(), 1));
            jp.add(cansel);
            jp.add(doIt);
            add(jp, BorderLayout.SOUTH);
            setSize(500, 500);
            setVisible(true);
            setLocationRelativeTo(null);

        }

        private DefaultTableModel tableModelI() {
            var ss = new String[12][2];
            var s = student_i.getRowInfo();
            for (int i = 0; i < 9; i++) {
                ss[i][0] = rowI[i];
                ss[i][1] = s[i];
            }
            ss[9][0] = "总成绩";
            ss[9][1] = s[9];
            ss[10][0] = "地址";
            ss[10][1] = s[10];
            ss[11][0] = "专业";
            ss[11][1] = s[11];

            return new DefaultTableModel(ss, cloumnNames);
        }

        private DefaultTableModel tableModelII() {

            var ss = new String[13][2];
            var s = student_ii.getRowInfo();
            for (int i = 0; i < 9; i++) {
                ss[i][0] = rowII[i];
                ss[i][1] = s[i];
            }
            ss[9][0] = "总成绩";
            ss[9][1] = s[9];
            ss[10][0] = "地址";
            ss[10][1] = s[10];
            ss[11][0] = "导师";
            ss[11][1] = s[11];
            ss[12][0] = "方向";
            ss[12][1] = s[12];

            return new DefaultTableModel(ss, cloumnNames);
        }

        private DefaultTableModel AddTableModelI() {

            var ss = new String[13][2];
            for (int i = 0; i < 9; i++) {
                ss[i][0] = rowII[i];
                ss[i][1] = "";
            }
            ss[9][0] = "总成绩";
            ss[9][1] = "";
            ss[10][0] = "地址";
            ss[10][1] = "";
            ss[11][0] = "专业";
            ss[11][1] = "";

            return new DefaultTableModel(ss, cloumnNames);
        }

        private DefaultTableModel AddTableModelII() {

            var ss = new String[13][2];
            for (int i = 0; i < 9; i++) {
                ss[i][0] = rowII[i];
                ss[i][1] = "";
            }
            ss[9][0] = "总成绩";
            ss[9][1] = "";
            ss[10][0] = "地址";
            ss[10][1] = "";
            ss[11][0] = "导师";
            ss[11][1] = "";
            ss[12][0] = "方向";
            ss[12][1] = "";

            return new DefaultTableModel(ss, cloumnNames);
        }


        private String[] getColumn() {
            String[] ss;
            if (Objects.equals(student_i, null)) {
                ss = new String[12];
                ss[0] = this.table.getValueAt(0, 1).toString();
                ss[1] = this.table.getValueAt(1, 1).toString();
                ss[2] = this.table.getValueAt(2, 1).toString();
                ss[3] = this.table.getValueAt(3, 1).toString();
                ss[4] = this.table.getValueAt(4, 1).toString();
                ss[5] = this.table.getValueAt(5, 1).toString();
                ss[6] = this.table.getValueAt(6, 1).toString();
                ss[7] = this.table.getValueAt(7, 1).toString();
                ss[8] = this.table.getValueAt(8, 1).toString();
                ss[9] = this.table.getValueAt(10, 1).toString().replace(",", " ");
                ss[10] = this.table.getValueAt(11, 1).toString();
                ss[11] = this.table.getValueAt(12, 1).toString();

            } else {
                ss = new String[11];
                ss[0] = this.table.getValueAt(0, 1).toString();
                ss[1] = this.table.getValueAt(1, 1).toString();
                ss[2] = this.table.getValueAt(2, 1).toString();
                ss[3] = this.table.getValueAt(3, 1).toString();
                ss[4] = this.table.getValueAt(4, 1).toString();
                ss[5] = this.table.getValueAt(5, 1).toString();
                ss[6] = this.table.getValueAt(6, 1).toString();
                ss[7] = this.table.getValueAt(7, 1).toString();
                ss[8] = this.table.getValueAt(8, 1).toString();
                ss[9] = this.table.getValueAt(10, 1).toString().replace(",", " ");
                ss[10] = this.table.getValueAt(11, 1).toString();
            }
            return ss;
        }

        private class okAction implements ActionListener { // descp 设置监听器
            String[] s;
            int i;

            public okAction(String[] o, int i) {
                this.s = o;
                this.i = i;
            }

            public void actionPerformed(ActionEvent e) {
                System.out.println(Arrays.toString(this.s));
                if (Objects.equals(student_i, null)) {
                    var stu = new Student_II(this.s);

                    try {
                        models.UpdateStuII(stu, i);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                } else {
                    var stu = new Student_I(this.s);
                    try {
                        models.UpdateStuI(stu, i);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
                dispose();
            }
        }

    }

}
