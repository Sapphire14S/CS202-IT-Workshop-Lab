import java.util.*;

class Account {
    private int accountNumber;
    private String accountType;
    private String serviceBranchIFSC;
    private float minimumBalance;
    private float availableBalance;
    private int customerID;
    private String customerName;

    private static int totalAccountCreated = 0;

    public Account() {
        totalAccountCreated++;
    }

    public void setDetails(Scanner sc) {

        System.out.println("Enter Account Number: ");
        accountNumber = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Account Type: ");
        accountType = sc.nextLine();
        System.out.println("Enter Service Branch IFSC: ");
        serviceBranchIFSC = sc.nextLine();
        System.out.println("Enter Customer ID; ");
        customerID = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Customer Name: ");
        customerName = sc.nextLine();

    }

    public void getDetails(int accNo) {
        if (this.accountNumber == accNo) {
            System.out.println("Account Number: " + accountNumber);
            System.out.println("Account Type: " + accountType);
            System.out.println("Service Branch IFSC: " + serviceBranchIFSC);
            System.out.println("Minimum Balance: " + minimumBalance);
            System.out.println("Available Balance: " + availableBalance);
            System.out.println("Customer ID: " + customerID);
            System.out.println("Customer Name: " + customerName);
        }
    }

    public void mupdateDetails(int accNo, Scanner sc) {
        if (this.accountNumber == accNo) {
            System.out.println("1. Update A/C Type: ");
            System.out.println("2. Update Details: ");
            System.out.println("3. Update Minimum Balance: ");
            System.out.println("4. Update Customer Name: ");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter new A/C Type: ");
                    accountType = sc.nextLine();
                    break;
                
                case 2:
                    System.out.println("Enter new IFSC: ");
                    serviceBranchIFSC = sc.nextLine();
                    break;

                case 3:
                    System.out.println("Enter new Minimum Balance: ");
                    minimumBalance = sc.nextFloat();
                    break;

                case 4:
                    System.out.println("Enter new Customer Name: ");
                    customerName = sc.nextLine();
                    break;

                default:
                    System.out.println("Invalid Choice!");
                    break;
            }

        }
    }

    public float getBalance(int accNo) {
        if (this.accountNumber == accNo) {
            return availableBalance;
        }

        return -1;
    }

    public void deposit(int accNo, float amount) {
        if (this.accountNumber == accNo) {
            availableBalance += amount;
            System.out.println("Deposited: " + amount + "\n New Balance: " + availableBalance);
        }
    }

    public void withdraw(int accNo, float amount) {
        if (this.accountNumber == accNo) {
            if (availableBalance - amount >= minimumBalance) {
                availableBalance -= amount;
                System.out.println("Withdrawn: " + amount + "\n New Balance: " + availableBalance);
            } else {
                System.out.println("Insufficient Balance! ");
            }
        }
    }

    public static int totalAccount() {
        return totalAccountCreated;
    }

    public static void compare(Account a1, Account a2) {
        if (a1.availableBalance > a2.availableBalance) {
            System.out.println("Account " + a1.accountNumber + " has higher balance."); 
            a1.getDetails(a1.accountNumber);
        } else if (a2.availableBalance > a1.availableBalance) {
            System.out.println("Account " + a2.accountNumber + " has higher balance.");
            a2.getDetails(a2.accountNumber);
        } else {
            System.out.println("Both accounts have equal balance.");
        }
    }
}

public class banking {
    public static void main(String[] args) {
        
    }
}
