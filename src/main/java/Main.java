import Controller.LoginAndSignUpController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        runApp();
    }

    public static void runApp() throws IOException {
        // TODO ServerController.loadData();

        ServerSocket serverSocket = new ServerSocket(7777);
        while (true) {
            Socket socket = serverSocket.accept();
            startNewThread(serverSocket, socket);
        }
    }

    private static void startNewThread(ServerSocket serverSocket, Socket socket) {
        new Thread(() -> {
            try {
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                getInputAndProcess(dataInputStream, dataOutputStream);
                socket.close();
                serverSocket.close();
                dataInputStream.close();
            } catch (IOException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void getInputAndProcess(DataInputStream dataInputStream, DataOutputStream dataOutputStream) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        while (true) {
            String input = dataInputStream.readUTF();
            String response = process(input);
            if (input.equals(""))
                break;
            else {
                dataOutputStream.writeUTF(response);
                dataOutputStream.flush();
            }
        }
    }

    private static String process(String input) {
        if (input.startsWith("create user")) {
            LoginAndSignUpController loginAndSignUpController = LoginAndSignUpController.getInstance();
            return loginAndSignUpController.createUser(input);
        }
        return "";
    }

    public Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
}
