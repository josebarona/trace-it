package TpProg2.Users;

import TpProg2.Main;

public class Administrator extends User {
    String type;

    public Administrator(String userName, String cuil, String phoneNumber) {
        super(userName ,cuil, phoneNumber);
        this.type = "Administrador";
    }

    public void banCitizen(Citizen citizen) { // bloquea a un citizen de la aplicacion
        Main.generalAMB.getBannedCitizens().add(citizen);
        citizen.isBan = true;
        Main.generalAMB.getCitizenDataStore().edit(citizen);
    }

    public void unbanCitizen(Citizen citizen) { //desbloquea a un citizen de la aplicacion
        Main.generalAMB.getBannedCitizens().remove(citizen);
        citizen.isBan = false;
    }

    @Override
    public String getType() {
        return this.type + "es";
    }

    @Override
    public String getFileRepresentation() {
        return getId() + "," + getCuil() + "," + getPhoneNumber();
    }


    @Override
    public String getId() {
        return this.userName;
    }

    @Override
    public String getUserName() {
        return super.getUserName();
    }

}