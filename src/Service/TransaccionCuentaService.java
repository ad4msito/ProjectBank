package Service;

import Controlador.Transacciones.TransaccionCuenta;
import DAO.TransaccionCuentaDAO;
import Exceptions.DAOException;
import Exceptions.ServiceException;
import manager.DBManager;

import java.sql.Connection;
import java.util.List;

public class TransaccionCuentaService implements Service<TransaccionCuenta> {

    Connection conn;
    TransaccionCuentaDAO transaccionCuentaDAO;

    public TransaccionCuentaService() {
        this.conn = DBManager.connect();
        this.transaccionCuentaDAO = new TransaccionCuentaDAO();
    }

    @Override
    public void create(TransaccionCuenta t) throws ServiceException {
        try {
            transaccionCuentaDAO.create(t,conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(TransaccionCuenta t) throws ServiceException {
        try {
            transaccionCuentaDAO.update(t,conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            transaccionCuentaDAO.delete(id,conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<TransaccionCuenta> readAll() throws ServiceException {
        try {
            return transaccionCuentaDAO.readAll(conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public TransaccionCuenta readOne(Long id) throws ServiceException {
        try {
            return transaccionCuentaDAO.readOne(id,conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
