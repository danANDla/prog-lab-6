package commands.interfaces;

public interface RemoteArgumentedCommand extends RemoteCommand {
    public boolean parseArgs(String[] command);
    public String getArgsDescription();
}
