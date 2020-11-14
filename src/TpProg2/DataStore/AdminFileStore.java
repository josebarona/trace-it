package TpProg2.DataStore;

import TpProg2.DataStore.FileStore;
import TpProg2.Events.Symptom;
import TpProg2.Exceptions.SymptomsNotRegistred;
import TpProg2.Exceptions.UserNotExistDataStore;
import TpProg2.Users.Administrator;
import TpProg2.Users.Citizen;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AdminFileStore extends FileStore<Administrator> {

    //ArrayList<Administrator> toArrayListAdministrator;

    public AdminFileStore(String fileName) {
        super(fileName);
        //toArrayListAdministrator = new ArrayList<>();
    }

    @Override
    public Administrator lineToObject(String line) {
        String[] data = line.split(",");
        String id = data[0];
        String phoneNumber = data[2];
        String cuil = data[1];
        return new Administrator(id,cuil,phoneNumber);
    }

    @Override
    public ArrayList<Administrator> toArrayList() throws UserNotExistDataStore {  // leer el archivo completo y por linea crear un Administrator(lineToObject(line);), y meter citizens en arrays.
        ArrayList<Administrator> toArrayListAdministrator = new ArrayList<>();
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            if (!this.isEmpty()) {
                while ((strLine = br.readLine()) != null) {
                    Administrator administrator = lineToObject(strLine);
                    if (!toArrayListAdministrator.contains(administrator)) {
                        toArrayListAdministrator.add(administrator);
                    }
                }
                return toArrayListAdministrator;
            }
            fstream.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        throw new UserNotExistDataStore();
    }

}
