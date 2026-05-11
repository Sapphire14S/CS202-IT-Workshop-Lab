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
        System.out.print("Enter A/C Number: ");
        accountNumber = sc.nextInt();
        sc.nextLine(); 
        System.out.print("Enter A/C Type: ");
        accountType = sc.nextLine();
        System.out.print("Enter Service Branch IFSC: ");
        serviceBranchIFSC = sc.nextLine();
        System.out.print("Enter Minimum Balance: ");
        minimumBalance = sc.nextFloat();
        System.out.print("Enter Available Balance: ");
        availableBalance = sc.nextFloat();
        System.out.print("Enter Customer ID: ");
        customerID = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Customer Name: ");
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

    public void updateDetails(int accNo, Scanner sc) {
        if (this.accountNumber == accNo) {
            System.out.println("1. Update A/C Type");
            System.out.println("2. Update IFSC");
            System.out.println("3. Update Minimum Balance");
            System.out.println("4. Update Customer Name");
            System.out.print("\n\nEnter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter new A/C Type: ");
                    accountType = sc.nextLine();
                    // String aType = sc.nextLine();
                    // if (aType == "Work") accountType = aType;
                    // else if (aType == "Savings") accountType = aType;
                    // else if (aType == "Business") accountType = aType;
                    // else sout("Invalid! ");
                    break;

                case 2:
                    System.out.print("Enter new IFSC: ");
                    serviceBranchIFSC = sc.nextLine();
                    break;

                case 3:
                    System.out.print("Enter new Minimum Balance: ");
                    minimumBalance = sc.nextFloat();
                    break;

                case 4:
                    System.out.print("Enter new Customer Name: ");
                    customerName = sc.nextLine();
                    break;

                default:
                    System.out.println("Invalid choice!");

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
            System.out.println("Deposited " + amount + ". New Balance: " + availableBalance);
        }
    }

    public void withdraw(int accNo, float amount) {
        if (this.accountNumber == accNo) {
            if (availableBalance - amount >= minimumBalance) {
                availableBalance -= amount;
                System.out.println("Withdrawn: " + amount + "\nNew Balance: " + availableBalance);
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

// Main class
public class bankingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Account> accounts = new ArrayList<>(); 


        while (true) {
            System.out.println("\n     <Menu>    ");
            System.out.println("> 1. Create Account");
            System.out.println("> 2. Update Details");
            System.out.println("> 3. Get Details");
            System.out.println("> 4. Deposit");
            System.out.println("> 5. Withdraw");
            System.out.println("> 6. Total Accounts");
            System.out.println("> 7. Compare Accounts");
            System.out.println("> 8. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                  
                    Account newAcc = new Account();
                    newAcc.setDetails(sc);
                    accounts.add(newAcc);
                    break;

                case 2:
                    System.out.print("Enter Account Number to update: ");
                    int uAcc = sc.nextInt();
                 
                    for (Account acc : accounts) {
                        acc.updateDetails(uAcc, sc);
                    }
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    int gAcc = sc.nextInt();
                    
                    for (Account acc : accounts) {
                        acc.getDetails(gAcc);
                    }
                    break;
                
                case 4:
                    System.out.print("Enter Account Number: ");
                    int dAcc = sc.nextInt();
                    System.out.print("Enter Amount: ");
                    float damt = sc.nextFloat();
    
                    for (Account acc : accounts) {
                        acc.deposit(dAcc, damt);
                    }
                    break;

                case 5:
                    System.out.print("Enter Account Number: ");
                    int wAcc = sc.nextInt();
                    System.out.print("Enter Amount: ");
                    float wamt = sc.nextFloat();

                    for (Account acc : accounts) {
                        acc.withdraw(wAcc, wamt);
                    }
                    break;

                case 6:
                    System.out.println("Total Accounts Created: " + Account.totalAccount());
                    break;

                case 7:
                    System.out.print("Enter first Account index (0-" + (accounts.size() - 1) + "): ");
                    int idx1 = sc.nextInt();
                    System.out.print("Enter second Account index (0-" + (accounts.size() - 1) + "): ");
                    int idx2 = sc.nextInt();
                    if (idx1 < accounts.size() && idx2 < accounts.size()) {
                        Account.compare(accounts.get(idx1), accounts.get(idx2));
                    } else {
                        System.out.println("Invalid indices!");
                    }
                    break;

                case 8:
                    System.out.println("Exiting...\n\n");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!\n\n");

            }
        }
    }
}
