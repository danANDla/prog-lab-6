package commands;

import commands.interfaces.Command;
import services.IOutil;

public class Exit implements Command {
    IOutil io;

    public Exit(IOutil io) {
        this.io = io;
    }

    @Override
    public void execute() {
        io.printWarning("завершить программу?\n(несохраненные данные могут быть утеряны) y/n");
        String answer = io.readLine().trim().toUpperCase();
        if(answer.equals("Y")) { System.exit(0); }
    }

    @Override
    public String getdescription() {
        String descr = "завершить выполнение программы";
        return descr;
    }
}
