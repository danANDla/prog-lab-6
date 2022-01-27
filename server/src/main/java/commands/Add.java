package commands;

import collection.music.MusicBand;
import collection.utils.CollectionManager;
import commands.interfaces.Command;
import commands.interfaces.ExtendedCommand;

public class Add implements ExtendedCommand {
    private CollectionManager collecManager;
    private MusicBand band;

    public Add(CollectionManager collMan){
        collecManager = collMan;
    }

    @Override
    public void extendedExecute(MusicBand band) {
        this.band = band;
        execute();
    }

    @Override
    public void execute() {
        collecManager.insertBand(band);
    }

    @Override
    public String getdescription() {
        String descr = "добавить новый элемент в коллекцию";
        return descr;
    }
}
