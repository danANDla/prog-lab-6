package commands;

import services.IOutil;

import java.util.HashMap;
import java.util.Map;

public class Help implements Command{
    private HashMap<String, Command> commandsList;
    private HashMap<String, ArgumentedCommand> argumentedCommandsList;
    private IOutil io;

    public Help(HashMap<String, Command> commandsList, HashMap<String, ArgumentedCommand> argumentedCommandsList, IOutil io) {
        this.commandsList = commandsList;
        this.argumentedCommandsList = argumentedCommandsList;
        this.io = io;
    }

    @Override
    public void execute() {
       io.printText("Команды без аргументов");
       for(Map.Entry<String, Command> command: commandsList.entrySet()){
          io.printCommand(command.getKey(), command.getValue().getdescription());
       }
       io.printText("Команды с аргументами");
        for(Map.Entry<String, ArgumentedCommand> command: argumentedCommandsList.entrySet()){
            io.printArgumentedCommand(command.getKey(), command.getValue().getArgsDescription(), command.getValue().getdescription());
        }
    }

    @Override
    public String getdescription() {
        String descr = "вывести справку по доступным командам";
        return descr;
    }
}
