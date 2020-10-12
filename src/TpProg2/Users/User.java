package TpProg2.Users;

import TpProg2.DataStore.FileSaveable;

public abstract class User implements FileSaveable {
    String userName;
    String cuil;
    String phoneNumber;
    String type;

    public User(String userName, String cuil, String phoneNumber) {
        this.userName = userName;
        this.cuil = cuil;
        this.phoneNumber = phoneNumber;
    }

    public String getFileRepresentation() {
        String fileData = getId() + "," + getUserName() + "," + getCuil(); //por ahora... despues hay que agregar + cosas.
        return fileData;
    }

    @Override
     public String getId() {
        return this.phoneNumber;
    }

    public String getCuil() {
        return cuil;
    }

    public String getUserName() {
        return userName;
    }

    public String getType() {
        return type;
    }
}