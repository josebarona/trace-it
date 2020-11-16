package TpProg2.DataStore;

public interface FileSaveable extends Saveable { // t0d0 dato que se pueda guardar en archivos va a tener que implementar esto.

    String getFileRepresentation(); // como un objeto se va a representar en un archivo.

    @Override
    String getId(); // obtiene la identificacion de ese objeto

}
