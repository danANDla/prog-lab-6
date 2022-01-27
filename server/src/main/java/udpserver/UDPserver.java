package udpserver;

import services.IOutil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPserver {
    private IOutil io;

    // Создайте новый экземпляр DatagramSocket, чтобы получать ответы от клиента
    private DatagramSocket serverSocket;

    // Серверный UDP-сокет запущен на этом порту
    private final static int SERVICE_PORT=50001;

    /* Создайте буферы для хранения отправляемых и получаемых данных.
    Они временно хранят данные в случае задержек связи */
    private byte[] receivingDataBuffer = new byte[1024];
    private byte[] sendingDataBuffer = new byte[1024];

    public UDPserver(IOutil ioutil){
        io = ioutil;
        try{
            serverSocket = new DatagramSocket(SERVICE_PORT);
        } catch (SocketException e){
            io.printError("Unable to create server socket");
        }
    }

    public Request recieveRequest() throws IOException{
        /* Создайте экземпляр UDP-пакета для хранения клиентских данных с использованием буфера для полученных данных */
        DatagramPacket inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
        System.out.println("Waiting for a client to connect...");

        // Получите данные от клиента и сохраните их в inputPacket
        serverSocket.receive(inputPacket);

        // Выведите на экран отправленные клиентом данные
        String receivedData = new String(inputPacket.getData());
        System.out.println("Received from the client: " + receivedData);

//        // Закройте соединение сокетов
//        serverSocket.close();
        return  null;
    }
}