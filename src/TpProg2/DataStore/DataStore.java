package TpProg2.DataStore;

public interface DataStore<T extends Saveable> {
    void save(T t);
    void remove(String id);
    T findById(String id);
    void edit(T t);
}
