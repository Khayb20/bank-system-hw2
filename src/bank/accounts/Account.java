package bank.accounts;

public abstract class Account {

    public double balance;
    public Account successor;
    public String name;

    public Account(Account nextAccount, String name){
        this.successor = nextAccount;
        this.name = name;
    }

    public void setNext(Account nextAccount){
        this.successor = nextAccount;
    }

    public abstract void deduct(double amount) throws InsufficientFundsException;

}
