package g8;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import g8.TaggedConnection.Frame;
public class Demultiplexer implements AutoCloseable {
    private TaggedConnection tc;
    private Map<Integer, TaggedFrame> mensagensDeque;
    private ReentrantLock lock = new ReentrantLock();
    public Demultiplexer(TaggedConnection conn) {
        this.tc = conn;
        this.mensagensDeque = new HashMap<>();
    }
    public void start() {
        new Thread(() -> {
            try {
                while (true){
                    TaggedConnection.Frame f = this.tc.receive();
                    this.lock.lock();
                    try {
                        if(!mensagensDeque.containsKey(f.tag)){
                            this.mensagensDeque.put(f.tag, new TaggedFrame(this.lock));
                        }
                        this.mensagensDeque.get(f.tag).mensagens.add(f);
                        this.mensagensDeque.get(f.tag).con.signal();
                    } finally {
                        this.lock.unlock();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
    public void send(Frame frame) throws IOException {
        this.tc.send(frame);
    }
    public void send(int tag, byte[] data) throws IOException {
        this.tc.send(tag, data);
    }
    public byte[] receive(int tag) throws IOException, InterruptedException {
        this.lock.lock();
        try {
            while (this.mensagensDeque.get(tag).mensagens.isEmpty()){
                this.mensagensDeque.get(tag).con.wait();
            }
            Frame f = this.mensagensDeque.get(tag).mensagens.element();
            return f.data;
        } finally {
            this.lock.unlock();
        }
    }

    public void close() throws IOException {
        this.tc.close();
    }
}