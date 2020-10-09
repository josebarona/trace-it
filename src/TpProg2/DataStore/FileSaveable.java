package TpProg2.DataStore;

public interface FileSaveable extends Saveable {
    @Override
    String getId();
    String getFileRepresentation();
}
