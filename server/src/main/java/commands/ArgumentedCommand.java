package commands;

public interface ArgumentedCommand extends Command{
    boolean parseArgs(String[] command);
    String getArgsDescription();
}
