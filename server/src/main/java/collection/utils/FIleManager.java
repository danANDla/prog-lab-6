package collection.utils;

import services.IOutil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FIleManager {
    private Asker asker;
    private IOutil io;
    private String filepath;

    public FIleManager(IOutil ioutil) {
        filepath = getPath();
        io = ioutil;
        asker = new Asker(io);
    }

    private String getPath() {
        Properties prop = new Properties();
        String path = "";
        String fileName = "/home/danandla/BOTAY/programming/labs/prog-5/prog-lab-5/app.config";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
            path = prop.getProperty("app.filepath");
        } catch (FileNotFoundException ex) {
            System.out.println("Can't find config file");
        } catch (IOException ex) {
            System.out.println("Unknown error while finding pepper");
        }
        return path;
    }

    public void fromXML() {

    }

    public void toXML() {

    }
}
