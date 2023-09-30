package bank.accounts;

public class CreditAccount extends Account{

    protected double limit;

    public CreditAccount(double amount, Account nextAccount){
        super(nextAccount);
        limit = amount;
    }
        
    public void deduct(double amount){}

    public void charge(double amount){}

}
