package TpProg2.Users;

import TpProg2.DataStore.FileSaveable;
import TpProg2.Exceptions.*;

public interface ABM<T extends FileSaveable>{  //T(generico) puede ser cualquier tipo de cla

    // Base de datos generica ---> depende que objeto sea.

    // Agrega un T a la base de datos.
    public T add(T t) throws ABMException;

    // Elimina a un ciudadano de la base de datos.
    public void remove(T t) throws ABMException;

    // Modifica un T dentro de la base de datos.
    public void edit(T t) throws ABMException;
}
