package Service;

import Controlador.Cuenta;
import Controlador.Transacciones.TransaccionCuenta;
import DAO.CuentaDAO;
import DAO.TransaccionCuentaDAO;
import Exceptions.DAOException;
import Exceptions.ServiceException;
import manager.DBManager;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class TransaccionCuentaService implements Service<TransaccionCuenta> {

    Connection conn;
    TransaccionCuentaDAO transaccionCuentaDAO;
    TransaccionCuenta transaccionCuenta;
    CuentaService cuentaService;
    CuentaDAO cuentaDAO;

    public TransaccionCuentaService() {
        this.conn = DBManager.connect();
        this.cuentaDAO = new CuentaDAO();
        this.transaccionCuentaDAO = new TransaccionCuentaDAO();
        this.cuentaService = new CuentaService();
    }

    public void realizarTransaccion(Cuenta o, Cuenta d, Double monto) throws ServiceException {
        try {
            if (o.getSaldo() > monto) {
                o.setSaldo(o.getSaldo() - monto);
                cuentaDAO.update(o, conn);

                d.setSaldo(d.getSaldo() + monto);
                cuentaDAO.update(d, conn);
                transaccionCuenta = new TransaccionCuenta(monto, o.getId(), d.getId());
                transaccionCuentaDAO.create(transaccionCuenta, conn);
            } else {
                throw new ServiceException("No hay saldo suficiente para realizar la operacion.");
            }
        } catch (DAOException e1) {
            throw new ServiceException(e1.getMessage());
        }
    }

    @Override
    public void create(TransaccionCuenta t) throws ServiceException {
        try {
            transaccionCuentaDAO.create(t, conn);
        } catch (DAOException e) {
            throw new ServiceException("Error al crear una transaccion.");
        }
    }

    @Override
    public void update(TransaccionCuenta t) throws ServiceException {
        try {
            transaccionCuentaDAO.update(t, conn);
        } catch (DAOException e) {
            throw new ServiceException("Error al editar la transaccion");
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            transaccionCuentaDAO.delete(id, conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<TransaccionCuenta> readAll() throws ServiceException {
        try {
            return transaccionCuentaDAO.readAll(conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public TransaccionCuenta readOne(Long id) throws ServiceException {
        try {
            return transaccionCuentaDAO.readOne(id, conn);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void realizarTransaccion(Cuenta cuentaEmisora, Cuenta cuentaReceptora, double monto) throws ServiceException{
        cuentaEmisora.setSaldo(cuentaEmisora.getSaldo() - monto);
        cuentaService.update(cuentaEmisora);
        cuentaReceptora.setSaldo(cuentaReceptora.getSaldo() + monto);
        cuentaService.update(cuentaReceptora);
    }
}
