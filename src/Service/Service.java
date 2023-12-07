package Service;

import Exceptions.DAOException;
import Exceptions.ServiceException;

import java.util.List;

public interface Service<Type/*DAO*/,Type2/*CONTROLADOR*/> {
    void insertarService(Type2 t) throws  ServiceException;
    void actualizarService(Type2 t) throws  ServiceException;
    void eliminarService(String id) throws  ServiceException;
    List<Type2> obtenerTodosService()throws  ServiceException;
    Type2 obtenerUnoService(String id)throws  ServiceException;


}
