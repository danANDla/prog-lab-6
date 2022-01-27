package commands.interfaces;

import udpclient.Request;

public interface RemoteCommand extends Command {
    public Request makeRequest();
}
