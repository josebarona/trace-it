package TpProg2.DataStore;

import TpProg2.Events.Symptom;
import TpProg2.Exceptions.ABMUserException;
import TpProg2.Exceptions.DataStoreException;
import TpProg2.Exceptions.SymptomsNotRegistred;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class SymptomFileStore implements DataStore<Symptom>{

    String fileName;

    public SymptomFileStore(String fileName) {
        this.fileName = "src/TpProg2/DataStore/data/" + fileName;
    }

    @Override
    public void save(Symptom symptom) {
        if (!exists(symptom.getName())) {
            try {
                FileWriter fileWriter = new FileWriter(fileName, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.newLine();
                bufferedWriter.write(symptom.getName());
                bufferedWriter.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Metodo remove line de un file ---> chequealo mas a fondo.
    @Override
    public void remove/*FileLine*/(String symptom) { /* leer archivo guardar en un arraylist users, borro el user con ese id y lo escribo devuelta.*/
        ArrayList<String> allLines = collectFileLines();
        for(Iterator<String> itr = allLines.iterator(); itr.hasNext();){
            String line = itr.next();
            if(line.startsWith(symptom)){
                itr.remove();
                cleanFile();
            }

        }
        for (String line : allLines) {
            writeFile(line);
        }
    }

    public ArrayList<String> collectFileLines(){
        ArrayList<String> lines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(this.fileName));){
            String line = br.readLine();
            int i = 0;
            while(line != null){
                lines.add(line);
                line = br.readLine();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public void writeFile(String toWrite){
        //metodo para abstraer escribir en un archivo.
        try(BufferedWriter br = new BufferedWriter(new FileWriter(this.fileName, true));){
            br.write(toWrite + "\n");
        } catch(IOException e){
            e.getMessage();
        }
    }

    private void cleanFile(){
        try {
            PrintWriter writer = new PrintWriter(new File((this.fileName)));
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Symptom findById(String symptom) throws ABMUserException {
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                if (strLine.equals(symptom)) {
                    return this.lineToSymtom(strLine);
                }
            }
            fstream.close();
        }catch (IOException e){
            e.getMessage();
        }
        return null;
    }

    private Symptom lineToSymtom(String strLine) {
        Symptom symptom = new Symptom(strLine);
        return symptom;
    }

    @Override
    public void edit(Symptom symptom) {
        remove(symptom.getName());
        save(symptom);
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
    public boolean exists(String symptom) {
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                if (strLine.equals(symptom)) {
                    return true;
                }
            }
            fstream.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public ArrayList<Symptom> toArrayList() throws DataStoreException {
        ArrayList<Symptom> symptoms = new ArrayList();
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            if (!this.isEmpty()) {
                while ((strLine = br.readLine()) != null) {
                    Symptom symptom = lineToSymtom(strLine);
                    if (!symptoms.contains(symptom)) {
                        symptoms.add(symptom);
                    }
                }
                return symptoms;
            }
            fstream.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        throw new SymptomsNotRegistred();
    }
}
