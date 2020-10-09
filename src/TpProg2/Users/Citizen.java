package TpProg2.Users;

import TpProg2.ImplementOfUsers.FaceToFaceMeeting;
import TpProg2.ImplementOfUsers.Invitation;

import java.util.ArrayList;

public class Citizen extends User {

    boolean isBan;
    ArrayList<Invitation> receivedInvitations;
    ArrayList<FaceToFaceMeeting> acceptedRequest;
    ArrayList<FaceToFaceMeeting> rejectedInvitations;
    int rejections;

    public Citizen(String cuil, String phoneNumber) {
        super(cuil, phoneNumber);
        this.receivedInvitations = new ArrayList<>(); // ArrayList<Invitation>
        this.acceptedRequest = new ArrayList<>(); //ArrayList<FaceToFaceMeeting>
        this.rejectedInvitations = new ArrayList<>(); //ArrayList<FaceToFaceMeeting>
        this.isBan = false;
        this.rejections = 0;
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

}