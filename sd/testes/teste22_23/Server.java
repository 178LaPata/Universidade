package teste22_23;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Server {
    private ServerSocket socket;
    private Cache cache;

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.startServer();
    }

    public void startServer() throws IOException{
        socket = new ServerSocket(12345);
        while(true){
            Socket ss = socket.accept();
            Thread t = new Thread(new ClientHandler(ss, cache));
            t.start();
        }
    }

    public class ClientHandler implements Runnable {
        private Socket socket;
        private Cache cache;
        private DataOutputStream out;
        private DataInputStream in;
        private Map<Integer, Integer> naoLida; 
        private int nrOperacoes;

        public ClientHandler(Socket socket, Cache cache) throws IOException{
            this.socket = socket;
            this.cache = cache;
            try {
                this.out = new DataOutputStream(socket.getOutputStream());
                this.in = new DataInputStream(socket.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void run() {
            String command;
            while (true){
                try {
                    command = in.readUTF();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if(command.equals("ler")){
                    int key = 0;
                    try {
                        key = in.readInt();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    byte [] resposta = cache.get(key);
                    if(resposta != null) {
                       naoLida.put(key, -1);
                    }
                    try {
                        out.write(resposta);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else if (command.equals("escrever")) {
                    int chave1;
                    try {
                        chave1 = in.readInt();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    byte[] value;
                    try {
                        value = new byte[in.readInt()];
                        in.readFully(value);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        cache.put(chave1, value);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                for(int chave : naoLida.keySet()){
                    if(naoLida.get(chave) == nrOperacoes){
                        cache.evict(chave);
                        naoLida.remove(chave);
                    }
                    else{
                        naoLida.put(chave, naoLida.get(chave) + 1);
                    }
                }
            }
        }
    }
}
