package DAO.reusable;

import Exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class deleteMethodDAO {
        public static void methodDelete(Long a, Connection c, String strDelete) throws DAOException {
            PreparedStatement ps = null;
            try {
                c.setAutoCommit(false);
                ps = c.prepareStatement(strDelete);
                ps.setLong(1,a);
                ps.executeUpdate();
                c.commit();
            }catch (SQLException e1){
                throw new DAOException(e1.getMessage());
            } finally {
                try {
                    if(ps!=null){
                        ps.close();}
                } catch (SQLException e3) {
                    throw new DAOException(e3.getMessage());
                }
            }
        }

    }
