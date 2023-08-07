package bankapplication;

import bankapplication.CustomExceptions.InvalidAmountWithdrawalException;

public class BankAccount{

    private String accountName;
    private double accountBalance;
    private String accountType = AccountTypes.CURRENT.getAccountType(); // default value for account type be "CURRENT"


    public BankAccount(String accountName, double accountBalance) {
        this.accountName = accountName;
        this.accountBalance = accountBalance;
    }

    public BankAccount(String accountName, double accountBalance, String accountType) {
        this.accountName = accountName;
        this.accountBalance = accountBalance;
        this.accountType = accountType;

    }

    // setters for Balance available in account

    public void setcurrentBalance(double currentAmount){
        this.accountBalance = currentAmount ;
    }

    public void deposit(double depositAmount) {
        this.accountBalance += depositAmount;
    }


    // Withdrawing 
    public void withdraw(double withdrawAmount) {
        try {
            if (this.accountBalance - withdrawAmount < 0) {
                throw new InvalidAmountWithdrawalException();
            }
            this.accountBalance -= withdrawAmount;
        } catch (InvalidAmountWithdrawalException e) {
            System.out.println(" Error Encountered:" + e.getMessage());
        }
    }



    //getters

    public String getAccountName() {
        return this.accountName;
    }

    public double getAccountBalance() {
        return this.accountBalance;
    }

    public String getAccountType() {
        return this.accountType;
    }

}
