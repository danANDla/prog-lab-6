package commands.interfaces;

import commands.interfaces.Command;

public interface RemoteCommand extends Command {
    public void preSend();
}
