package TpProg2.Users;

import TpProg2.DataStore.FileSaveable;

public abstract class User implements FileSaveable {
    String cuil;
    String phoneNumber;

    public User(String cuil, String phoneNumber) {
        this.cuil = cuil;
        this.phoneNumber = phoneNumber;
    }

    public void SelfRecordingOfSymptoms(){}

    @Override
    public String getFileRepresentation() {
        return null;
    }

    @Override
    public String getId() {
        return this.phoneNumber;
    }
}