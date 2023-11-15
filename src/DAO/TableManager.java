package DAO;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableManager {

    public void createUserTable() {

        Connection c = DBManager.connect();

        String sql = "CREATE TABLE CUENTAS ( ACCOUNTID VARCHAR(256) PRIMARY KEY, ACCOUNTTYPE VARCHAR(256), SALDO FLOAT, U INTEGER(10))";

        try {
            Statement s = c.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public void dropUserTable() {

        Connection c = DBManager.connect();

        String sql = "DROP TABLE cuentas";

        try {
            Statement s = c.createStatement();
            s.execute(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

}
