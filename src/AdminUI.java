import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andy on 9/6/16.
 */
public class AdminUI {
    public JPanel jp_admin;
    private JButton bt_addCensus;
    private JButton bt_deleteCensus;
    private JButton bt_updateCensus;
    private JButton bt_searchCensus;
    private JButton bt_manageCensus;
    private JButton bt_quit;
    private JLabel jl_title;

    public Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    public AdminUI() {

//        ImageIcon imageIcon = new ImageIcon("/home/andy/Projects/IdeaProjects/CensusManagement/resources/images/title.jpg");
//        jl_title.setIcon(imageIcon);


        bt_addCensus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("AdminAddCensus");
                frame.setContentPane(new AdminAddCensus().jp_addCensus);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setSize(600, 400);
                frame.setLocation((dimension.width - frame.getWidth())/2, (dimension.height - frame.getHeight())/2);
                frame.setResizable(false);
                frame.setVisible(true);

            }
        });


        bt_deleteCensus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("AdminDeleteCensus");
                frame.setContentPane(new AdminDeleteCensus().jp_deleteCensus);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setSize(600, 400);
                frame.setLocation((dimension.width - frame.getWidth())/2, (dimension.height - frame.getHeight())/2);
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });
        bt_updateCensus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("AdminUpdateCensus");
                frame.setContentPane(new AdminUpdateCensus().jp_updateCensus);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setSize(600, 400);
                frame.setLocation((dimension.width - frame.getWidth())/2, (dimension.height - frame.getHeight())/2);
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });
        bt_searchCensus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("AdminSearchCensus");
                frame.setContentPane(new AdminSearchCensus().jp_searchCensus);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setSize(600, 400);
                frame.setLocation((dimension.width - frame.getWidth())/2, (dimension.height - frame.getHeight())/2);
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });
        bt_manageCensus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("AdminUsersManagement");
                frame.setContentPane(new AdminUsersManagement().jp_usersManagement);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setSize(600, 400);
                frame.setLocation((dimension.width - frame.getWidth())/2, (dimension.height - frame.getHeight())/2);
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });
        bt_quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Programme quits now.");
                System.exit(0);
            }
        });
    }
}
