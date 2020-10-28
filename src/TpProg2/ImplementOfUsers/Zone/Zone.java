package TpProg2.ImplementOfUsers.Zone;

import TpProg2.DataStore.Saveable;
import TpProg2.Events.Symptom;
import TpProg2.ImplementOfUsers.Date;
import TpProg2.Main;
import TpProg2.Users.Citizen;
import TpProg2.Util.UserInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class Zone{
    String name;
    private ArrayList<Citizen> citizens = new ArrayList<>();

    public Zone(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Citizen> getCitizens() {
        return citizens;
    }

    public void refresh (){
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