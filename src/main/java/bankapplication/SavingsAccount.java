package bankapplication;

import bankapplication.CustomExceptions.InvalidAmountWithdrawalException;

public class SavingsAccount extends BankAccount {

    public SavingsAccount(String accountName, double accountBalance, String accountType) {
        super(accountName, accountBalance, accountType);
    }

    @Override
    public void withdraw(double withdrawAmount) {
        try {
            if ( this.getAccountBalance() < withdrawAmount ) {
                throw new InvalidAmountWithdrawalException();
            }
            if (this.getAccountBalance() - withdrawAmount < 100) {
                throw new InvalidAmountWithdrawalException(" Minimum Amount in account is not maintained ie Rs 100 ");
            }

            double currentAmount = this.getAccountBalance();
            this.setcurrentBalance(currentAmount - withdrawAmount);

        } catch (InvalidAmountWithdrawalException e) {
            System.out.println(" Error Encountered: " + e.getMessage());
        }
    }

}
