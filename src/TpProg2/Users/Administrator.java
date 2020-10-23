package TpProg2.Users;

import TpProg2.Events.Symptom;
import TpProg2.Main2;
import TpProg2.util.Scanner;


public class Administrator extends User {
    String type;

    public Administrator(String userName, String cuil, String phoneNumber) {
        super(userName ,cuil, phoneNumber);
        this.type = "Administrador";
    }

    public void banCitizen(Citizen citizen) {
        citizen.isBan = true;
    }

    public void unbanCitizen(Citizen citizen) {
        citizen.isBan = false;
    }

    @Override
    public String getType() {
        return this.type + "es";
    }

    @Override
    public String getFileRepresentation() {
        return super.getFileRepresentation();
    }

    @Override
    public String getCuil() {
        return super.getCuil();
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public String getUserName() {
        return super.getUserName();
    }

}