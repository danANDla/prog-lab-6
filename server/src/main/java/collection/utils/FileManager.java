package collection.utils;

import collection.music.MusicBand;
import services.IOutil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.Properties;

public class FileManager {
    private Asker asker;
    private IOutil io;
    private String filepath;
    private CollectionManager collectionManager;

    public FileManager(IOutil ioutil, CollectionManager manager) {
        filepath = getPath();
        io = ioutil;
        asker = new Asker(io);
        collectionManager = manager;
    }

    private String getPath() {
        Properties prop = new Properties();
        String path = "";
        String fileName = "/home/danandla/BOTAY/programming/labs/prog-5/prog-lab-5/app.config";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
            path = prop.getProperty("app.xmlpath");
        } catch (FileNotFoundException ex) {
            io.printError("Can't find config file");
        } catch (IOException ex) {
            io.printError("Unknown error while finding file path");
        }
        return path;
    }

    public void toXML() {
        String path = getPath();
    }

    public void fromXML() {
        String path = getPath();
        System.out.println("here");
        try{
            io.printText(path);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            JAXBContext jaxbContext = JAXBContext.newInstance(CollectionManager.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            CollectionManager collectionManager1 = (CollectionManager) unmarshaller.unmarshal(bufferedReader);
            System.out.println(collectionManager1.getBandsList().size());
            for (MusicBand val : collectionManager1.getBandsList()) {
                if (collectionManager1.validate(val)) {
                    this.collectionManager.insertBand(val);
                }
                else System.out.println("Из XML-файла элемент с именем: " + val.getName() + " не добавлен в коллекцию!");
            }
            io.printText("XML was read successfully");
        } catch (JAXBException | FileNotFoundException e){
            io.printError("Can't read xml file " + e);
        }
    }
}
