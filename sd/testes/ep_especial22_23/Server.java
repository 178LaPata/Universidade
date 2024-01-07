package ep_especial22_23;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Server {
    private ServerSocket socket;
    private HPC hpc;

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.startServer();
    }

    public void startServer() throws IOException{
        socket = new ServerSocket(12345);
        while(true){
            Socket ss = socket.accept();
            Thread t = new Thread(new ClientHandler(ss, hpc));
            t.start();
        }
    }

    public class ClientHandler implements Runnable {
        private Socket socket;
        private HPC hpc;
        private DataOutputStream out;
        private DataInputStream in;

        public ClientHandler(Socket socket, HPC hpc) throws IOException{
            this.socket = socket;
            this.hpc = hpc;
            try {
                this.out = new DataOutputStream(socket.getOutputStream());
                this.in = new DataInputStream(socket.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void run() throws IOException {
            String opcao = null;
            while(true){
                try {
                    opcao = in.readUTF();
                } catch (IOException e) {
                    new RuntimeException(e);
                }
                if(opcao.equals("inicio")){
                    int nr_cores = in.readInt();
                    int id_tarefa = hpc.inicio(nr_cores);
                    out.writeInt(id_tarefa);
                } else if (opcao.equals("fim")) {
                    int id_tarefa = in.readInt();
                    Long tempo = in.readLong();
                    hpc.fim(id_tarefa, tempo);
                } else if (opcao.equals("historia")) {
                    Map<Integer, Long> resposta = hpc.historia();
                    for(Integer id : resposta.keySet()){
                        String resp = "Tarefa: " + id + "Tempo: " + resposta.get(id);
                        out.writeUTF(resp);
                    }
                }
            }           
        }        
    }
}

