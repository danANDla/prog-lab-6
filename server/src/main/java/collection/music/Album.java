package collection.music;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "album")
public class Album implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private int tracks; //Значение поля должно быть больше 0
    private Integer length; //Поле может быть null, Значение поля должно быть больше 0
    private double sales; //Значение поля должно быть больше 0

    public  Album() {}

    public Album(String name, int tracks, Integer length, double sales) {
        this.name = name;
        this.tracks = tracks;
        this.length = length;
        this.sales = sales;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public int getTracks() {
        return tracks;
    }

    @XmlElement
    public void setTracks(int tracks) {
        this.tracks = tracks;
    }

    public Integer getLength() {
        return length;
    }

    @XmlElement
    public void setLength(Integer length) {
        this.length = length;
    }

    public double getSales() {
        return sales;
    }

    @XmlElement
    public void setSales(double sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", tracks=" + tracks +
                ", length=" + length +
                ", sales=" + sales +
                '}';
    }
}