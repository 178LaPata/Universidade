package g8;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FramedConnection implements AutoCloseable {
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private ReentrantLock sendLock = new ReentrantLock();
    private ReentrantLock receiveLock = new ReentrantLock();

    public FramedConnection(Socket socket) throws IOException{
        this.socket = socket;
        this.inputStream = new DataInputStream(socket.getInputStream());
        this.outputStream = new DataOutputStream(socket.getOutputStream());
    }

    public void send(byte[] data) throws IOException{
        this.sendLock.lock();
        try {
            this.outputStream.writeInt(data.length);
            this.outputStream.write(data);
            this.outputStream.flush();
        } finally {
            this.sendLock.unlock();
        }
    }

    public byte[] receive() throws IOException{
        this.receiveLock.lock();
        try {
            int size = this.inputStream.readInt();
            byte[] receivedData = new byte[size];
            this.inputStream.readFully(receivedData);
            return receivedData;
        } finally {
            this.receiveLock.lock();
        }
    }

    public void close() throws IOException{
        this.sendLock.lock();
        this.receiveLock.lock();
        try {
            this.outputStream.close();
            this.inputStream.close();
            this.socket.close();
        } finally {
            this.sendLock.unlock();
            this.receiveLock.unlock();
        }
    }
}
