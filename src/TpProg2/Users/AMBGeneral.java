package TpProg2.Users;

import TpProg2.DataStore.*;
import TpProg2.Events.Disease;
import TpProg2.Events.Symptom;
import TpProg2.Exceptions.ABMAdminException;
import TpProg2.Exceptions.ABMCitizenException;
import TpProg2.Exceptions.ABMCitizenException2;
import TpProg2.Exceptions.ABMUserException;
import TpProg2.ImplementOfUsers.Zone.Zone;
import TpProg2.Util.Scanner;
import TpProg2.Util.UserInterface;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
    }*/// ----- para crearlo y hacerlo mas a tu gusto

    public AMBGeneral(){ // --------> predeterminado covid.
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

    public ArrayList<Zone> getZones() {
        return zones;
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
        //adminABM.add(administrator.getUserName(),administrator.getId(),administrator.getCuil());
        adminABM.add(administrator);
        //System.out.println("la base de datos esta vacia? " + administratorDataStore.isEmpty());
    }

    public void citizenRegister() throws ABMCitizenException, ABMUserException {
        //Zone zone = zones.get((int) (Math.random() * (zones.size() + 1) + 0));

        String id = Scanner.getString("Ingrese su cuil: ");
        if (anses.exists(id)) {
            String userName;
            Boolean segui = false;

            do {
                userName = Scanner.getString("Ingrese su nombre de usuario: ");
                if(administratorDataStore.exists(userName)){
                    System.out.println(" Este nombre de usuario no esta disponible!");
                }else{segui = true;}
            }while (!segui);

            Citizen citizen = new Citizen(userName, id, Scanner.getString("Ingrese su numero de telefono: "));
            Zone zone = obtenerZonaCiudadano(citizen);
            citizen.setZone(zone);
            //citizenABM.add(citizen.getUserName(), citizen.getId(), citizen.getCuil());
            citizenABM.add(citizen);
            zone.refresh();
            /* hacer metodo que al buscar en el anses al citizen
            segun su cuil, guarde la zona que va a tener a su lado
            en una variable y setear la zona de donde vive al citizen como esta abajo*/
        }

        // HAY QUE AGREGARLO EN LA LISTA DE ZONES.

        //System.out.println("la base de datos esta vacia? " + citizenDataStore.isEmpty());
    }

    private Zone obtenerZonaCiudadano(Citizen citizen) { // Metodo que sirve para obtener la zona de un Citizen al buscar en El DataStore de Anses.
        /*
        idea para anses:
        cuil(id), zona -----> registre la zona al citizen segun el id que metas
        */
        try {
            FileInputStream fstream = new FileInputStream("src/TpProg2/DataStore/data/FileAnsesData");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                String[] data = strLine.split(",");
                if (data[0].equals(citizen.getId())) {
                    for (int i = 0; i < zones.size(); i++) {
                        if (zones.get(i).getName().equals(data[1])){
                            return zones.get(i);
                        }
                    }
                }
            }
            fstream.close();
        }catch (IOException e){
            e.getMessage();
        }
        return null;
    }

    public void iniciarSesion(String userName) throws ABMUserException {
        // "TpGrupo14" ---> contraseña de administradores.
        if (administratorDataStore.exists(userName)){
            String password = Scanner.getString("Ingrese contraseña de administrador: ");
            if (password.equals("TpGrupo14")) {
                Administrator admin = administratorDataStore.findById(userName);
                UserInterface.menuAdministrator(admin);
            }else{
                UserInterface.clear();
                UserInterface.message("CONTRASEÑA INCORRECTA");
                return;
            }
        }else {
            String cuil = Scanner.getString("Ingrese su cuil: ");
            if (citizenDataStore.exists(cuil)) {
                Citizen citizen = citizenDataStore.findById(cuil);
                if (!citizen.isBan()) {
                    UserInterface.menuCitizen(citizen);
                } else {
                    System.out.println("El cuidadano se encuentra bloqueado momentaneamente");
                }
            } else {
                UserInterface.clear();
                UserInterface.message("Este usuario no existe!");
                return;
            }
        }
        /*
        busca en los datos si existe un usuario con el nombre y id que le pasa
        si existe pasa al menu del usuario al cual se asigna
         */
    }



}
