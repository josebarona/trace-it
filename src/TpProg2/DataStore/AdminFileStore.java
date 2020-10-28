package TpProg2.DataStore;

import TpProg2.Users.Administrator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AdminFileStore extends FileStore<Administrator>{

    public AdminFileStore(String fileName) {
        super(fileName);
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
    public ArrayList<Administrator> toArrayList() {
        // leer el archivo completo y por linea crear un Administrator(lineToObject(line);), y meter citizens en arrays.
        return null;
    }

}
