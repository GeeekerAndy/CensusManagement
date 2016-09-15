import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andy on 9/11/16.
 */
public class AdminUsersManagement {
    private JButton bt_addUser;
    private JButton bt_deleteUser;
    private JButton bt_updateUser;
    private JButton bt_searchUser;
    public JPanel jp_usersManagement;
    public Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    public AdminUsersManagement() {
        bt_addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("AdminAddUser");
                frame.setContentPane(new AdminAddUser(frame).jp_addUser);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setSize(600, 400);
                frame.setLocation((dimension.width - frame.getWidth())/2, (dimension.height - frame.getHeight())/2);
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });

        bt_deleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("AdminDeleteUser");
                frame.setContentPane(new AdminDeleteUser().jp_deleteUser);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setSize(dimension.width, dimension.height);
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });
        bt_updateUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("AdminUpdateUser");
                frame.setContentPane(new AdminUpdateUser().jp_updateUser);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setSize(dimension.width, dimension.height);
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });
        bt_searchUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("AdminSearchUser");
                frame.setContentPane(new AdminSearchUser().jp_searchUser);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setSize(dimension.width, dimension.height);
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });
    }
}
