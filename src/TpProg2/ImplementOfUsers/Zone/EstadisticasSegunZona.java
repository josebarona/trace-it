package TpProg2.ImplementOfUsers.Zone;

import TpProg2.Events.Symptom;
import TpProg2.Exceptions.DataStoreException;
import TpProg2.ImplementOfUsers.Date;
import TpProg2.Main;
import TpProg2.Users.Citizen;
import java.util.ArrayList;
import java.util.HashMap;

public class EstadisticasSegunZona {

    public static Symptom commonSymptom (HashMap<Symptom, Integer> count, ArrayList<Symptom> symptoms){ //Devuelve el sintoma mas comun dentro de un HashMap
        int max = 0;
        Symptom commonSymptom = new Symptom("Vacio");
        for (int i = 0; i < symptoms.size(); i++){
            if (count.get(symptoms.get(i)) > max){
                commonSymptom = symptoms.get(i);
                max = count.get(symptoms.get(i));
            }
        }
        return commonSymptom;

    }// Devuelva el sintoma que haya sido registrado mas veces dentro de la zona.

    public static HashMap<Symptom, Integer> symptomCounter (Zone zone, ArrayList<Symptom> symptoms) throws DataStoreException {

        HashMap<Symptom, Integer> count = new HashMap<>();

        for (int i = 0; i < symptoms.size(); i++){ //Crea un HashMap con los sintomas (key) y ceros predeterminados
            count.put(symptoms.get(i), 0);
        }

        for (int i = 0; i < zone.getCitizens().size(); i++){ //Se hace un conteo de todos los sintomas registrados.
            for (int j = 0; j < zone.getCitizens().get(i).getRegisteredSymptoms().size(); j++) {
                Symptom key = zone.getCitizens().get(i).getRegisteredSymptoms().get(j);
                count.put(key, count.get(key) + 1);
            }
        }
        return count;
    }// Rellena un HashMap con un valor para cada sintoma segun cuantos ciudadanos en la zona lo hayan registrado.

    public static HashMap<Symptom, Integer> top3CommonSymptoms(Zone zone,ArrayList<Symptom> symptoms) throws DataStoreException {
        zone.refresh();
        HashMap<Symptom, Integer> count = symptomCounter(zone, symptoms);
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
    }// Devuelve un HashMap con los 3 sintomas mas comunes por zona y la cantidad de ciudadanos que la registraron.

    public static String convertWithIteration(HashMap<Symptom, ?> map) {
        StringBuilder mapAsString = new StringBuilder("(");
        for (Symptom key : map.keySet()) {
            if (!(map.get(key) == null)){
                mapAsString.append(key.getName() + ": " + map.get(key) + " - ");
            }
        }
        mapAsString.delete(mapAsString.length()-2, mapAsString.length()).append(")");
        return mapAsString.toString();
    }// Convierte un HashMap a string. (para testear)

    public static String top3CommonSymptomsString(Zone zone, ArrayList<Symptom> symptoms) throws DataStoreException {
        HashMap<Symptom, Integer> top3 = top3CommonSymptoms(zone, symptoms);
        if (!algunSintoma(zone)){
            return "Ninguno de los ciudadanos registro ningun sintoma.";
        }else{
            return "Los tres sintomas registrados mas comunes registrados por ciudadanos de la zona son: " + convertWithIteration(top3);
        }

    }

    private static boolean algunSintoma(Zone zona) throws DataStoreException {
        for (int i = 0; i < zona.getCitizens().size(); i++) {
            if (!zona.getCitizens().get(i).getRegisteredSymptoms().isEmpty()){
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Citizen> seekCitizens (Zone zone) throws DataStoreException {
        zone.refresh();
        ArrayList<Citizen> seekCitizens = new ArrayList<>();
        for (int i = 0; i < zone.getCitizens().size(); i++) {
            if (zone.getCitizens().get(i).getSeek()){
                seekCitizens.add(zone.getCitizens().get(i));
            }
        }
        return seekCitizens;
    }

    public static int brote(Zone zone) throws DataStoreException {
        zone.refresh();
        ArrayList<Citizen> seekCitizens = seekCitizens(zone);
        int minBroteSize = 2;
        int mayorBrote = 0;

        for (int i = 0; i < seekCitizens.size(); i++) {
            int thisBroteSize = 1;
            ArrayList<Citizen> posiblesConectados = new ArrayList<>();
            posiblesConectados.add(seekCitizens.get(i));
            for (int j = 0; j < seekCitizens.size(); j++) {
                Date thisCitizenDate = seekCitizens.get(i).getGotSeek();
                Date otherCitizenDate = seekCitizens.get(j).getGotSeek();
                int dateDiference = Date.dateDiference(otherCitizenDate, thisCitizenDate);
                if (i != j && dateDiference >= 0 && dateDiference < 47){
                    thisBroteSize ++;
                    posiblesConectados.add(seekCitizens.get(j));
                }
            }
            if (thisBroteSize >= minBroteSize){
                int tamanoDeBroteValido = conectadosEnBrote(posiblesConectados);
                if (tamanoDeBroteValido > mayorBrote){mayorBrote = tamanoDeBroteValido;}
            }
        }

        if (mayorBrote >= minBroteSize){
            return mayorBrote;
        }else{return 0;}
    }

    public static int conectadosEnBrote(ArrayList<Citizen> posiblesConectados){
        int mayorConectados = 0;
        for (int i = 0; i < posiblesConectados.size(); i++) {
            ArrayList<Citizen> conectados = new ArrayList<>();
            conectados.add(posiblesConectados.get(0));
            for (int v = 0; v < posiblesConectados.size(); v++) {
                for (int j = 0; j < posiblesConectados.size(); j++) {
                    if (!conectados.contains(posiblesConectados.get(j)) && hayAlgunContacto(conectados, posiblesConectados.get(j))){
                        conectados.add(posiblesConectados.get(j));
                    }
                }
            }
            if (conectados.size() > mayorConectados){mayorConectados = conectados.size();}
        }
        return mayorConectados;
    }

    public static boolean hayAlgunContacto(ArrayList<Citizen> conectados, Citizen posibleContacto){
        for (int i = 0; i < conectados.size(); i++) {
            if (conectados.get(i).getContacts().contains(posibleContacto)){
                return true;
            }
        }
        return false;
    }

    public static String listadoDeBrotes(ArrayList<Zone> zonas) throws DataStoreException {
        if (mayorBrote(zonas) != null){
            String lista = "\n (nombre de zona) -> [tamaño de su mayor brote]\n\n";
            int count = 1;
            ArrayList<Zone> zonasSinVer = (ArrayList<Zone>) zonas.clone();
            while(mayorBrote(zonasSinVer) != null){
                Zone mayorBrote = mayorBrote(zonasSinVer);
                zonasSinVer.remove(mayorBrote);
                lista += "             " + count + ". " + mayorBrote.getName() + " ->  [" + brote(mayorBrote) + "]\n";
                count ++;
            }
            return lista;
        } else {
            return  "\n    (No hay ningun brote registrado hasta el momento)\n";
        }
    }

    public static Zone mayorBrote(ArrayList<Zone> zonas) throws DataStoreException {
        Zone mayorBrote = null;
        int tamañoDelMayorBrote = 0;
        for (int i = 0; i < zonas.size(); i++) {
            if (brote(zonas.get(i)) > tamañoDelMayorBrote){
                mayorBrote = zonas.get(i);
                tamañoDelMayorBrote = brote(zonas.get(i));
            }
        }
        return mayorBrote;
    }
}
