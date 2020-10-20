package TpProg2.Users;

import TpProg2.Events.Symptom;
import TpProg2.Main2;
import TpProg2.util.Scanner;

public class Administrator extends User {
    String type;

    public Administrator(String userName, String cuil, String phoneNumber) {
        super(userName ,cuil, phoneNumber);
        this.type = "Administrador";
    }

    public void banCitizen(Citizen citizen) {
        citizen.isBan = true;
    }

    public void unbanCitizen(Citizen citizen) {
        citizen.isBan = false;
    }

    @Override
    public String getType() {
        return this.type + "es";
    }

    @Override
    public String getFileRepresentation() {
        return super.getFileRepresentation();
    }

    @Override
    public String getCuil() {
        return super.getCuil();
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public String getUserName() {
        return super.getUserName();
    }

    public void symptomRegister(){
        int opcion;
        do {
            System.out.println(" Sintomas: \n" + viewSymptoms(Main2.symptoms) + "\n97. Agregar sintoma\n98. Eliminar sintoma\n99. (volver)");
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