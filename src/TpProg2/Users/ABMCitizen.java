package TpProg2.Users;

import TpProg2.DataStore.DataStore;
import TpProg2.Exceptions.ABMCitizenException;
import TpProg2.Exceptions.ABMCitizenException2;
import TpProg2.Exceptions.ABMUserException;

public class ABMCitizen implements ABM<Citizen>/*metodos que va a utilizar ABM*/{

    DataStore<Citizen> dataStore;

    public ABMCitizen(DataStore<Citizen> dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Citizen add(Citizen citizen) throws ABMCitizenException, ABMUserException {  // chquear excepciones
        if (this.dataStore.findById(citizen.getId()) == null){
            this.dataStore.save(citizen);
            return citizen;
        }
        throw new ABMCitizenException(citizen.getId());
    }

    @Override
    public void remove(Citizen citizen) throws ABMCitizenException2, ABMUserException {  // chquear excepciones
        if (this.dataStore.findById(citizen.getId()) != null){
            this.dataStore.remove(citizen.getId());
        }
        throw new ABMCitizenException2(citizen.getId());
    }

    @Override
    public void edit(Citizen citizen) throws ABMCitizenException2, ABMUserException { // chequear excepciones
        if (this.dataStore.findById(citizen.getId()) != null){
            this.dataStore.edit(citizen);
        }
        throw new ABMCitizenException2(citizen.getId());
    }

}
