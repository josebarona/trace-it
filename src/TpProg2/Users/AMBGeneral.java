package TpProg2.Users;

import TpProg2.DataStore.DataStore;
import TpProg2.Events.Disease;
import TpProg2.Events.Symptom;
import TpProg2.Exceptions.ABMAdminException;
import TpProg2.Exceptions.ABMCitizenException;
import TpProg2.Exceptions.ABMUserException;
import TpProg2.ImplementOfUsers.Zone;
import TpProg2.util.Scanner;
import TpProg2.util.UserInterface;
import java.util.ArrayList;

public class AMBGeneral {
    DataStore<Citizen> anses;
    ABMAdmin adminABM;
    ABMCitizen citizenABM;
    ArrayList<Symptom> symptoms;
    Disease disease; // No usamos una lista de enfermedades por ahora, solo hay una.
    ArrayList<Zone> zones;

    public AMBGeneral(DataStore<Citizen> anses, ABMAdmin adminABM, ABMCitizen citizenABM, ArrayList<Symptom> symptoms, Disease disease, ArrayList<Zone> zones) {
        this.anses = anses;
        this.adminABM = adminABM;
        this.citizenABM = citizenABM;
        this.symptoms = symptoms;
        this.disease = disease;
        this.zones = zones;
    }

    //aca irian los metodos de iniciar sesion y registrarse entre otros
}
