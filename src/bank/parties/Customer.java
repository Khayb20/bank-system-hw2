package bank.parties;

import bank.accounts.Account;
import bank.accounts.BankAccount;

public class Customer implements PayingParty {

    private Account checking = new BankAccount();

    public void pay(double amount){}

    public void addAccount(Account name){}

}
