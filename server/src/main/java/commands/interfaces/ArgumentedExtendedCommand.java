package commands.interfaces;

import collection.music.MusicBand;

public interface ArgumentedExtendedCommand extends ArgumentedCommand{
    public void extendedExecute(MusicBand band);
}
