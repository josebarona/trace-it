package TpProg2.ImplementOfUsers;

import TpProg2.DataStore.FileSaveable;
import TpProg2.Users.Citizen;

public class Notification implements FileSaveable {
    public Citizen seekCitizen;
    public Date date;

    public Notification(Citizen seekCitizen, Date date) {
        this.seekCitizen = seekCitizen;
        this.date = date;
    }

    @Override
    public String getFileRepresentation() {
        return seekCitizen.getId() + "," + date.getFileRepresentation();
    }

    @Override
    public String getId() {
        return null;
    }
}
