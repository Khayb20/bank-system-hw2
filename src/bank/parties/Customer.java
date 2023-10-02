package bank.parties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bank.CheckProcessor;

import bank.accounts.Account;
import bank.accounts.BankAccount;
import bank.accounts.CreditAccount;
import bank.accounts.InsufficientFundsException;


/**
 * The Customer class represents a bank customer who can have multiple accounts,
 * including checking, savings, and credit accounts. It implements the PayingParty
 * interface to handle payments and maintain balances across accounts.
 * @author Kobby Asante-Ansong
 */
public class Customer implements PayingParty {
    
    /** The primary checking account associated with the customer. */
    private BankAccount checking = new BankAccount(0, "checking", null);

    /** List of savings accounts associated with the customer. */
    private List<BankAccount> savingsAccounts = new ArrayList<>();

    /** List of credit accounts associated with the customer. */
    private List<CreditAccount> creditAccounts = new ArrayList<>();

    /** A map to track the original balances of all accounts for reset purposes. */
    private Map<Account, Double> balanceTracker = new HashMap<>();


    /**
     * Constructs a Customer with an initial checking account.
     *
     * @param checking The initial checking account associated with the customer.
     */
    public Customer(BankAccount checking){
        this.checking = checking;
    }

    /**
     * Initiates a payment transaction by deducting the specified amount from the
     * customer's accounts. Handles insufficient funds using the CheckProcessor and
     * an InsufficientFundsException.
     *
     * @param amount The amount to be paid.
     */
    public void pay(double amount){
        storeBalances();
        if(CheckProcessor.processCheck(checking, amount)){
            System.out.println("SUCCESS!!");
            return;
        }else{
            Account previousAccount = checking;
            for(BankAccount account: savingsAccounts){
                if(previousAccount.balance > 0){
                    amount -= previousAccount.balance;
                    previousAccount.balance = 0;
                }
                if(CheckProcessor.processCheck(account, amount)){
                    System.out.println("SUCCESS!!");
                    return;
                }
                previousAccount = account;
            }
            for(Account account: creditAccounts){
                if(previousAccount instanceof BankAccount){
                    if(previousAccount.balance > 0){
                        amount -= previousAccount.balance;
                        previousAccount.balance = 0;
                    }
                }else{
                    if(((CreditAccount) previousAccount).limitLeft > 0){
                        amount -= ((CreditAccount) previousAccount).limitLeft;
                        ((CreditAccount) previousAccount).limitLeft = 0;
                        ((CreditAccount) previousAccount).balance = ((CreditAccount) previousAccount).limit;
                    }
                }
                try{
                    account.deduct(amount);
                    System.out.println("SUCCESS!!");
                    return;
                }catch (InsufficientFundsException e){
                    System.out.println(e.getMessage());
                    previousAccount = account;
                }
            }
        }
        resetAccounts();
    }

    /**
     * Stores the current balances of all accounts in the balanceTracker map for
     * later use in resetting account balances.
     */
    private void storeBalances(){
        balanceTracker.put(checking, checking.balance);
        for(BankAccount account: savingsAccounts){
            balanceTracker.put(account, account.balance);
        }
        for(CreditAccount account: creditAccounts){
            balanceTracker.put(account, account.balance);
        }
    }

    /**
     * Resets all account balances and credit limits to their original values stored
     * in the balanceTracker map.
     * @author Kobby Asante-Ansong
     */
    private void resetAccounts(){
        for(Map.Entry<Account, Double> entry : this.balanceTracker.entrySet()){
            Account account = entry.getKey();
            Double originalBalance = entry.getValue();
            account.balance = originalBalance;
            if(account instanceof CreditAccount){
                ((CreditAccount) account).limitLeft = ((CreditAccount) account).limit - account.balance;
            }
        }
    }

    /**
     * Adds an account to the customer's list of accounts, either a savings or
     * credit account.
     *
     * @param account The account to be added.
     */
    public void addAccount(Account account){
        if(account.name.equals("savings")){
            savingsAccounts.add((BankAccount) account);
        }else
            creditAccounts.add((CreditAccount) account);
    }

}
