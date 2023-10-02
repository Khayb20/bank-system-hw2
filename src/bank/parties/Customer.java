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

/** */
public class Customer implements PayingParty {
    
    private BankAccount checking = new BankAccount(0, "checking", null);
    private List<BankAccount> savingsAccounts = new ArrayList<>();
    private List<CreditAccount> creditAccounts = new ArrayList<>();
    private Map<Account, Double> balanceTracker = new HashMap<>();

    public Customer(BankAccount checking){
        this.checking = checking;
    }

    /**
     * 
     */
    public void pay(double amount){
        storeBalances();
        if(CheckProcessor.processCheck(checking, amount)){
            System.out.println("SUCCESS!!");
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

    private void storeBalances(){
        balanceTracker.put(checking, checking.balance);
        for(BankAccount account: savingsAccounts){
            balanceTracker.put(account, account.balance);
        }
        for(CreditAccount account: creditAccounts){
            balanceTracker.put(account, account.balance);
        }
    }

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

    public void addAccount(Account account){
        if(account.name.equals("savings")){
            savingsAccounts.add((BankAccount) account);
        }else
            creditAccounts.add((CreditAccount) account);
    }

}
