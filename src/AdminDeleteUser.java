import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andy on 9/12/16.
 */
public class AdminDeleteUser {
    private JButton bt_search;
    private JTextField tf_IDNumer;
    private JButton bt_deleteUser;
    public JPanel jp_deleteUser;
    private JTable jt_personalInfo;

    public AdminDeleteUser() {
        DBOperations dbOperations = new DBOperations();
        bt_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] personalInfoArr = dbOperations.searchPersonalInfo(tf_IDNumer.getText());
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
                jt_personalInfo.setModel(model);
                for (int row = 0; row < jt_personalInfo.getRowCount(); row++)
                {
                    int rowHeight = jt_personalInfo.getRowHeight();

                    for (int column = 0; column < jt_personalInfo.getColumnCount(); column++)
                    {
                        Component comp = jt_personalInfo.prepareRenderer(jt_personalInfo.getCellRenderer(row, column), row, column);
                        rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
                    }

                    jt_personalInfo.setRowHeight(row, rowHeight);
                }
            }
        });
        bt_deleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dbOperations.deletePersonalInfo(tf_IDNumer.getText());
            }
        });
    }
}
