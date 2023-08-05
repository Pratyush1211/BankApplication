package bankapplication;

import java.util.*;


public class UserInterface {

    public static void main(String args[]) {
        char userchoice;
        List<BankAccount> accountsList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Choose an option:\n(a) Add Account\n(l) Display Accounts\n(q) Quit");
            userchoice = scanner.next().toCharArray()[0];
            String bufferText = scanner.nextLine();

            switch (userchoice) {

                case 'a':
                    System.out.print("\nName: ");
                    String name = scanner.nextLine();
                    System.out.print("Balance: ");
                    double balance = scanner.nextDouble();
                    bufferText = scanner.nextLine();
                    System.out.print("Type: ");
                    String type = scanner.nextLine();
                    if (type.equals("Current")) {
                        BankAccount regularAccount = new BankAccount(name, balance);
                        accountsList.add(regularAccount);
                    } else if (type.equals("Savings")) {
                        SavingsAccount savingsAccount = new SavingsAccount(name, balance, type);
                        accountsList.add(savingsAccount);
                    } else {
                        System.out.println("Invalid type, retry");
                        break;
                    }
                    System.out.println("Account created successfully\n");
                    break;

                case 'l':
                    System.out.println("\nDisplaying accounts:\n");
                    int counter = 1;
                    for (BankAccount tempAccount : accountsList) {
                        System.out.println("Account Number: " + counter);
                        System.out.println("Account Name: " + tempAccount.getAccountName());
                        System.out.println("Account Balance: " + tempAccount.getAccountBalance());
                        System.out.println("Account Type: " + tempAccount.getAccountType());
                        System.out.println();
                        counter++;
                    }
                    break;

                case 'q':
                    break;

                default:
                    System.out.println("Invalid userchoice");
                    break;
            }
        } while (userchoice != 'q');
        System.out.println("Exited successfully");
        scanner.close();
    }
}
