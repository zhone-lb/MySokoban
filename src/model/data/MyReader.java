package model.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MyReader {
    public static <T> T read(String filename) {
        T myClass = null;
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fileIn);
            myClass = (T) ois.readObject();
            fileIn.close();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return myClass;
    }
}
