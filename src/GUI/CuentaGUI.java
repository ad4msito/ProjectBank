package GUI;

import Controlador.Cuenta;
import DAO.DAOException;
import Service.CuentaService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CuentaGUI extends JFrame {
    private JFrame ventanaPrincipal = new JFrame();
    private  JPanel panelIzquierda = new JPanel();
    private   JPanel panelDerecha = new JPanel();

    private CuentaService cuentaService = new CuentaService();
    private Cuenta cuenta = new Cuenta();
    private void ventana () {
        ventanaPrincipal.setSize(500, 500);
        ventanaPrincipal.setLocationRelativeTo(null);
        ventanaPrincipal.setBackground(new Color(100,150,200));
        ventanaPrincipal.setVisible(true);
    }
        //PANELES
    private void paneles() {

        panelIzquierda.setBackground(new Color(100, 100, 100));
        panelIzquierda.setBounds(500,500,150,150);

        ventanaPrincipal.getContentPane().add(panelIzquierda, BorderLayout.SOUTH);
        //////////////////////////
        panelDerecha.setBackground(new Color(120, 120, 120));
        //panelDerecha.setBounds(150,0,150,150);
        ventanaPrincipal.getContentPane().add(panelDerecha, BorderLayout.CENTER);


       // panelArriba.setBackground(new Color(20, 240, 230));

    }

    private void botones(){
        JButton button1 = new JButton("AGREGAR");
        button1.setSize(200,500);
        panelIzquierda.add(button1);
        button1.addActionListener(e -> actionsAgregar());

        JButton button2 = new JButton("ELIMINAR");
        panelIzquierda.add(button2);
        button2.addActionListener(e -> ActionEliminar());
        JButton button3 = new JButton("MODIFICAR");
        panelIzquierda.add(button3);
        JButton button4 = new JButton("LISTAR");
        panelIzquierda.add(button4);
    }
    private void actionsAgregar(){
        //ventana
        JFrame ventanaAgregar = new JFrame("Agregar cuenta");
        ventanaAgregar.setLayout(new FlowLayout());
        ventanaAgregar.setLocationRelativeTo(null);
        ventanaAgregar.setSize(400, 200);
        ventanaAgregar.setBackground(new Color(50,50,50));
        //paneles
        JPanel jp1 = new JPanel();
        jp1.setLayout(new GridLayout(4,0,10,10));
        ventanaAgregar.add(jp1);
        JPanel jp2 = new JPanel();
        jp2.setLayout(new GridLayout(4,0,10,10));
        ventanaAgregar.add(jp2);
        JPanel jpDown = new JPanel();
        ventanaAgregar.getContentPane().add(jpDown, BorderLayout.SOUTH);
        //cajas de texto
        JTextField tf1 = new JTextField(20);
        tf1.setBounds(10,10,100,30);
        JTextField tf2 = new JTextField(20);
        tf2.setSize(100,30);
        JTextField tf3 = new JTextField(20);
        tf3.setSize(100,30);
        JTextField tf4 = new JTextField(20);
        tf1.setBounds(10,10,100,30);

        //botones
        JButton bt1 = new JButton("ACEPTAR"); //ACCION
        jpDown.add(bt1);

        //etiquetas de texto
        JLabel l4 = new JLabel("ALIAS:");
        l4.setVisible(true);
        JLabel l1 = new JLabel("TIPO DE CUENTA:");
        l1.setVisible(true);
        JLabel l2 = new JLabel("SALDO:");
        l2.setVisible(true);
        JLabel l3 = new JLabel("USUARIO:");
        l3.setVisible(true);
        //añadir todo a los panels
        jp1.add(l4);
        jp2.add(tf4);
        jp1.add(l1);
        jp2.add(tf1); // Añadir el primer campo de texto
        jp1.add(l2);
        jp2.add(tf2); // Añadir el segundo campo de texto
        jp1.add(l3);
        jp2.add(tf3); // Añadir el tercer campo de texto
        ventanaAgregar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaAgregar.setVisible(true);

        ActionListener actionInsertar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountID = tf4.getText();
                String accountType = tf1.getText();
                float saldo = Float.parseFloat(tf2.getText());
                long user = Long.parseLong(tf3.getText());
                try {
                    cuentaService.insertarService(cuentaService.convertirObjeto(accountID,accountType,saldo,user));
                } catch (DAOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
        bt1.addActionListener(actionInsertar);

    }
    private void ActionEliminar(){
        JFrame ventanaEliminar = new JFrame("Eliminar cuenta");
        ventanaEliminar.setLayout(new FlowLayout());
        ventanaEliminar.setLocationRelativeTo(null);
        ventanaEliminar.setSize(400, 100);
        ventanaEliminar.setBackground(new Color(50,50,50));

        JPanel jp1 = new JPanel();
        jp1.setLayout(new GridLayout(1,0,10,10));
        ventanaEliminar.add(jp1);
        JPanel jp2 = new JPanel();
        jp2.setLayout(new GridLayout(1,0,10,10));
        ventanaEliminar.add(jp2);
        JPanel jpDown = new JPanel();
        ventanaEliminar.getContentPane().add(jpDown, BorderLayout.SOUTH);
        //caja d texto
        JTextField tf1 = new JTextField(20);
        tf1.setBounds(10,10,100,30);
        //etiquetas de texto
        JLabel jl1 = new JLabel("ALIAS:");
        //botones
        JButton bt1 = new JButton("ACEPTAR"); //ACCION
        jpDown.add(bt1);
        ActionListener actionEliminar = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = tf1.getText();
                try {
                    cuentaService.eliminarService(id);
                } catch (DAOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
        bt1.addActionListener(actionEliminar);
        //añadir cosas
        jp2.add(tf1);
        jp1.add(jl1);
        ventanaEliminar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaEliminar.setVisible(true);

    }
    public static void main(String[] args) {
        CuentaGUI a = new CuentaGUI();
        a.ventana();
        a.ventana();
        a.paneles();
        a.botones();
    }
}