package TpProg2.DataStore;

import TpProg2.Exceptions.ABMUserException;

public interface DataStore<T extends Saveable> {
    void save(T t);
    void remove(String id);
    T findById(String id) throws ABMUserException;
    void edit(T t);
    boolean isEmpty();
}
