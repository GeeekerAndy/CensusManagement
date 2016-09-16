import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andy on 9/10/16.
 */
public class UserChangePassword {
    private JTextField tf_oldPassword;
    private JPasswordField tf_newPassword;
    private JPasswordField tf_confirmedPassword;
    private JButton bt_confirm;
    private JButton bt_cancel;
    public JPanel jp_userChangePassword;

    public UserChangePassword(String userName) {

        bt_confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBOperations dbOperations = new DBOperations();
                if(String.valueOf(tf_newPassword.getPassword()).equals(String.valueOf(tf_confirmedPassword.getPassword()))) {
                    dbOperations.updatePassword(userName, tf_oldPassword.getText(), String.valueOf(tf_newPassword.getPassword()));
                } else {
                    JOptionPane.showMessageDialog(null, "两次密码不一致！", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        bt_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame)SwingUtilities.getWindowAncestor(jp_userChangePassword.getComponent(1));
                frame.dispose();

            }
        });
    }
}
