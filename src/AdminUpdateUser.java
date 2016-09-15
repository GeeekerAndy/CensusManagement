import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andy on 9/12/16.
 */
public class AdminUpdateUser {
    private JTextField tf_IDNumber;
    private JButton bt_search;
    private JButton bt_update;
    private JTextField tf_update;
    private JRadioButton rb_name;
    private JRadioButton rb_religion;
    private JRadioButton rb_censusID;
    private JRadioButton rb_IDNumber;
    private JRadioButton rb_hostOrRelation;
    private JRadioButton rb_height;
    private JRadioButton rb_formerName;
    private JRadioButton rb_gender;
    private JRadioButton rb_birthplace;
    private JRadioButton rb_nationality;
    private JRadioButton rb_birthday;
    private JRadioButton rb_address;
    private JRadioButton rb_bloodType;
    private JRadioButton rb_educationLevel;
    private JRadioButton rb_marriageStatus;
    private JRadioButton rb_militaryStatus;
    private JRadioButton rb_occupation;
    private JRadioButton rb_ImEm;
    public JPanel jp_updateUser;
    private JTable tb_personalInfo;

    public AdminUpdateUser() {
        DBOperations dbOperations = new DBOperations();
        ButtonGroup group = new ButtonGroup();
        group.add(rb_address);
        group.add(rb_birthday);
        group.add(rb_birthplace);
        group.add(rb_bloodType);
        group.add(rb_censusID);
        group.add(rb_educationLevel);
        group.add(rb_formerName);
        group.add(rb_gender);
        group.add(rb_height);
        group.add(rb_hostOrRelation);
        group.add(rb_IDNumber);
        group.add(rb_ImEm);
        group.add(rb_marriageStatus);
        group.add(rb_militaryStatus);
        group.add(rb_name);
        group.add(rb_nationality);
        group.add(rb_occupation);
        group.add(rb_religion);

        bt_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String[] personalInfoArr = dbOperations.searchPersonalInfo(tf_IDNumber.getText());
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("姓名");
                model.addColumn("户号");
                model.addColumn("户主或关系");
                model.addColumn("曾用名");
                model.addColumn("性别");
                model.addColumn("籍贯");
                model.addColumn("民族");
                model.addColumn("出生日期");
                model.addColumn("住址");
                model.addColumn("宗教信仰");
                model.addColumn("身份证号");
                model.addColumn("身高");
                model.addColumn("血型");
                model.addColumn("文化程度");
                model.addColumn("婚姻状况");
                model.addColumn("兵役状况");
                model.addColumn("职业");
                model.addColumn("迁入或迁出");
                model.addRow(personalInfoArr);
                tb_personalInfo.setModel(model);
                for (int row = 0; row < tb_personalInfo.getRowCount(); row++)
                {
                    int rowHeight = tb_personalInfo.getRowHeight();

                    for (int column = 0; column < tb_personalInfo.getColumnCount(); column++)
                    {
                        Component comp = tb_personalInfo.prepareRenderer(tb_personalInfo.getCellRenderer(row, column), row, column);
                        rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
                    }

                    tb_personalInfo.setRowHeight(row, rowHeight);
                }
            }
        });
        bt_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rb_address.isSelected()) {
                    dbOperations.updatePersonalInfo(tf_IDNumber.getText(), "address", tf_update.getText());
                }
                if(rb_birthday.isSelected()) {
                    dbOperations.updatePersonalInfo(tf_IDNumber.getText(), "birthday", tf_update.getText());
                }
                if(rb_birthplace.isSelected()) {
                    dbOperations.updatePersonalInfo(tf_IDNumber.getText(), "birthplace", tf_update.getText());
                }
                if(rb_bloodType.isSelected()) {
                    dbOperations.updatePersonalInfo(tf_IDNumber.getText(), "bloodType", tf_update.getText());
                }
                if(rb_censusID.isSelected()) {
                    dbOperations.updatePersonalInfo(tf_IDNumber.getText(), "censusID", tf_update.getText());
                }
                if(rb_educationLevel.isSelected()) {
                    dbOperations.updatePersonalInfo(tf_IDNumber.getText(), "educationLevel", tf_update.getText());
                }
                if(rb_formerName.isSelected()) {
                    dbOperations.updatePersonalInfo(tf_IDNumber.getText(), "formerName", tf_update.getText());
                }
                if(rb_gender.isSelected()) {
                    dbOperations.updatePersonalInfo(tf_IDNumber.getText(), "gender", tf_update.getText());
                }
                if(rb_height.isSelected()) {
                    dbOperations.updatePersonalInfo(tf_IDNumber.getText(), "height", tf_update.getText());
                }
                if(rb_hostOrRelation.isSelected()) {
                    dbOperations.updatePersonalInfo(tf_IDNumber.getText(), "hostOrRelation", tf_update.getText());
                }
                if(rb_ImEm.isSelected()) {
                    dbOperations.updatePersonalInfo(tf_IDNumber.getText(), "ImmigrationEmigration", tf_update.getText());
                }
                if(rb_marriageStatus.isSelected()) {
                    dbOperations.updatePersonalInfo(tf_IDNumber.getText(), "marriageStatus", tf_update.getText());
                }
                if(rb_militaryStatus.isSelected()) {
                    dbOperations.updatePersonalInfo(tf_IDNumber.getText(), "militaryStatus", tf_update.getText());
                }
                if(rb_name.isSelected()) {
                    dbOperations.updatePersonalInfo(tf_IDNumber.getText(), "name", tf_update.getText());
                }
                if(rb_nationality.isSelected()) {
                    dbOperations.updatePersonalInfo(tf_IDNumber.getText(), "nationality", tf_update.getText());
                }
                if(rb_occupation.isSelected()) {
                    dbOperations.updatePersonalInfo(tf_IDNumber.getText(), "occupation", tf_update.getText());
                }
                if(rb_religion.isSelected()) {
                    dbOperations.updatePersonalInfo(tf_IDNumber.getText(), "religion", tf_update.getText());
                }
            }
        });
    }
}
