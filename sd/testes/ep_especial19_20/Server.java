package ep_especial19_20;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    ServerSocket serverSocket;
    SalaDeEspera salaDeEspera;

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(12345);
        this.salaDeEspera = new SalaDeEspera();
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.StartServer();
    }

    public void StartServer() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            Thread thread = new Thread(new ClientHandler(socket, salaDeEspera));
            thread.start();
        }
    }

    class ClientHandler implements Runnable {
        Socket socket;
        SalaDeEspera salaDeEspera;

        DataInputStream in;
        DataOutputStream out;

        public ClientHandler(Socket socket, SalaDeEspera salaDeEspera) throws IOException {
            this.socket = socket;
            this.salaDeEspera = salaDeEspera;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
        }

        public void run() {
            while (true) {
                try {
                    String nome = in.readUTF();
                    if (nome.equals("desiste")) {
                        String desistente = in.readUTF();
                        salaDeEspera.desiste(desistente);
                    }
                    else if (nome.equals("atende")) {
                        int n = in.readInt();
                        List<String> atendidos = salaDeEspera.atende(n);
                        for (String atendido : atendidos) {
                            out.writeUTF(atendido);
                        }
                    }
                    else {
                        boolean atendido = salaDeEspera.espera(nome);
                        out.writeBoolean(atendido);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
