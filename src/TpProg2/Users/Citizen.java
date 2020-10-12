package TpProg2.Users;

import TpProg2.ImplementOfUsers.FaceToFaceMeeting;
import TpProg2.ImplementOfUsers.Invitation;
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
        if (rejections >= 5){
            isBan = true; // lo tiene que bloquear un admin?? o se bloquea solo automaticamente?
        }
        this.type = "Ciudadano";
    }

    public void receiveMeetingRequest(Invitation invitation){ // aceptar o rechazar invitacion para faceToFaceMeeting; - Nacho B
        this.receivedInvitations.add(invitation);
    }

    public void sendRequest(Citizen sendTo, Invitation invitation){
        sendTo.receiveMeetingRequest(invitation);
    }

    public void acceptedRequest(Invitation invitation){
        receivedInvitations.remove(invitation);
        acceptedRequest.add(invitation.meeting);
    }

    public void rejectedRequest(Invitation invitation){
        receivedInvitations.remove(invitation);
        invitation.transmitter.rejections ++;
    }

    public void SelfRecordingOfSymptoms() {
        // auto registro de sintomas
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

    public String viewInvitationsNames(){
        String lista = "";
        for(int i = 0; i < receivedInvitations.size(); i++){
            lista += i + ". " + receivedInvitations.get(i).transmitter.getUserName();
        }
        return lista;
    }
    public String viewInvitationInfo(Invitation invitation){
        String info = " Invitacion de " + invitation.transmitter.getUserName() + ": \n";
        info += " Ecuentro realizado en ......, desde las ...... hasta las ....."; //falta modelar las calses de ubicaciones y fechas.
        return info;
    }
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
                    opcion1 = Scanner.getInt(" Usted confirma haber estado en este encuentro? \n1. Aceptar \n2. Rechazar \n3. (volver)");
                    switch (opcion1){
                        case 1:
                            acceptedRequest(receivedInvitations.get(opcion));
                            System.out.println(" Solicitud aceptada!!");
                            break;
                        case 2:
                            rejectedRequest(receivedInvitations.get(opcion));
                            System.out.println(" Solicitud rechazada!!");
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
    }
}
