package TpProg2.ImplementOfUsers;

import TpProg2.DataStore.FileSaveable;
import TpProg2.Users.Citizen;

import java.util.Random;

public class FaceToFaceMeeting implements FileSaveable {

    public String location;
    public Date start;
    public Date finish;
    public Citizen[] attendeesCitizens;

    public FaceToFaceMeeting(String location, Date start, Date finish, Citizen[] meeting){
        this.location = location;
        this.start = start;
        this.finish = finish;
        this.attendeesCitizens = meeting;
    }

    public Citizen[] getAttendeesCitizens() {
        return attendeesCitizens;
    }

    @Override
    public String getFileRepresentation() {
        return this.getId() + "," + this.location + "," + start.getFileRepresentation() + "," + finish.getFileRepresentation();
    }

    @Override
    public String getId() {
        Random r = new Random();
        int low = 1;
        int high = 999999999;
        int result = r.nextInt(high-low) + low;
        return result + "";
    }
}

//Esta clase representa un encuentro social que haya sucedido en el pasado, inclye una localizacion, fecha e integrantes de la misma.