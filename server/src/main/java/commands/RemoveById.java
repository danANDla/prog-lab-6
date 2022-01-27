package commands;

import collection.utils.CollectionManager;
import services.IOutil;

public class RemoveById implements ArgumentedCommand{
    private CollectionManager collectionManager;
    private Integer bandId;
    private IOutil io;

    public RemoveById(CollectionManager collectionManager, IOutil io) {
        this.collectionManager = collectionManager;
        this.io = io;
    }

    @Override
    public boolean parseArgs(String[] command) {
        if(command.length - 1 < 1){
            io.printError("введено недостаточно аргументов");
            return false;
        }
        else{
            try {
                bandId = Integer.parseInt(command[1]);
            } catch (NumberFormatException e){
                io.printError("неправильный формат аргумента");
                return false;
            }
        }
        return true;
    }

    @Override
    public void execute() {
        collectionManager.removeBand(bandId);
    }

    @Override
    public String getdescription() {
        String descr = "удалить элемент из коллекции по его id";
        return descr;
    }

    @Override
    public String getArgsDescription() {
        String descr = "id";
        return descr;
    }
}
