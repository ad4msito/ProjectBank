package Service;

import Controlador.Tarjeta;
import DAO.TarjetaDAO;
import Exceptions.DAOException;
import Exceptions.ServiceException;
import manager.DBManager;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TarjetaService implements Service<Tarjeta> {
    Connection conn;
    TarjetaDAO tarjetaDAO;

    public TarjetaService() {
        this.conn = DBManager.connect();
        this.tarjetaDAO = new TarjetaDAO();
    }

    @Override
    public void create(Tarjeta t) throws ServiceException {
        try {
            tarjetaDAO.create(t,conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(Tarjeta t) throws ServiceException {
        try {
            tarjetaDAO.update(t,conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            tarjetaDAO.delete(id,conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Tarjeta> readAll() throws ServiceException {
        try {
            return tarjetaDAO.readAll(conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Tarjeta readOne(Long id) throws ServiceException {
        try {
            return tarjetaDAO.readOne(id, conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public List<Tarjeta> filtrarPorUsuario(List<Tarjeta> t, Long id){
        List<Tarjeta> tarjetasFiltradas = new ArrayList<>();
        for(Tarjeta tarjeta: t){
            if(Objects.equals(tarjeta.getUsuarioID(), id)){
                tarjetasFiltradas.add(tarjeta);
            }
        }
        return tarjetasFiltradas;
    }

}
