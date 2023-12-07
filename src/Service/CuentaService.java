package Service;

import Controlador.Cuenta;
import DAO.CuentaDAO;
import DAO.CuentaDAOH2Impl;
import Exceptions.DAOException;
import Exceptions.ServiceException;

import java.util.List;

public class CuentaService implements Service<CuentaDAO, Cuenta> {
    private CuentaDAO cuentaDao = new CuentaDAOH2Impl();
    private Cuenta cuenta = new Cuenta();

    @Override
    public void insertarService(Cuenta t) throws ServiceException {
        try {
            cuentaDao.insertar(t);
        } catch (DAOException ex){
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public void actualizarService(Cuenta t) throws ServiceException {
        try {
            cuentaDao.modificar(t);
        } catch (DAOException ex){
             throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public void eliminarService(String id) throws ServiceException {
       try{
           cuentaDao.eliminar(id);
       }catch (DAOException ex){
           throw new ServiceException(ex.getMessage());
       }
    }

    @Override
    public List<Cuenta> obtenerTodosService() throws ServiceException {
        try {
            return cuentaDao.obtenerTodos();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    @Override
    public Cuenta obtenerUnoService(String id) throws ServiceException{
        try {
            return cuentaDao.obtener(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public Cuenta convertirObjeto(String id, String t, float s, long u){
        cuenta.setAccountID(id);
        cuenta.setAccountType(t);
        cuenta.setSaldo(s);
        cuenta.setUserID(u);
        return cuenta;
    }
}
