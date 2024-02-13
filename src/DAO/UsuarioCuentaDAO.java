package DAO;

import Controlador.UsuarioCuenta;
import DAO.reusable.deleteMethodDAO;
import Exceptions.DAOException;
import com.sun.org.apache.xpath.internal.operations.Bool;
import jdk.nashorn.internal.ir.CatchNode;
import manager.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public  class UsuarioCuentaDAO implements DAO<UsuarioCuenta,Long> {
    private final String strCreate = "INSERT INTO USUARIOS (NOMBRE, EMAIL, PASSWORD, ESADMIN) VALUES (?, ?, ?, ?)";
    private final String strUpdate = "UPDATE USUARIOS SET NOMBRE= ?, EMAIL = ?, PASSWORD = ?, ESADMIN=? WHERE ID = ?";
    private final String strDelete = "DELETE FROM Usuarios WHERE id = ?";
    private final String strReadAll = "SELECT ID, NOMBRE, EMAIL, PASSWORD, ESADMIN FROM USUARIOS";
    private final String strReadOne = "SELECT ID, NOMBRE, EMAIL, PASSWORD, ESADMIN FROM USUARIOS WHERE ID = ?";
    private final String strReadbyEmail = "SELECT ID, NOMBRE, EMAIL, PASSWORD, ESADMIN FROM USUARIOS WHERE EMAIL = ?";


    @Override
    public void create(UsuarioCuenta a, Connection conn) throws DAOException {
        String nombre = a.getNombre();
        String email = a.getEmail();
        String pass = a.getPassword();
        boolean esAdmin = a.getEsAdmin();
        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(strCreate);
            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, pass);
            ps.setBoolean(4, esAdmin);
            ps.executeUpdate();
            conn.commit();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                a.setId(rs.getLong(1));
            }
        } catch (SQLException e1) {
            try {
                conn.rollback();
                if (e1.getErrorCode() == 23505) {
                    throw new DAOException(e1.getMessage());//objduplicado
                }
            } catch (SQLException e2) {
                throw new DAOException(e2.getMessage());//error al insertar
            }
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e3) {
                throw new DAOException(e3.getMessage());//error al cerrar
            }
        }


    }

    @Override
    public void update(UsuarioCuenta a, Connection conn) throws DAOException {
        String nombre = a.getNombre();
        String email = a.getEmail();
        String pass = a.getPassword();
        Boolean esAdmin = a.getEsAdmin();
        Long id = a.getId();
        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(strUpdate);
            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, pass);
            ps.setBoolean(4,esAdmin);
            ps.setLong(5, id);
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e1) {
            try {
                conn.rollback();
            } catch (SQLException e2) {
                throw new DAOException(e2.getMessage());
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                } catch (SQLException e3) {
                    throw new DAOException(e3.getMessage());
                }
            }
        }
    }

    @Override
    public void delete(Long a, Connection c) throws DAOException {
        deleteMethodDAO.methodDelete(a, c, strDelete);

    }

    private UsuarioCuenta convertir(ResultSet rs) throws DAOException, SQLException {
        Long id = rs.getLong("ID");
        String nombre = rs.getString("NOMBRE");
        String email = rs.getString("EMAIL");
        String pass = rs.getString("PASSWORD");
        Boolean esadmin = rs.getBoolean("ESADMIN");
        UsuarioCuenta usuario = new UsuarioCuenta(nombre, email, pass);
        usuario.setId(id);
        usuario.setEsAdmin(esadmin);
        return usuario;
    }

    @Override
    public List<UsuarioCuenta> readAll(Connection conn) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<UsuarioCuenta> usuarios = new ArrayList<>();
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(strReadAll);
            rs = ps.executeQuery();
            conn.commit();
            while (rs.next()) {
                usuarios.add(convertir(rs));
            }
        } catch (SQLException e1) {
            throw new DAOException(e1.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e2){
                throw new DAOException(e2.getMessage());
            }
        }
        return usuarios;
    }

    @Override
    public UsuarioCuenta readOne(Long id, Connection conn) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        UsuarioCuenta usuario = null;
        try {
            ps = conn.prepareStatement(strReadOne);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                usuario = convertir(rs);
            }
        } catch (SQLException e1) {
            throw new DAOException(e1.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e2){
                throw new DAOException(e2.getMessage());
            }
        }
        return usuario;
    }
    public UsuarioCuenta readEmail(String email, Connection conn) throws DAOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        UsuarioCuenta usuario = null;
        try {
            ps = conn.prepareStatement(strReadbyEmail);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                usuario = convertir(rs);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e2){
                throw new DAOException(e2.getMessage());
            }
        }
        return usuario;
    }
}
