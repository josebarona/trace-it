package TpProg2.Util;
import TpProg2.Events.Symptom;
import TpProg2.Exceptions.ABMAdminException;
import TpProg2.Exceptions.ABMCitizenException;
import TpProg2.Exceptions.ABMUserException;
import TpProg2.ImplementOfUsers.Date;
import TpProg2.ImplementOfUsers.FaceToFaceMeeting;
import TpProg2.ImplementOfUsers.Invitation;
import TpProg2.ImplementOfUsers.Notification;
import TpProg2.Main;
import TpProg2.Users.Administrator;
import TpProg2.Users.Citizen;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
//La idea de este metodo es tener todos los metodos de interfaz que se imprimen en consola por  parte de los usuarios, principalmente para no cargar el main.

public class UserInterface {

    private UserInterface() {
    }

    //Menus
    public static void menuPrincipal() {
        traceIt();
        int opcion;
        String password = "TpGrupo14";
        do {
            title("  Menu: ");
            System.out.println("  Operaciones: \n\n 1. Registrarse \n 2. Iniciar sesion \n 3.Exit\n");
            opcion = Scanner.getInt("Que operación desea realizar: ");
            clear();

            switch (opcion) {
                case 1:
                    try {
                        Main.generalAMB.citizenRegister();
                    } catch (ABMCitizenException | ABMUserException e) {
                        e.printStackTrace();
                    }
                    clear();
                    break;
                case 2:
                    String userName = Scanner.getString("Nombre de Usuario: ");
                    String cuil = Scanner.getString("Numero de Cuil: ");
                    try {
                        Main.generalAMB.iniciarSesion(userName, cuil);
                    } catch (ABMUserException e) {
                        e.printStackTrace();
                    }
                    break;
                case 999: //Registro secreto para ser administrador
                    String word = Scanner.getString("Contraseña de usuarios administradores: ");
                    if (word.equals(password)) {
                        try {
                            Main.generalAMB.adminRegister();
                        } catch (ABMAdminException | ABMUserException e) {
                            e.printStackTrace();
                        }
                    } else {
                        clear();
                        message("haz sido bloqueado del servidor");
                        System.exit(0);
                    }
                    break;
                case 3:
                    System.out.println();
                    System.out.println("Adios ;D, gracias por usar nuestro programa");
                    break;
                default:
                    clear();
                    message(" Opcion invalida! (intente con otra opcion).");
            }
        } while (opcion != 3);
        System.exit(0);
    }

    public static void menuCitizen(Citizen citizen) throws ABMUserException {
        clear();
        int opcion;
        do {
            title("  Menu Ciudadano: ");
            System.out.println("  Operaciones: \n\n 1. Notificaciones(" + citizen.getNotifications().size()+ ") \n 2. Bandeja de entrada de invitaciones(" + citizen.getReceivedInvitations().size()+ ") \n 3. Mandar solicitudes de encuentro \n 4. Registro de sintomas  \n 5. Ver/eliminar sintomas registrados\n 6. Log Out \n 7. Exit\n");
            opcion = Scanner.getInt(" Que operación desea realizar: ");
            clear();

            switch (opcion) {
                case 1:
                    notifications(citizen);
                    clear();
                    break;
                case 2:
                    inbox(citizen);
                    clear();
                    break;
                case 3:
                    createIvitation(citizen);
                    break;
                case 4:
                    selfRecordingOfSymptoms(citizen);
                    clear();
                    break;
                case 5:
                    removeSymptom(citizen);
                    clear();
                    break;
                case 10:
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
                    message("opcion invalida!");
            }
        } while (opcion != 6);// seguramente vaya a haber mas opciones
    }

