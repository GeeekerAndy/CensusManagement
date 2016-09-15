import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andy on 9/12/16.
 */
public class AdminAddUser {
    public JPanel jp_addUser;
    private JButton bt_addUser;
    private JTextField tf_name;
    private JTextField tf_religion;
    private JTextField tf_censusID;
    private JTextField tf_hostOrRelation;
    private JTextField tf_formerName;
    private JTextField tf_birthplace;
    private JTextField tf_nationality;
    private JTextField tf_birthday;
    private JTextField tf_address;
    private JTextField tf_IDNumber;
    private JTextField tf_height;
    private JTextField tf_bloodType;
    private JTextField tf_educationalLevel;
    private JTextField tf_marriageStatus;
    private JTextField tf_militaryStatus;
    private JTextField tf_occupation;
    private JTextField tf_ImmigrationEmigration;
    private JTextField tf_gender;

    public AdminAddUser(JFrame frame) {

        bt_addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] arrPersonalInfo = {tf_name.getText(), tf_censusID.getText(), tf_hostOrRelation.getText(), tf_formerName.getText(),
               tf_gender.getText(), tf_birthplace.getText(), tf_nationality.getText(), tf_birthday.getText(),
                tf_address.getText(), tf_religion.getText(), tf_IDNumber.getText(), tf_height.getText(), tf_bloodType.getText(),
                tf_educationalLevel.getText(), tf_marriageStatus.getText(), tf_militaryStatus.getText(), tf_occupation.getText(),
                tf_ImmigrationEmigration.getText()};
                DBOperations dbOperations = new DBOperations();
                dbOperations.addPersonalInfo(arrPersonalInfo);
            }
        });
    }

}
