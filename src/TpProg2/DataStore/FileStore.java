package TpProg2.DataStore;

public class FileStore<T extends FileSaveable> implements DataStore<T>{
    String fileName;

    @Override
    public void save(T t) {

    }

    @Override
    public void remove(String id) {

    }

    @Override
    public T findById(String id) {
        return null;
    }

    @Override
    public void edit(T t) {

    }
}
