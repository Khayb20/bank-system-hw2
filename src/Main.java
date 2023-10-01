import bank.accounts.BankAccount;
import bank.accounts.CreditAccount;
import bank.parties.Customer;

class Main{

    public static void main(String[] args){
        Customer consumer = new Customer(new BankAccount(200, "checking", null));
        consumer.addAccount(new BankAccount(50, "savings", null));
        consumer.addAccount(new BankAccount(10, "savings", null));
        consumer.addAccount(new CreditAccount(50, null));
        consumer.pay(320);
        // consumer.pay(210);
        System.out.println("Hello world!");
    }
}