package Service;

import Controlador.Cuenta;
import Controlador.Transacciones.TransaccionCuenta;
import DAO.CuentaDAO;
import DAO.TransaccionCuentaDAO;
import Exceptions.DAOException;
import Exceptions.ServiceException;
import manager.DBManager;

import java.sql.Connection;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        List<TransaccionCuenta> lista= new ArrayList<>();
        try {
            lista = transaccionCuentaDAO.readAll(conn);
            for (TransaccionCuenta t: lista){
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
        return lista;
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
        if (cuentaEmisora.getSaldo()>monto) {
            cuentaEmisora.setSaldo(cuentaEmisora.getSaldo() - monto);
            cuentaService.update(cuentaEmisora);
            cuentaReceptora.setSaldo(cuentaReceptora.getSaldo() + monto);
            cuentaService.update(cuentaReceptora);
        } else {
            throw new RuntimeException("Saldo insuficiente");
        }
    }
    public ArrayList<String> realizarResumenOrigen(List<TransaccionCuenta> transacciones, Long idUser){
        ArrayList<String> transaccionesFiltradas = new ArrayList<>();
        for (int i = transacciones.size() - 1; i >= 0; i--) {
            TransaccionCuenta transaccion = transacciones.get(i);
            if(Objects.equals(transaccion.getCuentaOrigen(), idUser)){
                String item = "Transferencia enviada: " + "$" + transaccion.getMonto() + " Fecha: " + transaccion.getFecha();
                transaccionesFiltradas.add(item);
            }
            if(Objects.equals(transaccion.getCuentaDestino(), idUser)){
                String item = "Transferencia recibida: " + "$" + transaccion.getMonto() + " Fecha: " + transaccion.getFecha();
                transaccionesFiltradas.add(item);
            }
        }
        return transaccionesFiltradas;
    }

    public static void main(String[] args) {
       TransaccionCuentaService t = new TransaccionCuentaService();
        try {
            ArrayList<String> str = t.realizarResumenOrigen(t.readAll(),16L);
            int n = 1;
            for (String s: str){
                System.out.println(n + s);
                n++;
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
