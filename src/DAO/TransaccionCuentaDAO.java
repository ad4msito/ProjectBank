package DAO;

import Controlador.Transaccion;
import Controlador.Transacciones.TransaccionCuenta;
import Exceptions.DAOException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TransaccionCuentaDAO implements DAO<TransaccionCuenta, Long> {
    final String strCreate = "INSERT INTO TRANSACCIONCUENTA(ID,FECHA,MONTO,ORIGEN,DESTINO) VALUES (?,?,?,?,?)";
    final String strUpdate = "UPDATE TRANSACCIONCUENTA SET FECHA=?, MONTO=? WHERE ID = ?";
    final String strDelete = "DELETE FROM TRANSACCIONCUENTA WHERE ID = ?";
    @Override
    public void create(TransaccionCuenta a, Connection c) throws DAOException {
        Long id = a.getId();
        Date fecha = a.getFecha();
        Double monto = a.getMonto();
        Long origen = a.getCuentaOrigen().getId();
        Long destino = a.getCuentaDestino().getId();
        PreparedStatement ps = null;
        try {
            c.setAutoCommit(false);
            ps = c.prepareStatement(strCreate);
            ps.setLong(1,id);
            ps.setDate(2,fecha);
            ps.setDouble(3,monto);
            ps.setLong(4,origen);
            ps.setLong(5,destino);
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
                ps.close();
            } catch (SQLException e3){
                throw new DAOException(e3.getMessage());
            }
        }
    }

    @Override
    public void update(TransaccionCuenta a, Connection c) throws DAOException {
        Date fecha = a.getFecha();
        Double monto = a.getMonto();
        PreparedStatement ps = null;
        try {
            c.setAutoCommit(false);
            ps = c.prepareStatement(strUpdate);
            ps.setDate(1,fecha);
            ps.setDouble(2,monto);
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
                ps.close();
            } catch (SQLException e3){
                throw new DAOException(e3.getMessage());//error al cerrar conexion
            }
        }
    }

    @Override
    public void delete(Long a, Connection c) throws DAOException {
        PreparedStatement ps = null;
        Long id = a;
        try {
            c.setAutoCommit(false);
            ps = c.prepareStatement(strDelete);
            ps.executeUpdate();
            c.commit();
        }catch (SQLException e1){
            try {
                c.rollback();
                throw new DAOException(e1.getMessage());
            } catch (SQLException e2){
                throw new DAOException(e2.getMessage());
            } finally {
                try {
                    ps.close();
                } catch (SQLException e3) {
                    throw new DAOException(e3.getMessage());
                }
            }

        }
    }

    @Override
    public List<TransaccionCuenta> readAll(Connection c) throws DAOException {
        return null;
    }

    @Override
    public TransaccionCuenta readOne(String id, Connection c) throws DAOException {
        return null;
    }
}
