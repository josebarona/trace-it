package TpProg2.ImplementOfUsers;

import TpProg2.Users.Citizen;

public class FaceToFaceMeeting {
    public Location location;
    public Date start;
    public Date finish;
    public Citizen[] attendeesCitizens;

    public FaceToFaceMeeting(Location location, Date start, Date finish, Citizen[] meeting){
        this.location = location;
        this.start = start;
        this.finish = finish;
        this.attendeesCitizens = meeting;
    }
}
//Esta clase representa un encuentro social que haya sucedido en el pasado, inclye una localizacion, fecha e integrantes de la misma.