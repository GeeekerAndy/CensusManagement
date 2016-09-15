import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andy on 9/11/16.
 */
public class AdminUpdateCensus {
    private JButton bt_updateCensus;
    private JTextField tf_censusID;
    private JTextField tf_hostName;
    private JTextField tf_address;
    private JRadioButton rb_censusType;
    private JRadioButton rb_hostName;
    private JRadioButton rb_address;
    public JPanel jp_updateCensus;
    private JTextField tf_censusType;

    public AdminUpdateCensus() {
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rb_censusType);
        buttonGroup.add(rb_hostName);
        buttonGroup.add(rb_address);
        bt_updateCensus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBOperations dbOperations = new DBOperations();
                if(rb_censusType.isSelected()) {
                    dbOperations.updateCensus(tf_censusID.getText(), "censusType", tf_censusType.getText());
                }
                if(rb_hostName.isSelected()) {
                    dbOperations.updateCensus(tf_censusID.getText(), "censusHost", tf_hostName.getText());
                }
                if(rb_address.isSelected()) {
                    dbOperations.updateCensus(tf_censusID.getText(), "censusAddress", tf_address.getText());
                }
            }
        });
    }
}
