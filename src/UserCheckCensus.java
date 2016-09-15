import javax.swing.*;

/**
 * Created by andy on 9/10/16.
 */
public class UserCheckCensus {
    public JPanel jp_userCheckCensus;
    public JLabel lb_CensusAddress;
    private JLabel lb_censusID;
    private JLabel lb_hostName;
    private JLabel lb_censusType;
    private JLabel lb_hostOrRelation;

    public UserCheckCensus(String userName) {
        DBOperations dbOperations = new DBOperations();
        String[] censusInfo = dbOperations.searchCensusByUserName(userName);
        lb_censusID.setText(censusInfo[0]);
        lb_hostName.setText(censusInfo[1]);
        lb_censusType.setText(censusInfo[2]);
        lb_hostOrRelation.setText(censusInfo[3]);
        lb_CensusAddress.setText(censusInfo[4]);

    }
}
