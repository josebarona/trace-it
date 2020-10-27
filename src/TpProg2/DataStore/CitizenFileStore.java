package TpProg2.DataStore;

import TpProg2.ImplementOfUsers.Zone.Zone;
import TpProg2.Users.Citizen;

import java.util.ArrayList;

public class CitizenFileStore extends FileStore<Citizen>{

    public CitizenFileStore(String fileName) {
        super(fileName);
    }

    private boolean stringToBoolean(String string){
        return string.equals("true");
    }
    private Zone stringToZone (String string){
        return new Zone(string);
    }

    @Override
    public Citizen lineToObject(String line) {
        String[] data = line.split(",");
        String id = data[0];
        String userName = data[1];
        String phoneNumber = data[2];
        String ban = data[3];
        String rejections = data[4];
        String zone = data[5];
        Zone zone1 = stringToZone(zone);
        Citizen citizen = new Citizen(userName,id,phoneNumber);
        citizen.setBan(this.stringToBoolean(ban));
        citizen.setRejections(Integer.parseInt(rejections.trim()));
        citizen.setZone(zone1);
        return citizen;
    }

    public ArrayList<Citizen> toArrayList(){
        // leer el archivo completo y por linea crear un Citizen(lineToObject(line);), y meter citizens en arrays.
        return null;
    }
}
