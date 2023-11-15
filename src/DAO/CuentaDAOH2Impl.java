package DAO;

import java.lang.String;
import controlador.Cuenta;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CuentaDAOH2Impl implements CuentaDAO{
    final String INSERT = "INSERT INTO cuentas(accountID, accountType, saldo, u) VALUES(?, ?, ?, ?)";
    final String UPDATE = "UPDATE cuentas SET accountType = ?, saldo = ? WHERE accountID = ?";
    final String DELETE = "DELETE FROM cuentas WHERE accountID = ?";
    final String GETALL = "SELECT accountID, accountType, saldo, u FROM cuentas ";
    final String GETONE = "SELECT ACCOUNTID, ACCOUNTTYPE, SALDO, U FROM CUENTAS WHERE ACCOUNTID = ?";

    private Connection conn;

    /* public CuentaDAOH2Impl(Connection conn){
        this.conn = conn;
    }*/


    public void insertar(Cuenta c) throws DAOException{
        String accountID = c.getAccountID();
        String accountType = c.getAccountType();
        float saldo = c.getSaldo();
        long user = c.getUserID();
        Date d = new Date();
        Connection conn = DBManager.connect();
        try {
            Statement s = conn.createStatement();
            String sql = "INSERT INTO CUENTAS(ACCOUNTID, ACCOUNTTYPE, SALDO, U) VALUES('" + accountID +  "', '" + accountType +  "', '" + saldo + "', '"+ user + "')";
            System.out.println("\ninsertando...");
            s.executeUpdate(sql);
            conn.commit();
        } catch (SQLException ex) {
           try {
               conn.rollback();
               if(ex.getErrorCode() == 23505){
                   throw new DAOException("Objeto duplicado", ex);
               }
           } catch (SQLException e1){
               ex.printStackTrace();
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
        String accountID = c.getAccountID();
        String accountType = c.getAccountType();
        float saldo = c.getSaldo();
        long user = c.getUserID();
        Date d = new Date();

        Connection conn = DBManager.connect();
        try {
            Statement s = conn.createStatement();
            String sql = "UPDATE CUENTAS set ACCOUNTTYPE = '" + accountType + "', SALDO = '" + saldo + "', U = '" + user + "' WHERE ACCOUNTID = '" + accountID + "'";
            System.out.println("\nmodificando...");
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
    public void eliminar(Cuenta u) throws DAOException{

        String sql = "DELETE FROM CUENTAS WHERE ACCOUNTID = '" + u.getAccountID() + "'";
        Connection conn = DBManager.connect();
        try {
            Statement stat = conn.createStatement();
            System.out.println("\nborrando...");
            stat.executeUpdate(sql);
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new DAOException("Error",ex);
            }
        } finally {
            try {
                conn.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
        }
    }
    private Cuenta convertir(ResultSet rs) throws SQLException{
        System.out.println("\nconvirtiendo...");
        String id = rs.getString("ACCOUNTID");
        String tipo = rs.getString("ACCOUNTTYPE");
        float saldo = rs.getFloat("SALDO");
        long userID = rs.getLong("U");
        Cuenta a = new Cuenta(id,tipo,saldo,userID);
        return a;
    }
    public List<Cuenta> obtenerTodos() throws DAOException{
        Connection conn = DBManager.connect();
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Cuenta> cuentas = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                cuentas.add(convertir(rs));
                System.out.println(cuentas);
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en sql", ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex1) {
                    throw new DAOException("ERROR EN SQL", ex1);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex2) {
                    throw new DAOException("ERROR EN SQL", ex2);
                }
            }
        } return cuentas;
    }
    public Cuenta obtener(String userID)throws DAOException {
        Connection conn = DBManager.connect();
        PreparedStatement stat = null;
        ResultSet rs = null;
        Cuenta a = null;
        try {
            stat = conn.prepareStatement(GETONE);
            stat.setString(1, userID);
            rs = stat.executeQuery();
            if (rs.next()) {
                a = convertir(rs);
                System.out.println("Cuenta:" + a.toString());
            } else {
                throw new DAOException("No se encontro ese registro");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en sql", ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex1) {
                    throw new DAOException("ERROR EN SQL", ex1);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex2) {
                    throw new DAOException("ERROR EN SQL", ex2);
                }
            }

            return a;
        }
    }
}
