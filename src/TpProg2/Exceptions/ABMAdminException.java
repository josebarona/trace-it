package TpProg2.Exceptions;

public class ABMAdminException extends ABMException {
    public ABMAdminException(String id) {
        super("Administrador ya existente con id:" + id);
    }
}
