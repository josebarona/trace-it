package TpProg2.Users;

import TpProg2.DataStore.FileSaveable;
import TpProg2.Exceptions.*;

public interface ABM<T extends FileSaveable>{  //T(generico) puede ser cualquier tipo de cla

    // Agrega un T a la base de datos.
    //public T add(String userName, String cuil_password, String phoneNumber) throws ABMException; ----> no tiene sentido / revisarlo por tema de interaccion con las demas clases.
    public T add(T t) throws ABMException;

    // Elimina a un ciudadano de la base de datos.
    public void remove(T t) throws ABMException;

    // Modifica un T dentro de la base de datos.
    public void edit(T t) throws ABMException;
}
