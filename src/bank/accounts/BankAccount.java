package bank.accounts;

/**
 * The BankAccount class represents a specific type of bank account that extends the abstract
 * Account class. It allows deposits and deductions, handling insufficient funds using an
 * InsufficientFundsException.
 * @author Kobby Asante-Ansong
 */
public class BankAccount extends Account{

    /**
     * Constructs a BankAccount with an initial balance, a name, and a successor account.
     *
     * @param amount       The initial balance of the account.
     * @param name         The name associated with the account.
     * @param nextAccount  The successor account linked to this account.
     */
    public BankAccount(double amount, String name, Account nextAccount){
        super(nextAccount, name);
        balance = amount;
    }
    
    /**
     * Makes a deposit into the bank account, increasing the balance by the specified amount.
     *
     * @param amount The amount to be deposited into the account.
     */
    public void makeDeposit(double amount){
        balance += amount;
    }
    
    /**
     * Deducts the specified amount from the bank account's balance, handling insufficient
     * funds using an InsufficientFundsException.
     *
     * @param amount The amount to be deducted from the account.
     * @throws InsufficientFundsException If there are insufficient funds in the account
     *                                    to cover the deduction.
     */
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
