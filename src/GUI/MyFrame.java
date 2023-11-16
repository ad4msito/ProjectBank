package GUI;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    MyFrame(){
        this.setTitle("Cuentas");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500,500);
        this.setLayout(new FlowLayout());
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(209, 228, 255));
    }
}
