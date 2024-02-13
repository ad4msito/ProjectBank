package Service;

import Controlador.Cuenta;
import DAO.CuentaDAO;
import Exceptions.DAOException;
import Exceptions.ServiceException;
import manager.DBManager;
import java.sql.Connection;
import java.util.List;

public class CuentaService implements Service<Cuenta> {
    private Connection conn;
    private CuentaDAO cuentaDAO;

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
    public List<Cuenta> readAllForUser(Long id) throws ServiceException {
        try {
            return cuentaDAO.readAllForUser(id,conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public Cuenta readOneForAlias(String alias) throws ServiceException {
        try {
            return cuentaDAO.readOneForAlias(alias,conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public Cuenta readOneForCbu(int cbu) throws ServiceException {
        try {
            return cuentaDAO.readOneForCBU(cbu,conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
