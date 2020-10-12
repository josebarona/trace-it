package TpProg2.Users;

import java.lang.reflect.InvocationTargetException;

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

    public void symptomRegister(){
        // dar de alta sintomas
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