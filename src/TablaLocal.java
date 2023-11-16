import DAO.DAOException;
import DAO.DBManager;

import java.sql.*;
public class TablaLocal {
    public void setTable(String ID, String type, float sal, int userID) throws DAOException {
            String accountID = ID;
        String accountType = type;
        float saldo = sal;
        long user = userID;
        Connection conn = DBManager.connect();
        try {
            PreparedStatement preparedStatement=null;
            Statement s = conn.createStatement();
            String sql = "INSERT INTO CUENTAS(ACCOUNTID, ACCOUNTTYPE, SALDO, U) VALUES('" + accountID +  "', '" + accountType +  "', '" + saldo + "', '"+ user + "')";
            System.out.println("\ninsertando...");
            s.executeUpdate(sql);
            conn.commit();
        } catch (SQLException ex) {
            try {
                conn.rollback();
                if(ex.getErrorCode() == 23505){
                    throw new DAOException("duplicado", ex);
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
}

