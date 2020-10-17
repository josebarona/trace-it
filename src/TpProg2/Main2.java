//pruebas de metodos y menus...
package TpProg2;

import TpProg2.DataStore.*;
import TpProg2.Events.Disease;
import TpProg2.Events.Symptom;
import TpProg2.Exceptions.ABMAdminException;
import TpProg2.Exceptions.ABMCitizenException;
import TpProg2.Exceptions.ABMUserException;
import TpProg2.Users.*;
import TpProg2.util.Scanner;

import java.util.ArrayList;
import java.util.HashMap;

// main pasado un poco mas en limpio.

public class Main2 {
    // DATA DE adminis:
    //public static DataStore<Administrator> administratorDataStore = new CollectionStore<>(new HashMap<>()); // GUARDADO EN COLLECTIONS
    static DataStore<Administrator> administratorDataStore = new AdminFileStore("FileAdminData"); // GUARDADO EN FILES
    static ABMAdmin adminABM = new ABMAdmin(administratorDataStore);

    // DATA DE Citizens:
    //public static DataStore<Citizen> citizenDataStore = new CollectionStore<>(new HashMap<>()); // GUARDADO EN COLLECTIONS
    public static DataStore<Citizen> citizenDataStore = new CitizenFileStore("FileCitizenData"); // GUARDADO EN FILES
    static ABMCitizen citizenABM = new ABMCitizen(citizenDataStore);

    //Efermedades y sintomas predeterminados
    Disease covid = new Disease("COVID", new ArrayList<Symptom>());
    // Falta agregar estos sintomas
    //{"Tos", "seca", "Cansancio", "Molestias y dolores", "Dolor de garganta", "Diarrea", "Conjuntivitis", "Dolor de cabeza", "Pérdida del sentido del olfato o del gusto", "Dificultad para respirar o sensación de falta de aire", "Dolor o presión en el pecho"}

    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void adminRegister() throws ABMAdminException, ABMUserException {
        Administrator administrator = new Administrator(Scanner.getString("Ingrese su nombre de usuario: "),Scanner.getString("Ingrese su cuil: "),Scanner.getString("Ingrese su numero de telefono: "));
        adminABM.add(administrator.getUserName(),administrator.getId(),administrator.getCuil());
        //System.out.println("la base de datos esta vacia? " + administratorDataStore.isEmpty());
    }

    public static void citizenRegister() throws ABMCitizenException, ABMUserException {
        Citizen citizen = new Citizen(Scanner.getString("Ingrese su nombre de usuario: "),Scanner.getString("Ingrese su cuil: "),Scanner.getString("Ingrese su numero de telefono: "));
        citizenABM.add(citizen.getUserName(),citizen.getId(),citizen.getCuil());
        //System.out.println("la base de datos esta vacia? " + citizenDataStore.isEmpty());
    }

    public static void iniciarSesion(String userName, String phoneNumber) throws ABMUserException {
        if (administratorDataStore.exists(phoneNumber)){
            Administrator admin = administratorDataStore.findById(phoneNumber);
            menuAdministrator(admin);
        }else if (citizenDataStore.exists(phoneNumber)) {
            Citizen citizen = citizenDataStore.findById(phoneNumber);
            if (!citizen.isBan()){
                menuCitizen(citizen);
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

    public static void menuPrincipal(){
        int opcion;
        String password = "TpGrupo14";
        do{
            System.out.println("\n  Menu: ");
            System.out.println(" _________________________________________\n Operaciones: \n 1. Registrarse \n 2. Iniciar sesion \n 3.Exit");
            opcion = Scanner.getInt("Que operación desea realizar: ");

            switch (opcion) {
                case 1:
                    try {
                        citizenRegister();
                    } catch (ABMCitizenException | ABMUserException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    String userName = Scanner.getString("Nombre de Usuario: ");
                    String phoneNumber = Scanner.getString("Numero de Telefono: ");
                    try {
                        iniciarSesion(userName,phoneNumber);
                    } catch (ABMUserException e) {
                        e.printStackTrace();
                    }
                    break;
                case 999: //Registro secreto para ser administrador
                    String word = Scanner.getString("Contraseña de usuarios administradores: ");
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
                    break;
                case 3:
                    System.out.println();
                    System.out.println("Adios ;D, gracias por usar nuestro programa");
                    System.exit(0);
                default:
                    System.out.println("\n Opcion invalida! (intente con otra opcion).\n");
            }
        }while(opcion != 3);
    }

    public static void menuCitizen(Citizen citizen) throws ABMUserException {
        int opcion;
        do {
            System.out.println("\n  Menu Ciudadano: ");
            System.out.println(" _________________________________________\n Operaciones: \n 1 Bandeja de entrada de invitaciones \n 2. Mandar solicitudes de encuentro \n 3. Registro de sintomas  \n 4. ... \n 5. ... \n 6. Log Out \n 7. Exit ");
            opcion = Scanner.getInt(" Que operación desea realizar: ");

            switch (opcion){
                case 1:
                    citizen.inbox();
                    break;
                case 2:
                    citizen.createIvitation();
                    break;
                case 3:
                    //registro de sintomas;
                    break;
                case 4:
                    //remover sintomas;
                    break;
                case 5:
                    // añadir sintomas
                    break;
                case 6: //volver atras
                    // menuPrincipal(); //no es necesario, termina volviendo solo.
                    break;
                case 7: // finalizar programa
                    System.out.println();
                    System.out.println("Adios ;D");
                    System.exit(0);
                    break;
                /*
                   case 8:
                        ver en las meetings en las que estuvo; ----> (?)
                 */
                default:
                    System.out.println("opcion invalida!");
            }
        }while (opcion != 6);// seguramente vaya a haber mas opciones
    }

    public static void menuAdministrator(Administrator admin) throws ABMUserException {
        int opcion;
        do {
            System.out.println("\n  Menu Administrador: ");
            System.out.println(" _________________________________________\n Operaciones: \n 1... \n 2...  \n 3...  \n 4. Bloquear Ciudadano \n 5. Desbloquear Cuidadano \n 6. Log Out \n 7. Exit ");
            opcion = Scanner.getInt(" Que operación desea realizar: ");

            switch (opcion){
                case 1:
                    // dar de alta sintoma ---> CREARLOS(?)
                    // sacaralos agregarlos
                    break;
                case 2:
                    // ver los sintomas que hay
                    break;
                case 3:
                    //ver citizens bloqueados.
                    break;
                case 4:
                    // el administrador deberia bloquear a un ciudadano?? preguntar ---> si no bloquea, se saca la opcion.
                    String idCitizen = Scanner.getString("Ingrese id del ciudadano al que quiere Bloquear: ");
                    if (citizenDataStore.exists(idCitizen)) {
                        admin.banCitizen(citizenDataStore.findById(idCitizen));
                        System.out.println("El ciudadano a sido bloqueado. ");
                    }else{
                        System.out.println("el usuario al que quiere bloquear no existe");
                    }
                    break;
                case 5:
                    String idCitizen2 = Scanner.getString("Pase id de ciudadano que quiere Desbloquear: ");
                    if (citizenDataStore.exists(idCitizen2)) {
                        admin.unbanCitizen(citizenDataStore.findById(idCitizen2));
                        System.out.println("El ciudadano a sido desbloqueado. ");
                    }else{
                        System.out.println("el usuario al que quiere desbloquear no existe");
                    }
                    break;
                case 6:
                    //volver atras
                    //menuPrincipal(); //no es necesario
                    break;
                case 7: // finalizar programa
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
