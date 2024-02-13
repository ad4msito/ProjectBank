package GUI.Logos;

import java.io.File;

public class ObtenerImagen {

    public String obtener1() {
        File img1 = new File("imagenes/boke12.png");
        return img1.getAbsolutePath();
    }
    public String obtener2(){
        File img2 = new File("imagenes/boke12logochico.png");
        return img2.getAbsolutePath();
    }

    public static void main(String[] args) {
        ObtenerImagen o = new ObtenerImagen();
        System.out.println(o.obtener1());
    }
}
