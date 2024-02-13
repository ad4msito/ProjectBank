package GUI.AdminButtons;

import Controlador.UsuarioCuenta;
import Exceptions.ServiceException;
import Service.UsuarioCuentaService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ActionReadUsuarios extends JFrame{
    public ActionReadUsuarios() {
        UsuarioCuentaService servicio = new UsuarioCuentaService();

        List<UsuarioCuenta> datos = null;
        try {
            datos = servicio.readAll();
        }catch(ServiceException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar datos" + e.getMessage());
        }

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("E-Mail");
        model.addColumn("Contraseña");
        model.addColumn("¿Administrador?");

        for(UsuarioCuenta usuario :datos) {
            model.addRow(new Object[]{usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getPassword(), usuario.getEsAdmin()});
        }

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
        this.setSize(700,250);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}