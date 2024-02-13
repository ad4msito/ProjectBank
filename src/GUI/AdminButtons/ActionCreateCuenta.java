package GUI.AdminButtons;

import Controlador.Cuenta;
import Exceptions.ServiceException;
import Service.CuentaService;

import javax.swing.*;

public class ActionCreateCuenta {
    private JFrame frame;
    private JTextField aliasField;
    private JTextField saldoField;
    private JTextField tipoCuentaField;
    private JTextField usuarioIdField;
    private JTextField cbuField;

    public ActionCreateCuenta() {
        frame = new JFrame("Registro de Cuenta");
        frame.setSize(300, 270);
        frame.setLocationRelativeTo(null); // Esto centra la ventana

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel aliasLabel = new JLabel("Alias:");
        aliasLabel.setBounds(10, 20, 80, 25);
        panel.add(aliasLabel);

        aliasField = new JTextField(20);
        aliasField.setBounds(100, 20, 165, 25);
        panel.add(aliasField);

        JLabel saldoLabel = new JLabel("Saldo:");
        saldoLabel.setBounds(10, 50, 80, 25);
        panel.add(saldoLabel);

        saldoField = new JTextField(20);
        saldoField.setBounds(100, 50, 165, 25);
        panel.add(saldoField);

        JLabel tipoCuentaLabel = new JLabel("Tipo de Cuenta:");
        tipoCuentaLabel.setBounds(10, 80, 100, 25);
        panel.add(tipoCuentaLabel);

        tipoCuentaField = new JTextField(20);
        tipoCuentaField.setBounds(120, 80, 145, 25);
        panel.add(tipoCuentaField);

        JLabel usuarioIdLabel = new JLabel("ID de Usuario:");
        usuarioIdLabel.setBounds(10, 110, 100, 25);
        panel.add(usuarioIdLabel);

        usuarioIdField = new JTextField(20);
        usuarioIdField.setBounds(120, 110, 145, 25);
        panel.add(usuarioIdField);

        JLabel cbuLabel = new JLabel("CBU:");
        cbuLabel.setBounds(10,140,100,25);
        panel.add(cbuLabel);

        cbuField = new JTextField(20);
        cbuField.setBounds(100,140,165,25);
        panel.add(cbuField);

        JButton botonRegistro = new JButton("Registrar");
        botonRegistro.setBounds(85, 170, 100, 25);
        panel.add(botonRegistro);

        botonRegistro.addActionListener(e -> {
            String alias = aliasField.getText();
            Double saldo = Double.valueOf(saldoField.getText());
            int tipoCuenta = Integer.parseInt(tipoCuentaField.getText());
            Long userID = Long.valueOf(usuarioIdField.getText());
            int cbu = Integer.parseInt(cbuField.getText());

            Cuenta cuenta = new Cuenta(alias,saldo,tipoCuenta,userID,cbu);
            CuentaService cuentaService = new CuentaService();

            try {
                cuentaService.create(cuenta);
                JOptionPane.showMessageDialog(null,"Cuenta creada con exito.");
            } catch (ServiceException ex) {
                JOptionPane.showMessageDialog(null,"Error al crear una cuenta" + ex.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        new ActionCreateCuenta();
    }
}