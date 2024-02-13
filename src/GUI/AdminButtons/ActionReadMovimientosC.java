package GUI.AdminButtons;

import Controlador.Tarjeta;
import Controlador.Transacciones.TransaccionCuenta;
import Exceptions.ServiceException;
import Service.TarjetaService;
import Service.TransaccionCuentaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ActionReadMovimientosC extends JFrame {
    public ActionReadMovimientosC() {
        TransaccionCuentaService servicio = new TransaccionCuentaService();

        List<TransaccionCuenta> datos = null;
        try {
            datos = servicio.readAll();
        }catch(ServiceException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar datos" + e.getMessage());
        }

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        model.addColumn("ID");
        model.addColumn("Fecha");
        model.addColumn("Monto");
        model.addColumn("ID de cuenta");
        model.addColumn("ID de Destino");
        model.addColumn("Fecha");

        for(TransaccionCuenta transaccion :datos) {
            model.addRow(new Object[]{transaccion.getId(), transaccion.getFecha(), transaccion.getMonto(), transaccion.getCuentaOrigen(), transaccion.getCuentaDestino(), transaccion.getFecha()});
        }

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
        this.setSize(400,250);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
