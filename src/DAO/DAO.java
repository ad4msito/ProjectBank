package DAO;

import Exceptions.DAOException;

import java.sql.Connection;
import java.util.List;
public interface DAO<T,T1>{
    void create(T a, Connection c) throws DAOException;
    void update(T a, Connection c) throws DAOException;
    void delete(T1 a, Connection c) throws DAOException;
    List<T> readAll(Connection c) throws DAOException;
    T readOne(T1 id, Connection c) throws DAOException;
}
