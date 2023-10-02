package bank;

import bank.accounts.BankAccount;
import bank.accounts.InsufficientFundsException;

/**
 * The CheckProcessor class is responsible for processing checks by deducting the specified
 * amount from a bank account. If there are insufficient funds in the account, it catches
 * the InsufficientFundsException and handles it appropriately.
 * @author Kobby Asante-Ansong
 */
public class CheckProcessor {
    
    /**
     * Processes a check by deducting the specified amount from the given bank account.
     *
     * @param name The bank account from which the amount should be deducted.
     * @param amount  The amount to be deducted from the account.
     * @return True if the check is successfully processed (amount deducted), false otherwise.
     */
    public static boolean processCheck(BankAccount name, double amount){
        try {
            name.deduct(amount);
            return true;
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
