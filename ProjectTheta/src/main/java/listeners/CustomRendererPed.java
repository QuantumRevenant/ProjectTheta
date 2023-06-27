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
public class CustomRendererPed extends DefaultTableCellRenderer {
    
    private final Color[] colors = {new Color(72, 46, 28),
    new Color(99, 64, 39),
    new Color(126, 83, 51),
    new Color(153, 101, 63),
    new Color(180, 119, 74),
    new Color(207, 138, 86)};

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