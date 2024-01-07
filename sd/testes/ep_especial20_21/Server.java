package ep_especial20_21;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket socket;
    private ControloVacinas cv;

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.startServer();
    }

    public void startServer() throws IOException{
        socket = new ServerSocket(12345);
        while(true){
            Socket ss = socket.accept();
            Thread t = new Thread(new ClientHandler(ss, cv));
            t.start();
        }
    }

    public class ClientHandler implements Runnable {
        private Socket socket;
        private ControloVacinas cv;
        private DataOutputStream out;
        private DataInputStream in;

        public ClientHandler(Socket socket, ControloVacinas cv) throws IOException{
            this.socket = socket;
            this.cv = cv;
            try {
                this.out = new DataOutputStream(socket.getOutputStream());
                this.in = new DataInputStream(socket.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void run() {
            while(true){
                String utente = null;
                try{
                    utente = in.readUTF();
                } catch(IOException e){
                    throw new RuntimeException(e);
                }
                if(utente.equals("cliente")){
                    try {
                        cv.pedirParaVacinar();
                  } catch(InterruptedException e){
                        throw new RuntimeException(e);
                    }
                } else if(utente.equals("fornecedor")){
                    int frascos;
                    try{ 
                        frascos = in.readInt();
                    } catch(IOException e){
                        throw new RuntimeException(e);
                    }
                    cv.fornecerFrascos(frascos);
                }
            }
        }

    }
}
