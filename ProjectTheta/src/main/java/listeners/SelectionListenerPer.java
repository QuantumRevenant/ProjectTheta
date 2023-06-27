
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
public class SelectionListenerPer implements ListSelectionListener {
    private final JTable table;

    public SelectionListenerPer(JTable table) {
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
            CustomRendererPer customRenderer = new CustomRendererPer();
            column.setCellRenderer(customRenderer);
        }
    }
}
