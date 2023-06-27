
package listeners;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author YLEM DIOSES
 */
public class CustomRenderer extends DefaultTableCellRenderer {
    
    private final Color[] colors = {new Color(26, 35, 126), new Color(40, 53, 147), new Color(48, 63, 159), new Color(57, 73, 171), new Color(63, 81, 181), new Color(92, 107, 192)};

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        // Cambiar el color de fondo si la fila está seleccionada
        if (isSelected) {
            int colorIndex = column % colors.length;
            component.setBackground(colors[colorIndex]);
        } else {
            component.setBackground(table.getBackground());
        }

        return component;
    }
}