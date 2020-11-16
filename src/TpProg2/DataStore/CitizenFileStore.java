package TpProg2.DataStore;

import TpProg2.DataStore.Relations.NotificationRelation;
import TpProg2.DataStore.Relations.SymptomRelation;
import TpProg2.Events.Symptom;
import TpProg2.Exceptions.ABMUserException;
import TpProg2.Exceptions.CitizenFileStoreException;
import TpProg2.Exceptions.UserNotExistDataStore;
import TpProg2.ImplementOfUsers.Date;
import TpProg2.ImplementOfUsers.Notification;
import TpProg2.ImplementOfUsers.Zone.Zone;
import TpProg2.Main;
import TpProg2.Users.Citizen;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CitizenFileStore extends FileStore<Citizen>{

    //ArrayList<Citizen> toArrayListCitizen;
    NotificationFileStore notificationFileStore = new NotificationFileStore("FileNotificationByCitizenData");
    RegisteredSymptomsData registeredSymptomsFileStore = new RegisteredSymptomsData("FileSymptomPerCitizenData");
    SymptomFileStore symptomFileStore = new SymptomFileStore("FileSymptomData");

    public CitizenFileStore(String fileName) {
        super(fileName);
        //toArrayListCitizen = new ArrayList<>();
    }

    @Override
    public ArrayList<Citizen> toArrayList() throws UserNotExistDataStore { // leer el archivo completo y por linea crear un Citizen(lineToObject(line);), y meter citizens en arrays.
        ArrayList<Citizen> toArrayListCitizen = new ArrayList<>();
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            if (!this.isEmpty()) {
                while ((strLine = br.readLine()) != null) {
                    Citizen citizen = lineToObject(strLine);
                    if (!toArrayListCitizen.contains(citizen)) {
                        toArrayListCitizen.add(citizen);
                    }
                }
                return toArrayListCitizen;
            }
            fstream.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        throw new UserNotExistDataStore();
    }

    @Override
    public Citizen lineToObject(String line) { // lee una linea de un archivo y o transforma en un citizen.
        String[] data = line.split(",");
        String id = data[0];
        String userName = data[1];
        String phoneNumber = data[2];
        String ban = data[3];
        String rejections = data[4];
        String zone = data[5];
        String seek = data[6];
        String gotSeek = data[7];
        Zone zone1 = stringToZone(zone);
        Citizen citizen = new Citizen(userName,id,phoneNumber);
        citizen.setBan(this.stringToBoolean(ban));
        citizen.setRejections(Integer.parseInt(rejections.trim()));
        citizen.setZone(zone1);
        citizen.setSeek(this.stringToBoolean(seek));
        if (!gotSeek.equals("no date")){
            citizen.setGotSeek(Date.stringToDate(gotSeek));
        }
        this.setNotifications(citizen);
        return citizen;
    }


    public void setNotifications(Citizen citizen){ // setea las notificaciones levantadas de un archivo a un ciudadano.
        ArrayList<NotificationRelation> notificationRelations = notificationFileStore.getNotificationsForCitizen(citizen);
        ArrayList<Notification> notifications = new ArrayList<>();
        ArrayList<Notification> receivedNotifications = new ArrayList<>();
        for (NotificationRelation notificationRelation : notificationRelations) {
            String seekCitizenId = notificationRelation.seekCitizenId;
            Citizen seekCitizen1 = this.findById(seekCitizenId);
            if (!notificationRelation.received) {
                notifications.add(new Notification(seekCitizen1, notificationRelation.date));
            }else {
                receivedNotifications.add(new Notification(seekCitizen1, notificationRelation.date));
            }
        }
        citizen.setNotifications(notifications);
        citizen.setReceivedNotifications(receivedNotifications);
    }

    public void setRegisteredSymptom(Citizen citizen){ // setea el array list de los sintomasRegistrados por un ciudadano a un ciudadano. (levantado de un archivo)
        ArrayList<SymptomRelation> symptomRelations = registeredSymptomsFileStore.getSymptomsForCitizen(citizen);
        ArrayList<Symptom> registeredSymptoms = new ArrayList<>();
        for (SymptomRelation symptomRelation : symptomRelations) {
            try {
                Symptom symptom = symptomFileStore.findById(symptomRelation.getSymptomId());
                registeredSymptoms.add(symptom);
            } catch (ABMUserException e) {
                e.printStackTrace();
            }
        }
        citizen.setRegisteredSymptoms(registeredSymptoms);
    }

    private boolean stringToBoolean(String string){
        return string.equals("true");
    } // convierte un string a un boollean

    private Zone stringToZone (String zoneName){ // convierte un string a una zona existente de el ABMGeneral
        try {
            for (int i = 0; i < Main.generalAMB.getZones().size(); i++) {
                if (Main.generalAMB.getZones().get(i).getName().equals(zoneName)){
                    return Main.generalAMB.getZones().get(i);
                }
            }
            throw new CitizenFileStoreException(zoneName);
        } catch (CitizenFileStoreException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Citizen citizen) {
        super.save(citizen);
        for (Notification notification : citizen.getNotifications()) {
            NotificationRelation notificationRelation = NotificationFileStore.toNotificationRelation(citizen, notification,false);
            notificationFileStore.save(notificationRelation);
        }
        for (Notification notification : citizen.getReceivedNotifications()) {
            NotificationRelation notificationRelation = NotificationFileStore.toNotificationRelation(citizen, notification,true);
            notificationFileStore.save(notificationRelation);
        }
        for (Symptom symptom : citizen.getRegisteredSymptoms()) {
            SymptomRelation symptomRelation = RegisteredSymptomsData.toSymptomRelation(citizen, symptom);
            registeredSymptomsFileStore.save(symptomRelation);
        }
    }

}
