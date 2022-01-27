package commands;

import collection.utils.CollectionManager;
import commands.interfaces.ArgumentedCommand;
import commands.interfaces.Command;
import commands.interfaces.ArgumentedExtendedCommand;
import commands.interfaces.ExtendedCommand;
import services.IOutil;
import udp.Request;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Locale;

public class CommandsManager {
    private HashMap<String, Command> commandsList;
    private HashMap<String, ArgumentedCommand> argumentedComandsList;
    private HashMap<String, ArgumentedExtendedCommand> argumentedExtendedCommandList;
    private HashMap<String, ExtendedCommand> extendedCommandList;
    private IOutil io;
    CollectionManager collectionManager;

    ArrayDeque<String> history;

    public CommandsManager(IOutil ioutil){
        io = ioutil;
        collectionManager = new CollectionManager(io);
        history = new ArrayDeque<String>();

        fillLists();
    }

    public void fillLists(){
        commandsList = new HashMap<String, Command>();
        argumentedComandsList = new HashMap<String, ArgumentedCommand>();
        extendedCommandList = new HashMap<String, ExtendedCommand>();
        argumentedExtendedCommandList = new HashMap<String, ArgumentedExtendedCommand>();

        commandsList.put("exit", new Exit(io));
        commandsList.put("help", new Help(commandsList, argumentedComandsList, io));
        commandsList.put("info", new Info(collectionManager));
        commandsList.put("history", new History(history, io));

        extendedCommandList.put("add", new Add(collectionManager));
    }

    public void executeRequest(Request request){
        String commandName = request.getCommand().toLowerCase(Locale.ROOT);
        if(extendedCommandList.containsKey(commandName)){
            ExtendedCommand parsedCommand = extendedCommandList.get(commandName);
            parsedCommand.extendedExecute(request.getMusicBand());

            history.addFirst(commandName);
            if(history.size() > 14){
                history.removeLast();
            }
        }
        else if(argumentedExtendedCommandList.containsKey(commandName)){
            ArgumentedExtendedCommand parsedCommand = argumentedExtendedCommandList.get(commandName);
            String[] commandArgs = request.getArgs().trim().toLowerCase(Locale.ROOT).split("\\s+");
            if(parsedCommand.parseArgs(commandArgs)) parsedCommand.extendedExecute(request.getMusicBand());

            history.addFirst(commandName);
            if(history.size() > 14){
                history.removeLast();
            }
        }
        else if(argumentedComandsList.containsKey(commandName)){
            ArgumentedCommand parsedCommand = argumentedComandsList.get(commandName);
            String[] commandArgs = request.getArgs().trim().toLowerCase(Locale.ROOT).split("\\s+");
            if (parsedCommand.parseArgs(commandArgs)) parsedCommand.execute();

            history.addFirst(commandName);
            if(history.size() > 14){
                history.removeLast();
            }
        }
        else if(commandsList.containsKey(commandName)){
            Command parsedCommand = commandsList.get(commandName);
            parsedCommand.execute();

            history.addFirst(commandName);
            if(history.size() > 14){
                history.removeLast();
            }
        }
        else{
            io.printError("Такой команды не найдено");
        }
    }
}
