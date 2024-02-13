package GUI.Logos;

import javax.swing.*;
import java.awt.*;

public class LogoImagen extends JPanel {
    ObtenerImagen obtenerImagen;
    public LogoImagen(int w, int h){
        obtenerImagen = new ObtenerImagen();
        setPreferredSize(new Dimension(w,h));
    }
    public void paint(Graphics g){
        super.paintComponent(g);
        ImageIcon img  = new ImageIcon("C:\\Users\\adamf\\OneDrive\\Documentos\\programacion\\ProjectBank\\imagenes\\boke12.png");
        int width = getWidth();
        int height = getHeight();
        setOpaque(false);
        g.drawImage(img.getImage(),0,0,width,height,null);

    }
}
