package TpProg2.Events;

import TpProg2.DataStore.FileSaveable;
import java.util.ArrayList;
import java.util.List;

public class Disease implements FileSaveable {
    String name;
    List<Symptom> symptomArrayList;

    public Disease(String name, List<Symptom> symptomArrayList) {
        this.name = name;
        this.symptomArrayList = symptomArrayList;
    }

    public void addSymptom(String symptomName){
        Symptom symptom = new Symptom(symptomName);
        symptomArrayList.add(symptom);
    }

    public String getName() {
        return name;
    }

    @Override
    public String getFileRepresentation() {
        return null;
    }

    @Override
    public String getId() {
        return name;
    }
}
