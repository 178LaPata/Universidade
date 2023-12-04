package ex2;

public class Deposit implements Runnable{
    private Bank bank;

    public Deposit(Bank bank){
        this.bank = bank;
    }

    public void run(){
        for(long i = 0; i < 1000; i++){
            this.bank.deposit(100);
        }
    }
    
}
