package data;

import java.io.*;

/**
 * Generic Serialization Class
 * @param <T> type of object to serialize
 */
public class Serializator<T> {

    /**
     * Serialization
     * @param filename name of file that will get all the information
     * @param object object to serialize
     * @throws IOException exception
     */
    public void serializeObject(String filename, T object) throws IOException {
        FileOutputStream outFile = new FileOutputStream(filename);
        ObjectOutputStream outObject = new ObjectOutputStream(outFile);
        outObject.writeObject(object);
        outFile.close();
        outObject.close();
    }

    /**
     * Deserialization
     * @param filename name of file from which the information is taken
     * @return object to deserialize
     * @throws ClassNotFoundException exception
     * @throws IOException exception
     */
    public T deserializeObject(String filename) throws ClassNotFoundException, IOException {
        FileInputStream inFile = new FileInputStream(filename);
        ObjectInputStream inObject = new ObjectInputStream(inFile);
        T obj = (T) inObject.readObject();
        inFile.close();
        inObject.close();
        return obj;
    }

}
