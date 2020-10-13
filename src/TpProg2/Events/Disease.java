package TpProg2.Events;

import java.util.ArrayList;

public class Disease {
    String name;
    ArrayList<Symptom> symptomArrayList;

    public Disease(String name, ArrayList<Symptom> symptomArrayList) {
        this.name = name;
        this.symptomArrayList = symptomArrayList;
    }

    public String getName() {
        return name;
    }
}
