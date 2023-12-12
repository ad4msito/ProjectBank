package DAO;

import Controlador.Cuenta;
import DAO.reusable.deleteMethodDAO;
import Exceptions.DAOException;
import manager.DBManager;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CuentaDAO implements DAO<Cuenta, Long> {
    final String strCreate = "INSERT INTO CUENTAS(ALIAS,SALDO,TIPOCUENTA,USUARIOID) VALUES (?,?,?,?)";
    final String strUpdate = "UPDATE CUENTAS SET ALIAS = ?, SALDO = ?, TIPOCUENTA = ?, USUARIOID=? WHERE ID = ?";
    final String strDelete = "DELETE FROM CUENTAS WHERE ID = ?";
    final String strReadAll = "SELECT  ID, ALIAS, SALDO, TIPOCUENTA, USUARIOID FROM CUENTAS";
    final String strReadOne = "SELECT ID, ALIAS, SALDO, TIPOCUENTA, USUARIOID FROM CUENTAS WHERE ID = ?";


    @Override
    public void create(Cuenta a, Connection conn) throws DAOException {
        String alias = a.getAlias();
        Double saldo = a.getSaldo();
        int tipo = a.getTipoCuenta();
        Long usuarioid = a.getUsuarioCuenta();
        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(strCreate);
            ps.setString(1, alias);
            ps.setDouble(2, saldo);
            ps.setInt(3, tipo);
            ps.setLong(4, usuarioid);
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e1) {
            try {
                conn.rollback();
                if (e1.getErrorCode() == 23505) {
                    throw new DAOException(e1.getMessage());
                }
            } catch (SQLException e2) {
                throw new DAOException(e2.getMessage());
            }
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

    @Override
    public void update(Cuenta a, Connection conn) throws DAOException {
        String alias = a.getAlias();
        Double saldo = a.getSaldo();
        int tipo = a.getTipoCuenta();
        Long usuario = a.getUsuarioCuenta();
        Long id = a.getId();
        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(strUpdate);
            ps.setString(1, alias);
            ps.setDouble(2, saldo);
            ps.setInt(3, tipo);
            ps.setLong(4, usuario);
            ps.setLong(5, id);
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e1) {
            try {
                conn.rollback();
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

    private Cuenta convertir(ResultSet rs) throws SQLException {
        Long id = rs.getLong("ID");
        String alias = rs.getString("ALIAS");
        Double saldo = rs.getDouble("SALDO");
        int tipocuenta = rs.getInt("TIPOCUENTA");
        Long usuarioid = rs.getLong("USUARIOID");
        Cuenta cuenta = new Cuenta(alias, saldo, tipocuenta, usuarioid);
        cuenta.setId(id);
        return cuenta;
    }

    @Override
    public List<Cuenta> readAll(Connection conn) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Cuenta> cuentas = new ArrayList<>();
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(strReadAll);
            rs = ps.executeQuery();
            conn.commit();
            while (rs.next()) {
                cuentas.add(convertir(rs));
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
        }
        return cuentas;
    }

    @Override
    public Cuenta readOne(Long id, Connection conn) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cuenta cuenta = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(strReadOne);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            conn.commit();
            if (rs.next()) {
                cuenta = convertir(rs);
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
        }
        return cuenta;
    }
}
