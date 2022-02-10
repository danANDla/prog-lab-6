package commands.interfaces;

import collection.music.MusicBand;
import udp.Response;

public interface ArgumentedExtendedCommand extends ArgumentedCommand{
    Response extendedExecute(MusicBand band);
}
