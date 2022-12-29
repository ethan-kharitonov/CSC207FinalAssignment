package console.usecases;
import java.io.*;

public class TextFileLogger {
    private String filePath;
    public TextFileLogger(String filePath){
        this.filePath = filePath;
    }

    public void logToFile(Serializable obj) throws IOException {
            FileOutputStream fileOut  = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();
    }

    public Object readFromFile() {
        try{
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object item = in.readObject();
            in.close();
            fileIn.close();
            return item;
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }

    }
}
