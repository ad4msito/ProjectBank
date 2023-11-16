package GUI;

import Controlador.Cuenta;
import DAO.DAOException;
import Service.CuentaService;

import javax.swing.*;
import java.awt.*;

public class CuentaGUI extends JFrame {

    private JTextField campoAccountId;
    private JTextField campoAccountType;
    private JTextField campoSaldo;
    private JTextField campoUsuario;
    private CuentaService cuentaService;

    public CuentaGUI(CuentaService cuentas) {
    }

    public static void main(String[] args) {
        MyFrame ventana = new MyFrame();
        JButton button1 = new JButton();
        JPanel izquierdo = new JPanel();
        JPanel derecho = new JPanel();
        JPanel arriba = new JPanel();
        JPanel abajo = new JPanel();
        ventana.add(izquierdo);
        ventana.add(derecho);
        ventana.add(arriba);
        ventana.add(abajo);
        derecho.add(button1);
        button1.setBounds(5,5,100,100);
        ventana.setLayout(new FlowLayout());
        izquierdo.setBackground(new Color(0,200,255));
        izquierdo.setPreferredSize(new Dimension(125,125));
        derecho.setBackground(new Color(0,200,200));
        derecho.setPreferredSize(new Dimension(125,125));
        arriba.setBackground(new Color(0,100,200));
        arriba.setPreferredSize(new Dimension(125,125));
        abajo.setBackground(new Color(100,0,200));
        abajo.setPreferredSize(new Dimension(125,125));

    }
}