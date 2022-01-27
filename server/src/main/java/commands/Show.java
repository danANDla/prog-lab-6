package commands;

import collection.utils.CollectionManager;

public class Show implements Command{
    private CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
       collectionManager.show();
    }

    @Override
    public String getdescription() {
        String descr = "вывести все элементы коллекции в строковом представлении";
        return descr;
    }
}
