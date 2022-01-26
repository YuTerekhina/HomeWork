package HomeWork6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
    Scanner scanner = new Scanner(System.in);
    public DataOutputStream out;


    public static void main(String[] args) {
        new EchoServer();
    }

    public EchoServer() {
        start();

    }

    private void start() {
        Socket socket = null;

        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился... ");

            final DataInputStream in = new DataInputStream(socket.getInputStream());
            final DataOutputStream out = new DataOutputStream(socket.getOutputStream());


            while (true) {
                final String message = in.readUTF();
                System.out.println("Получено сообщение от клиента: " + message);
                if ("/end".equalsIgnoreCase(message)) {
                    System.out.println("Клиент отключился");
                    out.writeUTF("/end");
                    break;
                }

                out.writeUTF(scanner.nextLine());
                if ("/end".equalsIgnoreCase(scanner.nextLine())){
                    socket.close();
                    break;
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}