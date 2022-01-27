package udp;

import collection.music.MusicBand;

import java.io.Serializable;

public class Request implements Serializable {
    private String command;
    private String args;
    private MusicBand musicBand;

    public Request() {
    }

    public Request(String command, String args, MusicBand musicBand) {
        this.command = command;
        this.args = args;
        this.musicBand = musicBand;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public MusicBand getMusicBand() {
        return musicBand;
    }

    public void setMusicBand(MusicBand musicBand) {
        this.musicBand = musicBand;
    }

    @Override
    public String toString() {
        return "Request{" +
                "command='" + command + '\'' +
                ", args='" + args + '\'' +
                ", musicBand=" + musicBand +
                '}';
    }
}
