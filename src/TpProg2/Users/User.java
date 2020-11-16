package TpProg2.Users;

import TpProg2.DataStore.FileSaveable;
import TpProg2.Events.Symptom;
import java.util.ArrayList;

public abstract class User implements FileSaveable {
    String userName;
    String cuil;
    String phoneNumber;
    String type;

    public User(String userName, String cuil, String phoneNumber) {
        this.userName = userName;
        this.cuil = cuil;
        this.phoneNumber = phoneNumber;
    }

    public String getFileRepresentation() { // forma representativa de mostrar los datos de un usuario en un archivo
        String fileData = getId() + "," + getUserName() + "," + getPhoneNumber();
        return fileData;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
     public String getId() { // hacer abstracto para que despues el admin no tenga cuil. 
        return this.cuil;
    }

    public String getCuil() {
        return cuil;
    }

    public String getUserName() {
        return userName;
    }

    public String getType() {
        return type;
    }

    public String viewSymptoms(ArrayList<Symptom> symptoms){ // muestra los sintomas registrados en la base de datos por pantalla
        String lista = "";
        if (symptoms != null) {
            for (int i = 0; i < symptoms.size(); i++) {
                if (symptoms.get(i) != null){
                    lista += "   " + i + ". " + symptoms.get(i).getName() + "\n";
                }
            }
        }else{
            lista += "\n (No hay sintomas registrados en la base de datos!)\n";
        }
        return lista;
    }
}