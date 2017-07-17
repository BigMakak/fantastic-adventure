package org.academiadecodigo.bootcamp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
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
    private ExecutorService cachedThreadPool;
    private List<ClientDispatcher> list = Collections.synchronizedList(new LinkedList<>()); // locked by itself

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





    public class ClientDispatcher implements Runnable {

        private String name;
        private String passWord;
        private String fileName = "000";
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;
        private String clientOption;
        private MessageHandler messageHandler;

        private ClientDispatcher(Socket clientSocket) {

            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {

            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                Game game = new Game();
                game.init();
                messageHandler = game;


                logInOrRegister();

                send("\u001b[2J"); //clear screen terminal

                messageHandler.fromServer("resetFile");

                send(messageHandler.toServer());
                sceneTest();
                send("Press 1 to start the game. Write !leave to leave the game. Write !how to see game instructions.");


                while (true) {

                    clientOption = in.readLine();

                    leaveGame(clientOption);

                    gameInstructions(clientOption);

                    if (validClientOption(clientOption)) {

                        //send("\u001b[0m" + "\u001b[2J"); //clear screen terminal
                        send(  "\u001b[2J"); //clear screen terminal

                        messageHandler.fromServer(clientOption);
                        String message = messageHandler.toServer();

                        if (message != null && !message.equals("")) {

                            //send(Color.pickColor());
                            send( message.substring(3));

                            fileName = message.substring(0, 3);
                        }

                    } else {
                        if (!clientOption.matches("!how")) {
                            out.println("Not a valid option...Try again");
                        }
                        continue;
                    }
                }

            } catch (IOException e) {

                System.err.println("ClientDispatcher method run error: " + e.getMessage());
                System.exit(1);
            }
        }


        private void logInOrRegister() throws IOException {

            out.print("Choose [1] to Login or [2] to Register: ");
            out.flush();
            String result = in.readLine();

            if (result.equals("1")) {

                logIn();
            }

            if (result.equals("2")) {

                register();
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

            boolean check = true;
            while (check) {

                out.print("Enter your password: ");
                out.flush();
                String result = in.readLine();

                synchronized (list) {

                    for (ClientDispatcher cd : list) {

                        if (result.equals(cd.passWord)) {
                            check = false;
                            break;
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
            result[2] = clientOption;

            return result;
        }


        public void send(String data) {

            out.print(data);
            out.flush();
        }


        public String getName() {
            return name;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {

            this.fileName = fileName;
        }

        public void sceneTest() {

            BufferedReader inputBufferedReader;
            String fileContent = "";
            String firstLine;
            int line = 0;

            try {
                inputBufferedReader = new BufferedReader(new FileReader("resources/mainScene.txt"));

                while (line < 50) {

                    fileContent = inputBufferedReader.readLine();

                    if (fileContent == null) { //Defensive programming!
                        break;
                    }
                    out.println(fileContent);
                    Thread.sleep(1000);
                    ++line;
                }


            } catch (FileNotFoundException ex) {
                System.err.println("Error : " + ex.getLocalizedMessage());
            } catch (IOException ex) {
                System.err.println("Error :" + ex.getMessage());
            } catch (InterruptedException ex) {
                System.err.println("Error : " + ex.getMessage());
            }
        }

        public void leaveGame(String clientOption) {
            if (clientOption.matches("!leave")) {
                out.println("Try harder next time");
                //saveClientState();
                System.exit(0);
            }
        }

        private void gameInstructions(String clientOption) {
            if (clientOption.matches("!how")) {
                out.println("-->Start your adventure and chose between the possible options to proceed \n " +
                        "you can die from wrong choices");
            }
        }

        public boolean validClientOption(String clientOption) {

            return clientOption.matches("[1-4]");
        }
    }
}









