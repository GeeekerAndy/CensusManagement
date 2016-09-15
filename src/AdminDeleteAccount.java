import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andy on 9/13/16.
 */
public class AdminDeleteAccount {
    private JTextField tf_userName;
    private JButton bt_searchUserAccount;
    private JButton bt_deleteAccount;
    public JPanel jp_deleteAccount;
    private JTable tb_showAccountInfo;

    public AdminDeleteAccount() {
        DBOperations dbOperations = new DBOperations();
        bt_searchUserAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] accountInfo = dbOperations.searchAccount(tf_userName.getText());
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("用户名");
                model.addColumn("用户密码");
                model.addColumn("用户权限");
                model.addColumn("用户身份证号");
                model.addRow(accountInfo);
                tb_showAccountInfo.setModel(model);
                for (int row = 0; row < tb_showAccountInfo.getRowCount(); row++)
                {
                    int rowHeight = tb_showAccountInfo.getRowHeight();

                    for (int column = 0; column < tb_showAccountInfo.getColumnCount(); column++)
                    {
                        Component comp = tb_showAccountInfo.prepareRenderer(tb_showAccountInfo.getCellRenderer(row, column), row, column);
                        rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
                    }

                    tb_showAccountInfo.setRowHeight(row, rowHeight);
                }
            }
        });
        bt_deleteAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dbOperations.deleteUserAccount(tf_userName.getText());
            }
        });
    }
}
