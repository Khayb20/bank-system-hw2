package bank.accounts;

public class BankAccount extends Account{

    protected double balance;

    public BankAccount(double amount, Account nextAccount){
        super(nextAccount);
        balance = amount;
    }
    
    public void makeDeposit(double amount){
        balance += amount;
    }
    
    public void deduct(double amount) throws InsufficientFundsException{
        if(balance > amount){
            balance -= amount;
        }else if(successor != null){
            successor.deduct(amount);
        }else{
            throw new InsufficientFundsException("You have insufficient funds in your accounts");
        }
    }

}
