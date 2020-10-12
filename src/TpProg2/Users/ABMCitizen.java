package TpProg2.Users;

import TpProg2.DataStore.DataStore;
import TpProg2.Exceptions.ABMCitizenException;
import TpProg2.Exceptions.ABMCitizenException2;
import TpProg2.Exceptions.ABMException;
import TpProg2.Exceptions.ABMUserException;

public class ABMCitizen {
    DataStore<Citizen> dataStore;

    public ABMCitizen(DataStore<Citizen> dataStore) {
        this.dataStore = dataStore;
    }

    public Citizen add(String userName,String cuil, String phoneNumber) throws ABMCitizenException, ABMUserException {  // chquear excepciones
        if (this.dataStore.findById(phoneNumber) == null){
            Citizen citizen = new Citizen(userName,cuil,phoneNumber);
            this.dataStore.save(citizen);
            return citizen;
        }
        throw new ABMCitizenException(phoneNumber);
    }

    public void remove(Citizen citizen) throws ABMCitizenException2, ABMUserException {  // chquear excepciones
        if (this.dataStore.findById(citizen.getId()) != null){
            this.dataStore.remove(citizen.getId());
        }
        throw new ABMCitizenException2(citizen.getId());
    }

    public void edit(Citizen citizen) throws ABMCitizenException2, ABMUserException { // chquear excepciones
        if (this.dataStore.findById(citizen.getId()) != null){
            this.dataStore.edit(citizen);
        }
        throw new ABMCitizenException2(citizen.getId());
    }
}
