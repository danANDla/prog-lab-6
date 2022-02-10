package commands.interfaces;

import udp.Response;

public interface ArgumentedCommand extends Command {
    boolean parseArgs(String[] command);
    Response execute();
    String getArgsDescription();
}
