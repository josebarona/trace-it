package TpProg2.ImplementOfUsers;

import TpProg2.Users.Citizen;

public class Invitation {
    public FaceToFaceMeeting meeting;
    public Citizen transmitter;

    public Invitation(FaceToFaceMeeting meeting, Citizen transmitter) {
        this.meeting = meeting;
        this.transmitter = transmitter;
    }
}