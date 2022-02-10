package application;

import udp.Request;
import udp.Response;
import udp.UDPserver;
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
                Response resp = executor.executeRequest(recieved);
                udp.sendReponse(resp, recieved.getSender());
            } catch (Exception e){
                io.printError("Exception while receiving package");
            }
        }
    }
}
