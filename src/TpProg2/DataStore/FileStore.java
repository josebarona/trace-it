package TpProg2.DataStore;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileStore<T extends FileSaveable> implements DataStore<T>{
    String fileName;
    //CollectionStore<T> collectionData; // PROBANDO ALGO

    public FileStore(String fileName/*,CollectionStore<T> collectionData*/) {
        this.fileName = fileName;
        ///this.collectionData = collectionData;
    }

    @Override
    public void save(T t) {

        try {
            FileWriter fileWriter = new FileWriter("C:\\Users\\nacho\\IdeaProjects\\trace-it\\src\\TpProg2\\Archvios\\" + fileName); // comprobar que funciona.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Datos de " + t.getType() + " : " +  "\n");
            bufferedWriter.write(t.getFileRepresentation());
            bufferedWriter.close();
        } catch(Exception e ){
            System.out.println(e.getMessage());
        }

        // Funcionar va a funciona, ver tema del concepto ---> es correcto? tiene sentido?
        // VER MANERA DE QUE IMPRIMA CADA T NO SOLO EL ULTIMO (ITERADOR(?))
        /*try {
            FileWriter fileWriter = new FileWriter("C:\\Users\\nacho\\IdeaProjects\\trace-it\\src\\TpProg2\\Archvios\\" + fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Datos de " + t.getType() + " : " +  "\n");
            for (int i = 0; i < collectionData.size(); i++) {
                bufferedWriter.write(t.getFileRepresentation() + "\n");
            }
            bufferedWriter.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }*/

    }

    @Override
    public void remove(String id) {

    }

    @Override
    public T findById(String id) {
        return null;
    }

    @Override
    public void edit(T t) {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean userExist(String phoneNumber) {
        return true;
    }
}

/*
 try {
         FileReader fileReader = new FileReader("C:\\Users\\nacho\\IdeaProjects\\trace-it\\src\\TpProg2\\Archvios\\" + fileName);
         BufferedReader bufferedReader = new BufferedReader(fileReader);
         String line = bufferedReader.readLine();
         System.out.println(line);
         }catch (Exception e){
         System.out.println(e.getMessage());
         }
*/