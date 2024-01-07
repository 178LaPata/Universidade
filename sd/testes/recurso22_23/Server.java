package recurso22_23;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket socket;
    private Controlador controlador;

    public void server(ServerSocket socket, Controlador controlador) {
        this.socket = socket;
        this.controlador = new Controlador();
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.startServer();
    }

    public void startServer() throws IOException{
        socket = new ServerSocket(12345);
        while(true){
            Socket ss = socket.accept();
            Thread t = new Thread(new ClientHandler(ss, controlador));
            t.start();
        }
    }

    public class ClientHandler implements Runnable {
        private Socket socket;
        private Controlador controlador;
        private DataOutputStream out;
        private DataInputStream in;

        public ClientHandler(Socket socket, Controlador controlador) throws IOException {
            this.controlador = controlador;
            try {
                this.out = new DataOutputStream(socket.getOutputStream());
                this.in = new DataInputStream(socket.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void run(){
            int id;
            try {
                id = controlador.reserva();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                controlador.preparado(id);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean corridaEmAndamento = true;
            while (corridaEmAndamento) {
                String comando = null;
                try {
                    comando = in.readUTF();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (comando.equals("voltaCompleta")) {
                    controlador.completaVolta(id);
                } else if (comando.equals("verificarVoltas")) {
                    int[] voltas = controlador.voltasCompletas();
                    try {
                        out.writeInt(voltas.length);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                try {
                    if (controlador.vencedor() == id) {
                        out.writeUTF("Vencedor");
                        corridaEmAndamento = false;
                    } else if (controlador.vencedor() != -1) {
                        String vencedor = "Vencedor: Kart " + controlador.vencedor();
                        out.writeUTF(vencedor);
                        corridaEmAndamento = false;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
