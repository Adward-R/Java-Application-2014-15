import javax.swing.table.AbstractTableModel;
import java.io.IOException;

/**
 * Created by Adward on 14/12/23.
 */
public class MyTable extends AbstractTableModel {

    public DataObtainer dataObtainer;

    public MyTable(String filePath) throws IOException {
        dataObtainer = new DataObtainer(filePath);
    }

    public int getColumnCount() {
        //return n.length;
        return dataObtainer.getColumnNum();
    }
    //public int getColumnCount() {return n;}
    public int getRowCount() {
        //return p.length;
        return dataObtainer.getRowNum();
    }
    public Object getValueAt(int row, int col) {
        //return p[row][col];
        return dataObtainer.getBlockValue(row,col);
    }

    /*public String getColumnName(int col) {
        return dataObtainer.getHeadName(col);
    }*/
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    @Override
    public boolean isCellEditable(int i, int i1) {
        //return super.isCellEditable(i, i1);
        return true;
    }
    public void setValueAt(Object value, int row, int col) {
        //p[row][col] = value;
        dataObtainer.setBlock(row,col,value);
        fireTableCellUpdated(row, col);
    }
}
