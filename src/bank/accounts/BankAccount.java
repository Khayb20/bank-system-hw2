package bank.accounts;

public class BankAccount extends Account{

    public BankAccount(double amount, String name, Account nextAccount){
        super(nextAccount, name);
        balance = amount;
    }
    
    public void makeDeposit(double amount){
        balance += amount;
    }
    
    public void deduct(double amount) throws InsufficientFundsException{
        if(balance >= amount){
            balance -= amount;
            System.out.println(balance);
        }
        else{
            throw new InsufficientFundsException("You have insufficient funds in your accounts");
        }
    }

}
