package TpProg2.Exceptions;

public class UserNotExistDataStore extends DataStoreException{
    public UserNotExistDataStore() {
        super("El Archivo con el que desea trabajar se encuentra vacio");
    }
}
