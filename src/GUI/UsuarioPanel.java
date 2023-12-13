package GUI;

import Service.UsuarioCuentaService;
import javax.swing.*;
import java.awt.*;

public class UsuarioPanel extends JFrame {
    Container container;
    UsuarioCuentaService usuarioCuentaService;
    public UsuarioPanel() {
        this.usuarioCuentaService = new UsuarioCuentaService();
        setTitle("UserPanel");
        setBounds(300, 90, 600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        setVisible(true);
    }
}
