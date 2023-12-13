package GUI;


import Service.UsuarioCuentaService;
import sun.rmi.runtime.Log;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JFrame {
        UsuarioCuentaService usuarioCuentaService;
        public AdminPanel() {
            this.usuarioCuentaService = new UsuarioCuentaService();
            int width = 1000;
            int height = 400;
            int fontSize = 20;

            setTitle("AdminPanel");
            setBounds(300, 90, width, height);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setResizable(false);

            JPanel panelCentral = new JPanel();
            panelCentral.setPreferredSize(new Dimension(width,height));
            panelCentral.setBackground(new Color(191,217,214));
            panelCentral.setLayout(new GridBagLayout());

            JPanel panelCentralGrid  = new JPanel();
            panelCentralGrid.setOpaque(false);
            panelCentralGrid.setPreferredSize(new Dimension(width/2,height));
            panelCentralGrid.setLayout(new GridLayout(7,1));

            JPanel panelSur = new JPanel();
            panelSur.setOpaque(false);
            panelSur.setPreferredSize(new Dimension(width,height/10));
            panelSur.setLayout(new GridLayout(1,5));

            JButton botonUsuario = new JButton("Usuario");
            botonUsuario.setFont(new Font("Arial", Font.BOLD,fontSize));
            botonUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton botonCuenta = new JButton("Cuenta");
            botonCuenta.setFont(new Font("Arial",Font.BOLD,fontSize));
            botonCuenta.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton botonTarjeta = new JButton("Tarjeta");
            botonTarjeta.setFont(new Font("Arial",Font.BOLD,fontSize));
            botonTarjeta.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton botonTransferenciaC = new JButton("Transferencias de Cuentas");
            botonTransferenciaC.setFont(new Font("Arial",Font.BOLD,fontSize));
            botonTransferenciaC.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton botonTransferenciaT = new JButton("Transferencias de Tarjeta");
            botonTransferenciaT.setFont(new Font("Arial",Font.BOLD,fontSize));
            botonTransferenciaT.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton botonDebitoAutomatico = new JButton("Debitar Tarjeta");
            botonDebitoAutomatico.setFont(new Font("Arial",Font.BOLD,20));
            botonDebitoAutomatico.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton botonResumenCuenta = new JButton("Generar Resumen de Cuenta");
            botonResumenCuenta.setFont(new Font("Arial",Font.BOLD,15));
            botonResumenCuenta.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton botonResumenTarjeta = new JButton("Generar Resumen de Tarjeta");
            botonResumenTarjeta.setFont(new Font("Arial",Font.BOLD,15));
            botonResumenTarjeta.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton botonSalir = new JButton("Salir");
            botonSalir.setFont(new Font("Arial",Font.BOLD,20));
            botonSalir.setBackground(Color.PINK);
            botonSalir.setAlignmentX(Component.CENTER_ALIGNMENT);

            setLayout(new BorderLayout());
            this.add(panelCentral, BorderLayout.CENTER);
            this.add(panelSur,BorderLayout.SOUTH);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.fill = GridBagConstraints.CENTER;

            panelCentral.add(panelCentralGrid,gbc);

            panelCentralGrid.add(Box.createRigidArea(new Dimension(0,0)));
            panelCentralGrid.add(botonUsuario);
            panelCentralGrid.add(botonCuenta);
            panelCentralGrid.add(botonTarjeta);
            panelCentralGrid.add(botonTransferenciaC);
            panelCentralGrid.add(botonTransferenciaT);
            panelCentralGrid.add(Box.createRigidArea(new Dimension(0,0)));

            panelSur.add(botonDebitoAutomatico);
            panelSur.add(botonResumenCuenta);
            panelSur.add(botonResumenTarjeta);
            panelSur.add(botonSalir);
            //acciones
            botonSalir.addActionListener(e -> {
                dispose();
                Login login = new Login();
            });
            this.pack();
            setVisible(true);
        }
    }


