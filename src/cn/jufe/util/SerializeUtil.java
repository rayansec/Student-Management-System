package cn.jufe.util;

import java.io.*;

public class SerializeUtil {
    private static String filename;
    public static void setFilename(String filename){
        SerializeUtil.filename = filename;
    }
    private SerializeUtil(){
    }
    public static Object deSerialize() throws FileNotFoundException, IOException, ClassNotFoundException{
        InputStream is = new FileInputStream(filename);
        try{
            ObjectInputStream ois = new ObjectInputStream(is);
            Object object = ois.readObject();
            ois.close();
            return object;
        } catch (EOFException e) {
            return null;
        }
    }
    public static boolean serialize(Object object) throws FileNotFoundException, IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
        oos.writeObject(object);
        oos.flush();
        oos.close();
        return true;
    }

}
