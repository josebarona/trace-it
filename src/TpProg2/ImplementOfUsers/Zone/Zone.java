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
    ArrayList<Citizen> citizens = new ArrayList<>();

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

    public Symptom commonSymptom (HashMap<Symptom, Integer> count, ArrayList<Symptom> symptoms){ //Devuelve el sintoma mas comun dentro de un HashMap
        int max = 0;
        Symptom commonSymptom = null;
        for (int i = 0; i < symptoms.size(); i++){
            if (count.get(symptoms.get(i)) > max){
                commonSymptom = symptoms.get(i);
                max = count.get(symptoms.get(i));
            }
        }
        if (max == 0){
            return null;
        }else{
            return commonSymptom;
        }
    } //Devuelva el sintoma que haya sido registrado mas veces dentro de la zona.

    public HashMap<Symptom, Integer> symptomCounter (ArrayList<Symptom> symptoms){

        HashMap<Symptom, Integer> count = new HashMap<>();

        for (int i = 0; i < symptoms.size(); i++){ //Crea un HashMap con los sintomas (key) y ceros predeterminados
            count.put(symptoms.get(i), 0);
        }

        for (int i = 0; i < citizens.size(); i++){ //Se hace un conteo de todos los sintomas registrados.
            for (int j = 0; j < citizens.get(i).getRegisteredSymptoms().size(); j++) {
                Symptom key = citizens.get(i).getRegisteredSymptoms().get(j);
                count.put(key, count.get(key) + 1);
            }
        }
        return count;
    } //Rellena un HashMap con un valor para cada sintoma segun cuantos ciudadanos en la zona lo hayan registrado.


    public HashMap<Symptom, Integer> top3CommonSymptoms(ArrayList<Symptom> symptoms){
        refresh();
        HashMap<Symptom, Integer> count = symptomCounter(symptoms);
        HashMap<Symptom, Integer> topSymptoms = new HashMap<>();

        Symptom symptom1 = commonSymptom(count, symptoms);
        topSymptoms.put(symptom1, count.get(symptom1));
        count.put(symptom1, 0);

        Symptom symptom2 = commonSymptom(count, symptoms);
        topSymptoms.put(symptom2, count.get(symptom2));
        count.put(symptom2, 0);

        Symptom symptom3 = commonSymptom(count, symptoms);
        topSymptoms.put(symptom3, count.get(symptom3));
        count.put(symptom3, 0);

        return topSymptoms;
    } //Devuelve un HashMap con los 3 sintomas mas comunes por zona y la cantidad de ciudadanos que la registraron.

    public String convertWithIteration(HashMap<Symptom, ?> map) {
        StringBuilder mapAsString = new StringBuilder("{");
        for (Symptom key : map.keySet()) {
            mapAsString.append(key.getName() + ": " + map.get(key) + ", ");
        }
        mapAsString.delete(mapAsString.length()-2, mapAsString.length()).append("}");
        return mapAsString.toString();
    } //Convierte un HashMap a string. (para testear)

    public ArrayList<Citizen> seekCitizens (){
        refresh();
        ArrayList<Citizen> seekCitizens = new ArrayList<>();
        for (int i = 0; i < citizens.size(); i++) {
            if (citizens.get(i).getSeek()){
                seekCitizens.add(citizens.get(i));
            }
        }
        return seekCitizens;
    }

    public int brote(){
        refresh();
        ArrayList<Citizen> seekCitizens = seekCitizens();
        int tamañoDelBroteMin = 5;
        int mayorBrote = 0;
        for (int i = 0; i < seekCitizens.size(); i++) {
            int tamañoDeEsteBrote = 0;
            for (int j = 0; j < seekCitizens.size(); j++) {
                Date thisCitizenDate = seekCitizens.get(i).getGotSeek();
                Date otherCitizenDate = seekCitizens.get(j).getGotSeek();
                int dateDiference = UserInterface.dateDiference(otherCitizenDate, thisCitizenDate);
                if (i != j && dateDiference > 0 && dateDiference < 47){
                    tamañoDeEsteBrote ++;
                    if (tamañoDeEsteBrote > mayorBrote){mayorBrote = tamañoDeEsteBrote;}
                }
            }
        }
        if (mayorBrote >= tamañoDelBroteMin){
            return mayorBrote;
        }else{return 0;}
    }


}