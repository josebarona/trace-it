package TpProg2.ImplementOfUsers;

import TpProg2.Users.Citizen;

public class Notification {
    public Citizen seekCitizen;
    public Date date;

    public Notification(Citizen seekCitizen, Date date) {
        this.seekCitizen = seekCitizen;
        this.date = date;
    }
}
