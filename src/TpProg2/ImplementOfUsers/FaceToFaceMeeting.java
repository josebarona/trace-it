package TpProg2.ImplementOfUsers;

import TpProg2.Users.Citizen;

import java.util.Date;

public class FaceToFaceMeeting {
    Date start;
    Date finish;
    Citizen[] attendeesCitizens;

    public FaceToFaceMeeting(Date start, Date finish, Citizen[] meeting){
        this.start = start;
        this.finish = finish;
        this.attendeesCitizens = meeting;
    }
}