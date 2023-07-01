/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listeners;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
/**
 *
 * @author YLEM DIOSES
 */
public class SelectionListenerPro implements ListSelectionListener {
    private final JTable table;

    public SelectionListenerPro(JTable table) {
        this.table = table;
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        // Obtener la fila seleccionada
        int selectedRow = table.getSelectedRow();

        // Cambiar el color de fondo de la fila seleccionada
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableCellRenderer renderer = table.getCellRenderer(selectedRow, i);
            TableColumn column = table.getColumnModel().getColumn(i);
            
            // Crear una instancia de CustomRenderer
            CustomRendererPro customRenderer = new CustomRendererPro();
            column.setCellRenderer(customRenderer);
        }
    }
}