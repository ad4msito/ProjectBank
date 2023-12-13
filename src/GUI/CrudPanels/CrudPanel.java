package GUI.CrudPanels;

import GUI.AdminPanel;

import javax.swing.*;
import java.awt.*;

public abstract class CrudPanel <Type>extends JFrame {
    private Type crudService;

    public CrudPanel() {
        int width = 1200;
        int height = 700;
        int fontSize = 20;

        setTitle("Usuario");
        setBounds(300, 90, width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setPreferredSize(new Dimension(width,height));
        panelPrincipal.setLayout(new BorderLayout());

        JPanel panelCentro = new JPanel();
        panelCentro.setBackground(new Color(109,152,173));

        JPanel panelSur = new JPanel();
        panelSur.setPreferredSize(new Dimension(width,height/14));
        panelSur.setLayout(new GridLayout(1,5));

        JButton botonCrear = new JButton("Crear");
        botonCrear.setFont(new Font("Arial", Font.BOLD,fontSize));

        JButton botonActualizar = new JButton("Actualizar");
        botonActualizar.setFont(new Font("Arial",Font.BOLD,fontSize));

        JButton botonEliminar = new JButton("Eliminar");
        botonEliminar.setFont(new Font("Arial",Font.BOLD,fontSize));

        JButton botonMostrar = new JButton("Mostrar todos");
        botonMostrar.setFont(new Font("Arial",Font.BOLD,fontSize));

        JButton botonSalir = new JButton("Salir");
        botonSalir.setFont(new Font("Arial",Font.BOLD,20));
        botonSalir.setBackground(Color.PINK);

        this.add(panelPrincipal);
        panelPrincipal.add(panelSur, BorderLayout.SOUTH);
        panelPrincipal.add(panelCentro,BorderLayout.CENTER);
        panelSur.add(botonCrear);
        panelSur.add(botonActualizar);
        panelSur.add(botonEliminar);
        panelSur.add(botonMostrar);
        panelSur.add(botonSalir);

        botonCrear.addActionListener(e -> {
            botonCreate();
        });
        botonActualizar.addActionListener(e -> {
            botonUpdate();
        });
        botonEliminar.addActionListener(e-> {
            botonDelete();
        });
        botonMostrar.addActionListener(e-> {
            botonRead();
        });
        botonSalir.addActionListener(e -> {
            dispose();
            AdminPanel adminPanel = new AdminPanel();
        });
        this.pack();
    }

    public JPanel botonCreate(){

        return null;
    }
    public void botonUpdate(){

    }
    public void botonDelete(){

    }
    public void botonRead(){

    }
}
