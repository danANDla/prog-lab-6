package commands;

import collection.utils.CollectionManager;
import services.IOutil;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Locale;

public class CommandsManager {
    private HashMap<String, Command> commandsList;
    private HashMap<String, ArgumentedCommand> argumentedComandsList;
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

        commandsList.put("add", new Add(collectionManager));
        commandsList.put("exit", new Exit(io));
        commandsList.put("help", new Help(commandsList, argumentedComandsList, io));
        commandsList.put("info", new Info(collectionManager));
        commandsList.put("show", new Show(collectionManager));
        argumentedComandsList.put("update", new Update(collectionManager, io));
        argumentedComandsList.put("remove_by_id", new RemoveById(collectionManager,io));
        commandsList.put("clear", new Clear(collectionManager));
        commandsList.put("history", new History(history, io));
    }

    public void executeCommand(String newCommand){
        String[] command = newCommand.trim().toLowerCase(Locale.ROOT).split("\\s+");
        if(argumentedComandsList.containsKey(command[0])){
            ArgumentedCommand parsedCommand = argumentedComandsList.get(command[0]);
            if (parsedCommand.parseArgs(command)) parsedCommand.execute();
            history.addFirst(command[0]);
            if(history.size() > 14){
                history.removeLast();
            }
        }
        else if(commandsList.containsKey(command[0])){
            Command parsedCommand = commandsList.get(command[0]);
            parsedCommand.execute();
            history.addFirst(command[0]);
            if(history.size() > 14){
                history.removeLast();
            }
        }
        else{
            io.printError("Такой команды не найдено");
        }

    }
}
