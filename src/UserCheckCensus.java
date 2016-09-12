import javax.swing.*;

import static java.awt.SystemColor.text;

/**
 * Created by andy on 9/10/16.
 */
public class UserCheckCensus {
    public JPanel jp_userCheckCensus;
    public JLabel jl_userCensusAddress;

    public UserCheckCensus() {
        StringBuilder stringBuilder = new StringBuilder(256);
        stringBuilder.append("<html>住址：山东省济南市历下区舜华路街道<br>舜华路1500号山东大学齐鲁软件园</html>");
        jl_userCensusAddress.setText(stringBuilder.toString());
    }
}
