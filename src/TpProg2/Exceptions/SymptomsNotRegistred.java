package TpProg2.Exceptions;

public class SymptomsNotRegistred extends DataStoreException{
    public SymptomsNotRegistred() {
        super("El archvivo de symptomas se encuentra vacio, por favor contacte a un administrador");
    }
}
