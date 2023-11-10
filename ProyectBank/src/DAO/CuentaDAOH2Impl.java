package DAO;

import controlador.Cuenta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

public class CuentaDAOH2Impl implements CuentaDAO{
    /*final String INSERT = "INSERT INTO cuentas(accountID, accountType, saldo, u) VALUES(?, ?, ?, ?)";
    final String UPDATE = "UPDATE cuentas SET accountType = ?, saldo = ? WHERE accountID = ?";
    final String DELETE = "DELETE FROM cuentas WHERE accountID = ?";
    final String GETALL = "SELECT accountID, accountType, saldo, u FROM cuentas ";
    final String GETONE = "SELECT accountID, accountType, saldo, u FROM cuentas WHERE accountID = ?";
    */
    private Connection conn;

    public CuentaDAOH2Impl(Connection conn){
        this.conn = conn;
    }

    public CuentaDAOH2Impl() {
    }

    public void insertar(Cuenta c) throws DAOException{
        String accountType = c.getAccountType();
        float saldo = c.getSaldo();
        long user = c.getUserID();
        Date d = new Date();

        Connection conn = DBManager.connect();
        try {
            PreparedStatement preparedStatement=null;
            Statement s = conn.createStatement();
            String sql = "INSERT INTO CUENTAS(ACCOUNTTYPE, SALDO, U) VALUES('" + accountType +  "', '" + saldo + "', '"+ user + "')";
            s.executeUpdate(sql);
            conn.commit();
        } catch (SQLException e) {
           try {
               conn.rollback();
               if(e.getErrorCode() == 23505){
                   throw new DAOException("Objeto duplicado", e);
               }
           } catch (SQLException e1){
               e.printStackTrace();
           }
        } finally {
            try {
                conn.close();
            } catch (SQLException e1){
                e1.printStackTrace();
            }
        }
    }
    public void modificar(Cuenta c) throws DAOException{
        long accountID = 1;
        String accountType = c.getAccountType();
        float saldo = c.getSaldo();
        long user = c.getUserID();
        Date d = new Date();

        Connection conn = DBManager.connect();
        try {
            Statement s = conn.createStatement();
            String sql = "UPDATE CUENTAS set ACCOUNTTYPE = '" + accountType + "', SALDO = '" + saldo + "', U = '" + user + "' WHERE ACCOUNTID = '" + accountID + "'";
            s.executeUpdate(sql);
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
                if(e.getErrorCode() == 23505){
                    throw new DAOException("Objeto duplicado", e);
                }
            } catch (SQLException e1){
                e.printStackTrace();
            }
        } finally {
            try {
                conn.close();
            } catch (SQLException e1){
                e1.printStackTrace();
            }
        }
    }
    public void eliminar(Cuenta c){

    }
    public List<Cuenta> obtenerTodos(){ return null;}
    public Cuenta obtener(long id){return null;}
}
