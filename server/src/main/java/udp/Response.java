package udp;

public class Response {
    String command;
    String msg;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Response(String command, String msg) {
        this.command = command;
        this.msg = msg;
    }

    public Response() {
    }

    @Override
    public String toString() {
        return "Response{" +
                "command='" + command + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
