import DAO.*;
import controlador.Cuenta;
import controlador.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class NuevoMain {
    public static void main(String[] args) throws DAOException, SQLException {
        TableManager tm = new TableManager();
        tm.createUserTable();
        Connection conn=null;
        String url = DBManager.obtenerUbicacionBase();
        try {
            //conectarme
            conn = DriverManager.getConnection(url,"sa", "");
            //crear usuarios/cuentas
            CuentaDAO n = new CuentaDAOH2Impl();

            Usuario u = new Usuario(111, "adam@gmail.com", "1234");

            Cuenta c = new Cuenta("agus","CC",700,u);
            Cuenta cb = new Cuenta("adam","CA", 1000, u);
            //datos:
            //Query
            n.insertar(c);
            n.insertar(cb);
            cb.setSaldo(cb.getSaldo()-300);
            n.modificar(cb);
            TablaLocal tl = new TablaLocal();
            tl.setTable("ad4m","CC",10090, 111);
            tl.setTable("jot4", "CA", 2030, 101);
            List<Cuenta> cuentas = n.obtenerTodos();
            for(Cuenta a: cuentas){
                System.out.println(a.toString());
            }
        } catch(SQLException e){
            throw new DAOException("ERROR", e);
        } finally {
            if(conn!=null){
                conn.close();
            }
            tm.dropUserTable();
        }
    }
}