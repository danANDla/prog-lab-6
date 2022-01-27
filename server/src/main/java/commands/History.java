package commands;

import commands.interfaces.Command;
import services.IOutil;

import java.util.ArrayDeque;
import java.util.Iterator;

public class History implements Command {
    ArrayDeque<String> hist;
    IOutil io;

    public History(ArrayDeque<String> hist, IOutil io) {
        this.hist = hist;
        this.io = io;
    }


    @Override
    public void execute() {
        Iterator<String> it = hist.descendingIterator();
        while(it.hasNext()) {
            io.printText(it.next());
        }
    }

    @Override
    public String getdescription() {
        String descr = "вывести последние 14 команд";
        return descr;
    }
}
