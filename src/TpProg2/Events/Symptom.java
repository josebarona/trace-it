package TpProg2.Events;

import TpProg2.DataStore.FileSaveable;
import TpProg2.DataStore.Saveable;

public class Symptom implements FileSaveable {
    private final String name;

    public Symptom(String name) {
        this.name = name;
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