package TpProg2.DataStore;

import TpProg2.Users.Citizen;

public class CitizenFileStore extends FileStore<Citizen>{

    public CitizenFileStore(String fileName) {
        super(fileName);
    }

    private boolean stringToBoolean(String string){
        return string.equals("true");
    }

    @Override
    public Citizen lineToObject(String line) {
        String[] data = line.split(",");
        String id = data[0];
        String userName = data[1];
        String cuil = data[2];
        String ban = data[3];
        String rejections = data[4];
        Citizen citizen = new Citizen(userName,cuil,id);
        citizen.setBan(this.stringToBoolean(ban));
        citizen.setRejections(Integer.parseInt(rejections.trim()));
        return citizen;
    }
}
