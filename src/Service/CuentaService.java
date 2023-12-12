package Service;

import Controlador.Cuenta;
import DAO.CuentaDAO;
import Exceptions.DAOException;
import Exceptions.ServiceException;
import manager.DBManager;
import java.sql.Connection;
import java.util.List;

public class CuentaService implements Service<Cuenta> {
    Connection conn;
    CuentaDAO cuentaDAO;

    public CuentaService() {
        this.cuentaDAO = new CuentaDAO();
        this.conn = DBManager.connect();
    }


    @Override
    public void create(Cuenta cuenta) throws ServiceException {
        try {
            cuentaDAO.create(cuenta, conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(Cuenta cuenta) throws ServiceException {
        try {
            cuentaDAO.update(cuenta,conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            cuentaDAO.delete(id,conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Cuenta> readAll() throws ServiceException {
        try {
            return cuentaDAO.readAll(conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Cuenta readOne(Long id) throws ServiceException {
        try {
            return cuentaDAO.readOne(id,conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public static void main(String[] args) throws ServiceException {
        CuentaService cuentaService = new CuentaService();
        Cuenta cuenta = new Cuenta("M4l",1204D,1,10L);
        cuentaService.create(cuenta);
        List<Cuenta> cuentas = cuentaService.readAll();
        for(Cuenta a: cuentas){
            System.out.println(a.toString());
        }
    }
}
