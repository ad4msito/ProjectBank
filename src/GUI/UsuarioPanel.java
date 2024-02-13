package GUI;

import Controlador.Cuenta;
import Controlador.Tarjeta;
import Controlador.Transacciones.TransaccionCuenta;
import Controlador.UsuarioCuenta;
import Exceptions.ServiceException;
import GUI.Logos.LogoImagen;
import GUI.Logos.ObtenerImagen;
import Service.CuentaService;
import Service.TarjetaService;
import Service.TransaccionCuentaService;
import Service.UsuarioCuentaService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class UsuarioPanel extends JFrame{
    private Login login;
    private static JPanel panelPrincipal;
    private static JPanel panelTransferencia;
    private  JPanel contenedorSur;
    private JPanel contenedorSaldo;
    private JPanel panelSur;
    private JPanel panelNorte;
    private JButton buttonTransferir;
    private JButton buttonSalir;
    private JButton buttonAtras;
    private JComboBox modelCuentas;
    private JComboBox comboBox;
    private JLabel saldoLabel;
    private JComboBox comboBoxTransf;
    private JButton realizarTransfButton;
    private JTextField montoField;
    private JRadioButton aliasRadioButton;
    private JTextField cuentaDesintoTextF;
    private JRadioButton cbuRadioButton;
    private JButton buttonResumen;
    private JLabel saldoTarjetaLabel;
    private JPanel panelImagen;
    private JComboBox modelTarjetas;
    private JPanel contenedorSaldoTarjeta;
    private List<String> data;
    private UsuarioCuenta user;
    private CuentaService cuentaService;
    private TarjetaService tarjetaService;
    private UsuarioCuentaService usuarioCuentaService;
    private TransaccionCuentaService transaccionCuentaService;
    private ObtenerImagen obtenerImagen;
    private LogoImagen imagen;
    private Long userID;
    private Double saldoActual;
    private Double saldoActualTarjeta;
    private Boolean jbuttonCuentaSelect;
    private int width = 1200;
    private int height = 1000;
    public UsuarioPanel(Long userID) {
        setTitle("UserPanel");
        setBounds(300,0,width,height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        obtenerImagen = new ObtenerImagen();
        setIconImage(new ImageIcon(obtenerImagen.obtener2()).getImage());
        setResizable(false);
        setLayout(new BorderLayout());

        ///variables
        cuentaService = new CuentaService();
        tarjetaService = new TarjetaService();
        this.userID = userID;
        transaccionCuentaService = new TransaccionCuentaService();
        //paneles
        panelPrincipal = new JPanel();
        panelPrincipal.setPreferredSize(new Dimension(width,height));
        panelPrincipal.setBackground(new Color(109,152,173));
        panelPrincipal.setLayout(null);
        add(panelPrincipal);
        //panel sur
        panelSur = new JPanel();
        panelSur.setBackground(new Color(67,95,110,200));
        panelSur.setPreferredSize(new Dimension(width,height/10));
        add(panelSur,BorderLayout.SOUTH);
        //contenedorSur
        contenedorSur = new JPanel();
        contenedorSur.setOpaque(false);
        contenedorSur.setPreferredSize(new Dimension(width,height/10));
        contenedorSur.setLayout(null);
        panelSur.add(contenedorSur);

        //panel norte
        panelNorte = new JPanel();
        panelNorte.setPreferredSize(new Dimension(width,height/8));
        panelNorte.setBackground(new Color(67,95,110,200));
        panelNorte.setLayout(null);
        add(panelNorte, BorderLayout.NORTH);
        imagen = new LogoImagen(200,150);
        panelImagen = new JPanel();
        panelImagen.setBounds(490,-1,200,150);
        panelImagen.setOpaque(false);
        panelImagen.add(imagen);
        panelImagen.repaint();
        panelNorte.add(panelImagen);

        //saldo
        contenedorSaldo = new JPanel();
        saldoLabel = new JLabel("Saldo:");

        //comboBox
        modelCuentas = new JComboBox<>();
        List<Cuenta>cuentas = null;
        try {
            cuentas = cuentaService.readAllForUser(userID);
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(null,"Error en lectura" + e.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
        }
        for(Cuenta c: cuentas) {
            modelCuentas.addItem(c);
        }

        //model tarjetas:
        modelTarjetas = new JComboBox();
        List<Tarjeta> tarjetas = null;
        try {
            tarjetas = tarjetaService.readOneForUser(userID);
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(null,"Error en lectura" + e.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
        }
        for(Tarjeta t: tarjetas){
            modelTarjetas.addItem(t);
        }

        //incializacion
        sur1();
        principal(modelCuentas, modelTarjetas);
        setVisible(true);
    }
    public void principal(JComboBox modelCuentas, JComboBox modelTarjetas){
        contenedorSaldo.setLayout(new GridLayout(1,1));
        contenedorSaldo.setBounds(720,101,400,98);
        contenedorSaldo.setBackground(Color.white);
        panelPrincipal.add(contenedorSaldo);

        saldoLabel.setFont(new Font("Arial", Font.BOLD,50));
        saldoLabel.setBackground(Color.black);
        contenedorSaldo.add(saldoLabel);

        modelCuentas.setBounds(100,100,600,100);
        modelCuentas.setFont(new Font("Arial",Font.BOLD,30));
        modelCuentas.addActionListener(this::actionPerformance);
        panelPrincipal.add(modelCuentas);
  ///ver tarjetaS
        contenedorSaldoTarjeta = new JPanel();
        contenedorSaldoTarjeta.setLayout(new GridLayout(1,1));
        contenedorSaldoTarjeta.setBounds(720,300,400,98);
        contenedorSaldoTarjeta.setBackground(Color.white);
        panelPrincipal.add(contenedorSaldoTarjeta);

        saldoTarjetaLabel = new JLabel("Deuda:");
        saldoTarjetaLabel.setFont(new Font("Arial", Font.BOLD, 50));
        contenedorSaldoTarjeta.add(saldoTarjetaLabel);

        modelTarjetas.setBounds(100,300,600,100);
        modelTarjetas.setFont(new Font("Arial", Font.BOLD, 30));
        modelTarjetas.addActionListener(this::actionPerformance);
        panelPrincipal.add(modelTarjetas);
    }
    public void transferencia(JComboBox model){
        JLabel origenLabel = new JLabel("Seleccionar cuenta:");
        origenLabel.setFont(new Font("Arial", Font.BOLD,20));
        origenLabel.setBounds(250,100,200,20);
        panelPrincipal.add(origenLabel);

        comboBoxTransf = model;
        comboBoxTransf.setBounds(500,80,400,70);
        comboBoxTransf.setFont(new Font("Arial",Font.BOLD,20));
        panelPrincipal.add(comboBoxTransf);

        JLabel eleccionLabel = new JLabel("Transferir por:");
        eleccionLabel.setFont(new Font("Arial", Font.BOLD,20));
        eleccionLabel.setBounds(300,230,300,20);
        panelPrincipal.add(eleccionLabel);

        aliasRadioButton = new JRadioButton("Alias", true);
        aliasRadioButton.setBounds(500,215,100,50);
        aliasRadioButton.setFont(new Font("Arial",Font.BOLD,20));
        panelPrincipal.add(aliasRadioButton);

        cbuRadioButton = new JRadioButton("CBU", false);
        cbuRadioButton.setFont(new Font("Arial",Font.BOLD,20));
        cbuRadioButton.setBounds(700,215,100,50);
        panelPrincipal.add(cbuRadioButton);
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(cbuRadioButton);
        grupo.add(aliasRadioButton);

        JLabel cuentaDestintoLabel = new JLabel("Alias/CBU:");
        cuentaDestintoLabel.setFont(new Font("Arial",Font.BOLD,20));
        cuentaDestintoLabel.setBounds(325,310,300,40);
        panelPrincipal.add(cuentaDestintoLabel);

        cuentaDesintoTextF = new JTextField(20);
        cuentaDesintoTextF.setBounds(500, 305,300,40);
        panelPrincipal.add(cuentaDesintoTextF);

        JLabel fecha = new JLabel("Fecha:");
        fecha.setFont(new Font("Arial",Font.BOLD,20));
        fecha.setBounds(360,400,300,40);
        panelPrincipal.add(fecha);

        JTextField fechaField = new JTextField("DD-MM-YYYY");
        fechaField.setBounds(500,400,300,40);
        fechaField.setFont(new Font("Arial",Font.BOLD,20));
        fechaField.setEnabled(false);
        panelPrincipal.add(fechaField);

        JLabel montoLabel = new JLabel("Monto:");
        montoLabel.setBounds(360,490,300,50);
        montoLabel.setFont(new Font("Arial", Font.BOLD,20));
        panelPrincipal.add(montoLabel);

        montoField = new JTextField(20);
        montoField.setBounds(500,490,300,40);
        panelPrincipal.add(montoField);

        realizarTransfButton = new JButton("Realizar Transferencia");
        realizarTransfButton.setBounds(500,580,300,60);
        realizarTransfButton.setBackground(new Color(250,216,52));
        realizarTransfButton.setFont(new Font("Arial",Font.BOLD,20));
        realizarTransfButton.addActionListener(this::actionPerformance);
        panelPrincipal.add(realizarTransfButton);
    }
    public void sur1(){
        buttonTransferir = new JButton("Transferir");
        buttonTransferir.setBounds(50,10,200,70);
        buttonTransferir.setFont(new Font("Arial", Font.BOLD, 20));
        buttonTransferir.addActionListener(this::actionPerformance);
        contenedorSur.add(buttonTransferir);

        buttonSalir = new JButton("Cerrar Sesion");
        buttonSalir.setBackground(Color.PINK);
        buttonSalir.setBounds(950,10,200,70);
        buttonSalir.setFont(new Font("Arial", Font.BOLD,20));
        buttonSalir.addActionListener(this::actionPerformance);
        contenedorSur.add(buttonSalir);

        buttonResumen = new JButton("Resumen de Cuenta");
        buttonResumen.setBounds(440,10,300,70);
        buttonResumen.setFont(new Font("Arial", Font.BOLD, 20));
        buttonResumen.addActionListener(this::actionPerformance);
        contenedorSur.add(buttonResumen);
    }
    public void sur2(){
        buttonAtras= new JButton("Atras");
        buttonAtras.setBounds(50,10,200,70);
        buttonAtras.setFont(new Font("Arial", Font.BOLD, 20));
        buttonAtras.addActionListener(this::actionPerformance);
        contenedorSur.add(buttonAtras);
    }
    public void panelResumen(){

    }
    public void actionPerformance(ActionEvent e){
        if(e.getSource()==buttonTransferir){
            panelPrincipal.removeAll();
            contenedorSur.removeAll();
            revalidate();
            repaint();
            transferencia(modelCuentas);
            sur2();
        }
        if(e.getSource()==buttonAtras) {
            panelPrincipal.removeAll();
            contenedorSur.removeAll();
            revalidate();
            repaint();
            principal(modelCuentas, modelTarjetas);
            sur1();
        }
        if(e.getSource()==buttonSalir) {
            dispose();
            new FirstPage();
        }
        if(e.getSource()== modelCuentas){
            Cuenta cuentaSeleccionada = (Cuenta) modelCuentas.getSelectedItem();
            saldoActual = cuentaSeleccionada.getSaldo();
            saldoLabel.setText("Saldo:" + saldoActual);
            contenedorSaldo.add(saldoLabel);
            }
        if(e.getSource()==realizarTransfButton){
            Object selectedItem = comboBoxTransf.getSelectedItem();

            if (selectedItem instanceof Cuenta) {
                Cuenta cuentaSelect = ((Cuenta) selectedItem);
                Double montoT = Double.valueOf(montoField.getText());
                if (aliasRadioButton.isSelected()) {
                    System.out.println("Estoy 1");
                    try {
                        Cuenta cuentaDestino = cuentaService.readOneForAlias(cuentaDesintoTextF.getText());
                        TransaccionCuentaService transaccionCuentaService = new TransaccionCuentaService();
                        try {
                            transaccionCuentaService.realizarTransaccion(cuentaSelect, cuentaDestino, montoT);
                            JOptionPane.showMessageDialog(null, "Transferencia realizada con exito");
                        } catch (ServiceException ex) {
                            JOptionPane.showMessageDialog(null, "Error al realizar la transaccion " + ex.getMessage());
                        }
                    } catch (ServiceException ex1) {
                        JOptionPane.showMessageDialog(null, "Error al buscar la cuenta " + ex1.getMessage());
                    }
                } else if(cbuRadioButton.isSelected()) {
                    System.out.println("estoy 2");
                    try {
                        Cuenta cuentaDestino = cuentaService.readOneForCbu(Integer.parseInt(cuentaDesintoTextF.getText()));
                        TransaccionCuentaService transaccionCuentaService = new TransaccionCuentaService();
                        try {
                            transaccionCuentaService.realizarTransaccion(cuentaSelect,cuentaDestino,montoT);
                            JOptionPane.showMessageDialog(null, "Transferencia realizada con exito");
                        } catch (ServiceException ex) {
                            JOptionPane.showMessageDialog(null, "Error al realizar la transaccion " + ex.getMessage());
                        }
                    } catch (ServiceException ex1) {
                        JOptionPane.showMessageDialog(null, "Error al buscar la cuenta " + ex1.getMessage());
                    }
                }
            }
        }
        if(e.getSource()==modelTarjetas){
            Tarjeta tarjetaSelect = (Tarjeta) modelTarjetas.getSelectedItem();
            saldoActualTarjeta = tarjetaSelect.getSaldoAdeudado();
            saldoTarjetaLabel.setText("Deuda:" + saldoActualTarjeta);
            contenedorSaldoTarjeta.add(saldoTarjetaLabel);
        }
        if(e.getSource()==buttonResumen){
            List<TransaccionCuenta> movs = null;
            try {
                movs = transaccionCuentaService.readAllForUser(userID);
            } catch (ServiceException ex) {
                JOptionPane.showMessageDialog(null,"Error al buscar movimientos" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            for(TransaccionCuenta t: movs) {
                System.out.println(t);
            }
        }
    }

}
