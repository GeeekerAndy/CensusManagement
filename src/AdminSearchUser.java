import javafx.scene.control.PasswordField;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andy on 9/12/16.
 */
public class AdminSearchUser {
    public JPanel jp_searchUser;
    private JRadioButton rb_searchByName;
    private JRadioButton rb_searchByIDNumber;
    private JTextField tf_search;
    private JButton bt_search;
    private JTable tb_personalInfo;
    private JRadioButton rb_searchAll;

    public AdminSearchUser() {
        DBOperations dbOperations = new DBOperations();
        ButtonGroup group = new ButtonGroup();
        group.add(rb_searchByIDNumber);
        group.add(rb_searchByName);
        group.add(rb_searchAll);
        bt_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rb_searchByIDNumber.isSelected()) {
                    String[] personalInfoArr = dbOperations.searchPersonalInfo(tf_search.getText());
                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("姓名");
                    model.addColumn("户号");
                    model.addColumn("户主或关系");
                    model.addColumn("曾用名");
                    model.addColumn("性别");
                    model.addColumn("籍贯");
                    model.addColumn("民族");
                    model.addColumn("出生日期");
                    model.addColumn("住址");
                    model.addColumn("宗教信仰");
                    model.addColumn("身份证号");
                    model.addColumn("身高");
                    model.addColumn("血型");
                    model.addColumn("文化程度");
                    model.addColumn("婚姻状况");
                    model.addColumn("兵役状况");
                    model.addColumn("职业");
                    model.addColumn("迁入或迁出");
                    model.addRow(personalInfoArr);
                    tb_personalInfo.setModel(model);
                    for (int row = 0; row < tb_personalInfo.getRowCount(); row++)
                    {
                        int rowHeight = tb_personalInfo.getRowHeight();

                        for (int column = 0; column < tb_personalInfo.getColumnCount(); column++)
                        {
                            Component comp = tb_personalInfo.prepareRenderer(tb_personalInfo.getCellRenderer(row, column), row, column);
                            rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
                        }

                        tb_personalInfo.setRowHeight(row, rowHeight);
                    }
                }
                if(rb_searchByName.isSelected()) {
                    String[] personalInfoArr = dbOperations.searchPersonalInfoByName(tf_search.getText());
                    for(int i = 0; i < personalInfoArr.length; i++) {
                        System.out.println("数组" + i + "是：" + personalInfoArr[i]);
                    }
                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("姓名");
                    model.addColumn("户号");
                    model.addColumn("户主或关系");
                    model.addColumn("曾用名");
                    model.addColumn("性别");
                    model.addColumn("籍贯");
                    model.addColumn("民族");
                    model.addColumn("出生日期");
                    model.addColumn("住址");
                    model.addColumn("宗教信仰");
                    model.addColumn("身份证号");
                    model.addColumn("身高");
                    model.addColumn("血型");
                    model.addColumn("文化程度");
                    model.addColumn("婚姻状况");
                    model.addColumn("兵役状况");
                    model.addColumn("职业");
                    model.addColumn("迁入或迁出");
                    for(int i = 0; i < personalInfoArr.length/18; i++) {
                        Object[] row = new Object[18];
                        for(int j = 0; j < 18; j++) {
                            row[j] = personalInfoArr[i*18 + j];
                        }
                        model.addRow(row);
                    }
                    tb_personalInfo.setModel(model);
                    for (int row = 0; row < tb_personalInfo.getRowCount(); row++)
                    {
                        int rowHeight = tb_personalInfo.getRowHeight();

                        for (int column = 0; column < tb_personalInfo.getColumnCount(); column++)
                        {
                            Component comp = tb_personalInfo.prepareRenderer(tb_personalInfo.getCellRenderer(row, column), row, column);
                            rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
                        }

                        tb_personalInfo.setRowHeight(row, rowHeight);
                    }
                }
                if(rb_searchAll.isSelected()) {
                    String[] personalInfoArr = dbOperations.searchPersonalInfoAll();
                    for(int i = 0; i < personalInfoArr.length; i++) {
                        System.out.println("数组" + i + "是：" + personalInfoArr[i]);
                    }
                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("姓名");
                    model.addColumn("户号");
                    model.addColumn("户主或关系");
                    model.addColumn("曾用名");
                    model.addColumn("性别");
                    model.addColumn("籍贯");
                    model.addColumn("民族");
                    model.addColumn("出生日期");
                    model.addColumn("住址");
                    model.addColumn("宗教信仰");
                    model.addColumn("身份证号");
                    model.addColumn("身高");
                    model.addColumn("血型");
                    model.addColumn("文化程度");
                    model.addColumn("婚姻状况");
                    model.addColumn("兵役状况");
                    model.addColumn("职业");
                    model.addColumn("迁入或迁出");
                    for(int i = 0; i < personalInfoArr.length/18; i++) {
                        Object[] row = new Object[18];
                        for(int j = 0; j < 18; j++) {
                            row[j] = personalInfoArr[i*18 + j];
                        }
                        model.addRow(row);
                    }
                    tb_personalInfo.setModel(model);
                    for (int row = 0; row < tb_personalInfo.getRowCount(); row++)
                    {
                        int rowHeight = tb_personalInfo.getRowHeight();

                        for (int column = 0; column < tb_personalInfo.getColumnCount(); column++)
                        {
                            Component comp = tb_personalInfo.prepareRenderer(tb_personalInfo.getCellRenderer(row, column), row, column);
                            rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
                        }

                        tb_personalInfo.setRowHeight(row, rowHeight);
                    }
                }
            }
        });
    }
}
