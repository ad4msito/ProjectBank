package GUI;

import Exceptions.ServiceException;
import Service.UsuarioCuentaService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
    private Container container;
    private JLabel userLabel;
    private JTextField userTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;
    private UsuarioCuentaService usuarioCuentaService;
    private AdminPanel adminPanel;
    private UsuarioPanel usuarioPanel;

    public Login() {
        this.usuarioCuentaService = new UsuarioCuentaService();
        setTitle("Inicio de sesión");
        setBounds(300, 90, 400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        userLabel = new JLabel("Usuario");
        userLabel.setBounds(50, 30, 100, 30);
        container.add(userLabel);

        userTextField = new JTextField();
        userTextField.setBounds(150, 30, 150, 30);
        container.add(userTextField);

        passwordLabel = new JLabel("Contraseña");
        passwordLabel.setBounds(50, 70, 100, 30);
        container.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 70, 150, 30);
        container.add(passwordField);

        loginButton = new JButton("Iniciar sesión");
        loginButton.setBounds(50, 110, 250, 30);
        container.add(loginButton);

        statusLabel = new JLabel("");
        statusLabel.setBounds(50, 150, 250, 30);
        container.add(statusLabel);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = userTextField.getText();
                String contraseña = new String(passwordField.getPassword());
                try {
                    if(usuarioCuentaService.verificarCredenciales(usuario,contraseña)) {
                        if(usuarioCuentaService.verificarEsAdmin(usuario,contraseña)){
                            dispose();
                            adminPanel = new AdminPanel();
                        } else {
                            dispose();
                            usuarioPanel = new UsuarioPanel();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Los datos ingresados no son correctos.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, "Error al verificar datos", "ERROR", JOptionPane.ERROR_MESSAGE);

                }
    }
});

        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }
}