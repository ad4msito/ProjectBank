import Controlador.Cuenta;
import Controlador.Cuentas.CajaAhorro;
import Controlador.UsuarioCuenta;
import DAO.CuentaDAO;
import DAO.UsuarioCuentaDAO;
import Exceptions.DAOException;
import manager.DBManager;
import java.sql.Connection;
import java.sql.SQLException;

public class NuevoMain {
    public static void main(String[] args) throws DAOException {
        UsuarioCuenta a = new UsuarioCuenta(2L,"adam", "adam@gmail", "abc");
        //UsuarioCuentaDAO aDAO = new UsuarioCuentaDAO();
        Cuenta b= new Cuenta(3L,"c",103D,2,a);
        CuentaDAO bDAO = new CuentaDAO();
        Connection conn = DBManager.connect();
        try {
            //aDAO.create(a, conn);
            bDAO.delete(2L,conn);
        } catch (DAOException e) {
            throw new DAOException(e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e2) {
                throw new DAOException(e2.getMessage());
            }
        }
    }
}

