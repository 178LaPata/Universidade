import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

public class EchoServer {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(12345);
        Data data = new Data();
        while (true) {
            Socket socket = ss.accept();
            Thread s = new Thread(new ServerWorker(socket,data));
            s.start();
        }
    }
    public static class Data {
        int soma = 0;
        int num = 0;
        ReentrantLock l = new ReentrantLock();

        public void adicionar_soma(int n){
            try {
                l.lock();
                soma += n;
                num++;
            } finally {
                l.unlock();
            }
        }
        public double calcular_media(){
            try {
                l.lock();
                if (num == 0) return 0;
                return (double) soma/num;
            }
            finally {
                l.unlock();
            }
        }
    }

    // cria uma thread para cada cliente
    private static class ServerWorker implements Runnable {
        private Socket socket;
        private Data data;

        ServerWorker(Socket socket, Data data) {
            this.socket = socket;
            this.data = data;
        }

        public void run() {
            try {
                while (true) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream());
                    String line;
                    while ((line = in.readLine()) != null) {
                        if(line.matches("\\d+")){
                            int n = Integer.parseInt(line);
                            data.adicionar_soma(n);
                            out.println(data.soma);
                            out.flush();
                        } else if(line.matches("EDF")){
                            out.println(data.calcular_media());
                            out.flush();
                            break;
                        }
                    }
                    socket.shutdownOutput();
                    socket.shutdownInput();
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
