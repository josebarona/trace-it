package TpProg2.ImplementOfUsers;

import TpProg2.Events.Symptom;
import TpProg2.Users.Citizen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Zone {
    String name;
    ArrayList<Citizen> citizens = new ArrayList<>();

    public Zone(String name) {
        this.name = name;
    }

    public void addCitizen(Citizen citizen){
        citizens.add(citizen);
    }

    //mire a los ciudadanos y vea cuente los sintomas
    public HashMap<Symptom, Integer> arrayListToHashMap(ArrayList<Symptom> symptoms){ //crea un HashMap con los sintomas (key) y ceros predeterminados
        HashMap<Symptom, Integer> count = new HashMap<>();
        for (int i = 0; i < symptoms.size(); i++){
            count.put(symptoms.get(i), 0);
        }
        return count;
    }

    public HashMap<Symptom, Integer> symptomCounter (ArrayList<Symptom> symptoms){ //Rellena un HashMap con un valor para cada sintoma segun cuantos ciudadanos en la zona lo hayan registrado.
        HashMap<Symptom, Integer> count = arrayListToHashMap(symptoms);
        for (int i = 0; i < citizens.size(); i++){
            for (int j = 0; j < citizens.get(i).getRegisteredSymptoms().size(); j++) {
                Symptom key = citizens.get(i).getRegisteredSymptoms().get(j);
                count.put(key, count.get(key) + 1);
            }
        }
        return count;
    }

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
            System.out.println("fdlkjkjfkjfsdñlkj");
            return null;
        }else{
            return commonSymptom;
        }
    }

    public HashMap<Symptom, Integer> top3CommonSymptoms(ArrayList<Symptom> symptoms){
        HashMap<Symptom, Integer> count = symptomCounter(symptoms);
        HashMap<Symptom, Integer> topSymptoms = new HashMap<>();

        Symptom symptom1 = commonSymptom(count, symptoms);
        topSymptoms.put(symptom1, count.get(symptom1));
        count.remove(symptom1);

        Symptom symptom2 = commonSymptom(count, symptoms);
        topSymptoms.put(symptom2, count.get(symptom2));
        count.remove(symptom2);

        Symptom symptom3 = commonSymptom(count, symptoms);
        topSymptoms.put(symptom3, count.get(symptom3));

        return topSymptoms;
    }


    public String convertWithIteration(HashMap<Symptom, ?> map) {
        String mapAsString = map.keySet().stream()
                .map(key -> key + "=" + map.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
        return mapAsString;
    }

}