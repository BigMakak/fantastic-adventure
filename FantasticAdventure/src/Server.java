import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Óscar Cardoso on 10/07/17.
 */
public class Server {

    private int port = 8080;
    private ServerSocket serverSocket;
    private String header;
    private ExecutorService cachedThreadPool;
    private List<ClientDispatcher> list = new LinkedList<>();


    public Server() {

        try {

            serverSocket = new ServerSocket(port);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void start() {

        cachedThreadPool = Executors.newCachedThreadPool();

        while (true) {

            Socket clientSocket;

            try {

                clientSocket = serverSocket.accept();
                ClientDispatcher client = new ClientDispatcher(clientSocket);
                list.add(client);
                cachedThreadPool.submit(client);

            } catch (IOException e) {

                System.err.println("Client socket error: " + e.getMessage());
                System.exit(1);
            }
        }
    }

    private class ClientDispatcher implements Runnable {

        private String name;
        private String passWord;
        private String fileName = "1";
        private String data;
        private Socket clientSocket;
        private BufferedReader in = null;
        private PrintWriter out;

        public ClientDispatcher(Socket clientSocket) {

            this.clientSocket = clientSocket;

            try {

                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                out.print("Enter your nickname: ");
                out.flush();

                name = in.readLine();

                out.print("Enter your password: ");
                out.flush();

                passWord = in.readLine();

            } catch (IOException e) {

                System.out.println("in and out client dispatcher: " + e.getMessage());
                System.exit(1);
            }


        }

        @Override
        public void run() {

            while (true) {

                try {

                    data = in.readLine();

                    send(data);
                } catch (IOException e) {

                    System.err.println("ClientDispatcher run error: " + e.getMessage());
                    System.exit(1);
                }
            }
        }

        public void send(String data) {

            out.println(data);
        }

        public String getData() {

            return data;
        }

        public void setFileName(String fileName) {

            this.fileName = fileName;
        }
    }
}

