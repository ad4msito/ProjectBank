package GUI;

import Controlador.Cuenta;
import Service.CuentaService;
import Exceptions.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CuentaGUI extends JFrame {
    private JFrame ventanaPrincipal = new JFrame();
    private JPanel panelIzquierda = new JPanel();
    private JPanel panelDerecha = new JPanel();
    private String[] nombresColumnas = {"ALIAS", "CUENTA", "SALDO", "USUARIO"};
    private DefaultTableModel model = new DefaultTableModel(nombresColumnas, 0);
    private JTable tabla = new JTable(model);
    JScrollPane scrollPane = new JScrollPane(tabla);
    private CuentaService cuentaService = new CuentaService();
    private Cuenta cuenta = new Cuenta();

    public void ventana() {
        ventanaPrincipal.setSize(500, 500);
        ventanaPrincipal.setLocationRelativeTo(null);
        ventanaPrincipal.setTitle("Account Manager");
        ventanaPrincipal.setBackground(new Color(100, 150, 200));
        ventanaPrincipal.setVisible(true);
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //PANELES
    public void paneles() {

        panelIzquierda.setBackground(new Color(100, 100, 100));
        panelIzquierda.setBounds(500, 500, 150, 150);

        ventanaPrincipal.getContentPane().add(panelIzquierda, BorderLayout.SOUTH);
        //////////////////////////
        panelDerecha.setBackground(new Color(120, 120, 120));
        ventanaPrincipal.add(panelDerecha);

        // panelArriba.setBackground(new Color(20, 240, 230));

    }

    public void botones() {
        JButton button1 = new JButton("AGREGAR");
        button1.setSize(200, 500);
        panelIzquierda.add(button1);
        button1.addActionListener(e -> actionsAgregar());

        JButton button2 = new JButton("ELIMINAR");
        panelIzquierda.add(button2);
        button2.addActionListener(e -> actionEliminar());
        JButton button3 = new JButton("MODIFICAR");
        panelIzquierda.add(button3);
        button3.addActionListener(e -> actionModificar());
        JButton button4 = new JButton("LISTAR");
        panelIzquierda.add(button4);
        button4.addActionListener(e -> {
            try {
                actionMostrarTodos();
            } catch (ServiceException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void tabla() {
        panelDerecha.add(scrollPane);
    }

    private void actionsAgregar() {
        //ventana
        JFrame ventanaAgregar = new JFrame("Agregar cuenta");
        ventanaAgregar.setLayout(new FlowLayout());
        ventanaAgregar.setLocationRelativeTo(null);
        ventanaAgregar.setSize(400, 200);
        ventanaAgregar.setBackground(new Color(50, 50, 50));
        //paneles
        JPanel jp1 = new JPanel();
        jp1.setLayout(new GridLayout(4, 0, 10, 10));
        ventanaAgregar.add(jp1);
        JPanel jp2 = new JPanel();
        jp2.setLayout(new GridLayout(4, 0, 10, 10));
        ventanaAgregar.add(jp2);
        JPanel jpDown = new JPanel();
        ventanaAgregar.getContentPane().add(jpDown, BorderLayout.SOUTH);
        //cajas de texto
        JTextField tf1 = new JTextField(20);
        tf1.setBounds(10, 10, 100, 30);
        JTextField tf2 = new JTextField(3);
        tf2.setSize(100, 30);
        JTextField tf3 = new JTextField(20);
        tf3.setSize(100, 30);
        JTextField tf4 = new JTextField(20);
        tf1.setBounds(10, 10, 100, 30);

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
        ventanaAgregar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaAgregar.setVisible(true);

        ActionListener actionInsertar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountID = tf4.getText();
                String accountType = tf1.getText();
                float saldo = Float.parseFloat(tf2.getText());
                long user = Long.parseLong(tf3.getText());
                try {
                    cuentaService.insertarService(cuentaService.convertirObjeto(accountID, accountType, saldo, user));
                    JOptionPane.showMessageDialog(ventanaAgregar, "La acción se realizó con éxito");
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(ventanaAgregar, "Error en la capa de servicios:" + ex.getMessage());
                }
            }
        };
        bt1.addActionListener(actionInsertar);

    }

    private void actionEliminar() {
        JFrame ventanaEliminar = new JFrame("Eliminar cuenta");
        ventanaEliminar.setLayout(new FlowLayout());
        ventanaEliminar.setLocationRelativeTo(null);
        ventanaEliminar.setSize(400, 100);
        ventanaEliminar.setBackground(new Color(50, 50, 50));

        JPanel jp1 = new JPanel();
        jp1.setLayout(new GridLayout(1, 0, 10, 10));
        ventanaEliminar.add(jp1);
        JPanel jp2 = new JPanel();
        jp2.setLayout(new GridLayout(1, 0, 10, 10));
        ventanaEliminar.add(jp2);
        JPanel jpDown = new JPanel();
        ventanaEliminar.getContentPane().add(jpDown, BorderLayout.SOUTH);
        //caja d texto
        JTextField tf1 = new JTextField(20);
        tf1.setBounds(10, 10, 100, 30);
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
                    JOptionPane.showMessageDialog(ventanaEliminar, "La acción se realizó con éxito");
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(ventanaEliminar, "Error en la capa de servicios:" + ex.getMessage());
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

    private void actionModificar() {
        //ventana
        JFrame ventanaModificar = new JFrame("Modificar cuenta");
        ventanaModificar.setLayout(new FlowLayout());
        ventanaModificar.setLocationRelativeTo(null);
        ventanaModificar.setSize(700, 200);
        ventanaModificar.setBackground(new Color(50, 50, 50));
        //paneles
        JPanel jp1 = new JPanel();
        jp1.setLayout(new GridLayout(4, 1, 0, 10));
        ventanaModificar.add(jp1);
        JPanel jp2 = new JPanel();
        jp2.setLayout(new GridLayout(4, 1, 0, 10));
        ventanaModificar.add(jp2);
        JPanel jpDown = new JPanel();
        ventanaModificar.getContentPane().add(jpDown, BorderLayout.SOUTH);
        //cajas de texto
        JTextField tf1 = new JTextField(20);
        tf1.setBounds(10, 10, 100, 30);
        JTextField tf2 = new JTextField(20);
        tf2.setSize(100, 30);
        JTextField tf3 = new JTextField(20);
        tf3.setSize(100, 30);
        JTextField tf4 = new JTextField(20);
        tf1.setBounds(10, 10, 100, 30);


        //botones
        JButton bt1 = new JButton("ACEPTAR"); //ACCION
        jpDown.add(bt1);

        //etiquetas de texto
        JLabel l4 = new JLabel("ALIAS DE LA CUENTA:");
        l4.setVisible(true);
        JLabel l1 = new JLabel("TIPO DE CUENTA(modificar):");
        l1.setVisible(true);
        JLabel l2 = new JLabel("SALDO(modificar):");
        l2.setVisible(true);
        JLabel l3 = new JLabel("USUARIO(modificar):");
        l3.setVisible(true);
        //añadir todo a los panels
        jp1.add(l4);
        jp2.add(tf4);
        jp1.add(l1);
        jp2.add(tf1); // Añadir el primer campo de texto
        jp1.add(l2);
        jp2.add(tf2); // Añadir el segundo campo de texto
        jp1.add(l3);
        jp2.add(tf3);
        ventanaModificar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaModificar.setVisible(true);

        ActionListener actionModificar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountID = tf4.getText();
                String accountType = tf1.getText();
                float saldo = Float.parseFloat(tf2.getText());
                long user = Long.parseLong(tf3.getText());
                try {
                    cuentaService.actualizarService(cuentaService.convertirObjeto(accountID, accountType, saldo, user));
                    JOptionPane.showMessageDialog(ventanaModificar, "La acción se realizó con éxito");
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(ventanaModificar, "Error en la capa de servicios:" + ex.getMessage());
                }

            }
        };
        bt1.addActionListener(actionModificar);

    }

    private void actionMostrarTodos() throws ServiceException {
        model.setRowCount(0);
        //jp1.add(scroll);
        List<Cuenta> cuentas = cuentaService.obtenerTodosService();
        for (Cuenta a : cuentas) {
            model.addRow(new Object[]{a.getAccountID(), a.getAccountType(), a.getSaldo(), a.getUserID()});
        }
    }
}