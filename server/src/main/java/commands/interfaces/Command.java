package commands.interfaces;

import udp.Response;

public interface Command {
    Response execute();
    String getdescription();
}
