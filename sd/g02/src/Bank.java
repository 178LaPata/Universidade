import java.util.concurrent.locks.ReentrantLock;

public class Bank {

    private static class Account {
        ReentrantLock lock = new ReentrantLock();
        private int balance;
        Account(int balance) { this.balance = balance; }
        int balance() { return balance; }
        boolean deposit(int value) {
            balance += value;
            return true;
        }
        boolean withdraw(int value) {
            if (value > balance)
                return false;
            balance -= value;
            return true;
        }
    }

    // Bank slots and vector of accounts
    private int slots;
    private Account[] av;

    public Bank(int n) {
        slots=n;
        av=new Account[slots];
        for (int i=0; i<slots; i++) av[i]=new Account(0);
    }

    // Account balance
    public int balance(int id) {
        try {
            av[id].lock.lock();
            if (id < 0 || id >= slots)
                return 0;
            return av[id].balance();
        } finally {
            av[id].lock.unlock();
        }


    }

    // Deposit
    public boolean deposit(int id, int value) {
        try {
            av[id].lock.lock();
            if (id < 0 || id >= slots)
                return false;
            return av[id].deposit(value);
        } finally {
            av[id].lock.unlock();
        }

    }

    // Withdraw; fails if no such account or insufficient balance
    public boolean withdraw(int id, int value) {
        try {
            av[id].lock.lock();
            if (id < 0 || id >= slots)
                return false;
            return av[id].withdraw(value);
        } finally {
            av[id].lock.unlock();
        }
    }

    /* tambem esta correto
    boolean transfer (int from, int to, int value){
        try {
            av[id].lock.lock();
            if(from<0 || from >= slots || to<0 || to >= slots)
                return false;
            av[from].withdraw(value);
            return av[to].deposit(value);
        } finally {
            av[id].lock.unlock();
        }
    }
    */

    boolean transfer (int from, int to, int value){
        int a1 = Math.min(from, to);
        int a2 = Math.max(from, to);
        try{
            av[a1].lock.lock();
            av[a2].lock.lock();
            if(this.withdraw(from, value)){
                return this.deposit(to, value);
            }
        } finally {
            av[a1].lock.unlock();
            av[a2].lock.unlock();
        }
        return false;
    }

    // falta mudar os locks
    int totalBalance(){
        int sum=0;
        //lock.lock();
        for(int i=0; i<slots; i++) sum += av[i].balance();
        //lock.unlock();
        return sum;
    }
}
