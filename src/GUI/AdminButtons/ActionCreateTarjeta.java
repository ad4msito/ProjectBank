package GUI.AdminButtons;

import Controlador.Tarjeta;
import Exceptions.ServiceException;
import Service.TarjetaService;

import javax.swing.*;

public class ActionCreateTarjeta {
    private JFrame frame;
    private JTextField numeroField;
    private JTextField limiteField;
    private JTextField usuarioIdField;

    private JTextField saldoAdeudadoField;

    public ActionCreateTarjeta() {
        frame = new JFrame("Registro de Tarjeta");
        frame.setSize(300, 215);
        frame.setLocationRelativeTo(null); // Esto centra la ventana

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel numeroLabel = new JLabel("Número:");
        numeroLabel.setBounds(10, 20, 80, 25);
        panel.add(numeroLabel);

        numeroField = new JTextField(50);
        numeroField.setBounds(100, 20, 165, 25);
        panel.add(numeroField);

        JLabel limiteLabel = new JLabel("Límite:");
        limiteLabel.setBounds(10, 50, 80, 25);
        panel.add(limiteLabel);

        limiteField = new JTextField(20);
        limiteField.setBounds(100, 50, 165, 25);
        panel.add(limiteField);

        JLabel usuarioIdLabel = new JLabel("ID de Usuario:");
        usuarioIdLabel.setBounds(10, 80, 100, 25);
        panel.add(usuarioIdLabel);

        usuarioIdField = new JTextField(20);
        usuarioIdField.setBounds(110, 80, 145, 25);
        panel.add(usuarioIdField);

        JLabel saldoAdeudadoLabel = new JLabel("Saldo adeudado:");
        saldoAdeudadoLabel.setBounds(10,110,100,25);
        panel.add(saldoAdeudadoLabel);

        saldoAdeudadoField = new JTextField(20);
        saldoAdeudadoField.setBounds(120,110,145,25);
        panel.add(saldoAdeudadoField);

        JButton registerButton = new JButton("Registrar");
        registerButton.setBounds(85, 140, 100, 25);
        panel.add(registerButton);

        registerButton.addActionListener(e -> {
            int num = Integer.parseInt(numeroField.getText());
            Double limite = Double.valueOf(limiteField.getText());
            Long userID = Long.valueOf(usuarioIdField.getText());
            Double deuda = Double.valueOf(saldoAdeudadoField.getText());
            Tarjeta tarjeta = new Tarjeta(num,limite,userID,deuda);
            TarjetaService tarjetaService = new TarjetaService();
            try {
                tarjetaService.create(tarjeta);
                JOptionPane.showMessageDialog(null,"Tarjeta creada con exito.");
            } catch (ServiceException ex) {
                JOptionPane.showMessageDialog(null,"Error al crear la tarjeta." + ex.getMessage());
            }
        });
    }

}