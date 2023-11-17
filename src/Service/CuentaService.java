package Service;

import Controlador.Cuenta;
import DAO.CuentaDAO;
import DAO.CuentaDAOH2Impl;
import DAO.DAOException;

import java.sql.Connection;
import java.util.List;

public class CuentaService implements ServiceDAO<CuentaDAO, Cuenta> {
    private CuentaDAO cuentaDao = new CuentaDAOH2Impl();
    private Cuenta cuenta = new Cuenta();
    public CuentaService(){
    }
    @Override
    public CuentaDAO getDAO() {
        if(cuentaDao ==null){
            cuentaDao = new CuentaDAOH2Impl();
        }
        return cuentaDao;
    }

    @Override
    public void insertarService(Cuenta t) throws DAOException{
        cuentaDao.insertar(t);
    }

    @Override
    public void actualizarService(Cuenta t) throws DAOException {
        cuentaDao.actualizar(t);
    }

    @Override
    public void eliminarService(String id) throws DAOException {
       cuentaDao.eliminar(id);
    }

    @Override
    public List<Cuenta> obtenerTodosService() throws DAOException {
        return cuentaDao.obtenerTodos();
    }

    @Override
    public Cuenta obtenerUnoService(String id) throws DAOException {
        return cuentaDao.obtener(id);
    }
    public Cuenta convertirObjeto(String id, String t, float s, long u){
        cuenta.setAccountID(id);
        cuenta.setAccountType(t);
        cuenta.setSaldo(s);
        cuenta.setUserID(u);
        return cuenta;
    }
}
