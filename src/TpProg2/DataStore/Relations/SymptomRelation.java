package TpProg2.DataStore.Relations;

import TpProg2.DataStore.FileSaveable;

public class SymptomRelation implements FileSaveable {

    String citizenId;
    String symptomId;

    public SymptomRelation(String citizenId, String symptomId) {
        this.citizenId = citizenId;
        this.symptomId = symptomId;
    }

    public String getSymptomId() {
        return symptomId;
    }

    @Override
    public String getFileRepresentation() {
        return this.getId() + "," + citizenId + "," + symptomId;
    }

    @Override
    public String getId() {
        return citizenId + "-" + symptomId;
    }
}
