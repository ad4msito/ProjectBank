package Service;

import DAO.DAOException;

import java.util.List;

public interface ServiceDAO<Type/*DAO*/,Type2/*CONTROLADOR*/> {
    Type getDAO();
    void insertarService(Type2 t)throws DAOException;
    void actualizarService(Type2 t)throws DAOException;
    void eliminarService(String id)throws DAOException;
    List<Type2> obtenerTodosService()throws DAOException;
    Type2 obtenerUnoService(String id)throws DAOException;


}
