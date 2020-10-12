package TpProg2;

import TpProg2.DataStore.CollectionStore;
import TpProg2.DataStore.DataStore;
import TpProg2.DataStore.FileStore;
import TpProg2.Exceptions.ABMAdminException;
import TpProg2.Exceptions.ABMCitizenException;
import TpProg2.Exceptions.ABMException;
import TpProg2.Exceptions.ABMUserException;
import TpProg2.ImplementOfUsers.FaceToFaceMeeting;
import TpProg2.ImplementOfUsers.Invitation;
import TpProg2.Users.*;
import TpProg2.util.Scanner;
import java.util.Date;
import java.util.HashMap;

// main pasado un poco mas en limpio.

public class Main2 {
    // DATA DE adminis:
    static DataStore<Administrator> administratorDataStore = new CollectionStore<>(new HashMap<>());
    static DataStore<Administrator> administratorDataFile = new FileStore<>("FileAdminData");
    static ABMAdmin adminABM = new ABMAdmin(administratorDataStore);

    // DATA DE Citizens:
    static DataStore<Citizen> citizenDataStore = new CollectionStore<>(new HashMap<>());
    static DataStore<Citizen> citizenDataFile = new FileStore<>("FileCitizenData");
    static ABMCitizen citizenABM = new ABMCitizen(citizenDataStore);

    public static void main(String[] args) {

        menuPrincipal();

    }

    public static void adminRegister() throws ABMAdminException, ABMUserException {
        Administrator administrator = new Administrator(Scanner.getString("Ingrese su nombre de usuario: "),Scanner.getString("Ingrese su cuil: "),Scanner.getString("Ingrese su numero de telefono: "));
        adminABM.add(administrator.getUserName(),administrator.getId(),administrator.getCuil());
        System.out.println("la base de datos esta vacia? " + administratorDataStore.isEmpty());
        //chquear tema archivos, falta por hacer, incompleto
        administratorDataFile.save(administrator);

    }

    public static void citizenRegister() throws ABMCitizenException, ABMUserException {
        Citizen citizen = new Citizen(Scanner.getString("Ingrese su nombre de usuario: "),Scanner.getString("Ingrese su cuil: "),Scanner.getString("Ingrese su numero de telefono: "));
        citizenABM.add(citizen.getUserName(),citizen.getId(),citizen.getCuil());
        System.out.println("la base de datos esta vacia? " + citizenDataStore.isEmpty());
        // chequear tema archivos, falta por hacer, incompleto
        citizenDataFile.save(citizen);
    }

    public static void menuPrincipal(){
        int opcion;
        String password = "TpGrupo14";
        do{
            System.out.println("\n  Menu: ");
            System.out.println(" _________________________________________\n Operaciones:\n 1. Registrarse \n 2. Iniciar sesion (Ciudadano/Admin) \n 3.Exit");
            opcion = Scanner.getInt("Que operación desea realizar: ");

            switch (opcion) {
                case 1:
                    try {
                        citizenRegister();
                    } catch (ABMCitizenException | ABMUserException e) {
                        e.printStackTrace();
                    }
                case 2:
                    // hacer un metodo iniciar sesion y que cuando inicias sesion te mande a el menu de el admin o citizen segun lo que elijiste.
                    /*iniciarSesion(User user);
                    if (iniciarSesion(User user)){
                        MenuCitizen(Citizen citizen);
                    }else{
                        MenuAdmin(Administrator admin);
                    }
                    */
                    // si existe ese usuario al chequear en la base de datos pasa a los menus segun el tipo de usuario
                    //menuCitizen(); O menuAdmin();
                    break;
                case 999: //Registro secreto para ser administrador
                    String word = Scanner.getString("Contrasena de usuarios administradores: ");
                    if (word.equals(password)) {
                        try {
                            adminRegister();
                        } catch (ABMAdminException | ABMUserException e) {
                            e.printStackTrace();
                        }
                    }else{
                        System.out.println("haz sido bloqueado del servidor");
                        System.exit(0);
                    }
                case 3:
                    System.out.println();
                    System.out.println("Adios ;D, gracias por usar nuestro programa");
                    System.exit(0);
                default:
                    System.out.println("\n Opcion invalida! (intente con otra opcion).\n");
            }
        }while(opcion != 3);
    }

    public static boolean iniciarSesion(User user){
        /*
        busca en los datos si existe un usuario con el nombre y id que le pasa
        si existe pasa al menu del usuario al cual se asigna
         */
        return true;
    }

    public static void MenuCitizen(Citizen citizen){
        int opcion;
        do {
            System.out.println("\n  Menu: ");
            System.out.println(" _________________________________________\n Operaciones: \n 1... \n 3...  \n 4. ... \n 5. ... \n 6. Salir ");
            opcion = Scanner.getInt(" Que operación desea realizar: ");

            switch (opcion){
                case 1:
                    // bandejas de entrada salida y general de invitaciones y encuetros
                    break;
                case 2:
                    //registro de sintomas;
                    break;
                case 3:
                    //
                    break;
                case 4: //volver atras
                    menuPrincipal();
                    break;
                case 5: // finalizar programa
                    System.out.println();
                    System.out.println("Adios ;D");
                    System.exit(0);
                    break;
                default:
                    System.out.println("opcion invalida!");


            }

        }while (opcion != 6);// seguramente vaya a haber mas opciones

    }

    void MenuAdministrator(Administrator admin){
        int opcion;
        do {
            System.out.println("\n  Menu: ");
            System.out.println(" _________________________________________\n Operaciones: \n 1... \n 3...  \n 4. ... \n 5. ... \n 6. Salir ");
            opcion = Scanner.getInt(" Que operación desea realizar: ");

            switch (opcion){
                case 1:
                    // dar de alta sintoma
                    break;
                case 2:
                    //bloquear ciudadano
                    break;
                case 3:
                    // desbloquear cuidadano
                    break;
                case 4:
                    //volver atras
                    menuPrincipal();
                    break;
                case 5: // finalizar programa
                    System.out.println();
                    System.out.println("Adios ;D");
                    System.exit(0);
                    break;
                default:
                    System.out.println("opcion invalida!");

            }

        }while (opcion != 6); // seguramente va a haber mas opciones
    }
}
