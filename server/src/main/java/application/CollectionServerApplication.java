package application;

import collection.utils.CollectionManager;
import udp.Request;
import udp.Response;
import udp.UDPserver;
import commands.CommandsManager;
import services.IOutil;

public class CollectionServerApplication {
    private IOutil io;
    private CommandsManager commandsManager;
    private CollectionManager collectionManager;
    private UDPserver udp;

    public CollectionServerApplication(){
        io = new IOutil();
        collectionManager = new CollectionManager(io);
        commandsManager = new CommandsManager(io, collectionManager);
        udp = new UDPserver(io);
    }

    public void start(){
        collectionManager.readXML();
        listening();
    }

    public void listening(){
        while(true){
            try{
                Request recieved = udp.recieveRequest();
                Response resp = commandsManager.executeRequest(recieved);
                udp.sendReponse(resp, recieved.getSender());
            } catch (Exception e){
                io.printError("Exception while receiving package");
            }
        }
    }
}