    public static void menuAdministrator(Administrator admin) throws ABMUserException {
        clear();
        int opcion;
        do {
            System.out.println("  Menu Administrador: ");
            System.out.println(" _________________________________________\n Operaciones: \n 1. (sintomas) \n 2...  \n 3...  \n 4. Bloquear Ciudadano \n 5. Desbloquear Cuidadano \n 6. Log Out \n 7. Exit ");
            opcion = Scanner.getInt(" Que operación desea realizar: ");
            clear();

            switch (opcion) {
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
                    String idCitizen = Scanner.getString("Ingrese cuil del ciudadano al que quiere Bloquear: ");
                    clear();
                    if (Main.generalAMB.citizenDataStore.exists(idCitizen)) {
                        admin.banCitizen(Main.generalAMB.citizenDataStore.findById(idCitizen));
                        message("El ciudadano a sido bloqueado");
                    } else {
                        message("el usuario al que quiere bloquear no existe");
                    }
                    break;
                case 5:
                    String idCitizen2 = Scanner.getString("Pase id de ciudadano que quiere Desbloquear: ");
                    clear();
                    if (Main.generalAMB.citizenDataStore.exists(idCitizen2)) {
                        admin.unbanCitizen(Main.generalAMB.citizenDataStore.findById(idCitizen2));
                        message("El ciudadano a sido desbloqueado");
                    } else {
                        message("el usuario al que quiere desbloquear no existe");
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
                   message("opcion invalida!");

            }

        } while (opcion != 6); // seguramente va a haber mas opciones
    }

    //Ecuentros/Invitaciones
    public static void inbox(Citizen citizen) {
        int opcion;
        do {
            title("  - Bandeja de entrada de invitaciones:");
            System.out.println("\n" + viewInvitationsNames(citizen) + "\n  99. (volver)\n");
            opcion = Scanner.getInt(" Que invitacion deseas ver: ");
            clear();

            if (opcion < citizen.getReceivedInvitations().size()) {
                int opcion1;
                do {
                    System.out.println(viewInvitationInfo(citizen.getReceivedInvitations().get(opcion)));
                    opcion1 = Scanner.getInt("\n -Usted confirma haber estado en este encuentro? \n   1. Aceptar \n   2. Rechazar \n   3. (volver)\n\n   Opcion: ");
                    clear();
                    switch (opcion1) {
                        case 1:
                            citizen.acceptedRequest(citizen.getReceivedInvitations().get(opcion));
                            message(" Solicitud aceptada!!");
                            opcion1 = 3;
                            break;
                        case 2:
                            citizen.rejectedRequest(citizen.getReceivedInvitations().get(opcion));
                            message(" Solicitud rechazada!!");
                            opcion1 = 3;
                            break;
                        case 3:
                            break;
                        default:
                            message(" Opcion invalida!");
                    }
                } while (opcion1 != 3);
            } else if (opcion != 99) {
                message(" Opcion invalida!");
            }
        } while (opcion != 99);
    } // Metodo que permite navegar por la bandeja de entrada, pudiendo aceptar/rechazar invitaciones de meetings depues de ver su informacion.

    public static String viewInvitationsNames(Citizen citizen) {
        String lista = "";
        if (citizen.getReceivedInvitations().size() > 0) {
            for (int i = 0; i < citizen.getReceivedInvitations().size(); i++) {
                lista += "  " + i + ". " + citizen.getReceivedInvitations().get(i).transmitter.getUserName() + "\n";
            }
        } else {
            lista += "\n    (No tienes ninguna invitacion a eventos en tu bandeja de entrada)\n";
        }
        return lista;
    } // Devuelve un String con los nombres de los emisores de cada invitacion dentro de la bandeja de entrada (en forma de lista).

    public static String viewInvitationInfo(Invitation invitation) {
        title(" - Informacion del encuentro");
        String info = "\n Invitacion de " + invitation.transmitter.getUserName() + ": \n";
        info += " Ecuentro realizado en " + invitation.meeting.location + ".\n Iniciado el dia " + invitation.meeting.start.dia + " del mes " + invitation.meeting.start.dia + " a las " + invitation.meeting.start.hora + "hs.";
        info += "\n Hasta el dia " + invitation.meeting.finish.dia + " del mes " + invitation.meeting.finish.mes + " a las " + invitation.meeting.finish.hora + "hs.";
        return info;
    } //Metodo que devuelve un String con toda la informacion que lleva una invitacion (location, date, citizens)

    public static void createIvitation(Citizen citizen) throws ABMUserException {
        System.out.println(" - Porfavor ingrese los siguientes datos sobre el encuentro al cual asistio: \n\n");
        //1 Location
        title(" Ubicacion");
        String location = Scanner.getString(" El nombre de la ubicacion del encuentro: ");
        //2 Date
        title("\n Fecha de inicio");

        int month = Scanner.getMonth(" Ingrese el numero de mes en el cual inicio este evento: ");
        Date start = new Date(month,
                Scanner.getDay(" Ingrese el dia en el cual inicio el evento: ", month, 2020),
                Scanner.getHour(" Ingrese la hora a la cual inicio el evento: "));
        title("\n Fecha de cierre");
        month = Scanner.getMonth(" Ingrese el numero de mes en el cual finalizo este evento: ");
        Date end = new Date(month,
                Scanner.getDay(" Ingrese el dia en el cual finalizo el evento: ", month, 2020),
                Scanner.getHour(" Ingrese la hora a la cual finalizo el evento: "));
        //3 Citizens
        title("\n Ciudadanos presentes");
        int cantidad = Scanner.getInt(" Cuantas pesonas asistieron a este evento? ");
        Citizen[] presentCitizens = new Citizen[cantidad+1];
        presentCitizens[0] = citizen;
        for (int i = 1; i < cantidad+1; i++) {
            boolean v = true;
            do {
                Citizen citizen1 = Main.generalAMB.citizenDataStore.findById(Scanner.getString(" Ingrese el cuil del ciudadano (" + (i) + "): "));
                if (citizen1 != null) {
                    presentCitizens[i] = citizen1;
                    v = false;
                } else {
                    message(" Este usuario no esta registrado!");
                }
            } while (v);
        }
        //4
        //crear la invitacion
        FaceToFaceMeeting meeting = new FaceToFaceMeeting(location, start, end, presentCitizens);
        Invitation invitation = new Invitation(meeting, citizen);
        //enviarla a todos los participantes
        for (int i = 1; i < cantidad+1; i++) {
            citizen.sendRequest(presentCitizens[i], invitation);
            if (citizen.isSeek() && howLongAgo(end) < 48){
                citizen.sendNotification(presentCitizens[i], new Notification(citizen, citizen.getGotSeek()));
            }
        }
        citizen.getAcceptedRequest().add(invitation.meeting);;//tambien se agrega este encuentro a la persona que lo creo
        clear();
        message(" La solicitud del evento fue enviada a todos los participantes del mismo.");
    } // Con este metodo un ciudadano deberia poder crear una invitacion sobre una meeting/encuento, el cual debe tener una localizacion, fecha e integrantes de la misma.

    //Notificaciones
    public static void notifications(Citizen citizen) {
        int opcion;
        do {
            title("  - Notificaciones:");
            System.out.println("\n" + viewNotificationsTitles(citizen) + "\n  99. (volver)\n");
            opcion = Scanner.getInt(" Que notificacion deseas ver: ");
            clear();

            if (opcion < citizen.getNotifications().size()) {
                int opcion1;
                do {
                    System.out.println(viewNotificationInfo(citizen.getNotifications().get(opcion)));
                    opcion1 = Scanner.getInt("\n   1. Borrar notificacion \n   2. (volver)\n\n   Opcion: ");
                    clear();
                    switch (opcion1) {
                        case 1:
                            citizen.getNotifications().remove(citizen.getNotifications().get(opcion));
                            message(" Notificacion eliminada!!");
                            opcion1 = 2;
                            break;
                        case 2:
                            break;
                        default:
                            message(" Opcion invalida!");
                    }
                } while (opcion1 != 2);
            } else if (opcion != 99) {
                message(" Opcion invalida!");
            }
        } while (opcion != 99);
    } // Metodo que permite visualiar todas las notificaciones recibidas, solo llegaran alertas de sus contactos (personas a las cuales se les acepto una invitacion de encuentro)

    private static String viewNotificationsTitles(Citizen citizen) {
        String lista = "";
        if (citizen.getNotifications().size() > 0) {
            for (int i = 0; i < citizen.getNotifications().size(); i++) {
                lista += "  " + i + ". " + citizen.getNotifications().get(i).seekCitizen.getUserName() + "\n";
            }
        } else {
            lista += "\n    (No tienes ninguna notificacion)\n";
        }
        return lista;
    } // Devuelve un String con una lita de los emisores de sus notificaciones visibles

    private static String viewNotificationInfo(Notification notification) {
        return " - Hace menos de 48 horas estuviste reunido con " + notification.seekCitizen.getUserName() + "\n el cual recientemente presento mas de 3 sintomas de COVID!!";
    } // Devuelve un String con la informacion de la alerta (en este caso, el nombre de la persona contagiada con la que tuvo contacto reciente)

    public static void alertRecentCitizens(Citizen citizen){
        //1 Primero creamos una lista con todos los encuentros que sucedieron hace menos de 48 horas
        ArrayList<FaceToFaceMeeting> recentMeetings = new ArrayList<>();
        for (int i = 0; i < citizen.getAcceptedRequest().size(); i++) {
            if (howLongAgo(citizen.getAcceptedRequest().get(i).finish) < 48) {
                recentMeetings.add(citizen.getAcceptedRequest().get(i));
            }
        }
        //2 Armamos una lista con todos los ciudadanos que asistieron a estos encuentros y deberiamos notificar (no debemos estar en ella y no se tienen que repetir los ciudadanos)
        ArrayList<Citizen> deberiamosAviar = new ArrayList<>();
        for (int i = 0; i < recentMeetings.size(); i++) {
            for (int j = 0; j < recentMeetings.get(i).getAttendeesCitizens().length; j++) {
                if (!recentMeetings.get(i).getAttendeesCitizens()[j].equals(citizen) && !deberiamosAviar.contains(recentMeetings.get(i).getAttendeesCitizens()[j])) { // (si no soy yo, y todavia no esta en la lista)
                    deberiamosAviar.add(recentMeetings.get(i).getAttendeesCitizens()[j]);
                }
            }
        }
        //3 Creamos la notificacion
        Notification notification = new Notification(citizen, citizen.getGotSeek());
        //4 Enviamos la notificacion a esta lista de gente
        for (int i = 0; i < deberiamosAviar.size(); i++) {
            citizen.sendNotification(deberiamosAviar.get(i), notification);
        }
    } // Metodo que una vez que un ciudadano se autodiagnostica como enfermo, este alerta (envia notificacion, esta no sera visible para el otro ciudadano hasta que sean contactos) a todos los ciudadanos con los cuales estuvo en contacto en menos de 48 horas.

    //Sintomas
    public static void selfRecordingOfSymptoms(Citizen citizen) {
        int opcion;
        do {
            title(" - Registro de sintomas:");
            System.out.println("\n ¿Usted presenta alguno de los siguientes sintomas?\n" + citizen.viewSymptoms(Main.generalAMB.symptoms) + "\n   99. (volver)");
            opcion = Scanner.getInt(" Que sintoma desea registrar: ");
            clear();
            if (opcion != 99 && opcion < Main.generalAMB.symptoms.size()) {
                if (!citizen.getRegisteredSymptoms().contains(Main.generalAMB.symptoms.get(opcion))) {
                    citizen.getRegisteredSymptoms().add(Main.generalAMB.symptoms.get(opcion));
                    message("El sintoma (" + Main.generalAMB.symptoms.get(opcion).getName() + ") fue registrado!");

                    //aca tendriamos que ver si se considera que esta enfermo.
                    if (citizen.isSeek()) {
                        alertRecentCitizens(citizen);
                    }

                } else {
                    message(" Ya tienes registrado este sintoma!");
                }
            } else if (opcion != 99) {
                message(" Opcion invalida!");
            }
        } while (opcion != 99);
    }// Con este metodo un ciudadano deberia poder seleccionar los sintomas que posee y asi guardar un registro.

    public static void removeSymptom(Citizen citizen) {
        int opcion;
        do {
            title(" - Sintomas registrados:");
            System.out.println("\n" + citizen.viewSymptoms(citizen.getRegisteredSymptoms()) + "\n   99. (volver)\n");
            opcion = Scanner.getInt(" Que sintoma registrado desea eliminar: ");
            clear();
            if (opcion != 99 && opcion < citizen.getRegisteredSymptoms().size() && opcion >= 0) {
                message(" El sintoma (" + citizen.getRegisteredSymptoms().get(opcion).getName() + ") eliminado de su registro!!");
                citizen.getRegisteredSymptoms().remove(citizen.getRegisteredSymptoms().get(opcion));
                citizen.isSeek();//con esto lo elimina de la lista de enfermos
            } else if (opcion != 99) {
                message(" Opcion invalida!");
            }
        } while (opcion != 99);
    } // Permite a un ciudadano eliminar un sintoma previamente autodiagnosticado.

    public static void symptomRegister(Administrator administrator){
        int opcion;
        do {
            title(" Administracion y configuracion de sintomas");
            System.out.println("\n Sintomas: \n" + administrator.viewSymptoms(Main.generalAMB.symptoms) + "\n   97. Agregar sintoma\n   98. Eliminar sintoma\n   99. (volver)");
            opcion = Scanner.getInt("    Que opcion desea realizar: ");
            switch (opcion){
                case 97:
                    clear();
                    Main.generalAMB.symptoms.add(new Symptom(Scanner.getString(" Ingrese el nombre del sintoma que desea agregar: ")));
                    clear();
                    message(" El sintoma fue agregado!");
                    break;
                case 98:
                    opcion = Scanner.getInt(" Que sintoma desea eliminar (nro): ");
                    clear();
                    if (opcion != 99 && opcion < Main.generalAMB.symptoms.size()){

                        message("\n El sintoma "+ Main.generalAMB.symptoms.get(opcion).getName() +" fue eliminado de su registro!");
                        Main.generalAMB.symptoms.remove(opcion);
                    }else if(opcion != 99){
                        clear();
                        message(" Opcion invalida!");
                    }
                    break;
                case 99:
                    break;
                default:
                    clear();
                    message(" Opcion invalida!");
            }
        }while (opcion != 99);
    } // Con este metodo cualquier administrador deberia poder dar de alta/baja cualquier sintoma

    //Estadisticas
    public static void estadisticasZona(){
        //System.out.println(citizen.getRegisteredSymptoms().size());
        HashMap<Symptom, Integer> data = Main.generalAMB.zones.get(0).top3CommonSymptoms(Main.generalAMB.symptoms);
        System.out.println(Main.generalAMB.zones.get(0).convertWithIteration(data));
    }

    //Tiempo
    public static int howLongAgo (Date date){
        Date today = actualDate();
        return dateDiference(today,date);
    } //Devuelve las horas transcurridas desde una fecha hasta el dia de hoy.

    public static int dateDiference (Date date1, Date date2){
        int date1Hours = pastMonthDays(date1.mes)*24 + date1.dia*24 + date1.hora;
        int date2Hours = pastMonthDays(date2.mes)*24 + date2.dia*24 + date2.hora;
        return date1Hours - date2Hours;
    }

    public static Date actualDate(){
        Calendar fecha = Calendar.getInstance();
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int mes = fecha.get(Calendar.MONTH)+1;
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        return new Date(mes, dia, hora);
    } //Devuelve la fecha actual (mes, dia, hora).

    public static int pastMonthDays(int mes){// Hay que hacer que devuelva la cantidad de dias que hubieron en los meses anteriores a ese (solo para 2020 en este caso)

        int numeroDias=-1;

        switch(mes){

            case 12:
                numeroDias += 30;
            case 11:
                numeroDias += 31;
            case 10:
                numeroDias += 30;
            case 9:
                numeroDias += 31;
            case 8:
                numeroDias += 31;
            case 7:
                numeroDias += 30;
            case 6:
                numeroDias += 31;
            case 5:
                numeroDias += 30;
            case 4:
                numeroDias += 31;
            case 3:
                numeroDias += 29;
            case 2:
                numeroDias += 31;
            case 1:
                break;
        }
        return numeroDias;
    } //Devuelve todos los dias que pasaron desde principio de año hasta el comienzo del mes ingresado.

    public static int monthDays(int mes, int año) {
        switch (mes) {
            case 1:  // Enero
            case 3:  // Marzo
            case 5:  // Mayo
            case 7:  // Julio
            case 8:  // Agosto
            case 10:  // Octubre
            case 12: // Diciembre
                return 31;
            case 4:  // Abril
            case 6:  // Junio
            case 9:  // Septiembre
            case 11: // Noviembre
                return 30;
            case 2:  // Febrero
                if (((año % 100 == 0) && (año % 400 == 0)) ||
                        ((año % 100 != 0) && (año % 4 == 0)))
                    return 29;  // Año Bisiesto
                else
                    return 28;
            default:
                return -1;
        }
    }

    //Extras

    static void traceIt(){
        clear();
        System.out.println( "|''||''|        (presione enter)         '||'   .   \n" +
                            "   ||    ... ..   ....     ....    ....   ||  .||.  \n" +
                            "   ||     ||' '' '' .||  .|   '' .|...||  ||   ||   \n" +
                            "   ||     ||     .|' ||  ||      ||       ||   ||   \n" +
                            "  .||.   .||.    '|..'|'  '|...'  '|...' .||.  '|.' \n\n");
        Scanner.enter();
        clear();
    }

    public static void clear(){
        for (int i = 0; i < 30; ++i) System.out.println();
    }

    public static void message(String message){
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n " +
                                                    message +
                         "\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n ");
    }

    public static void title(String message){
        System.out.println( message + "\n_________________________________________");
    }
}

