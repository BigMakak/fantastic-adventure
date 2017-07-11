package org.academiadecodigo.bootcamp;

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
        }

        @Override
        public void run() {

            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                logInOrRegister();

                send("\u001b[2J"); //clear screen terminal

                while (true) {

                    data = in.readLine();
                }

            } catch (IOException e) {

                System.err.println("ClientDispatcher method run error: " + e.getMessage());
                System.exit(1);
            }
        }

        private void logInOrRegister() throws IOException {

            while (true) {

                out.print("Choose [1] to Login or [2] to Register: ");
                out.flush();
                String result = in.readLine();

                if (result.equals("1")) {

                    logIn();
                    return;
                }

                if (result.equals("2")) {

                    register();
                    return;
                }
            }
        }

        private void register() throws IOException {

            boolean islogin = true;

            while (islogin) {

                out.print("Enter a new nickname: ");
                out.flush();
                String result = in.readLine();

                result = checkName(result);

                if (result != null) {

                    name = result;
                    islogin = false;
                }
            }

            out.print("Enter a new password: ");
            out.flush();
            passWord = in.readLine();
        }

        private void logIn() throws IOException {

            boolean islogin = true;

            while (islogin) {

                out.print("Enter your nickname: ");
                out.flush();
                String result = in.readLine();

                result = checkName(result);

                if (result != null) {

                    islogin = false;
                }
            }

            while (true) {

                out.print("Enter your password: ");
                out.flush();
                String result = in.readLine();

                synchronized (list) {

                    for (ClientDispatcher cd : list) {

                        if (result.equals(cd.passWord)) {


                            return;
                        }
                    }
                }

                out.println("wrong password, try again...\n");
            }
        }

        private String checkName(String result) {

            synchronized (list) {

                for (ClientDispatcher cd : list) {

                    if (result.equals(cd.name)) {

                        out.println(result + " is taken.\n");
                        result = null;
                        break;
                    }
                }
            }
            return result;
        }

        public String[] clientRequest() {

            String[] result = new String[3];

            result[0] = fileName;
            result[1] = name;
            result[2] = data;

            return result;
        }

        public void send(String data) {

            out.print(data);
            out.flush();
        }

        public String getData() {

            return data;
        }

        public void setFileName(String fileName) {

            this.fileName = fileName;
        }
    }
}

