package g8;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class TaggedConnection implements AutoCloseable {

    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private ReentrantLock sendLock = new ReentrantLock();
    private ReentrantLock receiveLock = new ReentrantLock();

    public static class Frame {
        public final int tag;
        public final byte[] data;

        public Frame(int tag, byte[] data) {
            this.tag = tag;
            this.data = data;
        }
    }

    public TaggedConnection(Socket socket) throws IOException {
        this.socket = socket;
        this.inputStream = new DataInputStream(socket.getInputStream());
        this.outputStream = new DataOutputStream(socket.getOutputStream());
    }

    public void send(Frame frame) throws IOException {
        this.sendLock.lock();
        try {
            this.outputStream.writeInt(frame.tag);
            this.outputStream.writeInt(frame.data.length);
            this.outputStream.write(frame.data);
            this.outputStream.flush();
        } finally {
            this.sendLock.unlock();
        }
    }

    public void send(int tag, byte[] data) throws IOException {
        this.sendLock.lock();
        try {
            this.outputStream.writeInt(tag);
            this.outputStream.writeInt(data.length);
            this.outputStream.write(data);
            this.outputStream.flush();
        } finally {
            this.sendLock.unlock();
        }
    }

    public Frame receive() throws IOException {
        this.receiveLock.lock();
        try {
            int tag = this.inputStream.readInt();
            int len = this.inputStream.readInt();
            byte[] data = new byte[len];
            this.inputStream.readFully(data);
            return new Frame(tag, data);
        } finally {
            this.receiveLock.unlock();
        }
    }

    public void close() throws IOException {
        this.sendLock.lock();
        this.receiveLock.lock();
        try {
            this.inputStream.close();
            this.outputStream.close();
            this.socket.close();
        } finally {
            this.sendLock.unlock();
            this.receiveLock.unlock();
        }
    }
}