import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andy on 9/11/16.
 */
public class AdminDeleteCensus {
    private JTextField tf_censusID;
    private JButton bt_searchCensus;
    private JButton bt_deleteCensus;
    private JTable jt_resultCensus;
    public JPanel jp_deleteCensus;

    public AdminDeleteCensus() {
        bt_searchCensus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBOperations dbOperations = new DBOperations();
                String[] resultCensusArr = dbOperations.searchCensusByID(tf_censusID.getText());
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("户号");
                model.addColumn("户型");
                model.addColumn("户主");
                model.addColumn("住址");
                model.addRow(resultCensusArr);
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
        bt_deleteCensus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBOperations dbOperations = new DBOperations();
                dbOperations.deleteCensus(tf_censusID.getText());
            }
        });
    }
}
