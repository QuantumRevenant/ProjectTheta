/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listeners;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author YLEM DIOSES
 */
public class CustomRendererPer extends DefaultTableCellRenderer {
    
    private final Color[] colors = {new Color(110, 179, 91),
    new Color(114, 185, 96),
    new Color(118, 191, 101),
    new Color(122, 197, 106),
    new Color(126, 203, 111),
    new Color(130, 209, 116),
    new Color(134, 215, 121),
    new Color(138, 221, 126),
    new Color(142, 227, 131)};

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