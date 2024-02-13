package GUI.AdminButtons;

import Controlador.Transacciones.TransaccionCuenta;
import Controlador.Transacciones.TransaccionTarjeta;
import Exceptions.ServiceException;
import Service.TransaccionCuentaService;
import Service.TransaccionTarjetaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ActionReadMovimientosT extends JFrame {
    public ActionReadMovimientosT() {
        TransaccionTarjetaService servicio = new TransaccionTarjetaService();

        List<TransaccionTarjeta> datos = null;
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
        model.addColumn("ID de tarjeta");
        model.addColumn("ID de Destino");

        for(TransaccionTarjeta transaccion :datos) {
            model.addRow(new Object[]{transaccion.getId(), transaccion.getFecha(), transaccion.getMonto(), transaccion.getTarjetaOrigen(), transaccion.getCuentaDestino()});
        }

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
        this.setSize(400,250);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
