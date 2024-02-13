package GUI;


import GUI.AdminButtons.*;
import GUI.Logos.ObtenerImagen;
import Service.UsuarioCuentaService;
import sun.rmi.runtime.Log;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JFrame {
        private ObtenerImagen obtenerImagen;
        private UsuarioCuentaService usuarioCuentaService;
        private Long userID;
        public AdminPanel(Long s) {
            userID = s;
            obtenerImagen = new ObtenerImagen();
            this.usuarioCuentaService = new UsuarioCuentaService();
            int width = 600;
            int height = 350;
            int fontSize = 20;

            setTitle("vista de Administrador");
            setBounds(300, 90, width, height);
            setIconImage(new ImageIcon(obtenerImagen.obtener2()).getImage());
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);

            JPanel panelPrincipal = new JPanel();
            panelPrincipal.setBackground(new Color(109,152,173));
            panelPrincipal.setPreferredSize(new Dimension(width,height));
            panelPrincipal.setLayout(new BorderLayout());

            JPanel panelSur = new JPanel();
            panelSur.setOpaque(false);
            panelSur.setPreferredSize(new Dimension(width,height/7));
            panelSur.setLayout(new GridLayout(1,5));

            JButton createUsuarioButton = new JButton("Usuario(+)");
            createUsuarioButton.setFont(new Font("Arial", Font.BOLD,fontSize));
            createUsuarioButton.setBounds(10,40,200,50);

            JButton readUsuarioButton = new JButton("Ver usuarios");
            readUsuarioButton.setFont(new Font("Arial", Font.BOLD,fontSize));
            readUsuarioButton.setBounds(220,40,160,50);

            JButton createCuentaButton = new JButton("Cuenta(+)");
            createCuentaButton.setFont(new Font("Arial",Font.BOLD,fontSize));
            createCuentaButton.setBounds(10,100,200,50);

            JButton readCuentaButton = new JButton("Ver cuentas");
            readCuentaButton.setFont(new Font("Arial", Font.BOLD,fontSize));
            readCuentaButton.setBounds(220,100,160,50);

            JButton botonResumenCuenta = new JButton("Resumen Cuenta");
            botonResumenCuenta.setFont(new Font("Arial",Font.BOLD,fontSize));
            botonResumenCuenta.setBounds(390,100,200,50);

            JButton createTarjetaButton = new JButton("Tarjeta(+)");
            createTarjetaButton.setFont(new Font("Arial",Font.BOLD,fontSize));
            createTarjetaButton.setBounds(10,160,200,50);

            JButton readTarjetaButton = new JButton("Ver tarjetas");
            readTarjetaButton.setFont(new Font("Arial", Font.BOLD,fontSize));
            readTarjetaButton.setBounds(220,160,160,50);

            JButton botonDebitoAutomatico = new JButton("Debitar Tarjeta");
            botonDebitoAutomatico.setFont(new Font("Arial",Font.BOLD,fontSize));
            botonDebitoAutomatico.setBounds(390,40,200,50);

            JButton botonResumenTarjeta = new JButton("Resumen Tarjeta");
            botonResumenTarjeta.setFont(new Font("Arial",Font.BOLD,fontSize));
            botonResumenTarjeta.setBounds(390,160,200,50);

            JButton readTrasferenciasCuentasButton = new JButton("Movimientos Cuentas");
            readTrasferenciasCuentasButton.setFont(new Font("Arial", Font.BOLD,15));
            readTrasferenciasCuentasButton.setBounds(95,220,200,50);

            JButton readTransferenciasTarjetaButton = new JButton("Movimientos tarjetas");
            readTransferenciasTarjetaButton.setFont(new Font("Arial", Font.BOLD,15));
            readTransferenciasTarjetaButton.setBounds(305,220,200,50);

            JButton botonSalir = new JButton("Cerrar Sesion");
            botonSalir.setFont(new Font("Arial",Font.BOLD,20));
            botonSalir.setBackground(Color.PINK);

            this.add(panelPrincipal);
            panelPrincipal.add(panelSur,BorderLayout.SOUTH);

            panelPrincipal.add(createUsuarioButton);
            panelPrincipal.add(readUsuarioButton);

            panelPrincipal.add(createCuentaButton);
            panelPrincipal.add(readCuentaButton);

            panelPrincipal.add(createTarjetaButton);
            panelPrincipal.add(readTarjetaButton);

            panelPrincipal.add(botonDebitoAutomatico);
            panelPrincipal.add(botonResumenCuenta);
            panelPrincipal.add(botonResumenTarjeta);

            panelPrincipal.add(readTrasferenciasCuentasButton);
            panelPrincipal.add(readTransferenciasTarjetaButton);


            panelPrincipal.add(Box.createRigidArea(new Dimension(0,0)));

            panelSur.add(Box.createRigidArea(new Dimension(100,0)));
            panelSur.add(botonSalir);
            panelSur.add(Box.createRigidArea(new Dimension(100,0)));

            //acciones
            botonSalir.addActionListener(e -> {
                dispose();
                new FirstPage();
            });
            createUsuarioButton.addActionListener(e -> {
                new ActionCreateUsuario();
                System.out.println(userID);
            });
            createCuentaButton.addActionListener(e -> {
                new ActionCreateCuenta();
            });
            createTarjetaButton.addActionListener(e -> {
                new ActionCreateTarjeta();
            });
            readUsuarioButton.addActionListener(e -> {
                new ActionReadUsuarios();
            });
            readCuentaButton.addActionListener(e -> {
                new ActionReadCuentas();
            });
            readTarjetaButton.addActionListener(e -> {
                new ActionReadTarjetas();
            });
            readTrasferenciasCuentasButton.addActionListener(e -> {
                new ActionReadMovimientosC();
            });
            readTransferenciasTarjetaButton.addActionListener(e -> {
                new ActionReadMovimientosT();
            });
            this.pack();
            setVisible(true);
        }
    }


