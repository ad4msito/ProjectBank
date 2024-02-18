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
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.List;

public class VentanaUsers extends JFrame {
    private VentanaAdmins ventanaAdmins;
    private UsuarioCuentaService usuarioCuentaService;
    private TransaccionCuentaService transaccionCuentaService;
    private CuentaService cuentaService;
    private TarjetaService tarjetaService;
    private UsuarioCuenta usuarioCuenta;
    private TransaccionCuenta transaccionCuenta;
    //  paneles
    private JPanel panelPrincipal;
    private JPanel vista1;
    private JPanel vista2;
    // cajas de texto
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;
    // labels
    private JLabel jLabelUser;
    private JLabel jLabelPass;
    private JLabel jLabel1;
    private JLabel jLabel2;
    //botones
    private JButton jButtonLogin;
    private JButton botonVistaRegistro;
    private JButton botonRegistro;
    private JButton botonSalirApliacion;
    private JButton botonVistaTransferir;
    private JButton botonTransferir;
    private JButton botonVerCuentas;
    private JButton botonVerTarjetas;
    private JButton botonSalir;
    //radiobotones
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    //combobox
    private JComboBox<String> comboBoxSaldos;
    //diseño
    private int width;
    private int height;
    private Color color1;
    private Color color2;
    private Color color3;
    private Color color4;
    private Color color5;
    private Font fuenteLogin;
    private Font fuenteBotones;
    private Font fuente1;
    private Border borde1;
    // datos para operaciones:
    private List<Cuenta> cuentasDelUsuario;
    private List<Tarjeta> tarjetasDelUsuario;
    private Cuenta cuentaSeleccionada;
    private int isCBU;

