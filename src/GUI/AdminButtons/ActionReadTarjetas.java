package GUI.AdminButtons;

import Controlador.Cuenta;
import Controlador.Tarjeta;
import Exceptions.ServiceException;
import Service.CuentaService;
import Service.TarjetaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ActionReadTarjetas extends  JFrame {
    public ActionReadTarjetas(){
        TarjetaService servicio = new TarjetaService();

        List<Tarjeta> datos = null;
        try {
            datos = servicio.readAll();
        }catch(ServiceException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar datos" + e.getMessage());
        }

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        model.addColumn("ID");
        model.addColumn("Numero");
        model.addColumn("Limite");
        model.addColumn("ID del Usuario");
        model.addColumn("Saldo adeudado");

        for(Tarjeta tarjeta :datos) {
            model.addRow(new Object[]{tarjeta.getId(), tarjeta.getNumero(), tarjeta.getLimite(), tarjeta.getUsuarioID(), tarjeta.getSaldoAdeudado()});
        }

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
        this.setSize(500,250);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
