package commands.interfaces;

import udp.Request;

public interface RemoteCommand extends Command {
    public Request makeRequest();
}
