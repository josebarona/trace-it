package TpProg2.Exceptions;

import TpProg2.Users.Citizen;

public class ABMCitizenException extends ABMException {
    public ABMCitizenException(String id) {
        super("Usuario ya existente con id: " + id);
    }
}
