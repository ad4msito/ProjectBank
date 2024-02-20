package GUI;

import Controlador.Cuenta;
import Controlador.Tarjeta;
import Controlador.Transacciones.TransaccionCuenta;
import Controlador.UsuarioCuenta;
import Exceptions.ServiceException;
import Service.CuentaService;
import Service.TarjetaService;
import Service.TransaccionCuentaService;
import Service.UsuarioCuentaService;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VentanaCruds extends JFrame {
    private UsuarioCuentaService usuarioService;
    private CuentaService cuentaService;
    private TarjetaService tarjetaService;
    private TransaccionCuentaService transaccionCuentaService;
    private TablaGenerica tablaGenerica;
    private JPanel panelPrincipal;
    private JPanel vista;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private  JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JCheckBox checkBox1;
    private JButton botonCrear;
    private JButton botonEditar;
    private JButton botonEliminar;
    private Color color1;
    private Color color2;
    private int service; private int button;

    public VentanaCruds(int service, int button) {
        usuarioService = new UsuarioCuentaService();
        cuentaService = new CuentaService();
        tarjetaService = new TarjetaService();
        transaccionCuentaService = new TransaccionCuentaService();
        color1 = new Color(50,63,117); color2 = new Color(250,113,95);

        setBounds(0,0,400,400);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Admin");
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        add(panelPrincipal);
        if(service == 0){
            vistaUsuario(button);
        } if ( service == 1) {
            vistaCuenta(button);
        } if (service == 2){
            vistaTarjeta(button);
        } if (service == 3) {
            vistaTransferencias(button);
        }
        this.service = service;
        this.button = button;
        setVisible(true);
    }
    public void vistaUsuario(int button){
        vista = new JPanel();
        vista.setLayout(null);
        vista.setBounds(0, 0, 400, 400);
        vista.setBackground(color1);

        if (button == 1||button == 2) {
            textField1 = new JTextField("Nombre");
            textField1.setBounds(120, 80, 160, 30);
            vista.add(textField1);

            textField2 = new JTextField("email@example.com");
            textField2.setBounds(120, 140, 160, 30);
            vista.add(textField2);

            textField3 = new JPasswordField("password");
            textField3.setBounds(120, 200, 160, 30);
            vista.add(textField3);

            checkBox1 = new JCheckBox("Es admin?");
            checkBox1.setBackground(color1);
            checkBox1.setForeground(Color.white);
            checkBox1.setBounds(120, 240, 160, 30);
            vista.add(checkBox1);
            if (button == 1) {
                botonCrear = new JButton("Crear");
                botonCrear.setBounds(200, 280, 80, 30);
                botonCrear.addActionListener(this::actionPerformance);
                vista.add(botonCrear);
            }
            if (button == 2) {
                textField4 = new JTextField("id");
                textField4.setBounds(120,20,160,30);
                vista.add(textField4);

                botonEditar = new JButton("Editar");
                botonEditar.setBounds(200,280,80,30);
                botonEditar.addActionListener(this::actionPerformance);
                vista.add(botonEditar);
            }
        }

        if(button == 3){
            textField1 = new JTextField("id del usuario");
            textField1.setBounds(120, 155, 160,30);
            vista.add(textField1);

            botonEliminar = new JButton("Eliminar");
            botonEliminar.addActionListener(this::actionPerformance);
            botonEliminar.setBounds(200,205,80,30);
            vista.add(botonEliminar);
        }
        panelPrincipal.add(vista);
    }
    public void vistaCuenta(int button){
        vista = new JPanel();
        vista.setLayout(null);
        vista.setBounds(0, 0, 400, 400);
        vista.setBackground(color2);
        if(button == 1||button==2){
            textField1 = new JTextField("Alias");
            textField1.setBounds(120,30,160,30);
            vista.add(textField1);

            textField2 = new JTextField("Saldo");
            textField2.setBounds(120,70,160,30);
            vista.add(textField2);

            textField3 = new JTextField("Tipo cuenta");
            textField3.setBounds(120,110,160,30);
            vista.add(textField3);

            textField4 = new JTextField("id del usuario");
            textField4.setBounds(120,150,160,30);
            vista.add(textField4);

            textField5 =new JTextField("CBU");
            textField5.setBounds(120,190,160,30);
            vista.add(textField5);
            if (button==1) {
                botonCrear = new JButton("Crear");
                botonCrear.setBounds(200, 230, 80, 30);
                botonCrear.addActionListener(this::actionPerformance);
                vista.add(botonCrear);
            }
            if (button == 2){
                textField6 = new JTextField("ID de la cuenta");
                textField6.setBounds(120,230,160,30);
                vista.add(textField6);
                botonEditar = new JButton("Editar");
                botonEditar.setBounds(200,270,80,30);
                botonEditar.addActionListener(this::actionPerformance);
                vista.add(botonEditar);

            }
        } if(button == 3){
            textField1 = new JTextField("id de la Cuenta");
            textField1.setBounds(120, 155, 160,30);
            vista.add(textField1);

            botonEliminar = new JButton("Eliminar");
            botonEliminar.addActionListener(this::actionPerformance);
            botonEliminar.setBounds(200,205,80,30);
            vista.add(botonEliminar);
        }
        panelPrincipal.add(vista);
    }
    public void vistaTarjeta(int button){
        vista = new JPanel();
        vista.setLayout(null);
        vista.setBounds(0,0,400,400);
        vista.setBackground(color1);
        if(button==1||button==2) {
            textField1 = new JTextField("Numero:");
            textField1.setBounds(120, 105, 160, 30);
            vista.add(textField1);

            textField2 = new JTextField("Limite");
            textField2.setBounds(120, 145, 160, 30);
            vista.add(textField2);

            textField3 = new JTextField("ID USUARIO");
            textField3.setBounds(120, 185, 160, 30);
            vista.add(textField3);

            textField4 = new JTextField("Deuda");
            textField4.setBounds(120, 225, 160, 30);
            vista.add(textField4);
            if (button == 1) {
                botonCrear = new JButton("Crear");
                botonCrear.setBounds(200, 265, 80, 30);
                botonCrear.addActionListener(this::actionPerformance);
                vista.add(botonCrear);
            }
            if (button == 2) {
                textField5 = new JTextField("Id tarjeta");
                textField5.setBounds(120,65,160,30);
                vista.add(textField5);

                botonEditar = new JButton("Editar");
                botonEditar.setBounds(200,265,80,30);
                botonEditar.addActionListener(this::actionPerformance);
                vista.add(botonEditar);
            }
        }
        if (button == 3){
            textField1 =new JTextField("id de la tarjeta:");
            textField1.setBounds(120,170,160,30);
            vista.add(textField1);

            botonEliminar = new JButton("Eliminar");
            botonEliminar.setBounds(120,230, 80,30);
            botonEliminar.addActionListener(this::actionPerformance);
            vista.add(botonEliminar);
        }
        panelPrincipal.add(vista);
    }
    public void vistaTransferencias(int button){
        vista = new JPanel();
        vista.setLayout(null);
        vista.setBounds(0, 0, 400, 400);
        vista.setBackground(color2);
        if(button==1||button==2) {
            textField2 = new JTextField("Monto");
            textField2.setBounds(120,90,160,30);
            vista.add(textField2);

            textField3 = new JTextField("Origen");
            textField3.setBounds(120,150,160,30);
            vista.add(textField3);

            textField4 = new JTextField("Destino");
            textField4.setBounds(120,210,160,30);
            vista.add(textField4);
            if (button==1){
                botonCrear = new JButton("Crear");
                botonCrear.setBounds(200,270,80,30);
                botonCrear.addActionListener(this::actionPerformance);
                vista.add(botonCrear);
            }
            if (button == 2){
                textField1 = new JTextField("ID transferencia");
                textField1.setBounds(120, 30, 160,30);
                vista.add(textField1);

                botonEditar = new JButton("Editar");
                botonEditar.addActionListener(this::actionPerformance);
                botonEditar.setBounds(200,270,80,30);
                vista.add(botonEditar);
            }
        }
        if(button == 3) {
            textField1 = new JTextField("ID transferencia");
            textField1.setBounds(120, 210, 160, 30);
            vista.add(textField1);

            botonEliminar = new JButton("Eliminar");
            botonEliminar.addActionListener(this::actionPerformance);
            botonEliminar.setBounds(200, 270, 80, 30);
            vista.add(botonEliminar);
        }
        panelPrincipal.add(vista);
    }
    public void actionPerformance(ActionEvent e){
        if(service==0) {
            if (e.getSource() == botonCrear) {
                String nombre = textField1.getText();
                String email = textField2.getText();
                String pass = textField3.getText();
                Boolean esAdmin = checkBox1.isSelected();
                UsuarioCuenta usuario = new UsuarioCuenta(nombre, email, pass, esAdmin);
                try {
                    usuarioService.create(usuario);
                    JOptionPane.showMessageDialog(null, "Usuario creado.");
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, "Error al crear el usuario" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource() == botonEditar) {
                String nombre = textField1.getText();
                String email = textField2.getText();
                String pass = textField3.getText();
                Boolean esAdmin = checkBox1.isSelected();
                Long id = Long.valueOf(textField4.getText());
                UsuarioCuenta usuario = new UsuarioCuenta(id, nombre, email, pass, esAdmin);
                try {
                    usuarioService.update(usuario);
                    JOptionPane.showMessageDialog(null, "Usuario editado.");
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, "Error al editar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource() == botonEliminar) {
                Long id = Long.valueOf(textField1.getText());
                try {
                    usuarioService.delete(id);
                    JOptionPane.showMessageDialog(null, "Usuario eliminado.");
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if(service==1){
            if (e.getSource()==botonCrear){
                String alias = textField1.getText();
                Double saldo = Double.valueOf(textField2.getText());
                int tipo = Integer.parseInt(textField3.getText());
                Long idUser = Long.valueOf(textField4.getText());
                int cbu = Integer.parseInt(textField5.getText());
                Cuenta crearCuenta = new Cuenta(alias, saldo, tipo, idUser, cbu);
                try {
                    cuentaService.create(crearCuenta);
                    JOptionPane.showMessageDialog(null, "Cuenta creada.");
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, "Error al crear cuenta");
                }
            }
            if (e.getSource()==botonEditar){
                try {
                    String alias = textField1.getText();
                    Double saldo = Double.valueOf(textField2.getText());
                    int tipo = Integer.parseInt(textField3.getText());
                    Long idUser = Long.valueOf(textField4.getText());
                    int cbu = Integer.parseInt(textField5.getText());
                    Long id = Long.valueOf(textField6.getText());
                    Cuenta editarCuenta = new Cuenta(id, alias, saldo, tipo, idUser, cbu);
                    cuentaService.update(editarCuenta);
                    JOptionPane.showMessageDialog(null,"Cuenta editada.");
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, "Error al editar la cuenta");
                }
            }
            if (e.getSource()==botonEliminar){
                Long id2delete = Long.valueOf(textField1.getText());
                try {
                    cuentaService.delete(id2delete);
                    JOptionPane.showMessageDialog(null, "Cuenta eliminada.");
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar la cuenta");
                }
            }
        }
        if(service==2){
            if (e.getSource()==botonCrear){
                int numero = Integer.parseInt(textField1.getText());
                Double limite = Double.valueOf(textField2.getText());
                Long usuario = Long.valueOf(textField3.getText());
                Double deuda = Double.valueOf(textField4.getText());
                Tarjeta crearTarjeta = new Tarjeta(numero, limite, usuario, deuda);
                try {
                    tarjetaService.create(crearTarjeta);
                    JOptionPane.showMessageDialog(null, "Tarjeta creada con exito");
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, "Error al crear usuario");
                }

            }
            if(e.getSource()==botonEditar){
                Long id = Long.valueOf(textField5.getText());
                int numero = Integer.parseInt(textField1.getText());
                Double limite = Double.valueOf(textField2.getText());
                Long usuario = Long.valueOf(textField3.getText());
                Double deuda = Double.valueOf(textField4.getText());
                Tarjeta editarTarjeta = new Tarjeta(id, numero, limite, usuario, deuda);
                try {
                    tarjetaService.update(editarTarjeta);
                    JOptionPane.showMessageDialog(null, "Tarjeta editada con exito");
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null,"Error al editar Tarjeta.");
                }
            }
            if(e.getSource()==botonEliminar){
                Long id = Long.valueOf(textField1.getText());
                try {
                    tarjetaService.delete(id);
                    JOptionPane.showMessageDialog(null, "Tarjeta eliminada.");
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar tarjeta.");
                }
            }
        }
        if(service==3){
            if(e.getSource()==botonCrear){
                Double monto = Double.valueOf(textField2.getText());
                Long origen = Long.valueOf(textField3.getText());
                Long destino = Long.valueOf(textField4.getText());
                TransaccionCuenta crearTransaccion = new TransaccionCuenta(monto, origen, destino);
                try {
                    transaccionCuentaService.create(crearTransaccion);
                    JOptionPane.showMessageDialog(null, "Transaccion creada con exito.");
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, "Error al crear la transaccion");
                }

            }
            if(e.getSource()==botonEditar){
                Long id = Long.valueOf(textField1.getText());
                Double monto = Double.valueOf(textField2.getText());
                Long origen = Long.valueOf(textField3.getText());
                Long destino = Long.valueOf(textField4.getText());
                TransaccionCuenta editarTransaccion = new TransaccionCuenta(id, monto, origen, destino);
                try {
                    transaccionCuentaService.update(editarTransaccion);
                    JOptionPane.showMessageDialog(null, "Transaccion editada con exito.");
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, "Error al editar la transaccion");
                }
            }
            if (e.getSource()==botonEliminar){
                Long id = Long.valueOf(textField1.getText());
                try {
                    transaccionCuentaService.delete(id);
                    JOptionPane.showMessageDialog(null, "Transaccion eliminada con exito.");
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar la transaccion");
                }
            }
        }
    }

}
