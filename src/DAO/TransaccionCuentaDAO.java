package DAO;

import Controlador.Tarjeta;
import Controlador.Transaccion;
import Controlador.Transacciones.TransaccionCuenta;
import Controlador.Transacciones.TransaccionTarjeta;
import DAO.reusable.deleteMethodDAO;
import Exceptions.DAOException;
import manager.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaccionCuentaDAO implements DAO<TransaccionCuenta, Long> {
    private final String strCreate = "INSERT INTO TRANSACCIONESCUENTA(MONTO,CUENTA,DESTINO) VALUES (?,?,?)";
    private final String strUpdate = "UPDATE TRANSACCIONESCUENTA SET MONTO=?, CUENTA=?, DESTINO=? WHERE ID = ?";
    private final String strDelete = "DELETE FROM TRANSACCIONESCUENTA WHERE ID = ?";
    private final String strReadAll = "SELECT ID, FECHA, MONTO, CUENTA, DESTINO FROM TRANSACCIONESCUENTA";
    private final String strReadOne = "SELECT ID, FECHA, MONTO, CUENTA, DESTINO FROM TRANSACCIONESCUENTA WHERE ID = ?";
    @Override
    public void create(TransaccionCuenta a, Connection c) throws DAOException {
        Date fecha = a.getFecha();
        Double monto = a.getMonto();
        Long cuenta = a.getCuentaOrigen();
        Long destino = a.getCuentaDestino();
        PreparedStatement ps = null;
        try {
            c.setAutoCommit(false);
            ps = c.prepareStatement(strCreate);
            ps.setDouble(1,monto);
            ps.setLong(2,cuenta);
            ps.setLong(3,destino);
            ps.executeUpdate();
            c.commit();
        } catch (SQLException e1) {
            try {
                c.rollback();
            }catch (SQLException e2){
                throw new DAOException(e2.getMessage());
            }
        } finally {
            try {
                if(ps!=null){
                ps.close();}
            } catch (SQLException e3){
                throw new DAOException(e3.getMessage());
            }
        }
    }

    @Override
    public void update(TransaccionCuenta a, Connection c) throws DAOException {
        Double monto = a.getMonto();
        Long origen = a.getCuentaOrigen();
        Long destino = a.getCuentaDestino();
        Long id = a.getId();
        PreparedStatement ps = null;
        try {
            c.setAutoCommit(false);
            ps = c.prepareStatement(strUpdate);
            ps.setDouble(1,monto);
            ps.setLong(2,origen);
            ps.setLong(3,destino);
            ps.setLong(4,id);
            ps.executeUpdate();
            c.commit();
        } catch (SQLException e1){
            try {
                c.rollback();
                throw new DAOException(e1.getMessage());//error al actualzar datos
            } catch (SQLException e2){
                throw new DAOException(e2.getMessage());//error al restaurar los cambios
            }
        } finally {
            try {
                if(ps!=null){
                    ps.close();}
            } catch (SQLException e3){
                throw new DAOException(e3.getMessage());//error al cerrar conexion
            }
        }
    }

    @Override
    public void delete(Long a, Connection c) throws DAOException {
        deleteMethodDAO.methodDelete(a, c, strDelete);

    }
    private TransaccionCuenta convertir(ResultSet rs) throws SQLException {
        Long id = rs.getLong("ID");
        Date fecha = rs.getDate("FECHA");
        Double monto = rs.getDouble("MONTO");
        Long cuenta = rs.getLong("CUENTA");
        Long destino = rs.getLong("DESTINO");
        TransaccionCuenta transaccion = new TransaccionCuenta(fecha, monto, cuenta, destino);
        transaccion.setId(id);
        return transaccion;
    }

    @Override
    public List<TransaccionCuenta> readAll(Connection c) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TransaccionCuenta> transacciones = new ArrayList<>();
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
    public List<TransaccionCuenta> readAllForUser(Long id, Connection c) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TransaccionCuenta> transacciones = new ArrayList<>();
        try{
            c.setAutoCommit(false);
            ps = c.prepareStatement("SELECT ID, FECHA, MONTO, CUENTA, DESTINO FROM TRANSACCIONESCUENTA WHERE CUENTA = ?");
            ps.setLong(1,id);
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
    public TransaccionCuenta readOne(Long id, Connection c) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        TransaccionCuenta transaccion = null;
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
