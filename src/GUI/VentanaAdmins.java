package GUI;

import Controlador.Cuenta;
import Controlador.Tarjeta;
import Controlador.Transacciones.TransaccionCuenta;
import Controlador.Transacciones.TransaccionTarjeta;
import Controlador.UsuarioCuenta;
import Exceptions.ServiceException;
import Service.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;

public class VentanaAdmins extends JFrame {
    private UsuarioCuentaService usuarioService;
    private CuentaService cuentaService;
    private TarjetaService tarjetaService;
    private TransaccionCuentaService transaccionCuentaService;
    private TransaccionTarjetaService  transaccionTarjetaService;
    private JPanel panelPrincipal;
    private JPanel panel1;
    private JButton boton1;
    private JButton boton2;
    private JButton boton3;
    private JButton boton4;
    private JButton botonSalir;
    private JComboBox<String> comboBox1;
    private int width;
    private int height;
    private Color color1;
    private Color color2;
    private Color color3;
    private Color color4;
    private int selectedIndex;

    public VentanaAdmins() {
        usuarioService = new UsuarioCuentaService();
        cuentaService = new CuentaService();
        tarjetaService = new TarjetaService();
        transaccionCuentaService = new TransaccionCuentaService();
        transaccionTarjetaService = new TransaccionTarjetaService();
        width = 800;
        height = 800;
        color1 = new Color(126,160,95);
        color2 = new Color(213,219,245);
        color3 = new Color(50,63,117);
        color4 = new Color(245,220,213);
        this.setBounds(0,0,width,height);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        setTitle("Admin");
        panelPrincipal = new JPanel();

        panel1();
        this.add(panelPrincipal);
        this.setVisible(true);
    }
    public void panel1(){
        selectedIndex = -1;
        panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setPreferredSize(new Dimension(width,height));
        panel1.setBackground(color1);

        comboBox1 = new JComboBox<>();
        llenarJComboBox(comboBox1);
        comboBox1.setSelectedIndex(-1);
        comboBox1.setBounds(250,110,300,50);
        comboBox1.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                selectedIndex = comboBox1.getSelectedIndex();
            }
        });
        panel1.add(comboBox1);

        boton1 = new JButton("Crear");
        boton1.setBounds(250,239,300,40);
        boton1.addActionListener(this::actionPerformance);
        panel1.add(boton1);

        boton2 = new JButton("Editar");
        boton2.setBounds(250,353,300,40);
        boton2.addActionListener(this::actionPerformance);
        panel1.add(boton2);

        boton3 = new JButton("Eliminar");
        boton3.setBounds(250,467,300,40);
        boton3.addActionListener(this::actionPerformance);
        panel1.add(boton3);

        boton4 = new JButton("Leer");
        boton4.setBounds(250,581,300,40);
        boton4.addActionListener(this::actionPerformance);
        panel1.add(boton4);

        botonSalir = new JButton("Salir");
        botonSalir.setBounds(30,680,150,40);
        botonSalir.addActionListener(this::actionPerformance);
        panel1.add(botonSalir);

        panelPrincipal.add(panel1);
    }
    public void actionPerformance(ActionEvent e){
        if(e.getSource() == boton1 ){
            new VentanaCruds(selectedIndex, 1);
        } if (e.getSource()==boton2){
            new VentanaCruds(selectedIndex, 2);
        } if(e.getSource() == boton3){
            new VentanaCruds(selectedIndex, 3);
        }
        if(e.getSource()==boton4){
            if(selectedIndex == 0){
                List<UsuarioCuenta> usuarios = null;
                try{
                    usuarios = usuarioService.readAll();
                } catch (ServiceException e1){
                    JOptionPane.showMessageDialog(null,"Error al encontrar informacion", "Error", JOptionPane.ERROR_MESSAGE);
                }
                List<Object[]> datos = new ArrayList<>();
                for (UsuarioCuenta usuarioCuenta: usuarios){
                    datos.add(new Object[]{usuarioCuenta.getId(),usuarioCuenta.getNombre(), usuarioCuenta.getEmail(), usuarioCuenta.getPassword(), usuarioCuenta.getEsAdmin()});
                }
                Object[] columnas = new Object[]{"ID", "Nombre", "Email", "Password", "es admin"};
                TablaGenerica tablaUsuarios = new TablaGenerica(datos, columnas);
            }
            if(selectedIndex == 1){
                List<Cuenta> cuentas = null;
                try {
                    cuentas = cuentaService.readAll();
                } catch (ServiceException e1){
                    JOptionPane.showMessageDialog(null, "Error al encontrar informacion","Error", JOptionPane.ERROR_MESSAGE );
                }
                List<Object[]> datos = new ArrayList<>();
                for (Cuenta cuenta: cuentas){
                    datos.add(new Object[]{cuenta.getId(),cuenta.getSaldo(), cuenta.getAlias(), cuenta.getTipoCuenta(), cuenta.getUsuarioCuenta(), cuenta.getCbu()});
                }
                Object[] columnas = new Object[]{"ID", "SALDO", "ALIAS", "TIPO DE CUENTA", " ID USUARIO","CBU"};
                TablaGenerica tablaCuentas = new TablaGenerica(datos, columnas);
            }
            if(selectedIndex == 2){
                List<Tarjeta> tarjetas = null;
                try {
                    tarjetas = tarjetaService.readAll();
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null,"Error al encontrar datos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                List<Object[]> datos = new ArrayList<>();
                for (Tarjeta tarjeta: tarjetas){
                    datos.add(new Object[]{tarjeta.getId(),tarjeta.getSaldoAdeudado(),tarjeta.getLimite(),tarjeta.getUsuarioID(),tarjeta.getNumero()});
                }
                Object[] columnas = new Object[]{"ID", "SALDO ADEUDADO", "LIMITE", "ID USUARIO", "NUMERO"};
                TablaGenerica tablaTarjetas = new TablaGenerica(datos, columnas);
            }
            if(selectedIndex == 3){
                List<TransaccionCuenta> transacciones = null;
                try {
                    transacciones = transaccionCuentaService.readAll();
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null,"Error al encontrar los datos", "Erroir", JOptionPane.ERROR_MESSAGE);
                }
                List<Object[]> datos = new ArrayList<>();
                for (TransaccionCuenta transaccionCuenta: transacciones){
                    datos.add(new Object[]{transaccionCuenta.getId(),transaccionCuenta.getCuentaOrigen(), transaccionCuenta.getCuentaDestino(), transaccionCuenta.getMonto(), transaccionCuenta.getFecha()});
                }
                Object[] columnas = new Object[]{"ID", "ORIGEN", "DESTINO", "MONTO", "FECHA"};
                TablaGenerica tablaTransaccionesCuenta = new TablaGenerica(datos, columnas);
            }
            if(selectedIndex == 4){
                List<TransaccionTarjeta> transacciones = null;
                try {
                    transacciones = transaccionTarjetaService.readAll();
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null,"Error al encontrar datos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                List<Object[]> datos= new ArrayList<>();
                for (TransaccionTarjeta transaccion: transacciones){
                    datos.add(new Object[]{transaccion.getId(), transaccion.getTarjetaOrigen(), transaccion.getCuentaDestino(), transaccion.getMonto(), transaccion.getFecha()});
                }
                Object[] columnas = new Object[]{"ID", "TARJETA ORIGEN", "DESTINTO", "MONTO", "FECHA"};
                TablaGenerica tablaDebitos = new TablaGenerica(datos,columnas);
            }
        }
        if(e.getSource()==botonSalir){
            dispose();
            new VentanaUsers();
        }
    }
    public void llenarJComboBox(JComboBox<String> comboBox1){
        comboBox1.addItem("Usuarios");
        comboBox1.addItem("Cuentas");
        comboBox1.addItem("Tarjetas");
        comboBox1.addItem("Transferencias");
        comboBox1.addItem("Debitos");
    }

    public static void main(String[] args) {
        new VentanaAdmins();
    }
}