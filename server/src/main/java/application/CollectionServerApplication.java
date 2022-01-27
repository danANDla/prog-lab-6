package application;

import udpserver.Request;
import udpserver.UDPserver;
import commands.CommandsManager;
import services.IOutil;

public class CollectionServerApplication {
    private IOutil io;
    private CommandsManager executor;
    private UDPserver udp;

    public CollectionServerApplication(){
        io = new IOutil();
        executor = new CommandsManager(io);
        udp = new UDPserver(io);
    }

    public void start(){
        listening();
    }

    public void listening(){
        while(true){
            try{
                Request recieved = udp.recieveRequest();
            } catch (Exception e){
                io.printError("Exception while receiving package");
            }
        }
    }
}
