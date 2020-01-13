package pllapallpal.datastream.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerModel {

    private Socket socket;
    private ServerSocket serverSocket;

    private DataInputStream input;
    private DataOutputStream output;

    public ServerModel(int port) {

        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();

            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DataInputStream getInput() {
        return input;
    }

    public DataOutputStream getOutput() {
        return output;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
}
