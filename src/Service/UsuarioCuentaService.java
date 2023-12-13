package Service;

import Controlador.UsuarioCuenta;
import DAO.UsuarioCuentaDAO;
import Exceptions.DAOException;
import Exceptions.ServiceException;
import com.sun.org.apache.xpath.internal.operations.Bool;
import manager.DBManager;

import java.sql.Connection;
import java.util.List;

public class UsuarioCuentaService implements Service<UsuarioCuenta>{
    Connection conn;
    UsuarioCuentaDAO usuarioCuentaDAO;

    public UsuarioCuentaService() {
        this.conn = DBManager.connect();
        this.usuarioCuentaDAO = new UsuarioCuentaDAO();
    }

    @Override
    public void create(UsuarioCuenta t) throws ServiceException {
        try {
            usuarioCuentaDAO.create(t,conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(UsuarioCuenta t) throws ServiceException {
        try {
            usuarioCuentaDAO.create(t,conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            usuarioCuentaDAO.delete(id,conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<UsuarioCuenta> readAll() throws ServiceException {
        try {
            return usuarioCuentaDAO.readAll(conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public UsuarioCuenta readOne(Long id) throws ServiceException {
        try {
            return usuarioCuentaDAO.readOne(id,conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public UsuarioCuenta readEmail(String email)throws ServiceException{
            try {
                return usuarioCuentaDAO.readEmail(email, conn);
            } catch (DAOException e) {
                throw new ServiceException(e.getMessage());
            }
    }
    public Boolean verificarCredenciales(String u, String pass) throws ServiceException {
        try {
            UsuarioCuenta user = usuarioCuentaDAO.readEmail(u,conn);
            if(user!=null) {
                if(user.getPassword().equals(pass)){
                    return true;
                }
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
        return false;
    }
    public Boolean verificarEsAdmin(String mail, String pass) throws ServiceException {
        try {
            UsuarioCuenta user = usuarioCuentaDAO.readEmail(mail,conn);
            if (user != null) {
                if (user.getPassword().equals((pass))) {
                    if(user.getEsAdmin()){
                        return true;
                    }
                }
            }
        } catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
        return false;
    }

}
