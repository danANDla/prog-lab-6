package udp;

import services.IOutil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;

public class UDPserver {
    private IOutil io;

    // Создайте новый экземпляр DatagramSocket, чтобы получать ответы от клиента
    private DatagramChannel datagramChannel;

    // Серверный UDP-сокет запущен на этом порту
    private final static int SERVICE_PORT=50001;

    private ByteBuffer buffer;

    public UDPserver(IOutil ioutil){
        io = ioutil;
        try{
            datagramChannel = DatagramChannel.open();
            datagramChannel.bind(new InetSocketAddress(SERVICE_PORT));
            buffer = ByteBuffer.allocate(150);
            buffer.clear();
        } catch (SocketException e){
            io.printError("Unable to create server socket");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Request deserializeReq(ByteBuffer buffer){
        byte[] array = new byte[buffer.remaining()];
        buffer.get(array);
        ByteArrayInputStream bais = new ByteArrayInputStream(array);
        try (ObjectInputStream oip = new ObjectInputStream(bais)) {
            Request result = (Request) oip.readObject();
            return result;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("troubles " + e);
        }
        return null;
    }

    public Request recieveRequest() throws IOException{
        /* Создайте экземпляр UDP-пакета для хранения клиентских данных с использованием буфера для полученных данных */
        System.out.println("Waiting for a client to connect...");

        datagramChannel.receive(buffer);

        System.out.println("Buffer state: " + buffer + ", content: " + Arrays.toString(buffer.array()));
        buffer.flip();
        Request received = deserializeReq(buffer);
        System.out.println(received);
        buffer.clear();

//        // Закройте соединение сокетов
//        serverSocket.close();
        return  null;
    }
}