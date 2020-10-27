package TpProg2.Exceptions;

import TpProg2.Users.Administrator;

public class ABMAdminException2 extends ABMException {
    public ABMAdminException2(String id) {
        super("El administrador con id: " + id + " no existe" );
    }
}
