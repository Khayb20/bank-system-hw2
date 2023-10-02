package bank.accounts;

public class CreditAccount extends Account{

    public double limit;
    public double limitLeft;

    public CreditAccount(double limit, Account nextAccount){
        super(nextAccount, "credit");
        this.limit = limit;
    }
        
    public void deduct(double amount) throws InsufficientFundsException{
        try {
            charge(amount);
        } catch (InsufficientFundsException e) {
            // System.out.println(e.getMessage());
            throw new InsufficientFundsException("You have insufficient funds in your accounts");
        }
    }
    
    public void charge(double amount) throws InsufficientFundsException{

        if(limit >= amount){
            balance += amount;
            limitLeft = limit - balance;
            System.out.println(limitLeft);
        }else{
            throw new InsufficientFundsException("You have insufficient funds in your accounts");
        }
        
    }

}
