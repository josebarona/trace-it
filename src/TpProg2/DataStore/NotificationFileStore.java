package TpProg2.DataStore;

import TpProg2.DataStore.FileStore;
import TpProg2.DataStore.Relations.NotificationRelation;
import TpProg2.ImplementOfUsers.Date;
import TpProg2.ImplementOfUsers.Notification;
import TpProg2.Users.Citizen;
import java.io.*;
import java.util.ArrayList;

public class NotificationFileStore extends FileStore<NotificationRelation> {

    public NotificationFileStore(String fileName) {
        super(fileName);
    }

    public static NotificationRelation toNotificationRelation(Citizen citizen, Notification notification, boolean received){
        return new NotificationRelation(citizen.getId(), notification.seekCitizen.getId(), notification.date, received);
    }


    public ArrayList<NotificationRelation> getNotificationsForCitizen(Citizen citizen) {
        ArrayList<NotificationRelation> notificationRelations = new ArrayList<>();
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                String[] data = strLine.split(",");
                if (data[1].equals(citizen.getId())) {
                    notificationRelations.add(this.lineToObject(strLine));
                }
            }
            fstream.close();
        }catch (IOException e){
            e.getMessage();
        }
        return notificationRelations;
    }

    @Override
    public NotificationRelation lineToObject(String line) {
        String[] data = line.split(",");
        String citizenId = data[1];
        String seekCitizenId = data[2];
        Date date = Date.stringToDate(data[3]);
        String received1 = data[4];
        boolean received = received1.equals("true");
        return new NotificationRelation(citizenId,seekCitizenId,date,received);
    }


}
