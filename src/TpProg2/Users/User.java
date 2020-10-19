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

    public String getFileRepresentation() {
        String fileData = getId() + "," + getUserName() + "," + getCuil(); //por ahora... despues hay que agregar + cosas.
        return fileData;
    }

    @Override
     public String getId() {
        return this.phoneNumber;
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

    public String viewSymptoms(ArrayList<Symptom> symptoms){
        String lista = "";
        if (symptoms != null) {
            for (int i = 0; i < symptoms.size(); i++) {
                if (symptoms.get(i) != null){
                    lista += i + ". " + symptoms.get(i).getName() + "\n";
                }
            }
        }else{
            lista += "\n (No hay sintomas registrados en la base de datos!)\n";
        }
        return lista;
    }
}