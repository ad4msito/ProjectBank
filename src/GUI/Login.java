package GUI;

import Controlador.UsuarioCuenta;
import Exceptions.ServiceException;
import GUI.Logos.ObtenerImagen;
import Service.UsuarioCuentaService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Login {
    private JLabel userLabel;
    private JTextField userTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;
    private UsuarioCuentaService usuarioCuentaService;
    private AdminPanel adminPanel;
    private UsuarioPanel usuarioPanel;
    private UsuarioCuenta usuarioCuenta;
    private Long usuarioID;
    private ObtenerImagen obtenerImagen;

    public void loginPage() {
        this.obtenerImagen = new ObtenerImagen();
        this.usuarioCuentaService = new UsuarioCuentaService();
        int width = 400;
        int height = 200;
        JFrame frame = new JFrame();
        frame.setTitle("Inicio de sesión");
        frame.setBounds(300, 90, width, height);
        frame.setIconImage(new ImageIcon(obtenerImagen.obtener2()).getImage());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setPreferredSize(new Dimension(width,height));
        panelPrincipal.setLayout(null);
        frame.add(panelPrincipal);

        userLabel = new JLabel("Usuario");
        userLabel.setBounds(50, 30, 100, 30);
        panelPrincipal.add(userLabel);

        userTextField = new JTextField();
        userTextField.setBounds(150, 30, 150, 30);
        panelPrincipal.add(userTextField);

        passwordLabel = new JLabel("Contraseña");
        passwordLabel.setBounds(50, 70, 100, 30);
        panelPrincipal.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 70, 150, 30);
        panelPrincipal.add(passwordField);

        loginButton = new JButton("Iniciar sesión");
        loginButton.setBounds(50, 110, 250, 30);
        panelPrincipal.add(loginButton);

        statusLabel = new JLabel("");
        statusLabel.setBounds(50, 150, 250, 30);
        panelPrincipal.add(statusLabel);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = userTextField.getText();
                String contrasena = new String(passwordField.getPassword());
                try {
                    if (usuarioCuentaService.verificarCredenciales(usuario, contrasena)) {
                        if (usuarioCuentaService.verificarEsAdmin(usuario, contrasena)) {

                            usuarioCuenta = usuarioCuentaService.readEmail(usuario);
                            setUsuarioID(usuarioCuenta.getId());
                            frame.dispose();
                            adminPanel = new AdminPanel(usuarioID);

                        } else {

                            usuarioCuenta = usuarioCuentaService.readEmail(usuario);
                            setUsuarioID(usuarioCuenta.getId());
                            frame.dispose();
                            usuarioPanel = new UsuarioPanel(usuarioID);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Los datos ingresados no son correctos.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, "Error al verificar datos" + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        frame.setVisible(true);
    }

    public Long getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Long usuarioID) {
        this.usuarioID = usuarioID;
    }

    public static void main(String[] args) {
        new Login();
    }
}