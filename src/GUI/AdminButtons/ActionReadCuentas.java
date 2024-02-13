package GUI.AdminButtons;

import Controlador.Cuenta;
import Exceptions.ServiceException;
import Service.CuentaService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ActionReadCuentas extends JFrame {
    public ActionReadCuentas(){
        CuentaService servicio = new CuentaService();

        List<Cuenta> datos = null;
        try {
            datos = servicio.readAll();
        }catch(ServiceException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar datos" + e.getMessage());
        }

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        model.addColumn("ID");
        model.addColumn("Alias");
        model.addColumn("Saldo");
        model.addColumn("Tipo de cuenta");
        model.addColumn("ID del usuario");
        model.addColumn("CBU");

        for(Cuenta cuenta :datos) {
            model.addRow(new Object[]{cuenta.getId(), cuenta.getAlias(), cuenta.getSaldo(), cuenta.getTipoCuenta(), cuenta.getUsuarioCuenta(), cuenta.getCbu()});
        }

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
        this.setSize(500,250);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
