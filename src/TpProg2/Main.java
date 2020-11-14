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
        //generalAMB.obtenerZonaCiudadano(citizen).refresh();

        //Ciudadanos de la zona "A"
        Citizen citizen1 = new Citizen("Usuario1", "111", "+5491164430556");
        citizen.setZone(generalAMB.obtenerZonaCiudadano(citizen1));
        generalAMB.citizenABM.add(citizen1);
        System.out.println(generalAMB.obtenerZonaCiudadano(citizen1).getName());
        System.out.println(citizen.getZone().getName());
        //generalAMB.obtenerZonaCiudadano(citizen1).refresh();

        /*
        Citizen citizen2 = new Citizen("Usuario2", "222", "+5491164430557");
        citizen.setZone(generalAMB.obtenerZonaCiudadano(citizen));
        generalAMB.citizenABM.add(citizen2);
        Citizen citizen3 = new Citizen("Usuario3", "333", "+5491164430558");
        citizen.setZone(generalAMB.obtenerZonaCiudadano(citizen));
        generalAMB.citizenABM.add(citizen3);
        Citizen citizen4 = new Citizen("Usuario4", "444", "+5491164430559");
        citizen.setZone(generalAMB.obtenerZonaCiudadano(citizen));
        generalAMB.citizenABM.add(citizen4);
        Citizen citizen5 = new Citizen("Usuario5", "555", "+5491164430560");
        citizen.setZone(generalAMB.obtenerZonaCiudadano(citizen));
        generalAMB.citizenABM.add(citizen5);
        Citizen citizen6 = new Citizen("Usuario6", "666", "+5491164430561");
        citizen.setZone(generalAMB.obtenerZonaCiudadano(citizen));
        generalAMB.citizenABM.add(citizen6);
        Citizen citizen7 = new Citizen("Usuario7", "777", "+5491164430562");
        citizen.setZone(generalAMB.obtenerZonaCiudadano(citizen));
        generalAMB.citizenABM.add(citizen7);
        generalAMB.obtenerZonaCiudadano(citizen).refresh();
        generalAMB.obtenerZonaCiudadano(citizen).refresh();

        //Ciudadanos de la zona "B"
        Citizen citizen1_B = new Citizen("Usuario1_B", "11", "+5491164430656");
        citizen.setZone(generalAMB.obtenerZonaCiudadano(citizen));
        generalAMB.citizenABM.add(citizen1_B);
        Citizen citizen2_B = new Citizen("Usuario2_B", "22", "+5491164430657");
        citizen.setZone(generalAMB.obtenerZonaCiudadano(citizen));
        generalAMB.citizenABM.add(citizen2_B);
        Citizen citizen3_B = new Citizen("Usuario3_B", "33", "+5491164430658");
        citizen.setZone(generalAMB.obtenerZonaCiudadano(citizen));
        generalAMB.citizenABM.add(citizen3_B);
        Citizen citizen4_B = new Citizen("Usuario4_B", "44", "+5491164430659");
        citizen.setZone(generalAMB.obtenerZonaCiudadano(citizen));
        generalAMB.citizenABM.add(citizen4_B);
        Citizen citizen5_B = new Citizen("Usuario5_B", "55", "+5491164430660");
        citizen.setZone(generalAMB.obtenerZonaCiudadano(citizen));
        generalAMB.citizenABM.add(citizen5_B);
        Citizen citizen6_B = new Citizen("Usuario6_B", "66", "+5491164430661");
        citizen.setZone(generalAMB.obtenerZonaCiudadano(citizen));
        generalAMB.citizenABM.add(citizen6_B);
        Citizen citizen7_B = new Citizen("Usuario7_B", "77", "+5491164430662");
        citizen.setZone(generalAMB.obtenerZonaCiudadano(citizen));
        generalAMB.citizenABM.add(citizen7_B);
        */
        //-------------------------------------------------------------------------------------------------
        FaceToFaceMeeting meeting = new FaceToFaceMeeting("Jujuy", new Date(1,1,1), new Date(11,14,18), new Citizen[]{citizen});
        Invitation invitation = new Invitation(meeting, citizen1);
        citizen1.sendRequest(citizen, invitation);
        citizen1.getRegisteredSymptoms().add(generalAMB.getSymptoms().get(1));
        citizen1.getRegisteredSymptoms().add(generalAMB.getSymptoms().get(2));
        citizen1.getRegisteredSymptoms().add(generalAMB.getSymptoms().get(3));
        citizen1.getRegisteredSymptoms().add(generalAMB.getSymptoms().get(4));
        Notification notification = new Notification(citizen1, new Date(11,14,18));
        citizen1.sendNotification(citizen, notification);

    }

}