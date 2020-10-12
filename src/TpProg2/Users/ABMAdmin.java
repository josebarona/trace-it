package TpProg2.Users;

        import TpProg2.DataStore.CollectionStore;
        import TpProg2.DataStore.DataStore;
        import TpProg2.Exceptions.ABMAdminException;
        import TpProg2.Exceptions.ABMAdminException2;
        import TpProg2.Exceptions.ABMUserException;

public class ABMAdmin {
    DataStore<Administrator> dataStore;

    public ABMAdmin(DataStore<Administrator> dataStore) {
        this.dataStore = dataStore;
    }

    public Administrator add(String userName, String phoneNumber, String cuil) throws ABMAdminException, ABMUserException {

        if (this.dataStore.findById(phoneNumber) == null){
            Administrator administrator = new Administrator(userName, cuil, phoneNumber);
            this.dataStore.save(administrator);
            return administrator;
        }
        throw new ABMAdminException(phoneNumber);
    }

    public void remove(Administrator administrator) throws ABMAdminException2, ABMUserException {
        if (this.dataStore.findById(administrator.getId()) != null){
            this.dataStore.remove(administrator.getId());
        }
        throw new ABMAdminException2(administrator.getId());
    }

    public void edit(Administrator administrator) throws ABMAdminException2, ABMUserException {
        if (this.dataStore.findById(administrator.getId()) != null){
            this.dataStore.edit(administrator);
        }
        throw new ABMAdminException2(administrator.getId());
    }
}
