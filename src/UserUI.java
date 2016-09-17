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
    private JLabel lb_name;
    private JLabel lb_censusID;
    private JLabel lb_hostOrRelation;
    private JLabel lb_formerName;
    private JLabel lb_gender;
    private JLabel lb_birthplace;
    private JLabel lb_nationality;
    private JLabel lb_birthday;
    private JLabel lb_address;
    private JLabel lb_religion;
    private JLabel lb_IDNumber;
    private JLabel lb_height;
    private JLabel lb_bloodType;
    private JLabel lb_educationLevel;
    private JLabel lb_marriageStatus;
    private JLabel lb_militaryStatus;
    private JLabel lb_occupation;
    private JLabel lb_ImEm;

    public Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    public UserUI(String userName) {
        ImageIcon imageIcon = new ImageIcon("/home/andy/Projects/IdeaProjects/CensusManagement/resources/images/userImage.png");
        jl_userImage.setIcon(imageIcon);

        DBOperations dbOperations = new DBOperations();
        String accountInfo[] = dbOperations.searchAccount(userName);
        String personalInfo[] = dbOperations.searchPersonalInfo(accountInfo[3]);


        lb_name.setText("姓名：" + personalInfo[0]);
        lb_censusID.setText("户号：" + personalInfo[1]);
        lb_hostOrRelation.setText("户主或关系：" + personalInfo[2]);
        lb_formerName.setText("曾用名：" + personalInfo[3]);
        lb_gender.setText("性别：" + personalInfo[4]);
        lb_birthplace.setText("籍贯：" + personalInfo[5]);
        lb_nationality.setText("民族：" + personalInfo[6]);
        lb_birthday.setText("出生日期：" + personalInfo[7]);
        lb_address.setText("住址：" + personalInfo[8]);
        lb_religion.setText("宗教：" + personalInfo[9]);
        lb_IDNumber.setText("身份证号：" + personalInfo[10]);
        lb_height.setText("高度：" + personalInfo[11]);
        lb_bloodType.setText("血型：" + personalInfo[12]);
        lb_educationLevel.setText("文化程度：" + personalInfo[13]);
        lb_marriageStatus.setText("婚姻状况：" + personalInfo[14]);
        lb_militaryStatus.setText("兵役状况：" + personalInfo[15]);
        lb_occupation.setText("职业：" + personalInfo[16]);
        lb_ImEm.setText("迁入迁出：" + personalInfo[17]);

        bt_checkCensus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("UserCheckCensus");
                frame.setContentPane(new UserCheckCensus(userName).jp_userCheckCensus);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setSize(600, 400);
                frame.setLocation((dimension.width - frame.getWidth()) / 2, (dimension.height - frame.getHeight()) / 2);
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
                frame.setLocation((dimension.width - frame.getWidth()) / 2, (dimension.height - frame.getHeight()) / 2);
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });

    }
}
