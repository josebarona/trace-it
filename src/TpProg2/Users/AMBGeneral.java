package TpProg2.Users;

import TpProg2.DataStore.DataStore;
import TpProg2.Events.Disease;
import TpProg2.Events.Symptom;

public class AMBGeneral {
    DataStore<Disease> dataStoreDiseases;
    DataStore<Symptom> dataStoreSymptoms;

    public AMBGeneral(DataStore<Disease> dataStoreDiseases, DataStore<Symptom> dataStoreSymptoms) {
        this.dataStoreDiseases = dataStoreDiseases;
        this.dataStoreSymptoms = dataStoreSymptoms;
    }
}
