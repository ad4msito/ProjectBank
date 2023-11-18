package Service;

import Controlador.Cuenta;
import DAO.CuentaDAO;
import DAO.CuentaDAOH2Impl;
import DAO.DAOException;
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
    public void insertarService(Cuenta t) throws DAOException, ServiceException {
        try {
            cuentaDao.insertar(t);
        } catch (DAOException ex){
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public void actualizarService(Cuenta t) throws DAOException, ServiceException {
        try {
            cuentaDao.actualizar(t);
        } catch (DAOException ex){
             throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public void eliminarService(String id) throws DAOException, ServiceException {
       try{
           cuentaDao.eliminar(id);
       }catch (DAOException ex){
           throw new ServiceException(ex.getMessage());
       }
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
