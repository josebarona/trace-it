package TpProg2.Users;

import TpProg2.Main;

public class Administrator extends User {
    String type;

    public Administrator(String userName, String cuil, String phoneNumber) {
        super(userName ,cuil, phoneNumber);
        this.type = "Administrador";
    }

    public void banCitizen(Citizen citizen) {
        Main.generalAMB.bannedCitizens.add(citizen);
        citizen.isBan = true;
        Main.generalAMB.citizenDataStore.edit(citizen);
    }

    public void unbanCitizen(Citizen citizen) {
        Main.generalAMB.bannedCitizens.remove(citizen);
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