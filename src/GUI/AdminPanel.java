package GUI;


import Service.UsuarioCuentaService;
import javax.swing.*;
import java.awt.*;

    public class AdminPanel extends JFrame {
        Container container;
        UsuarioCuentaService usuarioCuentaService;
        public AdminPanel() {
            this.usuarioCuentaService = new UsuarioCuentaService();
            setTitle("AdminPanel");
            setBounds(300, 90, 600, 600);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setResizable(false);

            container = getContentPane();
            container.setLayout(null);

            setVisible(true);
        }
    }


