package DAO;

import Controlador.Transacciones.TransaccionCuenta;
import Controlador.Transacciones.TransaccionTarjeta;
import DAO.reusable.deleteMethodDAO;
import Exceptions.DAOException;
import manager.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaccionTarjetaDAO implements DAO<TransaccionTarjeta, Long> {
    private final String strCreate = "INSERT INTO TRANSACCIONESTARJETA(FECHA,MONTO,TARJETA,DESTINO) VALUES(?,?,?,?)";
    private final String strUpdate = "UPDATE TRANSACCIONESTARJETA SET FECHA=?, MONTO=?, TARJETA=?, DESTINO=? WHERE ID=?";
    private final String strDelete = "DELETE FROM TRANSACCIONESTARJETA WHERE ID=?";
    private final String strReadAll = "SELECT ID, FECHA, MONTO, TARJETA, DESTINO FROM TRANSACCIONESTARJETA";
    private final String strReadOne = "SELECT ID, FECHA, MONTO, TARJETA, DESTINO FROM TRANSACCIONESTARJETA WHERE ID = ?";

    @Override
    public void create(TransaccionTarjeta a, Connection c) throws DAOException {
        Date fecha = a.getFecha();
        Double monto = a.getMonto();
        Long tarjeta = a.getTarjetaOrigen();
        Long destino = a.getCuentaDestino();
        PreparedStatement ps = null;
        try {
            c.setAutoCommit(false);
            ps = c.prepareStatement(strCreate);
            ps.setDate(1,fecha);
            ps.setDouble(2,monto);
            ps.setLong(3,tarjeta);
            ps.setLong(4,destino);
            ps.executeUpdate();
            c.commit();
        } catch (SQLException e1){
            try {
                c.rollback();
                throw new DAOException(e1.getMessage());
            } catch (SQLException e2){
                throw new DAOException(e2.getMessage());
            } finally {
                try {
                    if(ps!=null){
                        ps.close();}
                } catch (SQLException e3){
                    throw new DAOException(e3.getMessage());
                }
            }
        }
    }

    @Override
    public void update(TransaccionTarjeta a, Connection c) throws DAOException {
        Date fecha = a.getFecha();
        Double monto = a.getMonto();
        Long tarjeta = a.getTarjetaOrigen();
        Long destino = a.getCuentaDestino();
        Long id = a.getId();
        PreparedStatement ps = null;
        try {
            c.setAutoCommit(false);
            ps = c.prepareStatement(strUpdate);
            ps.setDate(1,fecha);
            ps.setDouble(2,monto);
            ps.setLong(3,tarjeta);
            ps.setLong(4, destino);
            ps.setLong(5, id);
            ps.executeUpdate();
            c.commit();
        }catch (SQLException e1){
            try {
                c.rollback();
                throw new DAOException(e1.getMessage());
            } catch (SQLException e2){
                throw new DAOException( e2.getMessage());
            }finally {
                try{
                    if(ps!=null){
                        ps.close();}
                } catch (SQLException e3){
                    throw new DAOException(e3.getMessage());
                }
            }
        }
    }
@Override
    public void delete(Long a, Connection c) throws DAOException {
    deleteMethodDAO.methodDelete(a, c, strDelete);
}
    private TransaccionTarjeta convertir(ResultSet rs) throws SQLException {
        Long id = rs.getLong("ID");
        Date fecha = rs.getDate("FECHA");
        Double monto = rs.getDouble("MONTO");
        Long tarjeta = rs.getLong("TARJETA");
        Long destino = rs.getLong("DESTINO");
        TransaccionTarjeta transaccion = new TransaccionTarjeta(fecha, monto, tarjeta, destino);
        transaccion.setId(id);
        return transaccion;
    }
    @Override
    public List<TransaccionTarjeta> readAll(Connection c) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TransaccionTarjeta> transacciones = new ArrayList<>();
        try{
            c.setAutoCommit(false);
            ps = c.prepareStatement(strReadAll);
            rs = ps.executeQuery();
            c.commit();
            while(rs.next()){
                transacciones.add(convertir(rs));
            }
        } catch (SQLException e1){
            throw new DAOException(e1.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e2){
                throw new DAOException(e2.getMessage());
            }
        }
        return transacciones;
    }

    @Override
    public TransaccionTarjeta readOne(Long id, Connection c) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        TransaccionTarjeta transaccion = null;
        try{
            c.setAutoCommit(false);
            ps = c.prepareStatement(strReadOne);
            ps.setLong(1,id);
            rs = ps.executeQuery();
            c.commit();
            if(rs.next()){
                transaccion = convertir(rs);
            }
        } catch (SQLException e1){
            throw new DAOException(e1.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e2){
                throw new DAOException(e2.getMessage());
            }
        }
        return transaccion;
    }
}