    public VentanaUsers() {
        isCBU = 0;
        usuarioCuentaService = new UsuarioCuentaService();
        transaccionCuentaService = new TransaccionCuentaService();
        tarjetaService = new TarjetaService();
        cuentaService = new CuentaService();
        //diseño:
        fuenteLogin = new Font("Caslon", Font.BOLD,12);
        fuenteBotones = new Font("Swift", Font.BOLD,12);
        fuente1 = new Font("Arial", Font.BOLD, 17);
        color1 = new Color(250,113,95);
        color2 = new Color(95,157,250);
        color3 = new Color(214,250,95);
        color4 = new Color(108,114,122);
        color5 = new Color(119,122,108);
        width = 800; height = 800;
        borde1 = BorderFactory.createLineBorder(new Color(100,100,100,140 ), 5);
        //Atributos Ventana1:
        setBounds(0,0,width,height);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //cardLayout = new CardLayout(); //Layout del panelPrincipal

        //panel principal:
        panelPrincipal = new JPanel();
        panelPrincipal.setPreferredSize(new Dimension(width,height));
        add(panelPrincipal);

        //Ejecutar las vistas
        VistaLogin();
        //VistaTransferencia();
        setVisible(true);
    }
    private void VistaLogin(){

        //diseño vistaLogin:
        //fondo:
        vista2 = new JPanel();
        vista2.setPreferredSize(new Dimension(width, height));
        vista2.setLayout(null);
        vista2.setBackground(color3);
        //Cuadrado login:
        vista1 = new JPanel();
        vista1.setLayout(null);
        vista1.setBounds(200,160,width/2,height/2);
        vista1.setBorder(borde1);
        vista1.setBackground(color2);
        vista2.add(vista1);
        //Etiquetas de texto:
        //user:
        jLabelUser = new JLabel("Usuario:");
        jLabelUser.setBounds(70,125,100,30);
        jLabelUser.setForeground(Color.WHITE);
        jLabelUser.setFont(fuenteLogin);
        vista1.add(jLabelUser);
        //contraseña
        jLabelPass = new JLabel("Contraseña:");
        jLabelPass.setBounds(50,185, 100,30);
        jLabelPass.setForeground(Color.WHITE);
        jLabelPass.setFont(fuenteLogin);
        vista1.add(jLabelPass);
        //cajas de texto:
        //user:
        jTextField1 = new JTextField();
        jTextField1.setBounds(125,125,190,30);
        vista1.add(jTextField1);
        //contraseña:
        jTextField2 = new JPasswordField();
        jTextField2.setBounds(125, 185, 190,30);
        vista1.add(jTextField2);
        //Botones:
        //boton ingresar
        jButtonLogin = new JButton("Ingresar");
        jButtonLogin.addActionListener(this::actionPerformance);
        jButtonLogin.setBounds(85,245,100,30);
        vista1.add(jButtonLogin);

        //botonSingup:
        botonVistaRegistro = new JButton("Registrarse");
        botonVistaRegistro.setBounds(195,245,120,30);
        botonVistaRegistro.addActionListener(this::actionPerformance);
        vista1.add(botonVistaRegistro);

        //boton salir:
        botonSalirApliacion = new JButton("Salir");
        botonSalirApliacion.setBounds(30, 700, 100,30);
        vista2.add(botonSalirApliacion);

        panelPrincipal.add(vista2);
    }
    private void VistaRegistro(){
        //diseño:
        vista1 = new JPanel();
        vista1.setPreferredSize(new Dimension(width, height));
        vista1.setLayout(null);
        vista1.setBackground(color1);
        //jcomponentes:

        jLabelUser = new JLabel("Email:");
        jLabelUser.setBounds(257,350,100,30);
        jLabelUser.setForeground(Color.WHITE);
        jLabelUser.setFont(fuenteLogin);
        vista1.add(jLabelUser);
        //contraseña:
        jLabelPass = new JLabel("Contraseña:");
        jLabelPass.setBounds(257,420, 100,30);
        jLabelPass.setForeground(Color.WHITE);
        jLabelPass.setFont(fuenteLogin);
        vista1.add(jLabelPass);
        //nombre:
        jLabel2 = new JLabel("Nombre:");
        jLabel2.setBounds(257,280,70,20);
        jLabel2.setForeground(Color.WHITE);
        vista1.add(jLabel2);
        //cajas de texto:
        jTextField3 = new JTextField();
        jTextField3.setBounds(342,270,200,40);
        vista1.add(jTextField3);

        jTextField1 = new JTextField();
        jTextField1.setBounds(342,340,200,40);
        vista1.add(jTextField1);

        jTextField2 = new JPasswordField();
        jTextField2.setBounds(342, 410, 200,40);
        vista1.add(jTextField2);
        //botones:
        botonRegistro = new JButton("Registrarse");
        botonRegistro.setBounds(422,480,120,30);
        botonRegistro.addActionListener(this::actionPerformance);
        vista1.add(botonRegistro);

        botonSalir = new JButton("Atras");
        botonSalir.setBounds(30,700,100,30);
        botonSalir.addActionListener(this::actionPerformance);
        vista1.add(botonSalir);
        panelPrincipal.add(vista1);
    }
    private void VistaSaldos(int vista, UsuarioCuenta u) {
        cuentaSeleccionada = null;
        try {
            cuentasDelUsuario = cuentaService.filtrarPorUsuario(cuentaService.readAll(),u.getId());
            tarjetasDelUsuario = tarjetaService.filtrarPorUsuario(tarjetaService.readAll(),u.getId());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        //atributos Vista Saldos:
        vista1 = new JPanel();
        vista1.setLayout(null);
        Border bordeSaldos = BorderFactory.createLineBorder(new Color(0,0,0,100),2);
        vista1.setBounds(0, 0, 800, 300);
        vista1.setBorder(bordeSaldos);
        vista1.setBackground(Color.WHITE);

        vista2 = new JPanel();
        vista2.setLayout(null);
        vista2.setPreferredSize(new Dimension(width, height));

        //
        if(vista==1) {
            vista2.setBackground(color4);
            botonVistaTransferir = new JButton("Transferir");
            botonVistaTransferir.setBounds(60, 327, 180, 60);
            botonVistaTransferir.setFont(fuenteBotones);
            botonVistaTransferir.addActionListener(this::actionPerformance);
            vista2.add(botonVistaTransferir);

            botonVerTarjetas = new JButton("Ver tarjetas");
            botonVerTarjetas.setBounds(560, 327, 180, 60);
            botonVerTarjetas.setFont(fuenteBotones);
            botonVerTarjetas.addActionListener(this::actionPerformance);
            vista2.add(botonVerTarjetas);

            comboBoxSaldos = new JComboBox<>();
            comboBoxSaldos.setBounds(225, 225, 350, 40);
            llenarComboBoxCuentas(comboBoxSaldos,u.getId());
            comboBoxSaldos.setSelectedIndex(-1);
            comboBoxSaldos.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    int selectedIndex = comboBoxSaldos.getSelectedIndex();
                    cuentaSeleccionada = cuentasDelUsuario.get(selectedIndex);
                    jLabel1.setText("$" + cuentaSeleccionada.getSaldo());
                    jLabel1.setFont(new Font("Caslon", Font.BOLD, 100));
                }
            });
            vista1.add(comboBoxSaldos);

