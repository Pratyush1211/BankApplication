package bankapplication;

import java.util.*;

public class UserInterface {

    public static void main(String args[]) {
        
        char userchoice;
        Scanner sc = new Scanner(System.in);
        MongoDBClient dbClient = new MongoDBClient();

        dbClient.DBconnection();

        for (;;) {
            System.out.println("");
            System.out.println(
                    "####################################### Welcome to the Bank Application system! ########################################");
            System.out.println();
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------");
            System.out.println(
                    "-------------------------------------------------- a. Add Account ------------------------------------------------------");
            System.out.println(
                    "-------------------------------------------------- d. Delete Account ---------------------------------------------------");
            System.out.println(
                    "-------------------------------------------------- l. List All Accounts ------------------------------------------------");

            System.out.println(
                    "-------------------------------------------------- q. Exit  ------------------------------------------------------------");
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------");
            System.out.print("Please select the option to Continue : !");

            userchoice = sc.nextLine().toCharArray()[0];

            switch (userchoice) {

                case 'a':
                    System.out.print("\nName: ");
                    String name = sc.nextLine();

                    System.out.print("Balance: ");
                    double balance = sc.nextDouble();

                    sc.nextLine();
                    System.out.print("Type: ");
                    String type = sc.nextLine();

                    if (type.equals("Current")) {
                        BankAccount regularAccount = new BankAccount(name, balance);
                        System.out.println("Do you want to save this account in DataBase? (y/n)");
                        String choice = sc.nextLine();
                        if (choice.equals("y")) {
                            dbClient.insertDocumentsToCollection(regularAccount);
                        } else {
                            break;
                        }

                    } else if (type.equals("Savings")) {
                        SavingsAccount savingsAccount = new SavingsAccount(name, balance, type);
                        System.out.println("Do you want to save this account in DataBase? (y/n)");
                        String choice = sc.nextLine();
                        if (choice.equals("y")) {
                            dbClient.insertDocumentsToCollection(savingsAccount);
                        } else {
                            break;
                        }
                    } else {
                        System.out.println("Invalid type, retry");
                        break;
                    }
                    System.out.println("Account created successfully and saved in DataBase\n");
                    break;

                case 'l':
                    System.out.println("\nDisplaying accounts:\n");
                    dbClient.getDocumentsFromCollection();
                    break;

                case 'd':
                    System.out.println("Enter the Account name you wish to delete:");
                    String accountName = sc.nextLine();
                    dbClient.deleteDocumentFromCollection(accountName);
                    break;

                case 'q':
                    sc.close();
                    dbClient.closeConnection();
                    System.out.println("Thank You for using our Service !");
                    break;

                default:
                    System.out.println("Invalid userchoice");
                    break;

            }

            if (userchoice == 'q') {
                break;
            }

        }

    }
}
