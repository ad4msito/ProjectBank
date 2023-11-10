import DAO.*;
import controlador.Cuenta;
import controlador.Usuario;
import org.h2.table.Table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws DAOException, SQLException {
        TableManager tm = new TableManager();
        tm.createUserTable();
        Connection conn=null;
        String url = DBManager.obtenerUbicacionBase();
        try {
            //conectarme
            conn = DriverManager.getConnection(url,"sa", "");
            //crear usuarios/cuentas
            CuentaDAO n = new CuentaDAOH2Impl(conn);

            Usuario u = new Usuario(111, "adam@gmail.com", "1234");

            Cuenta c = new Cuenta("agus","CC",700,u);
            Cuenta cb = new Cuenta("adam","CA", 1000, u);
            //datos:
            //Query
            n.insertar(c);
            n.insertar(cb);
            cb.setSaldo(cb.getSaldo()-300);
            n.modificar(cb);
            n.eliminar(c);
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
