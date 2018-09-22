import java.util.*;

enum AccountType {
    Savings, Checking, Credit
}

interface AssetAccounts{};
interface LiabilityAccounts{};

abstract class Account {
    public String accountHolder;
    public int accountNumber;
    public double accountBalance;
    public double creditAmount;

    Account(String accountHolder, Double accountBalance) {
        this.accountHolder = accountHolder;
        this.accountNumber = new Random().nextInt(100000000);
        if (accountType() == AccountType.Credit)
            this.creditAmount = accountBalance;
        else
            this.accountBalance = accountBalance;
    }

    public abstract AccountType accountType();

    public void deposit(double amount) {
        accountBalance += amount;
    }

    public void credit(double amount) {
        creditAmount += amount;
    }

    public double withdraw(double withdrawAmount) {
        accountBalance -= withdrawAmount;
        return accountBalance;
    }

    public void display_details() {
        System.out.println("Depositor Name :" + accountHolder);
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Type : " + accountType());
        if (accountType() == AccountType.Credit)
            System.out.println("Amount owed : " + creditAmount);
        else
            System.out.println("Account Balance : " + accountBalance);
    }
}

class SavingsAccount extends Account implements AssetAccounts {
    SavingsAccount(String accountHolder, Double accountBalance) {
        super(accountHolder, accountBalance);
    }

    @Override
    public AccountType accountType() {
        return AccountType.Savings;
    }
}

class CheckingAccount extends Account implements AssetAccounts {
    CheckingAccount(String accountHolder, Double accountBalance) {
        super(accountHolder, accountBalance);
    }

    @Override
    public AccountType accountType() {
        return AccountType.Checking;
    }
}

class CreditAccount extends Account implements LiabilityAccounts {
    CreditAccount(String accountHolder, Double accountBalance) {
        super(accountHolder, accountBalance);
    }

    @Override
    public AccountType accountType() {
        return AccountType.Credit;
    }
}

public class Bank {
    public static void main(String args[]) {
        Vector<Account> accounts = new Vector<>();
        Scanner in = new Scanner(System.in);
        int userChoice;
        boolean quit = false;
        do {
            System.out.println("  1.   time - pass certain amount of time\n" +
                    "  2.   open - open a new account\n" +
                    "  3.   close - close an account\n" +
                    "  4.   credit - credit an account\n" +
                    "  5.   debit - debit an account\n" +
                    "  6.   summary - display current bank accounts\n" +
                    "  7.   exit - exit program");
            System.out.print("Enter Your Choice : ");
            userChoice = in.nextInt();
            switch (userChoice) {
                case 1:
                    break;
                case 2: //open account
                    Double depositAmmount;
                    System.out.println("1. Savings Account\n" + "2. Checking Account\n" + "3. Credit Account\n");
                    System.out.print("Enter your Name : ");
                    String accountHolder = new Scanner(System.in).nextLine();
                    System.out.print("Enter account Type : ");
                    int choice = in.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("\nEnter your initial deposit amount ");
                            depositAmmount = in.nextDouble();
                            Account savingsAccount = new SavingsAccount(accountHolder, depositAmmount);
                            System.out.println("**************************");
                            System.out.println("\nYour Account Details\nDon,t forget your account number\n");
                            savingsAccount.display_details();
                            accounts.add(savingsAccount);
                            break;
                        case 2:
                            System.out.println("\nEnter your initial deposit amount ");
                            depositAmmount = in.nextDouble();
                            Account checkingAccount = new CheckingAccount(accountHolder, depositAmmount);
                            System.out.println("**************************");
                            System.out.println("\nYour Account Details\nDon,t forget your account number\n");
                            checkingAccount.display_details();
                            accounts.add(checkingAccount);
                            break;
                        case 3:
                            System.out.println("\nEnter your amount of credit ");
                            Double creditAmmount = in.nextDouble();
                            Account creditAccount = new CreditAccount(accountHolder, creditAmmount);
                            System.out.println("**************************");
                            System.out.println("\nYour Account Details\nDon,t forget your account number\n");
                            creditAccount.display_details();
                            accounts.add(creditAccount);
                            break;
                    }
                    System.out.println("**************************");
                    break;

                case 3: // close account
                    System.out.print("Enter your account Number : ");
                    int accountNumberToBeDeleted = in.nextInt();
                    for (Account account : accounts) {
                        if (account.accountNumber == accountNumberToBeDeleted) {
                            System.out.println("**************************");
                            System.out.println("\nYour Account details, which is being deleted.\n");
                            account.display_details();
                            accounts.remove(account);
                            System.out.println("**************************");
                        }
                    }
                    break;

                case 4: // Credit an account
                    System.out.print("Enter your account Number : ");
                    int accountNumberToBeCredited = in.nextInt();

                    for (Account account : accounts) {
                        if (account.accountNumber == accountNumberToBeCredited && account.accountType() == AccountType.Credit) {
                            System.out.println("**************************");
                            System.out.println("\nYour Account details, which is being Credited.\n");
                            account.display_details();
                            System.out.println("\nEnter your amount of credit :");
                            Double creditAmount = in.nextDouble();
                            System.out.println("Previous credit amount : " + account.creditAmount);
                            account.credit(creditAmount);
                            account.display_details();
                            System.out.println("**************************");
                        }
                    }
                    break;

                case 5: // debit an account

                    System.out.print("Enter your account Number : ");
                    int accountNumberToBeDebited = in.nextInt();

                    for (Account account : accounts) {
                        if (account.accountNumber == accountNumberToBeDebited && account.accountType() != AccountType.Credit) {
                            System.out.println("**************************");
                            System.out.println("\nYour Account details, which is being Debited.\n");
                            account.display_details();
                            System.out.println("\nEnter your amount of debit :");
                            Double debitAmount = in.nextDouble();
                            System.out.println("Previous debit amount : " + account.accountBalance);
                            account.deposit(debitAmount);
                            account.display_details();
                            System.out.println("**************************");
                        }
                    }
                    break;

                case 6: // display all info
                    if(accounts.size() > 0) {
                        for (Account account : accounts) {
                            System.out.println("**************************");
                            account.display_details();
                            System.out.println("**************************");
                        }
                    }
                    else
                        System.out.println("No accounts created yet !");
                    break;
                case 7:
                    quit = true;
                    break;
                default:
                    System.out.println("Wrong Choice.");
                    break;
            }
            System.out.println("\n");
        } while (!quit);
        System.out.println("Thanks !");
    }
}