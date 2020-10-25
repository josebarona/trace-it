package TpProg2.Users;

import TpProg2.Events.Symptom;
import TpProg2.ImplementOfUsers.*;
import TpProg2.util.UserInterface;

import java.util.ArrayList;

public class Citizen extends User {

    String type;
    boolean isBan;
    ArrayList<Notification> receivedNotifications;
    ArrayList<Notification> notifications;
    ArrayList<Invitation> receivedInvitations; // todas las invitaciones llegan aca. Una vez que se acepta o se rechaza una invitacion se remueve de esta bandeja.
    ArrayList<FaceToFaceMeeting> acceptedRequest; // bandeja de invitaciones aceptadas.
    ArrayList<FaceToFaceMeeting> rejectedInvitations; // bandejas de invitaciones rechzadas.
    ArrayList<Symptom> registeredSymptoms;
    ArrayList<Citizen> contacts;
    Date gotSeek;
    int rejections;
    Zone zone;

    public Citizen(String userName, String cuil, String phoneNumber, Zone zone) {
        super(userName, cuil, phoneNumber);
        this.receivedNotifications = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.contacts = new ArrayList<>();
        this.receivedInvitations = new ArrayList<>(); // ArrayList<Invitation>
        this.acceptedRequest = new ArrayList<>(); //ArrayList<FaceToFaceMeeting>
        this.rejectedInvitations = new ArrayList<>(); //ArrayList<FaceToFaceMeeting>
        this.isBan = false;
        this.rejections = 0;
        this.type = "Ciudadano";
        this.zone = zone;
        this.registeredSymptoms = new ArrayList<>();

    }

    public void setGotSeek(Date gotSeek) {
        this.gotSeek = gotSeek;
    }

    public Date getGotSeek() {
        return gotSeek;
    }

    public ArrayList<Citizen> getContacts() {
        return contacts;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
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
        receivedInvitations.add(invitation);
    } // Recibe una invitacion dentro del inbox/bandeja de entrada

    public void receiveNotification(Notification notification){
        if (contacts.contains(notification.seekCitizen)){
            notifications.add(notification);
        }else{
            receivedNotifications.add(notification);
        }
    }

    public void sendNotification(Citizen sendTo, Notification notification){
        boolean avisar = true;
        for (int j = 0; j < sendTo.getReceivedNotifications().size(); j++) {// revisamos que ya no tenga ninguna notificacion nuestra en las notificaciones no visibles
            if (sendTo.getReceivedNotifications().get(j).seekCitizen.equals(this)) {
                avisar = false;
            }
        }
        for (int j = 0; j < sendTo.getNotifications().size(); j++) {// revisamos que ya no tenga ninguna notificacion nuestra en las notificaciones visibles
            if (sendTo.getNotifications().get(j).seekCitizen.equals(this)) {
                avisar = false;
            }
        }
        if (avisar){
            sendTo.receiveNotification(notification);
            sendTo.refreshNotifications();
        }
    }

    public void sendRequest(Citizen sendTo, Invitation invitation){
        sendTo.receiveMeetingRequest(invitation);
    }

    public void acceptedRequest(Invitation invitation){
        receivedInvitations.remove(invitation);
        acceptedRequest.add(invitation.meeting);
        this.addContact(invitation.transmitter);
        invitation.transmitter.addContact(this);
        if (UserInterface.isSeek(this)){
            this.sendNotification(invitation.transmitter, new Notification(this, this.getGotSeek()));
        }
    } // Metodo que acepta una invitacion dentro de su bandeja de entrada y guarda registro de este encuentro/meeting.

    public void addContact(Citizen contact){
        if (contacts == null || !contacts.contains(contact)){
            contacts.add(contact);
        }
        refreshNotifications();
    }

    public void refreshNotifications(){
        for (int i = 0; i < receivedNotifications.size(); i++) {
            if (contacts.contains(receivedNotifications.get(i).seekCitizen)){
                notifications.add(receivedNotifications.get(i));
                receivedNotifications.remove(receivedNotifications.get(i));
            }
        }
    }

    public void rejectedRequest(Invitation invitation){
        receivedInvitations.remove(invitation);
        invitation.transmitter.rejections ++;
    } // Metodo que rechaza una invitacion dentro de su bandeja de entrada y se suma a la cuenta de rechazos del emisor.

}
