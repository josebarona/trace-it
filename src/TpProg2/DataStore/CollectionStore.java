package TpProg2.DataStore;

import java.util.Map;

public class CollectionStore<T extends Saveable> implements DataStore<T>{
    Map<String,T> collectionStore;

    public CollectionStore(Map<String, T> collectionStore) {
        this.collectionStore = collectionStore;
    }

    @Override
    public void save(T t) {
        collectionStore.put(t.getId(),t);
    }

    @Override
    public void remove(String id) {
        collectionStore.remove(id);
    }

    @Override
    public T findById(String id) {
        //if (collectionStore.containsKey(id))
        return null;
    }

    @Override
    public void edit(T t) {
    }

    @Override
    public boolean isEmpty() {
        return collectionStore.isEmpty();
    }
}
