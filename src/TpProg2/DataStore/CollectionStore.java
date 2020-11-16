package TpProg2.DataStore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CollectionStore<T extends Saveable> implements DataStore<T>{
    Map<String,T> collectionStore;

    public CollectionStore(Map<String, T> collectionStore) {
        this.collectionStore = collectionStore;
    }

    public int size() {
        return collectionStore.size();
    }

    @Override
    public void save(T t) { // guarda un objeto de tipo t como dato en el Hashmap
        collectionStore.put(t.getId(),t);
    }

    @Override
    public void remove(String id) { // borra un objeto de tipo t al encontrarr su id
        collectionStore.remove(id);
    }

    @Override
    public T findById(String id) { // busca en el HashMapa, donde estan guardados los datos de el objeto t, a traves del cuil y te lo devuelve
        if (collectionStore.containsKey(id)){
            return collectionStore.get(id);
        }
        return null;
    }

    @Override
    public void edit(T t) { // buscar manera que en vez de remover y meter de nuevo en la lista. directamente editar lo que quieras editar sobre ese t que buscas. SETTER(?)
        if (collectionStore.containsKey(t.getId())){
            remove(t.getId()); // remueve t de la lista, ya que tiene datos desactualizados.
            save(t); // ingresa el nuevo t con datos actualizados.
        }
    }

    @Override
    public boolean isEmpty() { //pregunta si labase de datos en collections se encuntra vacia.
        return collectionStore.isEmpty();
    }

    @Override
    public boolean exists(String phoneNumber) { // pregunta si un objeto de tipo t se encuentra guardado en la base de datos de collections.
        if (collectionStore.containsKey(phoneNumber)){
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<T> toArrayList() { // pasa el HashMap que contiene a todos los ciudadanos a un ArrayList
        Collection<T> values = collectionStore.values();
        return new ArrayList<T>(values);
    }
}
