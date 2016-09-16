import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andy on 9/6/16.
 */
public class UserUI {
    private JButton bt_checkCensus;
    private JButton bt_changePassword;
    public JPanel jp_user;
    private JPanel jp_userImage;
    private JLabel jl_userImage;

    public Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    public UserUI(String userName) {
        ImageIcon imageIcon = new ImageIcon("/home/andy/Projects/IdeaProjects/CensusManagement/resources/images/userImage.png");
        jl_userImage.setIcon(imageIcon);

        bt_checkCensus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("UserCheckCensus");
                frame.setContentPane(new UserCheckCensus(userName).jp_userCheckCensus);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setSize(600, 400);
                frame.setLocation((dimension.width - frame.getWidth())/2, (dimension.height - frame.getHeight())/2);
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });

        bt_changePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("UserChangePassword");
                frame.setContentPane(new UserChangePassword(userName).jp_userChangePassword);
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
