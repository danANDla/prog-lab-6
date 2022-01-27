package commands;

import collection.utils.CollectionManager;
import commands.interfaces.Command;

public class Info implements Command {
    CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.info();
    }

    @Override
    public String getdescription() {
        String descr = "вывести информацию о коллекции";
        return descr;
    }
}
