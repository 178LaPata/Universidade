import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

class Bank {

    private ReentrantLock lockBank;

    private static class Account {
        private ReentrantLock lockAccount;
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

    private Map<Integer, Account> map = new HashMap<Integer, Account>();
    private int nextId = 0;

    // create account and return account id
    public int createAccount(int balance) {
        this.lockBank.lock();
        Account c = new Account(balance);
        int id = nextId;
        nextId += 1;
        map.put(id, c);
        this.lockBank.unlock();
        return id;
    }

    // close account and return balance, or 0 if no such account
    public int closeAccount(int id) {
        this.lockBank.lock();
        Account c = map.remove(id);
        if (c == null) {
            this.lockBank.unlock();
            return 0;
        }
        c.lockAccount.lock();
        this.lockBank.unlock();
        int saldo = c.balance;
        c.lockAccount.unlock();
        return saldo;
    }

    // account balance; 0 if no such account
    public int balance(int id) {
        this.lockBank.lock();
        Account c = map.get(id);
        if (c == null){
            this.lockBank.unlock();
            return 0;
        }
        c.lockAccount.lock();
        this.lockBank.unlock();
        int balance = c.balance;
        c.lockAccount.unlock();
        return balance;
    }

    // deposit; fails if no such account
    public boolean deposit(int id, int value) {
        this.lockBank.lock();
        Account c = map.get(id);
        if (c == null){
            this.lockBank.unlock();
            return false;
        }
        this.lockBank.unlock();
        c.lockAccount.lock();
        boolean deposit = c.deposit(value);
        c.lockAccount.unlock();
        return deposit;
    }

    // withdraw; fails if no such account or insufficient balance
    public boolean withdraw(int id, int value) {
        this.lockBank.lock();
        Account c = map.get(id);
        if (c == null) {
            this.lockBank.unlock();
            return false;
        }
        this.lockBank.unlock();
        c.lockAccount.lock();
        boolean withdraw = c.withdraw(value);
        c.lockAccount.unlock();
        return withdraw;
    }

    // transfer value between accounts;
    // fails if either account does not exist or insufficient balance
    public boolean transfer(int from, int to, int value) {
        if (from == to) {
            // Transferring to the same account is a no-op, or you might want to throw an exception.
            return false;
        }

        boolean retirar;
        boolean depositar;
        int minID = Math.min(from, to);
        int maxID = Math.max(from, to);
        Account AcMin, AcMax;
        this.lockBank.lock();

        AcMin = map.get(minID);
        AcMax = map.get(maxID);

        if (AcMin == null || AcMax ==  null) {
            this.lockBank.unlock();
            return false;
        }

        this.lockBank.unlock();
        AcMin.lockAccount.lock();
        AcMax.lockAccount.lock();

        if(minID == from){
            retirar = AcMin.withdraw(value);
            depositar = AcMax.deposit(value);
        }
        else {
            retirar = AcMax.deposit(value);
            depositar = AcMin.withdraw(value);
        }

        AcMin.lockAccount.unlock();
        AcMax.lockAccount.unlock();

        return retirar && depositar;
    }

    // sum of balances in set of accounts; 0 if some does not exist
    public int totalBalance(int[] ids) {
        int total = 0;
        List<Account> lockedAccount = new ArrayList<>();

        this.lockBank.lock();
        for(int i : ids){
            Account c = map.get(i);
            c.lockAccount.lock();
            lockedAccount.add(c);
        }
        for (Account acc : lockedAccount) {
            total += acc.balance();
            acc.lockAccount.unlock();
        }
        this.lockBank.unlock();
        return total;
    }

}