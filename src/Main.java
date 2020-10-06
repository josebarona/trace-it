import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Citizen c1 = new Citizen(1, 2);
        FaceToFaceMeeting f2fm = new FaceToFaceMeeting(
                new Date(),
                new Date(),
                new Citizen[] {c1}
        );
        Invitation invitation = new Invitation(f2fm, c1);
        Administrator admin = new Administrator(3, 4);
    }
}
