package commands.interfaces;

import collection.music.MusicBand;
import udp.Response;

public interface ExtendedCommand extends Command{
    Response extendedExecute(MusicBand band);
}
