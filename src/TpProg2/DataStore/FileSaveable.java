package TpProg2.DataStore;

public interface FileSaveable extends Saveable {

    String getFileRepresentation();

    @Override
    String getId();

}
