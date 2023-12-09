package DAO;

import Controlador.UsuarioCuenta;
import Exceptions.DAOException;
import manager.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public  class UsuarioCuentaDAO implements DAO<UsuarioCuenta,Long>{
    final String strCreate = "INSERT INTO USUARIOS (ID, NOMBRE, EMAIL, PASSWORD) VALUES (?, ?, ?, ?)";
    final String strUpdate = "UPDATE USUARIOS SET NOMBRE= ?, EMAIL = ?, PASSWORD = ? WHERE ID = ?";
    final String strDelete = "DELETE FROM Usuarios WHERE id = ?";
    final String strReadAll = "SELECT * FROMM USUARIOS";
    final String strReadOne = "SELECT * FROM USUARIOS WHERE ID = ?";
    Connection conn = null;

    @Override
    public void create(UsuarioCuenta a, Connection conn) throws DAOException {
        Long id = a.getId();
        String nombre = a.getNombre();
        String email = a.getEmail();
        String pass = a.getPassword();
        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(strCreate);
            ps.setLong(1,id);
            ps.setString(2,nombre);
            ps.setString(3,email);
            ps.setString(4,pass);
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e1){
            try {
                conn.rollback();
                if(e1.getErrorCode() == 23505){
                    throw new DAOException(e1.getMessage());//objduplicado
                }
            } catch (SQLException e2){
                throw new DAOException(e2.getMessage());//error al insertar
            }
        } finally {
            try {
                ps.close();
            } catch (SQLException e3){
                throw new DAOException(e3.getMessage());//error al cerrar
            }
        }


    }

    @Override
    public void update(UsuarioCuenta a, Connection conn) throws DAOException {
        String nombre = a.getNombre();
        String email = a.getEmail();
        String pass = a.getPassword();
        conn = DBManager.connect();
        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(strUpdate);
            ps.setString(1,nombre);
            ps.setString(2, email);
            ps.setString(3,pass);
            ps.executeUpdate();
            conn.commit();
        }catch (SQLException e1){
            try {
                conn.rollback();
            } catch (SQLException e2){
                throw new DAOException(e2.getMessage());
            }finally {
                try {
                    ps.close();
                } catch (SQLException e3) {
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
        } catch (SQLException e1){
                throw new DAOException(e1.getMessage());
            }finally{
                try{
                    ps.close();
                } catch (SQLException e3){
                    throw new DAOException(e3.getMessage());
                }
            }
        }

    @Override
    public List<UsuarioCuenta> readAll(Connection conn) throws DAOException {
        return null;
    }

    @Override
    public UsuarioCuenta readOne(String id, Connection conn) throws DAOException {
        return null;
    }
}

