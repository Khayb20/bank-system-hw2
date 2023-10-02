package bank.accounts;

/**
 * The CreditAccount class represents a specific type of bank account that extends the abstract
 * Account class. It allows charging and deducting funds while handling insufficient funds using
 * an InsufficientFundsException. A credit limit is associated with this account.
 * @author Kobby Asante-Ansong
 */
public class CreditAccount extends Account{

    /** The credit limit for this account. */
    public double limit;

    /** The remaining credit limit available for use. */
    public double limitLeft;

    /**
     * Constructs a CreditAccount with a specified credit limit and a successor account.
     *
     * @param limit        The credit limit for the account.
     * @param nextAccount  The successor account linked to this credit account.
     */
    public CreditAccount(double limit, Account nextAccount){
        super(nextAccount, "credit");
        this.limit = limit;
        this.limitLeft = limit;
    }
    
    /**
     * Deducts the specified amount from the credit account by charging it. It handles
     * insufficient funds using an InsufficientFundsException.
     *
     * @param amount The amount to be deducted from the account.
     * @throws InsufficientFundsException If there are insufficient funds or insufficient
     *                                    credit limit to cover the charge.
     */
    public void deduct(double amount) throws InsufficientFundsException{
        try {
            charge(amount);
        } catch (InsufficientFundsException e) {
            throw new InsufficientFundsException("You have insufficient funds in your accounts");
        }
    }
    
    /**
     * Charges the specified amount to the credit account, increasing the balance and
     * decreasing the available credit limit. It handles insufficient funds using
     * an InsufficientFundsException.
     *
     * @param amount The amount to be charged to the account.
     * @throws InsufficientFundsException If there are insufficient funds or insufficient
     *                                    credit limit to cover the charge.
     */
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