            jLabel1 = new JLabel("Seleccionar cuenta...");
            jLabel1.setBounds(225, 100, 450, 100);
            jLabel1.setFont(new Font("Caslon", Font.BOLD, 30));
            vista1.add(jLabel1);
        } else {
            vista2.setBackground(color5);
            botonVerCuentas = new JButton("Ver cuentas");
            botonVerCuentas.setBounds(60, 327, 180, 60);
            botonVerCuentas.setFont(fuenteBotones);
            botonVerCuentas.addActionListener(this::actionPerformance);
            vista2.add(botonVerCuentas);

            comboBoxSaldos = new JComboBox<>();
            comboBoxSaldos.setBounds(225, 225, 350, 40);
            llenarComboBoxTarjetas(comboBoxSaldos,usuarioCuenta.getId());
            comboBoxSaldos.setSelectedIndex(-1);
            comboBoxSaldos.addItemListener(e -> {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    int selectedIndex = comboBoxSaldos.getSelectedIndex();
                    Tarjeta tarjetaSeleccionada = tarjetasDelUsuario.get(selectedIndex);
                    jLabel1.setText("-" + tarjetaSeleccionada.getSaldoAdeudado());
                    jLabel1.setFont(new Font("Caslon", Font.BOLD,100));
                }
            });
            vista1.add(comboBoxSaldos);

            jLabel1 = new JLabel("Seleccionar tarjeta...");
            jLabel1.setBounds(225, 100, 375, 100);
            jLabel1.setFont(new Font("Caslon", Font.BOLD, 30));
            vista1.add(jLabel1);
        }
        botonSalir = new JButton("Salir");
        botonSalir.setBounds(60, 700, 180, 40);
        botonSalir.setFont(fuenteBotones);
        botonSalir.setBackground(color1);
        botonSalir.addActionListener(this::actionPerformance);
        vista2.add(botonSalir);
        vista2.add(vista1);
        panelPrincipal.add(vista2);
    }

    private void VistaTransferencia(Cuenta cuentaSeleccionada) {
        //atributos Vista Transferencia:
        vista2 = new JPanel();
        vista2.setPreferredSize(new Dimension(width, height));
        vista2.setLayout(null);
        vista2.setBackground(color2);

        vista1 = new JPanel();
        vista1.setBounds(200, 165, width / 2, height / 2);
        vista1.setLayout(null);
        vista1.setBackground(color1);

        //
        radioButton1 = new JRadioButton("CVU/CBU");
        radioButton1.addActionListener(this::actionPerformance);
        radioButton1.setBounds(133, 101, 100, 40);
        radioButton1.setForeground(Color.white);
        radioButton1.setBackground(color1);
        vista1.add(radioButton1);

        radioButton2 = new JRadioButton("Alias");
        radioButton2.addActionListener(this::actionPerformance);
        radioButton2.setBounds(272, 100, 100, 40);
        radioButton2.setForeground(Color.white);
        radioButton2.setBackground(color1);
        //
        jLabel1 = new JLabel("CVU/Alias:");
        jLabel1.setBounds(45, 154, 126, 19);
        jLabel1.setFont(fuente1);
        jLabel1.setForeground(Color.white);
        vista1.add(jLabel1);

        jLabel2 = new JLabel("Monto:");
        jLabel2.setBounds(50, 209, 126, 19);
        jLabel2.setFont(fuente1);
        jLabel2.setForeground(Color.white);
        vista1.add(jLabel2);

        jLabelUser = new JLabel();
        jLabelUser.setBounds(100, 70,300,30);
        jLabelUser.setForeground(Color.green);
        jLabelUser.setFont(fuente1);
        String tipoCuenta = tipoDeCuenta(cuentaSeleccionada);
        jLabelUser.setText(tipoCuenta + ": $" + cuentaSeleccionada.getSaldo());
        vista1.add(jLabelUser);

        jTextField1 = new JTextField();
        jTextField1.setBounds(133, 149, 213, 30);
        vista1.add(jTextField1);

        jTextField2 = new JTextField("0");
        jTextField2.setBounds(133, 204, 213, 30);
        vista1.add(jTextField2);

        botonTransferir = new JButton("Transferir");
        botonTransferir.setBounds(240,259,106,30 );
        botonTransferir.addActionListener(this::actionPerformance);
        vista1.add(botonTransferir);

        botonVerCuentas = new JButton("Salir");
        botonVerCuentas.setBounds(60,710,100,30);
        botonVerCuentas.addActionListener(this::actionPerformance);
        vista2.add(botonVerCuentas);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(radioButton1);
        grupo.add(radioButton2);

        vista1.add(radioButton2);



        vista2.add(vista1);
        panelPrincipal.add(vista2);
    }
    public void actionPerformance(ActionEvent e) {
        if (e.getSource() == jButtonLogin) {
            String user = jTextField1.getText();
            String pass = jTextField2.getText();
            try {
                usuarioCuenta = usuarioCuentaService.singUp(user, pass);
                if (usuarioCuenta != null) {
                    panelPrincipal.removeAll();
                    VistaSaldos(1, usuarioCuenta);
                    panelPrincipal.revalidate();
                    panelPrincipal.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (ServiceException e1) {
                JOptionPane.showMessageDialog(null, "Error al buscar el usuario" + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            }
        }
        if (e.getSource() == botonVistaRegistro) {
            panelPrincipal.removeAll();
            VistaRegistro();
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        }
        if (e.getSource() == botonRegistro) {
            String name = jTextField3.getText();
            String user = jTextField1.getText();
            String pass = jTextField2.getText();
            UsuarioCuenta u = new UsuarioCuenta(name, user, pass, false);
            try {
                usuarioCuentaService.create(u);
                JOptionPane.showMessageDialog(null, "Cuenta creada con exito", "Felicitaciones", JOptionPane.INFORMATION_MESSAGE);
                panelPrincipal.removeAll();
                VistaLogin();
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
            } catch (ServiceException e1) {
                JOptionPane.showMessageDialog(null, "Error al crear cuenta" + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == botonVistaTransferir) {
            if(cuentaSeleccionada!=null) {
                panelPrincipal.removeAll();
                VistaTransferencia(cuentaSeleccionada);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
            } else {
                JOptionPane.showMessageDialog(null,"Selecciona una cuenta antes de transferir", "Seleccionar cuenta", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if (e.getSource() == botonVerCuentas) {
            panelPrincipal.removeAll();
            VistaSaldos(1, usuarioCuenta);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        }
        if (e.getSource() == botonVerTarjetas) {
            panelPrincipal.removeAll();
            VistaSaldos(0, usuarioCuenta);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        }
        if (e.getSource() == botonSalir) {
            panelPrincipal.removeAll();
            VistaLogin();
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        }
        if (e.getSource() == radioButton1) {
            isCBU = 1;
        }
        if (e.getSource() == radioButton2) {
            isCBU = 2;
        }
        if (e.getSource() == botonTransferir) {
            boolean montoValido = false;
            //obtener el alias o cbu
            String aliasOrCbu = jTextField1.getText();
            String jTextMonto = jTextField2.getText();
            int monto = 0;
            //validar el monto
            if (jTextMonto!=null && !jTextMonto.isEmpty()) {
                try {
                    monto = Integer.parseInt(jTextMonto);
                    montoValido = true;
                } catch (RuntimeException e1){
                    JOptionPane.showMessageDialog(null,"Valor ingresado no valido","Error",JOptionPane.ERROR_MESSAGE);
                }
            }else{
                montoValido = false;
            }
            if (isCBU != 0) {
                try {
                    Cuenta cuentaReceptora = cuentaService.encontrarCuenta(cuentaService.readAll(), aliasOrCbu, isCBU);
                    if (monto>0) {
                        if(cuentaReceptora!=null) {
                            //realizar la transaccion
                            transaccionCuentaService.realizarTransaccion(cuentaSeleccionada, cuentaReceptora, monto);
                            JOptionPane.showMessageDialog(null, "Transferencia realizada con exito", "Operacion realizada", JOptionPane.INFORMATION_MESSAGE);
                            TransaccionCuenta transaccionRealizada = new TransaccionCuenta((double) monto, cuentaSeleccionada.getId(), cuentaReceptora.getId());
                            transaccionCuentaService.create(transaccionRealizada);
                            System.out.println(transaccionRealizada);
                            //volver a la vista anterior
                            panelPrincipal.removeAll();
                            VistaSaldos(1, usuarioCuenta);
                            panelPrincipal.revalidate();
                            panelPrincipal.repaint();
                        } else {
                            JOptionPane.showMessageDialog(null,"Cuenta no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,"El monto no es valido.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, "Cuenta no encontrada" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null,"Selecciona CBU o Alias", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    //metodos combobox:
    public void llenarComboBoxCuentas(JComboBox<String> comboBox, Long id) {
        try {
            List<Cuenta> cuentasFiltradas = cuentaService.filtrarPorUsuario(cuentaService.readAll(), id);
            for (Cuenta cuenta : cuentasFiltradas) {
                if(cuenta.getTipoCuenta() == 1) {
                    String item = "CAJA AHORROS" + " Saldo: " + cuenta.getSaldo();
                    comboBox.addItem(item);
                } if(cuenta.getTipoCuenta() == 2){
                    String item = "CUENTA CORRIENTE" + " Saldo: " + cuenta.getSaldo();
                    comboBox.addItem(item);
                }
            }
        } catch (ServiceException e1){
            JOptionPane.showMessageDialog(null,"Error al cargar cuentas" + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void llenarComboBoxTarjetas(JComboBox<String> comboBox, Long id){
        try {
            int n = 1;
            List<Tarjeta> tarjetasFiltradas = tarjetaService.filtrarPorUsuario(tarjetaService.readAll(),id);
            for (Tarjeta tarjeta: tarjetasFiltradas) {
                String item = "Deuda de la tarjeta n°" + n;
                comboBox.addItem(item);
                n++;
            }
        } catch (ServiceException e1){
            JOptionPane.showMessageDialog(null, "Error al cargar tarjetas" + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public String tipoDeCuenta(Cuenta cuenta){
        String tipoCuenta = null;
        if(cuenta.getTipoCuenta()==1){
            tipoCuenta = "CAJA DE AHORROS";
        } else if (cuenta.getTipoCuenta() == 2) {
            tipoCuenta = "CUENTA CORRIENTE";
        }
        return tipoCuenta;
    }
}
