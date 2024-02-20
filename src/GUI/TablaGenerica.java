package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TablaGenerica extends JFrame {
    public TablaGenerica(List<Object[]> datos, Object[] columnas){
        DefaultTableModel modelo = new DefaultTableModel();
        JTable table = new JTable(modelo);

        for (Object columna : columnas) {
            modelo.addColumn(columna);
        }

        for(Object[] fila : datos) {
            modelo.addRow(fila);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
        this.setSize(500,300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}