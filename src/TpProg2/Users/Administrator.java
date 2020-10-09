package TpProg2.Users;

public class Administrator extends User {

    public Administrator(String cuil, String phoneNumber) {
        super(cuil, phoneNumber);
    }

    public void banCitizen(Citizen citizen) {
        citizen.isBan = true;
    }

    public void unbanCitizen(Citizen citizen) {
        citizen.isBan = false;
    }

}