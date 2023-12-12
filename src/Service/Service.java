package Service;

import Exceptions.ServiceException;

import java.util.List;

public interface Service<Type1> {
    void create(Type1 t) throws  ServiceException;
    void update(Type1 t) throws  ServiceException;
    void delete(Long id) throws  ServiceException;
    List<Type1> readAll()throws  ServiceException;
    Type1 readOne(Long id)throws  ServiceException;


}
