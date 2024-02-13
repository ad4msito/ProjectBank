package GUI;

import GUI.Logos.LogoImagen;
import GUI.Logos.ObtenerImagen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FirstPage extends JFrame {
    private ObtenerImagen obtenerImagen = new ObtenerImagen();
    private LogoImagen imagen;
    private JButton buttonLogin;
    private JButton buttonSingUp;
    private JLayeredPane panel;
    public FirstPage() {
        super("Bienvenido a Banco12");
        super.setIconImage(new ImageIcon(obtenerImagen.obtener2()).getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setResizable(false);
        setLocationRelativeTo(null);

        panel = new JLayeredPane();
        panel.setBackground(Color.CYAN);
        panel.setLayout(null);

        imagen = new LogoImagen(275,200);
        JPanel panelImagen = new JPanel();
        panelImagen.setBounds(50,0,275,200);
        panelImagen.setOpaque(false);
        panelImagen.add(imagen);
        panelImagen.repaint();
        panel.add(panelImagen, new Integer((1)));

        buttonLogin = new JButton("Iniciar sesión");
        buttonLogin.setBounds(30,174,150,50);
        buttonLogin.setOpaque(false);
        buttonLogin.addActionListener(this::actionPerformance);
        panel.add(buttonLogin, new Integer(2));

        buttonSingUp = new JButton("Registrarse");
        buttonSingUp.setBounds(200,174,150,50);
        buttonSingUp.setOpaque(false);
        buttonSingUp.addActionListener(this::actionPerformance);
        panel.add(buttonSingUp,new Integer(2));

        add(panel);
        setVisible(true);

    }
    public void actionPerformance(ActionEvent e){
        if(e.getSource()==buttonLogin){
            dispose();
            Login l = new Login();
            l.loginPage();
        }
        if(e.getSource()==buttonSingUp){
            dispose();
            new SingUp();
        }
    }

    public static void main(String[] args) {
        new FirstPage();
    }
}