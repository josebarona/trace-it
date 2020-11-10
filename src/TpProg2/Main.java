//pruebas de metodos y menus...
package TpProg2;
import TpProg2.Exceptions.DataStoreException;
import TpProg2.Users.*;
import TpProg2.Util.UserInterface;

public class Main {

    public static AMBGeneral generalAMB;

    static {
        try {
            generalAMB = new AMBGeneral();
        } catch (DataStoreException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UserInterface.menuPrincipal();
    }

}