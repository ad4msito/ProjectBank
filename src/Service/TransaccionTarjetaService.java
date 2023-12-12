package Service;

import Controlador.Transacciones.TransaccionTarjeta;
import DAO.TransaccionTarjetaDAO;
import Exceptions.DAOException;
import Exceptions.ServiceException;
import manager.DBManager;

import java.sql.Connection;
import java.util.List;

public class TransaccionTarjetaService implements Service<TransaccionTarjeta>{
    Connection conn;
    TransaccionTarjetaDAO transaccionTarjetaDAO;

    public TransaccionTarjetaService() {
        this.conn = DBManager.connect();
        this.transaccionTarjetaDAO = new TransaccionTarjetaDAO();
    }

    @Override
    public void create(TransaccionTarjeta t) throws ServiceException {
        try {
            transaccionTarjetaDAO.create(t,conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(TransaccionTarjeta t) throws ServiceException {
        try {
            transaccionTarjetaDAO.update(t,conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            transaccionTarjetaDAO.delete(id,conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<TransaccionTarjeta> readAll() throws ServiceException {
        try {
            return transaccionTarjetaDAO.readAll(conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public TransaccionTarjeta readOne(Long id) throws ServiceException {
        try {
            return transaccionTarjetaDAO.readOne(id,conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
