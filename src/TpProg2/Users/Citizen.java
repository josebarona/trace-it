package TpProg2.Users;

import TpProg2.Exceptions.ABMUserException;
import TpProg2.ImplementOfUsers.Date;
import TpProg2.ImplementOfUsers.FaceToFaceMeeting;
import TpProg2.ImplementOfUsers.Invitation;
import TpProg2.ImplementOfUsers.Location;
import TpProg2.Main2;
import TpProg2.util.Scanner;

import java.util.ArrayList;

public class Citizen extends User {

    String type;
    boolean isBan;
    ArrayList<Invitation> receivedInvitations; // todas las invitaciones llegan aca. Una vez que se acepta o se rechaza una invitacion se remueve de esta bandeja.
    ArrayList<FaceToFaceMeeting> acceptedRequest; // bandeja de invitaciones aceptadas.
    ArrayList<FaceToFaceMeeting> rejectedInvitations; // bandejas de invitaciones rechzadas.
    int rejections;

    public Citizen(String userName, String cuil, String phoneNumber) {
        super(userName, cuil, phoneNumber);
        this.receivedInvitations = new ArrayList<>(); // ArrayList<Invitation>
        this.acceptedRequest = new ArrayList<>(); //ArrayList<FaceToFaceMeeting>
        this.rejectedInvitations = new ArrayList<>(); //ArrayList<FaceToFaceMeeting>
        this.isBan = false;
        this.rejections = 0;

        this.type = "Ciudadano";
    }
    @Override
    public String getFileRepresentation() {
        return super.getFileRepresentation() + "," + isBan + "," + rejections;
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public String getCuil() {
        return super.getCuil();
    }

    @Override
    public String getUserName() {
        return super.getUserName();
    }

    @Override
    public String getType() {
        return this.type + "s";
    }

    public boolean isBan() {
        return isBan;
    }

    public void setBan(boolean ban) {
        isBan = ban;
    }

    public int getRejections() {
        return rejections;
    }

    public void setRejections(int rejections) {
        this.rejections = rejections;
    }

    public void receiveMeetingRequest(Invitation invitation){ // aceptar o rechazar invitacion para faceToFaceMeeting; - Nacho B
        this.receivedInvitations.add(invitation);
    } // Recibe una invitacion dentro del inbox/bandeja de entrada

    public Invitation createRequest(){ return null;} // Con este metodo un ciudadano deberia poder crear una invitacion sobre una meeting/encuento, el cual debe tener una localizacion, fecha e integrantes de la misma.

    public void sendRequest(Citizen sendTo, Invitation invitation){
        sendTo.receiveMeetingRequest(invitation);
    }

    public void acceptedRequest(Invitation invitation){
        receivedInvitations.remove(invitation);
        acceptedRequest.add(invitation.meeting);
    } // Metodo que acepta una invitacion dentro de su bandeja de entrada y guarda registro de este encuentro/meeting.

    public void rejectedRequest(Invitation invitation){
        receivedInvitations.remove(invitation);
        invitation.transmitter.rejections ++;
    } // Metodo que rechaza una invitacion dentro de su bandeja de entrada y se suma a la cuenta de rechazos del emisor.

    public void SelfRecordingOfSymptoms() {
        // auto registro de sintomas
    } // Con este metodo un ciudadano deberia poder seleccionar los sintomas que posee y asi guardar un registro.

    public void addEvent(){

    } // Metodo para agregar un evento/enfermedad a un paciente

    public void removeEvent(){

    } // Metodo para eliminar un evento/enfermedad de un paciente

    public void inbox (){
        int opcion;
        do {
            System.out.println("\n  Bandeja de entrada de invitaciones: ");
            System.out.println(viewInvitationsNames() + "\n99. (volver)");
            opcion = Scanner.getInt(" Que invitacion deseas ver: ");

            if (opcion < receivedInvitations.size()) {
                int opcion1;
                do {
                    System.out.println(viewInvitationInfo(receivedInvitations.get(opcion)));
                    opcion1 = Scanner.getInt(" Usted confirma haber estado en este encuentro? \n1. Aceptar \n2. Rechazar \n3. (volver)\n Opcion: ");
                    switch (opcion1){
                        case 1:
                            acceptedRequest(receivedInvitations.get(opcion));
                            System.out.println(" Solicitud aceptada!!");
                            opcion1 = 3;
                            break;
                        case 2:
                            rejectedRequest(receivedInvitations.get(opcion));
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

    public String viewInvitationsNames(){
        String lista = "";
        if (receivedInvitations.size() > 0) {
            for (int i = 0; i < receivedInvitations.size(); i++) {
                lista += i + ". " + receivedInvitations.get(i).transmitter.getUserName() + "\n";
            }
        }else{
            lista += "\n (No tienes ninguna invitacion a eventos en tu bandeja de entrada)\n";
        }
        return lista;
    } // Devuelve un String con los nombres de los emisores de cada invitacion dentro de la bandeja de entrada (en forma de lista).

    public String viewInvitationInfo(Invitation invitation){
        String info = " Invitacion de " + invitation.transmitter.getUserName() + ": \n";
        info += " Ecuentro realizado en " + invitation.meeting.location.locationName + ", el dia " + invitation.meeting.start.mes + "/" + invitation.meeting.start.dia + " a las " + invitation.meeting.start.hora + "hs.";
        info += "\n hasta el dia " + invitation.meeting.finish.mes + "/" + invitation.meeting.finish.dia + " a las " + invitation.meeting.finish.hora + "hs.";
        return info;
    } //Metodo que devuelve un String con toda la informacion que lleva una invitacion (location, date, citizens)

    public void createIvitation() throws ABMUserException {
        System.out.println(" Porfavor ingrese los siguientes datos sobre el encuentro al cual asistio: ");
        //1 Location
        Location location = new Location(Scanner.getString(" El nombre de la ubicacion del encuentro: "));
        //2 Date
        Date start = new Date(Scanner.getInt(" Utilizando dos digitos ingrese el numero de mes en el cual inicio este evento: "),
                              Scanner.getInt(" Ingrese el dia en el cual inicio el evento: "),
                              Scanner.getInt(" Ingrese la hora a la cual inicio el evento: "));
        Date end = new Date(Scanner.getInt(" Utilizando dos digitos ingrese el numero de mes en el cual finalizo este evento: "),
                Scanner.getInt(" Ingrese el dia en el cual finalizo el evento: "),
                Scanner.getInt(" Ingrese la hora a la cual finalizo el evento: "));
        //3 Citizens
        int cantidad = Scanner.getInt(" Cuantas pesonas asistieron a este evento? ");
        Citizen[] presentCitizens = new Citizen[cantidad];
        for (int i = 0; i < cantidad; i++){
            presentCitizens[i] = Main2.citizenDataStore.findById(Scanner.getString(" Ingrese el id del ciudadano ("+ i+1 + "): "));
        }
        //4
        //crear la invitacion
        FaceToFaceMeeting meeting = new FaceToFaceMeeting(location, start, end, presentCitizens);
        Invitation invitation = new Invitation(meeting, this);
        //enviarla a todos los participantes
        for (int i = 0; i < cantidad; i++){
            sendRequest(presentCitizens[i], invitation);
        }
        System.out.println(" Perfecto, la solicitud de evento fue creada y enviada a todos los participantes del mismo.");
    }
}
