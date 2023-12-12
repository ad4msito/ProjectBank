package DAO;

import Controlador.Tarjeta;
import DAO.reusable.deleteMethodDAO;
import Exceptions.DAOException;
import manager.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarjetaDAO implements DAO<Tarjeta,Long> {
    final String strCreate = "INSERT INTO TARJETAS(NUMERO,LIMITE,USUARIO) VALUES (?,?,?)";
    final String strUpdate = "UPDATE TARJETAS SET NUMERO=?, LIMITE=?, USUARIO=? WHERE ID = ?";
    final String strDelete = "DELETE FROM TARJETAS WHERE ID = ?";
    final String strReadAll = "SELECT ID, NUMERO, LIMITE, USUARIO FROM TARJETAS";
    final String strReadOne = "SELECT ID, NUMERO, LIMITE, USUARIO FROM TARJETAS WHERE ID = ?";

    @Override
    public void create(Tarjeta a, Connection c) throws DAOException {
        int num = a.getNumero();
        Double limite = a.getLimite();
        Long usuario = a.getUsuarioID();
        PreparedStatement ps = null;
        try {
            c.setAutoCommit(false);
            ps = c.prepareStatement(strCreate);
            ps.setInt(1, num);
            ps.setDouble(2, limite);
            ps.setLong(3, usuario);
            ps.executeUpdate();
            c.commit();
        } catch (SQLException e1) {
            try {
                c.rollback();
                throw new DAOException(e1.getMessage());
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
    public void update(Tarjeta a, Connection c) throws DAOException {
        int num = a.getNumero();
        Double limite = a.getLimite();
        Long usuario = a.getUsuarioID();
        Long id = a.getId();
        PreparedStatement ps = null;
        try {
            c.setAutoCommit(false);
            ps = c.prepareStatement(strUpdate);
            ps.setInt(1, num);
            ps.setDouble(2, limite);
            ps.setLong(3, usuario);
            ps.setLong(4, id);
            ps.executeUpdate();
            c.commit();
        } catch (SQLException e1) {
            try {
                c.rollback();
                throw new DAOException(e1.getMessage());
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

    private Tarjeta convertir(ResultSet rs) throws SQLException {
        Long id = rs.getLong("ID");
        int numero = rs.getInt("NUMERO");
        Double limite = rs.getDouble("LIMITE");
        Long usuario = rs.getLong("USUARIO");
        Tarjeta tarjeta = new Tarjeta(numero, limite, usuario);
        tarjeta.setId(id);
        return tarjeta;
    }

    @Override
    public List<Tarjeta> readAll(Connection c) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Tarjeta> tarjetas = new ArrayList<>();
        try {
            c.setAutoCommit(false);
            ps = c.prepareStatement(strReadAll);
            rs = ps.executeQuery();
            c.commit();
            while (rs.next()) {
                tarjetas.add(convertir(rs));
            }
        } catch (SQLException e1) {
            throw new DAOException(e1.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e2) {
                throw new DAOException(e2.getMessage());
            }
            return tarjetas;
        }
    }

    @Override
    public Tarjeta readOne(Long id, Connection c) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Tarjeta tarjeta = null;
        try {
            c.setAutoCommit(false);
            ps = c.prepareStatement(strReadOne);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            c.commit();
            if (rs.next()) {
                tarjeta = convertir(rs);
            }
        } catch (SQLException e1) {
            throw new DAOException(e1.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e2) {
                throw new DAOException(e2.getMessage());
            }
            return tarjeta;
        }
    }
}
