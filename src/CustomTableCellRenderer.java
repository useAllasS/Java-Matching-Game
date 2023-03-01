import java.awt.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
class CustomTableCellRenderer extends DefaultTableCellRenderer {
    private static final Color COLOR1 = new Color(245, 245, 245); // light gray
    private static final Color COLOR2 = new Color(255, 255, 255); // white
    private int row;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        this.row = row;
        c.setBackground(row % 2 == 0 ? COLOR1 : COLOR2);
        return c;
    }
}
