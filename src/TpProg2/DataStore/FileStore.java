package TpProg2.DataStore;

import java.io.*;
import java.util.ArrayList;

public class FileStore<T extends FileSaveable> implements DataStore<T>{
    String fileName;

    public FileStore(String fileName) {
        this.fileName = "src/TpProg2/DataStore/data/" + fileName;
    }

    @Override
    public void save(T t) {
        if (!exists(t.getId())) {
            try {
                FileWriter fileWriter = new FileWriter(fileName, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.newLine();
                bufferedWriter.write(t.getFileRepresentation());
                bufferedWriter.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    @Override
    public void remove(String id) {

    }

    @Override
    public T findById(String id) {
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                String[] data = strLine.split(",");
                if (data[0].equals(id)) {
                    System.out.println(data[0]);
                    return this.lineToObject(strLine);
                }
            }
            fstream.close();
        }catch (IOException e){
            e.getMessage();
        }
        return null;
    }

    public T lineToObject(String line){
        return null;
    }

    @Override
    public void edit(T t) {
        this.remove(t.getId());
        this.save(t);
    }

    @Override
    public boolean isEmpty() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            if (br.readLine() == null) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean exists(String id) {
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                String[] data = strLine.split(",");
                if (data[0].equals(id)) {
                    System.out.println(data[0]);
                    return true;
                }
            }
            fstream.close();
        }catch (IOException e){
            e.getMessage();
            System.out.println(e.getMessage());
        }
        return false;
    }

    public ArrayList<T> toArrayList() {
        return null;
    }
}
