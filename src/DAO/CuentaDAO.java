package DAO;

import Controlador.Cuenta;

import java.util.List;

public interface CuentaDAO /*extends DAO<Cuenta>*/{
    void insertar(Cuenta a) throws DAOException;
    void actualizar(Cuenta a) throws DAOException;
    void eliminar(String id) throws DAOException;
    List<Cuenta> obtenerTodos() throws DAOException;
    Cuenta obtener(String id) throws DAOException;
}

