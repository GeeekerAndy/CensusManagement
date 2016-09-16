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
        lb_censusID.setText("户籍号：" + censusInfo[0]);
        lb_hostName.setText("户主姓名：" + censusInfo[1]);
        lb_censusType.setText("户籍类型：" + censusInfo[2]);
        lb_hostOrRelation.setText("户主或关系：" + censusInfo[3]);
        lb_CensusAddress.setText("户籍地址：" + censusInfo[4]);

    }
}
