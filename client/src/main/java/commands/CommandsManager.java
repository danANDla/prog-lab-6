package commands;

import collection.utils.Asker;
import collection.utils.MusicBandFactory;
import commands.interfaces.LocalCommand;
import commands.interfaces.RemoteArgumentedCommand;
import commands.interfaces.RemoteCommand;
import services.IOutil;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Locale;

public class CommandsManager {
    private HashMap<String, RemoteCommand> commandsList;
    private HashMap<String, RemoteArgumentedCommand> argumentedComandsList;
    private HashMap<String, LocalCommand> localComandsList;
    private IOutil io;
    private MusicBandFactory musicBandFactory;
    private Asker asker;

    ArrayDeque<String> history;

    public CommandsManager(IOutil ioutil){
        io = ioutil;
        asker = new Asker(io);
        musicBandFactory = new MusicBandFactory(asker);
        history = new ArrayDeque<String>();
        fillLists();
    }

    public void fillLists(){
        commandsList = new HashMap<String, RemoteCommand>();
        argumentedComandsList = new HashMap<String, RemoteArgumentedCommand>();
        localComandsList = new HashMap<String, LocalCommand>();

        localComandsList.put("exit", new Exit(io));
        localComandsList.put("help", new Help(commandsList, localComandsList, argumentedComandsList, io));
        localComandsList.put("history", new History(history, io));

    }

    public void executeCommand(String newCommand){
        String[] command = newCommand.trim().toLowerCase(Locale.ROOT).split("\\s+");
        if(localComandsList.containsKey(command[0])){
            LocalCommand parsedCommand = localComandsList.get(command[0]);
            parsedCommand.execute();
            history.addFirst(command[0]);
            if(history.size() > 14){
                history.removeLast();
            }
        }
        else{
            sendCommand(newCommand);
        }
    }

    private void sendCommand(String newCommand){
        String[] command = newCommand.trim().toLowerCase(Locale.ROOT).split("\\s+");
        if(argumentedComandsList.containsKey(command[0])){
            RemoteArgumentedCommand parsedCommand = argumentedComandsList.get(command[0]);

            //TODO add command package and send

            history.addFirst(command[0]);
            if(history.size() > 14){
                history.removeLast();
            }
        }
        else if(commandsList.containsKey(command[0])){
            RemoteCommand parsedCommand = commandsList.get(command[0]);

            //TODO add command package and send

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
