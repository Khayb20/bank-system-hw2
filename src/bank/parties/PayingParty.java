package bank.parties;

/**
 * The PayingParty interface represents an entity that is capable of making payments.
 * Classes that implement this interface are expected to provide an implementation for
 * the 'pay' method to define how payments are processed by the party.
 * @author Kobby Asante-Ansong
 */
public interface PayingParty {

    /**
     * Initiates a payment transaction with the specified amount.
     *
     * @param amount The amount to be paid by the paying party.
     */
    public void pay(double amount);

}
