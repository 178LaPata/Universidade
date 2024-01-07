package recurso21_22;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket socket;
    private Reuniao reuniao;

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.startServer();
    }

    public void startServer() throws IOException{
        socket = new ServerSocket(12345);
        while(true){
            Socket ss = socket.accept();
            Thread t = new Thread(new ClientHandler(ss, reuniao));
            t.start();
        }
    }

    public class ClientHandler implements Runnable {
        private Socket socket;
        private Reuniao reuniao;
        private DataOutputStream out;
        private DataInputStream in;

        public ClientHandler(Socket socket, Reuniao reuniao) throws IOException{
            this.socket = socket;
            this.reuniao = reuniao;
            try {
                this.out = new DataOutputStream(socket.getOutputStream());
                this.in = new DataInputStream(socket.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void run(){
            String comando;
            while(true){
                try {
                    comando = in.readUTF();
                } catch (IOException e) {
                    throw new RuntimeException();
                }
                if(comando.equals("Entrar")){
                    try {
                        reuniao.participa(in.readInt());
                        out.writeUTF("Entre");
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(comando.equals("Abandonei")){
                    try {
                        reuniao.abandona(in.readInt());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
