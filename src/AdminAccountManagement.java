import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andy on 9/13/16.
 */
public class AdminAccountManagement {
    private JButton bt_addAccount;
    private JButton bt_deleteAccount;
    public JPanel jp_addAccount;
    public Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    public AdminAccountManagement() {
        bt_addAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("AddAccount");
                frame.setContentPane(new AdminAddAccount().jp_addAccount);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setSize(600, 400);
                frame.setLocation((dimension.width - frame.getWidth())/2, (dimension.height - frame.getHeight())/2);
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });
        bt_deleteAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("DeleteAccount");
                frame.setContentPane(new AdminDeleteAccount().jp_deleteAccount);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setSize(600, 400);
                frame.setLocation((dimension.width - frame.getWidth())/2, (dimension.height - frame.getHeight())/2);
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });
    }
}
