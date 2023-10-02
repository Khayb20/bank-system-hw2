package bank.accounts;

/**
 * The InsufficientFundsException class is a custom exception that is thrown when an
 * attempt is made to perform a financial operation, with an account
 * that has insufficient funds to cover the operation.
 * @author Kobby Asante-Ansong
 */
public class InsufficientFundsException extends Exception{

    /**
     * Constructs an InsufficientFundsException with a specific error message.
     *
     * @param message A message describing the reason for the insufficient funds exception.
     */
    public InsufficientFundsException(String message) {
        super(message);
    }

}
