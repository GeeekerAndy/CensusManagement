import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andy on 9/11/16.
 */
public class AdminSearchCensus {
    public JPanel jp_searchCensus;
    private JRadioButton rb_byCensusID;
    private JRadioButton rb_byHostIDNumber;
    private JTextField tf_searchCensus;
    private JButton bt_searchCensus;
    private JTable jt_resultCensus;
    private JRadioButton rb_All;

    public AdminSearchCensus() {
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rb_byCensusID);
        buttonGroup.add(rb_byHostIDNumber);
        buttonGroup.add(rb_All);

        bt_searchCensus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBOperations dbOperations = new DBOperations();
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("户号");
                model.addColumn("户型");
                model.addColumn("户主");
                model.addColumn("住址");
                if(rb_byCensusID.isSelected()) {
                    String[] resultCensusArr = dbOperations.searchCensusByID(tf_searchCensus.getText());
                    model.addRow(resultCensusArr);
                }
                if(rb_byHostIDNumber.isSelected()) {
                    String[] resultCensusArr = dbOperations.searchCensusByHostIDNumber(tf_searchCensus.getText());
                    model.addRow(resultCensusArr);
                }
                if(rb_All.isSelected()) {
                    String[] resultCensusArr = dbOperations.searchCensusAll();
                    for(int i = 0; i < resultCensusArr.length/4; i++) {
                        Object[] objects = {resultCensusArr[i*4 + 0], resultCensusArr[i*4 + 1], resultCensusArr[i*4 +2], resultCensusArr[i*4 + 3]};
                        model.addRow(objects);
                    }
                }
                jt_resultCensus.setModel(model);
                for (int row = 0; row < jt_resultCensus.getRowCount(); row++)
                {
                    int rowHeight = jt_resultCensus.getRowHeight();

                    for (int column = 0; column < jt_resultCensus.getColumnCount(); column++)
                    {
                        Component comp = jt_resultCensus.prepareRenderer(jt_resultCensus.getCellRenderer(row, column), row, column);
                        rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
                    }

                    jt_resultCensus.setRowHeight(row, rowHeight);
                }
            }
        });
    }
}
