package commands.interfaces;

import collection.music.MusicBand;

public interface ExtendedCommand extends Command{
    public void extendedExecute(MusicBand band);
}
