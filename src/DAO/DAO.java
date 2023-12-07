package DAO;
import Exceptions.DAOException;

import java.util.List;
public interface DAO<T>{
    void insertar(T a) throws DAOException;
    void modificar(T a) throws DAOException;
    void eliminar(String a) throws DAOException;
    List<T> obtenerTodos() throws DAOException;
    T obtener(String id) throws DAOException;
}
