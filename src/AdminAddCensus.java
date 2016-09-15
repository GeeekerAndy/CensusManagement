import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andy on 9/11/16.
 */
public class AdminAddCensus extends Thread {
    private JTextField tf_hostName;
    private JTextField tf_address;
    private JButton bt_addCensus;
    public JPanel jp_addCensus;
    private JTextField tf_censusID;
    private JTextField tf_censusType;

    public AdminAddCensus() {

        if (!tf_address.getText().equals("") && !tf_hostName.getText().equals("") && !tf_censusID.getText().equals("") && !tf_censusType.equals("")) {
            bt_addCensus.setEnabled(true);
        }
        bt_addCensus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] arrCensusInfo = {tf_censusID.getText(), tf_censusType.getText(), tf_hostName.getText(), tf_address.getText()};
                DBOperations dbOperations = new DBOperations();
                dbOperations.addCensus(arrCensusInfo);
            }
        });

    }
}
