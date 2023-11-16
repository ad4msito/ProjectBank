package Service;

import Controlador.Cuenta;
import DAO.CuentaDAO;
import DAO.CuentaDAOH2Impl;
import DAO.DAOException;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CuentaService implements ServiceDAO<CuentaDAO, Cuenta> {
    private CuentaDAO c;
    private Connection conn;
    private CuentaDAO cuentas = null;
    public CuentaService(CuentaDAO cuenta, Connection conn){
        this.c = cuenta;
        this.conn = conn;
    }
    public CuentaService(){
    }
    @Override
    public CuentaDAO getDAO() {
        if(cuentas==null){
            cuentas = new CuentaDAOH2Impl(conn);
        }
        return cuentas;
    }

    @Override
    public void insertarService(Cuenta t) throws DAOException{
        cuentas.insertar(t);
    }

    @Override
    public void actualizarService(Cuenta t) throws DAOException {
        cuentas.actualizar(t);
    }

    @Override
    public void eliminarService(String id) throws DAOException {
        cuentas.eliminar(id);
    }

    @Override
    public List<Cuenta> obtenerTodosService() throws DAOException {
        return cuentas.obtenerTodos();
    }

    @Override
    public Cuenta obtenerUnoService(String id) throws DAOException {
        return cuentas.obtener(id);
    }
}
