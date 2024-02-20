package Service;

import Controlador.Cuenta;
import Controlador.Transacciones.TransaccionCuenta;
import DAO.CuentaDAO;
import Exceptions.DAOException;
import Exceptions.ServiceException;
import manager.DBManager;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CuentaService implements Service<Cuenta> {
    private Connection conn;
    private CuentaDAO cuentaDAO;

    public CuentaService() {
        this.cuentaDAO = new CuentaDAO();
        this.conn = DBManager.connect();
    }


    @Override
    public void create(Cuenta cuenta) throws ServiceException {
        try {
            cuentaDAO.create(cuenta, conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(Cuenta cuenta) throws ServiceException {
        try {
            cuentaDAO.update(cuenta,conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            cuentaDAO.delete(id,conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Cuenta> readAll() throws ServiceException {
        try {
            return cuentaDAO.readAll(conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Cuenta readOne(Long id) throws ServiceException {
        try {
            return cuentaDAO.readOne(id,conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public List<Cuenta> filtrarPorUsuario(List<Cuenta> cuentas, Long id){
        List<Cuenta> cuentasFiltradas = new ArrayList<>();
        for(Cuenta cuenta : cuentas){
            if (Objects.equals(cuenta.getUsuarioCuenta(), id)){
                cuentasFiltradas.add(cuenta);
            }
        }
        return cuentasFiltradas;
    }
    public Cuenta encontrarCuenta(List<Cuenta> cuentas, String aliasOrCbu, int isCbu){
        Cuenta cuentaEncontrada = null;
        switch (isCbu){
            case 1:
                try {
                    for (Cuenta cuenta : cuentas) {
                        int cbu = Integer.parseInt(aliasOrCbu);
                        if (cuenta.getCbu() == cbu) {
                            cuentaEncontrada = cuenta;
                        }
                    }
                }catch (RuntimeException e1) {
                    throw new RuntimeException(e1.getMessage() + "Error al encontrar el CBU.");
                }
            case 2:
                try {
                    for (Cuenta cuenta : cuentas) {
                        if (Objects.equals(cuenta.getAlias(), aliasOrCbu)) {
                            cuentaEncontrada = cuenta;
                        }
                    }
                } catch (RuntimeException e1){
                    throw new RuntimeException(e1.getMessage() + "Error al encontrar el alias.");
                }
        }
        return cuentaEncontrada;
    }

}
