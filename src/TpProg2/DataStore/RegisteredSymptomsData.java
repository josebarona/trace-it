package TpProg2.DataStore;

import TpProg2.DataStore.FileStore;
import TpProg2.DataStore.Relations.NotificationRelation;
import TpProg2.DataStore.Relations.SymptomRelation;
import TpProg2.Events.Symptom;
import TpProg2.ImplementOfUsers.Date;
import TpProg2.ImplementOfUsers.Notification;
import TpProg2.Users.Citizen;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RegisteredSymptomsData extends FileStore<SymptomRelation> {

    public RegisteredSymptomsData(String fileName) {
        super(fileName);
    }

    public static SymptomRelation toSymptomRelation(Citizen citizen, Symptom symptom){
        return new SymptomRelation(citizen.getId(), symptom.getName());
    }


    public ArrayList<SymptomRelation> getSymptomsForCitizen(Citizen citizen) {
        ArrayList<SymptomRelation> notificationRelations = new ArrayList<>();
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                String[] data = strLine.split(",");
                if (data[1].equals(citizen.getId())) {
                    notificationRelations.add(this.lineToObject(strLine));
                }
            }
            fstream.close();
        }catch (IOException e){
            e.getMessage();
        }
        return notificationRelations;
    }

    @Override
    public SymptomRelation lineToObject(String line) {
        String[] data = line.split(",");
        String citizenId = data[1];
        String symptomName = data[2];
        return new SymptomRelation(citizenId, symptomName);
    }
}
