package TpProg2.Users;

import TpProg2.DataStore.*;
import TpProg2.Events.Disease;
import TpProg2.Events.Symptom;
import TpProg2.Exceptions.ABMAdminException;
import TpProg2.Exceptions.ABMCitizenException;
import TpProg2.Exceptions.ABMUserException;
import TpProg2.ImplementOfUsers.Zone.Zone;
import TpProg2.util.Scanner;
import TpProg2.util.UserInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AMBGeneral {

    public DataStore<Citizen> anses;
    public DataStore<Administrator> administratorDataStore;
    public ABMAdmin adminABM;
    public DataStore<Citizen> citizenDataStore;
    public ABMCitizen citizenABM;
    public ArrayList<Symptom> symptoms;
    public Disease disease; // No usamos una lista de enfermedades por ahora, solo hay una.
    public ArrayList<Zone> zones;
    public ArrayList<Citizen> seekCitizens;
    //public ArrayList<Citizen> bloqueados;

    /*public AMBGeneral(DataStore<Citizen> anses, ABMAdmin adminABM, ABMCitizen citizenABM, ArrayList<Symptom> symptoms, Disease disease, ArrayList<Zone> zones) {
        this.anses = anses;
        this.adminABM = adminABM;
        this.citizenABM = citizenABM;
        this.symptoms = symptoms;
        this.disease = disease;
        this.zones = zones;
    }*/

    public AMBGeneral(){
        anses = new FileStore<>("FileAnsesData");

        //administratorDataStore = new CollectionStore<>(new HashMap<>()); // GUARDADO EN COLLECTIONS
        administratorDataStore = new AdminFileStore("FileAdminData"); // GUARDADO EN FILES
        adminABM = new ABMAdmin(administratorDataStore);

        citizenDataStore = new CollectionStore<>(new HashMap<>());    // GUARDADO EN COLLECTIONS
        //citizenDataStore = new CitizenFileStore("FileCitizenData"); // GUARDADO EN FILES
        citizenABM = new ABMCitizen(citizenDataStore);

        symptoms = new ArrayList<>(Arrays.asList(
                new Symptom("Toz seca"),
                new Symptom( "Cansancio"),
                new Symptom("Molestias y dolores"),
                new Symptom("Dolor de garganta"),
                new Symptom("Diarrea"),
                new Symptom("Conjuntivitis"),
                new Symptom("Dolor de cabeza"),
                new Symptom("Pérdida del sentido del olfato o del gusto"),
                new Symptom("Dificultad para respirar o sensación de falta de aire"),
                new Symptom("Dolor o presión en el pecho")
        ));

        disease = new Disease("COVID", symptoms);

        zones = new ArrayList<>(Arrays.asList(
                new Zone("A"),
                new Zone("B"),
                new Zone("C"),
                new Zone("D")
        ));

        this.seekCitizens = new ArrayList<Citizen>();
    }

    public void addSeekCitizen(Citizen citizen){
        if (seekCitizens == null || !seekCitizens.contains(citizen)) {
            seekCitizens.add(citizen);
        }
    }

    public void removeSeekCitizen(Citizen citizen){
        if (seekCitizens != null && seekCitizens.contains(citizen)) {
            seekCitizens.remove(citizen);
        }
    }

    public ArrayList<Symptom> getSymptoms() {
        return symptoms;
    }

    public void adminRegister() throws ABMAdminException, ABMUserException {
        Administrator administrator = new Administrator(Scanner.getString("Ingrese su nombre de usuario: "),Scanner.getString("Ingrese su cuil: "),Scanner.getString("Ingrese su numero de telefono: "));
        adminABM.add(administrator.getUserName(),administrator.getId(),administrator.getCuil());
        //System.out.println("la base de datos esta vacia? " + administratorDataStore.isEmpty());
    }

    public void citizenRegister() throws ABMCitizenException, ABMUserException {
        //Zone zone = zones.get((int) (Math.random() * (zones.size() + 1) + 0));

        String id = Scanner.getString("Ingrese su cuil: ");
        if (anses.exists(id)) {

            Citizen citizen = new Citizen(Scanner.getString("Ingrese su nombre de usuario: "), id, Scanner.getString("Ingrese su numero de telefono: "));
            citizenABM.add(citizen.getUserName(), citizen.getId(), citizen.getCuil());
            /* hacer metodo que al buscar en el anses al citizen
            segun su cuil, guarde la zona que va a tener a su lado
            en una variable y setear la zona de donde vive al citizen como esta abajo*/
            Zone zone = obtenerZona(citizen);
            citizen.setZone(zone);
        }

        // HAY QUE AGREGARLO EN LA LISTA DE ZONES.

        //System.out.println("la base de datos esta vacia? " + citizenDataStore.isEmpty());
    }

    private Zone obtenerZona(Citizen citizen) { // Metodo que sirve para obtener la zona de un Citizen al buscar en El DataStore de Anses.
        Zone zone = zones.get(0); // Solo para testear ----> Hacer metodo
        return zone;
    }

    public void iniciarSesion(String userName, String phoneNumber) throws ABMUserException {
        if (administratorDataStore.exists(phoneNumber)){
            Administrator admin = administratorDataStore.findById(phoneNumber);
            UserInterface.menuAdministrator(admin);
        }else if (citizenDataStore.exists(phoneNumber)) {
            Citizen citizen = citizenDataStore.findById(phoneNumber);
            if (!citizen.isBan()){
                UserInterface.menuCitizen(citizen);
            }else{
                System.out.println("El cuidadano se encuentra bloqueado momentaneamente");
            }
        }else{
            System.out.println("\n Este usuario no existe!");
        }
        /*
        busca en los datos si existe un usuario con el nombre y id que le pasa
        si existe pasa al menu del usuario al cual se asigna
         */
    }
}
