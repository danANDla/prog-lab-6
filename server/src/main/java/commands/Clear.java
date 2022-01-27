package commands;

import collection.utils.CollectionManager;

public class Clear implements Command{
    private CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.clearList();
    }

    @Override
    public String getdescription() {
        String descr = "очистить коллекцию";
        return descr;
    }
}
