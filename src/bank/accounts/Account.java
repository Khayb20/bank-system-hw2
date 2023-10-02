package bank.accounts;

/**
 * The abstract Account class serves as the base class for various types of bank accounts.
 * It defines common properties and methods that are shared among all account types.
 * @author Kobby Asante-Ansong
 */
public abstract class Account {

    /** The balance of the account. */
    public double balance;

    /** The successor account, used in case of insufficient funds for CoR. */
    public Account successor;

    /** The name associated with the account. */
    public String name;

    /**
     * Constructs an Account with a successor account and a name.
     *
     * @param nextAccount The successor account to be linked to this account.
     * @param name        The name associated with the account.
     */
    public Account(Account nextAccount, String name){
        this.successor = nextAccount;
        this.name = name;
    }
    
    /**
     * Sets the successor account for this account.
     *
     * @param nextAccount The successor account to be linked to this account.
     */
    public void setNext(Account nextAccount){
        this.successor = nextAccount;
    }

    /**
     * Deducts the specified amount from the account's balance. Subclasses must implement
     * this method to define their specific deduction logic.
     *
     * @param amount The amount to be deducted from the account.
     * @throws InsufficientFundsException If there are insufficient funds in the account 
     *                                                          to cover the deduction.
     */
    public abstract void deduct(double amount) throws InsufficientFundsException;

}
