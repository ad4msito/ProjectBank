package DAO;

import Controlador.Tarjeta;
import Exceptions.DAOException;

import java.sql.Connection;
import java.util.List;

public class TransaccionTarjetaDAO implements DAO<Tarjeta, Long>{
    final String strCreate = "INSERT INTO TRANSACCIONTARJETA(ID,FECHA,MONTO,ORIGEN,DESTINO) VALUES(?,?,?,?,?)";
    final String strUpdate = "";

    @Override
    public void create(Tarjeta a, Connection c) throws DAOException {

    }

    @Override
    public void update(Tarjeta a, Connection c) throws DAOException {

    }

    @Override
    public void delete(Long a, Connection c) throws DAOException {

    }

    @Override
    public List<Tarjeta> readAll(Connection c) throws DAOException {
        return null;
    }

    @Override
    public Tarjeta readOne(String id, Connection c) throws DAOException {
        return null;
    }
}
