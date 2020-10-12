package TpProg2.DataStore;

import TpProg2.Users.Administrator;

public class AdminFileStore extends FileStore<Administrator>{

    public AdminFileStore(String fileName) {
        super(fileName);
    }

    @Override
    public Administrator lineToObject(String line) {
        String[] data = line.split(",");
        String id = data[0];
        String cuil = data[2];
        String userName = data[1];
        return new Administrator(userName,cuil,id);
    }
}
