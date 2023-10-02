import bank.accounts.BankAccount;
import bank.accounts.CreditAccount;
import bank.parties.Customer;

class Main{

    public static void main(String[] args){
        Customer consumer = new Customer(new BankAccount(200, "checking", null));
        consumer.addAccount(new BankAccount(50, "savings", null));
        consumer.addAccount(new CreditAccount(50, null));
        consumer.addAccount(new CreditAccount(50, null));
        consumer.addAccount(new CreditAccount(50, null));
        consumer.pay(150);
        consumer.pay(220);
        consumer.pay(5000);
    }
}