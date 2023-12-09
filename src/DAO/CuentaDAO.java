package DAO;

import Controlador.Cuenta;
import Exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CuentaDAO implements DAO<Cuenta, Long>{
    final String strCreate = "INSERT INTO CUENTAS(ID,ALIAS,SALDO,TIPOCUENTA,USUARIOID) VALUES (?,?,?,?,?)";
    final String strUpdate = "UPDATE CUENTAS SET ALIAS = ?, SALDO = ?, TIPOCUENTA = ? WHERE ID = ?";
    final String strDelete = "DELETE FROM CUENTAS WHERE ID = ?";

    @Override
    public void create(Cuenta a, Connection conn) throws DAOException {
        Long id = a.getId();
        String alias = a.getAlias();
        Double saldo = a.getSaldo();
        int tipo =  a.getTipoCuenta();
        Long usuarioid = a.getUsuarioCuenta().getId();
        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(strCreate);
            ps.setLong(1,id);
            ps.setString(2,alias);
            ps.setDouble(3,saldo);
            ps.setInt(4,tipo);
            ps.setLong(5, usuarioid);
            ps.executeUpdate();
            conn.commit();
        }catch (SQLException e1){
            try {
                conn.rollback();
                if(e1.getErrorCode() ==  23505){
                    throw new DAOException(e1.getMessage());
                }
            }catch (SQLException e2){
                throw new DAOException(e2.getMessage());
            }
        }finally {
            try {
                ps.close();
            } catch (SQLException e3){
                throw new DAOException(e3.getMessage());
            }
        }
    }

    @Override
    public void update(Cuenta a, Connection conn) throws DAOException {
        String alias = a.getAlias();
        Double saldo = a.getSaldo();
        int tipo = a.getTipoCuenta();
        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(strUpdate);
            ps.setString(1,alias);
            ps.setDouble(2,saldo);
            ps.setInt(3, tipo);
            ps.executeUpdate();
            conn.commit();
        }catch(SQLException e1){
           try{
               conn.rollback();
               throw new DAOException(e1.getMessage());
           } catch (SQLException e2){
               throw new DAOException(e2.getMessage());
           } finally {
               try {
                   ps.close();
               } catch (SQLException e3){
                   throw new DAOException(e3.getMessage());
               }
           }
        }
    }

    @Override
    public void delete(Long a, Connection conn) throws DAOException {
        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(strDelete);
            ps.setLong(1,a);
            ps.executeUpdate();
            conn.commit();
        }catch(SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException e2){
                throw new DAOException(e2.getMessage());
            } finally {
                try {
                    ps.close();
                }catch (SQLException e3){
                    throw new DAOException(e3.getMessage());
                }
            }
        }
    }

    @Override
    public List<Cuenta> readAll(Connection conn) throws DAOException {
        return null;
    }

    @Override
    public Cuenta readOne(String id, Connection conn) throws DAOException {
        return null;
    }
}
