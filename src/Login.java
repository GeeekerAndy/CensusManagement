import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andy on 9/6/16.
 */
public class Login {
    private JPanel jp_login;
    private JTextField tf_userName;
    private JPasswordField pf_password;
    private JButton bt_login;
    private JButton bt_quit;
    private JPanel jpanel1;
    private JPanel jpanel2;
    private JPanel jpanel3;
    private JPanel jpanel2_1;
    private JPanel jpanel2_2;
    private JLabel jl_title;
    private JLabel jl_subTitle;
    private JLabel jl_userName;
    private JLabel jl_password;

    public static Dimension dimension;

    public Login(JFrame frame) {

        DBOperations dbOperations = new DBOperations();
        if(!dbOperations.checkValueExists("userAccount", "admin")) {
            dbOperations.addUserAccount("admin", "1234", "1", null);
        }

        //点击登录按钮从数据库中验证并判断权限
        bt_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //密码正确
                if(dbOperations.checkAccount(jl_userName.getText(), jl_password.getText())) {
                    //如果为管理员
                    if(dbOperations.checkPower(jl_userName.getText(), jl_password.getText())) {
                        JFrame adminFrame = new JFrame("AdministratorUI");
                        adminFrame.setContentPane(new AdministratorUI().jp_admin);
                        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        adminFrame.pack();
                        adminFrame.setResizable(false);
                        adminFrame.setSize(dimension.width, dimension.height);
                        adminFrame.setVisible(true);
                        frame.dispose();
                        System.out.println("Admin user login.");
                    } else {
                        //如果为普通用户
                        JFrame userFrame = new JFrame("UserUI");
                        userFrame.setContentPane(new UserUI().jp_user);
                        userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        userFrame.pack();
                        userFrame.setResizable(false);
                        userFrame.setSize(dimension.width, dimension.height);
                        userFrame.setVisible(true);
                        frame.dispose();
                        System.out.println("Normal user login");
                    }

                } else {    //密码错误
//                    new JDialog(new )
                }

            }
        });

        bt_quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Quit button clicked.");
                frame.dispose();
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login(frame).jp_login);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setSize(600, 400);
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((dimension.width - frame.getWidth())/2, (dimension.height - frame.getHeight())/2);
        frame.setVisible(true);

    }
}
