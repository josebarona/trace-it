package TpProg2.Users;

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