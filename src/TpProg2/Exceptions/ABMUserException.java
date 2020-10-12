package TpProg2.Exceptions;

public class ABMUserException extends ABMException {
    public ABMUserException(String id) {
        super("El usuario con id: " + id + " no existe.");
    }
}
