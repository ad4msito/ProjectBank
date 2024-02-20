package Service;

import Controlador.UsuarioCuenta;
import DAO.UsuarioCuentaDAO;
import Exceptions.DAOException;
import Exceptions.ServiceException;
import com.sun.org.apache.xpath.internal.operations.Bool;
import manager.DBManager;

import java.sql.Connection;
import java.util.ArrayList;
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
            usuarioCuentaDAO.update(t,conn);
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
    public UsuarioCuenta singUp(String u, String p) throws ServiceException {
        UsuarioCuenta usuarioEncontrado = null;
        try {
            List<UsuarioCuenta> usuarios = usuarioCuentaDAO.readAll(conn);
            for (UsuarioCuenta user : usuarios) {
                if (user.getEmail().equals(u) && user.getPassword().equals(p)) {
                    usuarioEncontrado = user;
                }
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
        return usuarioEncontrado;
    }
    public boolean esAdmin(UsuarioCuenta u){
        boolean esAdministrador;
        if (u.getEsAdmin()){
            esAdministrador = true;
        } else {
            esAdministrador = false;
        }
        return esAdministrador;
    }
}
