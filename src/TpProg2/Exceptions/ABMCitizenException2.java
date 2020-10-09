package TpProg2.Exceptions;

import TpProg2.Users.Citizen;

public class ABMCitizenException2 extends ABMException{
    public ABMCitizenException2(String id) {
        super("El usuario con id: " + id + " no existe");
    }
}
