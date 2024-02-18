package GUI;

import javax.swing.*;
import java.awt.*;

public class VentanaAdmins extends JFrame {
    private JPanel panelPrincipal;
    private JPanel panel1;
    private int width;
    private int height;
    public VentanaAdmins(JPanel panelPrincipal) {
        width = 800;
        height = 800;
        this.panelPrincipal = panelPrincipal;

        vista1(panelPrincipal);
        add(this.panelPrincipal);
    }
    private void vista1(JPanel panelPrincipal){
        panel1 = new JPanel();
        panel1.setBackground(Color.red);
        panel1.setPreferredSize(new Dimension(width,height));
        panelPrincipal.add(panel1);
    }
}
