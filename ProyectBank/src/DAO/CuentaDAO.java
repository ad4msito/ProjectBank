package DAO;

import controlador.Cuenta;

import java.util.List;

public interface CuentaDAO extends DAO<Cuenta>{
    void insertar(Cuenta a) throws DAOException;
    void modificar(Cuenta a) throws DAOException;
    void eliminar(Cuenta a) throws DAOException;
    List<Cuenta> obtenerTodos() throws DAOException;
    Cuenta obtener(long id) throws DAOException;
}

