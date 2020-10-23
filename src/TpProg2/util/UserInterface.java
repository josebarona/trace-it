package TpProg2.util;

import TpProg2.Events.Symptom;
import TpProg2.Exceptions.ABMAdminException;
import TpProg2.Exceptions.ABMCitizenException;
import TpProg2.Exceptions.ABMUserException;
import TpProg2.ImplementOfUsers.Date;
import TpProg2.ImplementOfUsers.FaceToFaceMeeting;
import TpProg2.ImplementOfUsers.Invitation;
import TpProg2.Main2;
import TpProg2.Users.Administrator;
import TpProg2.Users.Citizen;

import java.util.HashMap;

//La idea de este metodo es tener todos los metodos de interfaz que se ven en consola, para no cargar el main
public class UserInterface {
    private UserInterface() { }

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
                        Main2.citizenRegister();
                    } catch (ABMCitizenException | ABMUserException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    String userName = Scanner.getString("Nombre de Usuario: ");
                    String cuil = Scanner.getString("Numero de Cuil: ");
                    try {
                        Main2.iniciarSesion(userName,cuil);
                    } catch (ABMUserException e) {
                        e.printStackTrace();
                    }
                    break;
                case 999: //Registro secreto para ser administrador
                    String word = Scanner.getString("Contraseña de usuarios administradores: ");
                    if (word.equals(password)) {
                        try {
                            Main2.adminRegister();
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
                default:
                    System.out.println("\n Opcion invalida! (intente con otra opcion).\n");
            }
        }while(opcion != 3);
        System.exit(0);
    }

    public static void menuCitizen(Citizen citizen) throws ABMUserException {
        int opcion;
        do {
            System.out.println("\n  Menu Ciudadano: ");
            System.out.println(" _________________________________________\n Operaciones: \n 1. Bandeja de entrada de invitaciones \n 2. Mandar solicitudes de encuentro \n 3. Registro de sintomas  \n 4. Ver/eliminar sintomas registrados \n 5. ... \n 6. Log Out \n 7. Exit ");
            opcion = Scanner.getInt(" Que operación desea realizar: ");

            switch (opcion){
                case 1:
                    inbox(citizen);
                    break;
                case 2:
                    createIvitation(citizen);
                    break;
                case 3:
                    selfRecordingOfSymptoms(citizen);
                    break;
                case 4:
                    removeSymptom(citizen);
                    break;
                case 5:
                    // ver en las meetings en las que estuvo; ----> (?)
                    break;
                case 6: //volver atras
                    // menuPrincipal(); //no es necesario, termina volviendo solo.
                    break;
                case 7: // finalizar programa
                    System.out.println();
                    System.out.println("Adios ;D");
                    System.exit(0);
                    break;
                default:
                    System.out.println("opcion invalida!");
            }
        }while (opcion != 6);// seguramente vaya a haber mas opciones
    }

    public static void menuAdministrator(Administrator admin) throws ABMUserException {
        int opcion;
        do {
            System.out.println("\n  Menu Administrador: ");
            System.out.println(" _________________________________________\n Operaciones: \n 1 (sintomas) \n 2...  \n 3...  \n 4. Bloquear Ciudadano \n 5. Desbloquear Cuidadano \n 6. Log Out \n 7. Exit ");
            opcion = Scanner.getInt(" Que operación desea realizar: ");

            switch (opcion){
                case 1:
                    symptomRegister(admin); //se ven los sintomas, se pueden eliminar o agregar nuevos.
                    break;
                case 2:
                    //
                    break;
                case 3:
                    //ver citizens bloqueados.
                    break;
                case 4:
                    // el administrador deberia bloquear a un ciudadano?? preguntar ---> si no bloquea, se saca la opcion.
                    String idCitizen = Scanner.getString("Ingrese id del ciudadano al que quiere Bloquear: ");
                    if (Main2.citizenDataStore.exists(idCitizen)) {
                        admin.banCitizen(Main2.citizenDataStore.findById(idCitizen));
                        System.out.println("El ciudadano a sido bloqueado. ");
                    }else{
                        System.out.println("el usuario al que quiere bloquear no existe");
                    }
                    break;
                case 5:
                    String idCitizen2 = Scanner.getString("Pase id de ciudadano que quiere Desbloquear: ");
                    if (Main2.citizenDataStore.exists(idCitizen2)) {
                        admin.unbanCitizen(Main2.citizenDataStore.findById(idCitizen2));
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

    public static void clear(){
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    public static void message(String message){
        System.out.println("- - - - - - - - - - - - - - - -\n " + message + "- - - - - - - - - - - - - - - -\n ");
    }

    public static void inbox (Citizen citizen){
        int opcion;
        do {
            System.out.println("\n  Bandeja de entrada de invitaciones: ");
            System.out.println(viewInvitationsNames(citizen) + "\n99. (volver)");
            opcion = Scanner.getInt(" Que invitacion deseas ver: ");

            if (opcion < citizen.getReceivedInvitations().size()) {
                int opcion1;
                do {
                    System.out.println(viewInvitationInfo(citizen.getReceivedInvitations().get(opcion)));
                    opcion1 = Scanner.getInt(" Usted confirma haber estado en este encuentro? \n1. Aceptar \n2. Rechazar \n3. (volver)\n Opcion: ");
                    switch (opcion1){
                        case 1:
                            citizen.acceptedRequest(citizen.getReceivedInvitations().get(opcion));
                            System.out.println(" Solicitud aceptada!!");
                            opcion1 = 3;
                            break;
                        case 2:
                            citizen.rejectedRequest(citizen.getReceivedInvitations().get(opcion));
                            System.out.println(" Solicitud rechazada!!");
                            opcion1 = 3;
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println(" Opcion invalida!");
                    }
                }while(opcion1 != 3);
            }else if(opcion != 99){
                System.out.println(" Opcion invalida!");
            }
        }while (opcion != 99);
    } // Metodo que permite navegar por la bandeja de entrada, pudiendo aceptar/rechazar invitaciones de meetings depues de ver su informacion.

    public static String viewInvitationsNames(Citizen citizen){
        String lista = "";
        if (citizen.getReceivedInvitations().size() > 0) {
            for (int i = 0; i < citizen.getReceivedInvitations().size(); i++) {
                lista += i + ". " + citizen.getReceivedInvitations().get(i).transmitter.getUserName() + "\n";
            }
        }else{
            lista += "\n (No tienes ninguna invitacion a eventos en tu bandeja de entrada)\n";
        }
        return lista;
    } // Devuelve un String con los nombres de los emisores de cada invitacion dentro de la bandeja de entrada (en forma de lista).

    public static String viewInvitationInfo(Invitation invitation){
        String info = " Invitacion de " + invitation.transmitter.getUserName() + ": \n";
        info += " Ecuentro realizado en " + invitation.meeting.location + ", el dia " + invitation.meeting.start.mes + "/" + invitation.meeting.start.dia + " a las " + invitation.meeting.start.hora + "hs.";
        info += "\n hasta el dia " + invitation.meeting.finish.mes + "/" + invitation.meeting.finish.dia + " a las " + invitation.meeting.finish.hora + "hs.";
        return info;
    } //Metodo que devuelve un String con toda la informacion que lleva una invitacion (location, date, citizens)

    public static void createIvitation(Citizen citizen) throws ABMUserException {
        System.out.println(" Porfavor ingrese los siguientes datos sobre el encuentro al cual asistio: ");
        //1 Location
        String location = Scanner.getString(" El nombre de la ubicacion del encuentro: ");
        //2 Date
        Date start = new Date(Scanner.getDate(" Ingrese el numero de mes en el cual inicio este evento: "),
                Scanner.getDate(" Ingrese el dia en el cual inicio el evento: "),
                Scanner.getDate(" Ingrese la hora a la cual inicio el evento: "));
        Date end = new Date(Scanner.getDate(" Ingrese el numero de mes en el cual finalizo este evento: "),
                Scanner.getDate(" Ingrese el dia en el cual finalizo el evento: "),
                Scanner.getDate(" Ingrese la hora a la cual finalizo el evento: "));
        //3 Citizens
        int cantidad = Scanner.getInt(" Cuantas pesonas asistieron a este evento? ");
        Citizen[] presentCitizens = new Citizen[cantidad];
        for (int i = 0; i < cantidad; i++){
            boolean v = true;
            do {
                Citizen citizen1 = Main2.citizenDataStore.findById(Scanner.getString(" Ingrese el id del ciudadano ("+ (i+1) + "): "));
                if (citizen1 != null){
                    presentCitizens[i] = citizen1;
                    v = false;
                }else{
                    System.out.println(" Este usuario no esta registrado!");
                }
            }while(v);
        }
        //4
        //crear la invitacion
        FaceToFaceMeeting meeting = new FaceToFaceMeeting(location, start, end, presentCitizens);
        Invitation invitation = new Invitation(meeting, citizen);
        //enviarla a todos los participantes
        for (int i = 0; i < cantidad; i++){
            citizen.sendRequest(presentCitizens[i], invitation);
        }
        System.out.println(" Perfecto, la solicitud de evento fue creada y enviada a todos los participantes del mismo.");
    } // Con este metodo un ciudadano deberia poder crear una invitacion sobre una meeting/encuento, el cual debe tener una localizacion, fecha e integrantes de la misma.

    public static void selfRecordingOfSymptoms(Citizen citizen){
        int opcion;
        do {
            System.out.println(" Registro de sintomas:\n ¿Usted presenta alguno de los siguientes sintomas?\n" + citizen.viewSymptoms(Main2.symptoms) + "\n99. (volver)");
            opcion = Scanner.getInt(" Que sintoma desea registrar: ");
            if (opcion != 99 && opcion < Main2.symptoms.size()){
                if (!citizen.getRegisteredSymptoms().contains(Main2.symptoms.get(opcion))){
                    citizen.getRegisteredSymptoms().add(Main2.symptoms.get(opcion));
                    System.out.println("\n El sintoma fue registrado!");
                }else{
                    System.out.println(" Ya tienes registrado este sintoma!");
                }
            }else if(opcion != 99){
                System.out.println(" Opcion invalida!");
            }
        }while(opcion != 99);
    }// Con este metodo un ciudadano deberia poder seleccionar los sintomas que posee y asi guardar un registro.

    public static void removeSymptom(Citizen citizen){
        int opcion;
        do {
            System.out.println("\n Sintomas registrados: \n" + citizen.viewSymptoms(citizen.getRegisteredSymptoms()) + "\n99. (volver)");
            opcion = Scanner.getInt(" Que sintoma registrado desea eliminar: ");
            if (opcion != 99 && opcion < citizen.getRegisteredSymptoms().size() && opcion >= 0){
                citizen.getRegisteredSymptoms().remove(citizen.getRegisteredSymptoms().get(opcion));
                System.out.println("\n El sintoma fue eliminado de su registro!");
            }else if(opcion != 99){
                System.out.println(" Opcion invalida!");
            }
        }while (opcion != 99);
    } // Permite a un ciudadano eliminar un sintoma previamente autodiagnosticado.

    public static void estadisticasZona(){
        //System.out.println(citizen.getRegisteredSymptoms().size());
        HashMap<Symptom, Integer> data = Main2.zones.get(0).top3CommonSymptoms(Main2.symptoms);
        System.out.println(Main2.zones.get(0).convertWithIteration(data));
    }

    public static void symptomRegister(Administrator administrator){
        int opcion;
        do {
            System.out.println(" Sintomas: \n" + administrator.viewSymptoms(Main2.symptoms) + "\n97. Agregar sintoma\n98. Eliminar sintoma\n99. (volver)");
            opcion = Scanner.getInt(" Que opcion desea realizar: ");
            switch (opcion){
                case 97:
                    Main2.symptoms.add(new Symptom(Scanner.getString(" Ingrese el nombre del sintoma que desea agregar: ")));
                    System.out.println(" El sintoma fue agregado!");
                    break;
                case 98:
                    opcion = Scanner.getInt(" Que sintoma desea eliminar (nro): ");
                    if (opcion != 99 && opcion < Main2.symptoms.size()){
                        Main2.symptoms.remove(opcion);
                        System.out.println("\n El sintoma fue eliminado de su registro!");
                    }else if(opcion != 99){
                        System.out.println(" Opcion invalida!");
                    }
                    break;
                case 99:
                    break;
                default:
                    System.out.println(" Opcion invalida!");
            }
        }while (opcion != 99);
    } // Con este metodo cualquier administrador deberia poder dar de alta/baja cualquier sintoma

}

