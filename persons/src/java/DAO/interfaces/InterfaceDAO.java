package DAO.interfaces;

import java.io.Serializable;
import java.util.ArrayList;
import DAO.DAOException;


public interface InterfaceDAO<T> extends Serializable {
    void saveOrUpdate(T t) throws DAOException;

    T get(Class objectClass, Serializable id) throws DAOException;

    T load(Class objectClass, Serializable id) throws DAOException;

    void delete(T t) throws DAOException;

}
