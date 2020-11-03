package TpProg2.Exceptions;

public class CitizenFileStoreException extends DataStoreException{
    public CitizenFileStoreException(String zonaName) {
        super("La zona: " + zonaName + " no existe.");
    }
}
