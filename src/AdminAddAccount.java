import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andy on 9/13/16.
 */
public class AdminAddAccount {
    private JTextField tf_userName;
    private JTextField tf_userPassword;
    private JTextField tf_userPower;
    private JTextField tf_IDNumber;
    public JPanel jp_addAccount;
    private JButton bt_addAccount;

    public AdminAddAccount() {
        bt_addAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBOperations dbOperations = new DBOperations();
                dbOperations.addUserAccount(tf_userName.getText(), tf_userPassword.getText(), tf_userPower.getText(), tf_IDNumber.getText());
            }
        });
    }
}
