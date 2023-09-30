package bank.accounts;

public abstract class Account {

    protected Account successor;

    public Account(Account nextAccount){
        this.successor = nextAccount;
    }

    public void setNext(Account nextAccount){
        this.successor = nextAccount;
    }

    public abstract void deduct(double amount) throws InsufficientFundsException;

}
