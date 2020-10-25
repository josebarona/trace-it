package TpProg2.Users;

import TpProg2.Events.Symptom;
import TpProg2.ImplementOfUsers.*;

import java.util.ArrayList;

public class Citizen extends User {

    String type;
    boolean isBan;
    ArrayList<Notification> receivedNotifications;
    ArrayList<Invitation> receivedInvitations; // todas las invitaciones llegan aca. Una vez que se acepta o se rechaza una invitacion se remueve de esta bandeja.
    ArrayList<FaceToFaceMeeting> acceptedRequest; // bandeja de invitaciones aceptadas.
    ArrayList<FaceToFaceMeeting> rejectedInvitations; // bandejas de invitaciones rechzadas.
    ArrayList<Symptom> registeredSymptoms;
    int rejections;
    Zone zone;

    public Citizen(String userName, String cuil, String phoneNumber, Zone zone) {
        super(userName, cuil, phoneNumber);
        this.receivedNotifications = new ArrayList<>();
        this.receivedInvitations = new ArrayList<>(); // ArrayList<Invitation>
        this.acceptedRequest = new ArrayList<>(); //ArrayList<FaceToFaceMeeting>
        this.rejectedInvitations = new ArrayList<>(); //ArrayList<FaceToFaceMeeting>
        this.isBan = false;
        this.rejections = 0;
        this.type = "Ciudadano";
        this.zone = zone;
        this.registeredSymptoms = new ArrayList<>();
    }

    public ArrayList<Notification> getReceivedNotifications() {
        return receivedNotifications;
    }

    public ArrayList<Invitation> getReceivedInvitations() {
        return receivedInvitations;
    }

    public ArrayList<FaceToFaceMeeting> getAcceptedRequest() {
        return acceptedRequest;
    }

    public ArrayList<FaceToFaceMeeting> getRejectedInvitations() {
        return rejectedInvitations;
    }

    public ArrayList<Symptom> getRegisteredSymptoms() {
        return registeredSymptoms;
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

    public Zone getZone() {
        return zone;
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

    public void receiveNotification(Notification notification){this.receivedNotifications.add(notification); }

    public void sendNotification(Citizen sendTo, Notification notification){ sendTo.receiveNotification(notification);}

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

}
