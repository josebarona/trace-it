package TpProg2.ImplementOfUsers.Zone;

import TpProg2.Exceptions.DataStoreException;
import TpProg2.Main;
import TpProg2.Users.Citizen;
import java.util.ArrayList;

public class Zone{
    String name;
    private ArrayList<Citizen> citizens = new ArrayList<>();

    public Zone(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Citizen> getCitizens() throws DataStoreException {
        refresh();
        return citizens;
    }

    public void refresh () throws DataStoreException {
        ArrayList<Citizen> allCitizens = Main.generalAMB.citizenDataStore.toArrayList();
        ArrayList<Citizen> localCitizens = new ArrayList<>();
        for (int i = 0; i < allCitizens.size(); i++) {
            if (allCitizens.get(i).getZone().equals(this)){
                localCitizens.add(allCitizens.get(i));
            }
        }
        citizens = localCitizens;
    } //Actualiza y guarda la informacion de todos los ciudadanos de esta zona

}