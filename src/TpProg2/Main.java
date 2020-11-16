//pruebas de metodos y menus...
package TpProg2;
import TpProg2.Exceptions.ABMCitizenException;
import TpProg2.Exceptions.ABMUserException;
import TpProg2.Exceptions.DataStoreException;
import TpProg2.ImplementOfUsers.Date;
import TpProg2.ImplementOfUsers.FaceToFaceMeeting;
import TpProg2.ImplementOfUsers.Invitation;
import TpProg2.ImplementOfUsers.Notification;
import TpProg2.ImplementOfUsers.Zone.Zone;
import TpProg2.Users.*;
import TpProg2.Util.Scanner;
import TpProg2.Util.UserInterface;

import java.util.Random;

public class Main {

    public static AMBGeneral generalAMB;

    static {
        try {
            generalAMB = new AMBGeneral();
        } catch (DataStoreException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ABMCitizenException, ABMUserException, DataStoreException {
        demo();
        UserInterface.menuPrincipal();
    }

    public static void demo() throws ABMUserException, ABMCitizenException, DataStoreException {
        Citizen citizen = new Citizen("Marcos_PM", "43447273", "+5491164430555");
        citizen.setZone(generalAMB.obtenerZonaCiudadano(citizen));
        generalAMB.citizenABM.add(citizen);
        generalAMB.obtenerZonaCiudadano(citizen).refresh();
        //Ciudadanos de la zona "A"-----------------------------------------------------------------------------
        Citizen citizen1 = new Citizen("Usuario1", "111", "+5491164430556");
        citizen1.setZone(generalAMB.obtenerZonaCiudadano(citizen1));
        generalAMB.citizenABM.add(citizen1);
        generalAMB.obtenerZonaCiudadano(citizen1).refresh();
        Citizen citizen2 = new Citizen("Usuario2", "222", "+5491164430556");
        citizen2.setZone(generalAMB.obtenerZonaCiudadano(citizen2));
        generalAMB.citizenABM.add(citizen2);
        generalAMB.obtenerZonaCiudadano(citizen2).refresh();
        Citizen citizen3 = new Citizen("Usuario3", "333", "+5491164430556");
        citizen3.setZone(generalAMB.obtenerZonaCiudadano(citizen3));
        generalAMB.citizenABM.add(citizen3);
        generalAMB.obtenerZonaCiudadano(citizen3).refresh();
        Citizen citizen4 = new Citizen("Usuario4", "444", "+5491164430556");
        citizen4.setZone(generalAMB.obtenerZonaCiudadano(citizen4));
        generalAMB.citizenABM.add(citizen4);
        generalAMB.obtenerZonaCiudadano(citizen4).refresh();
        Citizen citizen5 = new Citizen("Usuario5", "555", "+5491164430556");
        citizen5.setZone(generalAMB.obtenerZonaCiudadano(citizen5));
        generalAMB.citizenABM.add(citizen5);
        generalAMB.obtenerZonaCiudadano(citizen5).refresh();
        Citizen citizen6 = new Citizen("Usuario6", "666", "+5491164430556");
        citizen6.setZone(generalAMB.obtenerZonaCiudadano(citizen6));
        generalAMB.citizenABM.add(citizen6);
        generalAMB.obtenerZonaCiudadano(citizen6).refresh();
        Citizen citizen7 = new Citizen("Usuario7", "777", "+5491164430556");
        citizen7.setZone(generalAMB.obtenerZonaCiudadano(citizen7));
        generalAMB.citizenABM.add(citizen7);
        generalAMB.obtenerZonaCiudadano(citizen7).refresh();
        //Ciudadanos de la zona "B"-----------------------------------------------------------------------------
        Citizen citizen1_B = new Citizen("Lucas_T", "42357465", "+5491164430556");
        citizen1_B.setZone(generalAMB.obtenerZonaCiudadano(citizen1_B));
        generalAMB.citizenABM.add(citizen1_B);
        generalAMB.obtenerZonaCiudadano(citizen1_B).refresh();
        Citizen citizen2_B = new Citizen("Pedro_R", "42562843", "+5491164430556");
        citizen2_B.setZone(generalAMB.obtenerZonaCiudadano(citizen2_B));
        generalAMB.citizenABM.add(citizen2_B);
        generalAMB.obtenerZonaCiudadano(citizen2_B).refresh();
        Citizen citizen3_B = new Citizen("Dylan_J", "42967721", "+5491164430556");
        citizen3_B.setZone(generalAMB.obtenerZonaCiudadano(citizen3_B));
        generalAMB.citizenABM.add(citizen3_B);
        generalAMB.obtenerZonaCiudadano(citizen3_B).refresh();
        Citizen citizen4_B = new Citizen("4_B", "44", "+5491164430556");
        citizen4_B.setZone(generalAMB.obtenerZonaCiudadano(citizen4_B));
        generalAMB.citizenABM.add(citizen4_B);
        generalAMB.obtenerZonaCiudadano(citizen4_B).refresh();
        Citizen citizen5_B = new Citizen("5_B", "55", "+5491164430556");
        citizen5_B.setZone(generalAMB.obtenerZonaCiudadano(citizen5_B));
        generalAMB.citizenABM.add(citizen5_B);
        generalAMB.obtenerZonaCiudadano(citizen5_B).refresh();
        //------------------------------------------------------------------------------------------------------
        //BROTE EN ZONA "A"
        //43447273 esta en contacto con 111, 222, 333, (333 fue reciente por lo cual va a recibir una notificacion)
        citizen1.sendRequest(citizen, new Invitation(new FaceToFaceMeeting("Jujuy", new Date(11,14,15), new Date(11,14,16), new Citizen[]{citizen}), citizen1));
        enfermarCiudadano(citizen1);
        citizen2.sendRequest(citizen, new Invitation(new FaceToFaceMeeting("CABA", new Date(1,2,10), new Date(1,2,18), new Citizen[]{citizen}), citizen2));
        enfermarCiudadano(citizen2);
        citizen3.sendRequest(citizen, new Invitation(new FaceToFaceMeeting("Salta", new Date(11,15,14), new Date(11,15,23), new Citizen[]{citizen}), citizen3));
        enfermarCiudadano(citizen3);
        citizen3.sendNotification(citizen, new Notification(citizen3, new Date(11,15,23)));
        //333 esta en contacto con 444
        citizen3.sendRequest(citizen4, new Invitation(new FaceToFaceMeeting("vacio", new Date(10,10,14), new Date(10,10,16), new Citizen[]{citizen4}), citizen3));
        enfermarCiudadano(citizen4);
        citizen4.acceptedRequest(citizen4.getReceivedInvitations().get(0));
        //444 esta en contacto con 777
        citizen4.sendRequest(citizen7, new Invitation(new FaceToFaceMeeting("vacio", new Date(10,10,14), new Date(10,10,16), new Citizen[]{citizen7}), citizen4));
        enfermarCiudadano(citizen7);
        citizen7.acceptedRequest(citizen7.getReceivedInvitations().get(0));
        //666 esta en contacto con 777 pero 666 no esta enfermo
        citizen6.sendRequest(citizen7, new Invitation(new FaceToFaceMeeting("vacio", new Date(10,10,14), new Date(10,10,16), new Citizen[]{citizen7}), citizen6));
        citizen7.acceptedRequest(citizen7.getReceivedInvitations().get(0));
        enfermarCiudadano(citizen5);
        //------------------------------------------------------------------------------------------------------
        //BROTE EN ZONA "B"
        //1 a 2
        citizen1_B.sendRequest(citizen2_B, new Invitation(new FaceToFaceMeeting("vacio", new Date(11,14,15), new Date(8,8,16), new Citizen[]{citizen2_B}), citizen1_B));
        enfermarCiudadano(citizen1_B);
        citizen2_B.acceptedRequest(citizen2_B.getReceivedInvitations().get(0));
        //2 a 3
        citizen2_B.sendRequest(citizen3_B, new Invitation(new FaceToFaceMeeting("vacio", new Date(11,14,15), new Date(8,8,16), new Citizen[]{citizen3_B}), citizen2_B));
        enfermarCiudadano(citizen2_B);
        citizen3_B.acceptedRequest(citizen3_B.getReceivedInvitations().get(0));
        //3 a 4
        citizen3_B.sendRequest(citizen4_B, new Invitation(new FaceToFaceMeeting("vacio", new Date(11,14,15), new Date(8,8,16), new Citizen[]{citizen4_B}), citizen3_B));
        enfermarCiudadano(citizen3_B);
        citizen4_B.acceptedRequest(citizen4_B.getReceivedInvitations().get(0));
        //4 a 5
        citizen4_B.sendRequest(citizen5_B, new Invitation(new FaceToFaceMeeting("vacio", new Date(11,14,15), new Date(8,8,16), new Citizen[]{citizen5_B}), citizen4_B));
        enfermarCiudadano(citizen4_B);
        citizen5_B.acceptedRequest(citizen5_B.getReceivedInvitations().get(0));
        enfermarCiudadano(citizen5_B);
        //------------------------------------------------------------------------------------------------------
        //Algunos ciudadanos bloqueados
        Main.generalAMB.bannedCitizens.add(citizen1_B);
        citizen1_B.setBan(true);
        Main.generalAMB.citizenDataStore.edit(citizen1_B);
        Main.generalAMB.bannedCitizens.add(citizen2_B);
        citizen2_B.setBan(true);
        Main.generalAMB.citizenDataStore.edit(citizen3_B);
        Main.generalAMB.bannedCitizens.add(citizen3_B);
        citizen3_B.setBan(true);
        Main.generalAMB.citizenDataStore.edit(citizen3_B);
    }
    static void enfermarCiudadano(Citizen citizen) throws DataStoreException {
        Random rn = new Random();
        while (citizen.getRegisteredSymptoms().size() < 4) {
            for (int i = 0; i < 4; i++) {
                citizen.getRegisteredSymptoms().add(generalAMB.getSymptoms().get(rn.nextInt(generalAMB.getSymptoms().size()-2) + 1));
            }
        }
        citizen.isSeek();
    }
}