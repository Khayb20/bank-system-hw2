package bank;

import bank.accounts.BankAccount;
import bank.accounts.InsufficientFundsException;

public class CheckProcessor {
    
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
