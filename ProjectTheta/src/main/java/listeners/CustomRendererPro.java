
package listeners;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author YLEM DIOSES
 */
public class CustomRendererPro extends DefaultTableCellRenderer {
    
    private final Color[] colors = {new Color(201, 87, 0),
    new Color(221, 107, 0),
    new Color(240, 127, 0),
    new Color(255, 152, 0),
    new Color(255, 176, 26)};

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
