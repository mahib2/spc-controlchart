
import javax.swing.JTable;
import javax.swing.event.*;
import javax.swing.table.TableModel;

public class SimpleTableDemo implements TableModelListener {
    
    public SimpleTableDemo() {
    	JTable jTable1 = new javax.swing.JTable();
        jTable1.getModel().addTableModelListener((TableModelListener) this);        
    }

    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);

        // Do something with the data...
    }
    
}