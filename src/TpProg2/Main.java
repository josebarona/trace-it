package TpProg2;

import TpProg2.ImplementOfUsers.FaceToFaceMeeting;
import TpProg2.ImplementOfUsers.Invitation;
import TpProg2.Users.Administrator;
import TpProg2.Users.Citizen;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Citizen c1 = new Citizen("1", "2");
        FaceToFaceMeeting f2fm = new FaceToFaceMeeting(
                new Date(),
                new Date(),
                new Citizen[] {c1}
        );
        Invitation invitation = new Invitation(f2fm, c1);
        Administrator admin = new Administrator("3", "4");
    }
}
/*
        //ejemplo de menu basico

    void menu (){
        int opcion;
        do{
            System.out.println("\n  Menu: ");
            System.out.println(" _________________________________________\n Operaciones:\n 1. Registrarse \n 2. Iniciar sesion (Ciudadano/Admin)\n 3. AMB \n 4. ... \n 5. ... \n 6. Salir ");
            opcion = Scanner.getInt(" Que operación desea realizar: ");

            switch (opcion) {
                case 1:
                    //codigo para registrar un usuario, pidiendo sus datos y validandolos.
                    break;
                case 2:
                    //aca tendriamos que preguntar si va a ingresar como ciudadano o admin
                    //y tendriamos que ver si existe el usuario y validar la contraseña.
                    break;
                case 3:
                    //AMB
                    break;
                case 4:
                    //
                    break;
                case 5:
                    //
                    break;
                case 6:
                    System.out.println("\n ¡Gracias por usar este programa!\n");
                    break;
                default:
                    System.out.println("\n Opcion invalida! (intente con otro numero).\n");
            }
        }while(opcion != 6);
    }
*/
