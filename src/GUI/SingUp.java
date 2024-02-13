package GUI;

import Controlador.UsuarioCuenta;
import Exceptions.ServiceException;
import GUI.Logos.ObtenerImagen;
import Service.UsuarioCuentaService;

import javax.swing.*;


public class SingUp {
    private JFrame frame;
    private ObtenerImagen obtenerImagen = new ObtenerImagen();
    private JTextField nombreField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JCheckBox esAdmin;
    private ImageIcon image;

    public SingUp() {
        frame = new JFrame("Registro de Usuario");
        frame.setIconImage(new ImageIcon(obtenerImagen.obtener2()).getImage());
        frame.setLocationRelativeTo(null);
        frame.setSize(300, 230);

        JPanel panel = new JPanel();
        frame.add(panel);
        componentes(panel);

        frame.setVisible(true);
    }

    private void componentes(JPanel panel) {
        panel.setLayout(null);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(10, 20, 80, 25);
        panel.add(nombreLabel);

        nombreField = new JTextField(20);
        nombreField.setBounds(100, 20, 165, 25);
        panel.add(nombreField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 50, 80, 25);
        panel.add(emailLabel);

        emailField = new JTextField(20);
        emailField.setBounds(100, 50, 165, 25);
        panel.add(emailField);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(10, 80, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 80, 165, 25);
        panel.add(passwordField);

        JButton botonRegistrar = new JButton("Registrar");
        botonRegistrar.setBounds(85, 130, 100, 25);
        panel.add(botonRegistrar);
        botonRegistrar.addActionListener(e -> {
            String nombre = nombreField.getText();
            String mail = emailField.getText();
            String pass = passwordField.getText();
            Boolean esAdm = false;
            UsuarioCuenta usuario = new UsuarioCuenta(nombre, mail, pass, esAdm);
            UsuarioCuentaService usuarioService = new UsuarioCuentaService();
            try {
                usuarioService.create(usuario);
                JOptionPane.showMessageDialog(null, "Usuario creado con exito");
            } catch (ServiceException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error al insertar" + ex.getMessage());
            }
            frame.dispose();
            new FirstPage();
        });
    }
}
