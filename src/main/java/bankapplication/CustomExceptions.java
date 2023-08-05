package bankapplication;
public class CustomExceptions {
    public static class InvalidAmountWithdrawalException extends Exception {
        public InvalidAmountWithdrawalException() {
            super("Insufficient Balance Available");
        }

        public InvalidAmountWithdrawalException(String message) {
            super(message);
        }


    }
    // Other custom exceptions can be added here
}