package TpProg2.ImplementOfUsers.Zone;

import TpProg2.Events.Symptom;
import TpProg2.Exceptions.DataStoreException;
import TpProg2.ImplementOfUsers.Date;
import TpProg2.Users.Citizen;
import java.util.ArrayList;
import java.util.HashMap;

public class EstadisticasSegunZona {

    public static String commonSymptom (HashMap<String, Integer> count, ArrayList<Symptom> symptoms){ //Devuelve el sintoma mas comun dentro de un HashMap
        int max = 0;
        String commonSymptom = "Vacio";
        for (int i = 0; i < symptoms.size(); i++){
            if (count.get(symptoms.get(i).getName()) > max){
                commonSymptom = symptoms.get(i).getName();
                max = count.get(symptoms.get(i).getName());
            }
        }
        return commonSymptom;

    }// Devuelva el sintoma que haya sido registrado mas veces dentro de la zona.

    public static HashMap<String, Integer> symptomCounter (Zone zone, ArrayList<Symptom> symptoms) throws DataStoreException {

        HashMap<String, Integer> count = new HashMap<>();

        for (int i = 0; i < symptoms.size(); i++){ //Crea un HashMap con los sintomas (key) y ceros predeterminados
            count.put(symptoms.get(i).getName(), 0);
        }

        for (int i = 0; i < zone.getCitizens().size(); i++){ //Se hace un conteo de todos los sintomas registrados.
            for (int j = 0; j < zone.getCitizens().get(i).getRegisteredSymptoms().size(); j++) {
                String key = zone.getCitizens().get(i).getRegisteredSymptoms().get(j).getName();
                count.put(key, count.get(key) + 1);
            }
        }
        return count;
    }// Rellena un HashMap con un valor para cada sintoma segun cuantos ciudadanos en la zona lo hayan registrado.

    public static String top3CommonSymptoms(Zone zone,ArrayList<Symptom> symptoms) throws DataStoreException {
        zone.refresh();
        HashMap<String, Integer> count = symptomCounter(zone, symptoms);
        String top3CommonSymptoms = "";
        for (int i = 0; i < 3; i++) {
            String commonSymptom = commonSymptom(count, symptoms);
            top3CommonSymptoms += commonSymptom + " ->[" + count.get(commonSymptom) + "]";
            if (i < 2){top3CommonSymptoms += ", ";}
            count.put(commonSymptom, 0);
        }//" 1. Cansancio ->(4) / 2. Toz seca ->(3) / 3. Dolores ->(1)
        return top3CommonSymptoms;
    }// Devuelve un HashMap con los 3 sintomas mas comunes por zona y la cantidad de ciudadanos que lo registraron.

    public static String viewTop3CommonSymptomsString(Zone zone, ArrayList<Symptom> symptoms) throws DataStoreException {
        if (!algunSintoma(zone)){
            return "Ninguno de los ciudadanos registro ningun sintoma.";
        }else{
            return "Los tres sintomas mas comunes registrados por ciudadanos de la zona son: " + top3CommonSymptoms(zone, symptoms);
        }

    } // Devuelve un texto que enseña los 3 sintomas mas comunes por zona.

    private static boolean algunSintoma(Zone zona) throws DataStoreException {
        for (int i = 0; i < zona.getCitizens().size(); i++) {
            if (!zona.getCitizens().get(i).getRegisteredSymptoms().isEmpty()){
                return true;
            }
        }
        return false;
    } // Confirma si un ciudadano registro al menos 1 sintoma

    public static ArrayList<Citizen> seekCitizens (Zone zone) throws DataStoreException {
        zone.refresh();
        ArrayList<Citizen> seekCitizens = new ArrayList<>();
        for (int i = 0; i < zone.getCitizens().size(); i++) {
            if (zone.getCitizens().get(i).getSeek()){
                seekCitizens.add(zone.getCitizens().get(i));
            }
        }
        return seekCitizens;
    } // Devuelve una lista con todos los ciudadanos enfermos de una zona

    public static int brote(Zone zone) throws DataStoreException {
        zone.refresh();
        ArrayList<Citizen> seekCitizens = seekCitizens(zone);
        int minBroteSize = 5;
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
    } // Devuelve el tamaño del mayor brote en una zona. (Cuando hay mas de 5 enfermos registrados en menos de 47 horas y estos comparten una conexion entre si)

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
    } // Devuelve la cantidad de cuidadanos conectados en un grupo de ciudadanos (que compartan un hilo de contactos)

    public static boolean hayAlgunContacto(ArrayList<Citizen> conectados, Citizen posibleContacto){
        for (int i = 0; i < conectados.size(); i++) {
            if (conectados.get(i).getContacts().contains(posibleContacto)){
                return true;
            }
        }
        return false;
    } // Confirma si un ciudadano es contacto de algun otro en un grupo de personas.

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
    } // Devuelve una lista de brotes de zonas (de mayor a menor tamaño).

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
    } // Devuelve la zona que contiene el mayor brote registrado.
}