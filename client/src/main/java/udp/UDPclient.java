package udp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.*;

public class UDPclient {
    /* Порт сервера, к которому собирается
    подключиться клиентский сокет */
    private final static int SERVICE_PORT = 50001;
    private DatagramSocket clientSocket;
    private InetAddress IPAddress;

    // Создайте соответствующие буферы
    private byte[] sendingDataBuffer = new byte[1024];
    private byte[] receivingDataBuffer = new byte[1024];

    public UDPclient() {
        /* Создайте экземпляр клиентского сокета.
        Нет необходимости в привязке к определенному порту */
        try {
            clientSocket = new DatagramSocket();
            // Получите IP-адрес сервера
            IPAddress = InetAddress.getByName("localhost");
        } catch (SocketException | UnknownHostException e) {
            System.out.println("Unable create client socket");
        }
    }

    public ByteArrayOutputStream serializeReq(Request newReq) throws IOException {
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        ObjectOutput oo = new ObjectOutputStream(bStream);
        oo.writeObject(newReq);
        oo.close();
        return bStream;
    }

    public void sendCommand(Request newReq) {
        try {
            /* Преобразуйте данные в байты
            и разместите в буферах */
            sendingDataBuffer = serializeReq(newReq).toByteArray();
            receivingDataBuffer = new byte[1024];

            // Создайте UDP-пакет
            DatagramPacket sendingPacket = new DatagramPacket(sendingDataBuffer, sendingDataBuffer.length, IPAddress, SERVICE_PORT);
            System.out.println("Created DatagramPacket, " + "buffer length: " + sendingDataBuffer.length);

            // Отправьте UDP-пакет серверу
            clientSocket.send(sendingPacket);
            System.out.println("file was sent");

            // Получите ответ от сервера, т.е. предложение из заглавных букв
            DatagramPacket receivingPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
            clientSocket.receive(receivingPacket);

            // Выведите на экране полученные данные
            String receivedData = new String(receivingPacket.getData());
            System.out.println("Sent from the server: " + receivedData);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SocketAddress getAddress(){
        return clientSocket.getLocalSocketAddress();
    }
}
