package commands;

import commands.interfaces.RemoteCommand;
import udpclient.Request;

public class Info implements RemoteCommand {
    @Override
    public Request makeRequest() {
        return new Request("info", null, null);
    }

    @Override
    public String getdescription() {
        String descr = "вывести информацию о коллекции";
        return descr;
    }
}
