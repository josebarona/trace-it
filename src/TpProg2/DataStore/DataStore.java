package TpProg2.DataStore;

import TpProg2.Exceptions.ABMUserException;
import TpProg2.Exceptions.DataStoreException;
import TpProg2.Exceptions.SymptomsNotRegistred;

import java.util.ArrayList;

public interface DataStore<T extends Saveable> {
    void save(T t);
    void remove(String id);
    T findById(String id) throws ABMUserException;
    void edit(T t);
    boolean isEmpty();
    boolean exists(String id);
    ArrayList<T> toArrayList() throws DataStoreException;
}
