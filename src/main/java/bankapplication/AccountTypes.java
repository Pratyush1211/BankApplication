package BankingApplication;

public enum AccountTypes {
    CURRENT("Current"),
    SAVINGS("Savings");
    
    private String accountType;
    
    private AccountTypes(String accountType) {
        this.accountType = accountType;
    }
    
    public String getAccountType() {
        return this.accountType;
    }
}
