package TpProg2.DataStore.Relations;

import TpProg2.DataStore.FileSaveable;
import TpProg2.ImplementOfUsers.Date;

public class NotificationRelation implements FileSaveable {
    String citizenId;
    public String seekCitizenId;
    public Date date;
    public boolean received;

    public NotificationRelation(String citizenId, String seekCitizenId, Date date, boolean received) {
        this.citizenId = citizenId;
        this.seekCitizenId = seekCitizenId;
        this.date = date;
        this.received = received;
    }

    @Override
    public String getFileRepresentation() {
        return this.getId() + "," + this.citizenId + "," + this.seekCitizenId + "," + this.date.getFileRepresentation() + this.received;
    }

    @Override
    public String getId() {
        return this.citizenId + "-" + this.seekCitizenId;
    }
}
