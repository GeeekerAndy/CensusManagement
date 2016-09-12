import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andy on 9/10/16.
 */
public class UserChangePassword {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton bt_confirm;
    private JButton bt_cancel;
    public JPanel jp_userChangePassword;

    public UserChangePassword() {

        bt_confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
