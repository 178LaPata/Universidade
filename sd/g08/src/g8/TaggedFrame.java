package g8;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TaggedFrame {
    public Condition con;
    public Deque<TaggedConnection.Frame> mensagens;

    public TaggedFrame(ReentrantLock l){
        this.con = l.newCondition();
        this.mensagens = new ArrayDeque<>();
    }
}
