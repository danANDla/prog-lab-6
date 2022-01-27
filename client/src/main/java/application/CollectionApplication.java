package application;

import commands.CommandsManager;
import services.IOutil;

public class CollectionApplication {
    private IOutil io;
    private CommandsManager executor;

    public CollectionApplication(){
        io = new IOutil();
        executor = new CommandsManager(io);
    }

    public void start(){
        executeCommands();
    }

    public void executeCommands(){
        while(true){
            System.out.print("> ");
            String newCommand = io.readLine();
            executor.executeCommand(newCommand);
        }
    }
}
