package TpProg2.DataStore;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class FileStore<T extends FileSaveable> implements DataStore<T>{
    String fileName;
    //CollectionStore<T> collectionData; // PROBANDO ALGO

    public FileStore(String fileName) {
        this.fileName = fileName;
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