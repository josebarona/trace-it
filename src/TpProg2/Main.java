package TpProg2;

import TpProg2.DataStore.CollectionStore;
import TpProg2.DataStore.DataStore;
import TpProg2.Exceptions.ABMAdminException;
import TpProg2.Exceptions.ABMCitizenException;
import TpProg2.ImplementOfUsers.FaceToFaceMeeting;
import TpProg2.ImplementOfUsers.Invitation;
import TpProg2.Users.ABMAdmin;
import TpProg2.Users.ABMCitizen;
import TpProg2.Users.Administrator;
import TpProg2.Users.Citizen;
import TpProg2.util.Scanner;

import java.util.Date;
import java.util.HashMap;

public class Main {
    // DATA DE adminis:
    static DataStore<Administrator> administratorDataStore = new CollectionStore<>(new HashMap<>());
    static ABMAdmin adminABM = new ABMAdmin(administratorDataStore);

    // DATA DE Citizens:
    static DataStore<Citizen> citizenDataStore = new CollectionStore<>(new HashMap<>());
    static ABMCitizen citizenABM = new ABMCitizen(citizenDataStore);

    public static void main(String[] args) throws ABMCitizenException, ABMAdminException {

        Citizen c1 = new Citizen("1", "2");
        FaceToFaceMeeting f2fm = new FaceToFaceMeeting(
                new Date(),
                new Date(),
                new Citizen[] {c1}
        );
        Invitation invitation = new Invitation(f2fm, c1);
        Administrator admin = new Administrator("3", "4");

        adminRegister();

    }

    public static void adminRegister() throws ABMAdminException {
        Administrator administrator = new Administrator(Scanner.getString("Ingrese su cuil: "),Scanner.getString("Ingrese su numero de telefono"));
        adminABM.add(administrator.getId(),administrator.getCuil());
        System.out.println("la base de datos esta vacia? " + administratorDataStore.isEmpty());
    }

    public static void citizenRegister() throws ABMCitizenException {
        Citizen citizen = new Citizen(Scanner.getString("Ingrese su cuil: "),Scanner.getString("Ingrese su numero de telefono"));
        citizenABM.add(citizen.getId(),citizen.getCuil());
        System.out.println("la base de datos esta vacia? " + citizenDataStore.isEmpty());
    }

    void menuStart(){
        int opcion;
        do{
            System.out.println("\n  Menu: ");
            System.out.println(" _________________________________________\n Operaciones:\n 1. Registrarse \n 2. Iniciar sesion (Ciudadano/Admin)\n 3. AMB \n 4. ... \n 5. ... \n 6. Salir ");
            opcion = Scanner.getInt(" Que operación desea realizar: ");

            switch (opcion) {
                case 1:     //registrar usuario
                    System.out.println("Para crear un nuevo usuario debe ingresar los siguientes datos");
                    String cuil = Scanner.getString(" Su numero de cuil: ");
                    String phoneNumber = Scanner.getString(" Su numero de celular: ");

                    // Deberiamos validar de alguna manera los datos y fijarnos que el usuario no este ya registrado.
                    // y tendriamos que ver si va a necesitar una contraseña para iniciar sesion.

                    /*
                    try{
                        ABMCitizen.add(cuil, phoneNumber);
                    }catch(ABMCitizenException e){
                        //...
                    }
                    */
                    break;
                case 2:     //Iniciar sesion
                    // aca tendriamos que preguntar si va a ingresar como ciudadano o admin
                    // primero tenemos que fijarnos que exista el usuario y que no este bloqueado, despues que la contraseña coincida
                    // y por ultimo deberiamos movernos a otro menu de usuario o admin con todas las opciones del programa.
                    break;
                case 3:
                    //AMB
                    break;
                case 4:
                    //
                    break;
                case 5:
                    //
                    break;
                case 6:
                    System.out.println("\n ¡Gracias por usar este programa!\n");
                    break;
                default:
                    System.out.println("\n Opcion invalida! (intente con otro numero).\n");
            }
        }while(opcion != 6);
    }

    void MenuCitizen(Citizen citizen){
        //algo parecido al de arriba pero tiene que tener todas las funcionalidades que nos piden para los ciudadanos.
        //Bandeja de entrada para poder aceptar o rechazar invitaciones.(Deberia aparecer quien lo envio y el lugar/horario del meeting)
        //Tambien deberian poder crear una invitacion y enviarla a otros ciudadanos.
        //Tienen que poder dignosticar sus sintomas y darlos de baja cuando quieran. (sintomas/eventos que crearon los admins)
    }
    void MenuAdministrator(Administrator admin){
        // Tiene que poder bloquear y desbloquear ciudadanos
        // Tiene que poder ingresar nuevos eventos/sintomas para los ciudadanos
        // ...
        //
    }


}