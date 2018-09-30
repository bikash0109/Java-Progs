/*
 * Program Name: RitBank.java
 *
 * Version :  1
 *
 * @author: Bikash Roy
 * @author: Tanay Bhardwaj
 *
 *
 * This program uses the concept of inheritance through Abstract class and Interfaces. Also uses type casting concept
 * to achieve a simple banking operations.
 * Also have created enums to set the account types.
 */

import java.util.*;

// Enums for Account types
enum AccountType {
    Savings, Checking, Credit
}

// Interface for all AssetAccounts
interface AssetAccounts {
    void calculateInterestGiven(int time);
}

// Interface for all LiabilityAccounts
interface LiabilityAccounts {
    void calculateInterestEarned(int time);
}

// Abstract class for Bank Account, and its basic methods
abstract class BankAccount {
    public String accountHolder;
    public int accountNumber;
    public double accountBalance;
    public double creditAmount;

    BankAccount(String accountHolder, Double accountBalance) {
        this.accountHolder = accountHolder;
        this.accountNumber = new Random().nextInt(100000000);
        if (accountType() == AccountType.Credit)
            this.creditAmount = accountBalance;
        else
            this.accountBalance = accountBalance;
    }

    public abstract AccountType accountType();

    public void deposit(double amount) {
        if (accountType() == AccountType.Credit)
            this.creditAmount -= amount;
        else
            this.accountBalance += amount;
    }

    public void credit(double amount) {
        if (accountType() == AccountType.Credit)
            this.creditAmount += amount;
        else
            this.accountBalance -= amount;
    }

    public void display_details() {
        System.out.println("Depositor Name :" + this.accountHolder);
        System.out.println("Account Number : " + this.accountNumber);
        System.out.println("Account Type : " + this.accountType());
        if (this.accountType() == AccountType.Credit)
            System.out.println("Amount owed : " + this.creditAmount);
        else
            System.out.println("Account Balance : " + this.accountBalance);
    }
}

// Class for Savings Account
class SavingsAccount extends BankAccount implements AssetAccounts {
    SavingsAccount(String accountHolder, Double accountBalance) {
        super(accountHolder, accountBalance);
    }

    @Override
    public AccountType accountType() {
        return AccountType.Savings;
    }

    // Calculate the interest for given time period.
    @Override
    public void calculateInterestGiven(int time) {
        double CI = this.accountBalance *
                (Math.pow((1 + 8.25 / 100), time));
        System.out.println(this.accountHolder + " earned $" + CI + " in interest in " + time +
                " months.\n Account balance is now. " + (this.accountBalance + CI));
    }
}

// Class for Checking Account
class CheckingAccount extends BankAccount implements AssetAccounts {
    CheckingAccount(String accountHolder, Double accountBalance) {
        super(accountHolder, accountBalance);
    }

    @Override
    public AccountType accountType() {
        return AccountType.Checking;
    }

    // Calculate the interest for given time period.
    @Override
    public void calculateInterestGiven(int time) {
        double CI = this.accountBalance *
                (Math.pow((1 + 6.25 / 100), time));
        this.accountBalance = this.accountBalance + CI;
        System.out.println(this.accountHolder + " earned $" + CI + " in interest in " + time +
                " months.\n Account balance is now. " + (this.accountBalance));
    }
}

// Class for Credit Account
class CreditAccount extends BankAccount implements LiabilityAccounts {
    CreditAccount(String accountHolder, Double accountBalance) {
        super(accountHolder, accountBalance);
    }

    @Override
    public AccountType accountType() {
        return AccountType.Credit;
    }

    // Calculate the interest monthly for credit account, hence the loop.
    @Override
    public void calculateInterestEarned(int time) {
        for (int i = 0; i < time; i++) {
            double CI = this.creditAmount *
                    (Math.pow((1 + 14.25 / 100), i));
            this.creditAmount = this.creditAmount + CI;
            System.out.println(this.accountHolder + " is charged with $" + CI + " in interest after " + (i + 1) +
                    " month.\n Account balance is now. " + this.creditAmount);
            System.out.println("Credit Card bill sent to customer " + this.accountHolder + ", for account number " +
                    this.accountNumber + " in the amount of " + this.creditAmount);
        }
    }
}

public class RitBank {
    public static void main(String args[]) {
        Vector<BankAccount> accounts = new Vector<>();
        Scanner input = new Scanner(System.in);
        int userChoice;
        boolean quit = false;
        do {
            System.out.println("1.   time - pass certain amount of time\n" +
                    "2.   open - open a new account\n" +
                    "3.   close - close an account\n" +
                    "4.   credit - credit an account\n" +
                    "5.   debit - debit an account\n" +
                    "6.   summary - display current bank accounts\n" +
                    "7.   exit - exit program");
            System.out.print("Enter Your Choice : ");
            userChoice = input.nextInt();
            switch (userChoice) {
                case 1: // Calculate interest for time period.
                    System.out.println("Enter the period of time: ");
                    int time = input.nextInt();
                    if (accounts.size() > 0) {
                        for (BankAccount account : accounts) {
                            if (account.accountType() == AccountType.Credit) {
                                LiabilityAccounts la = (LiabilityAccounts) account;
                                la.calculateInterestEarned(time);
                            } else {
                                AssetAccounts aa = (AssetAccounts) account;
                                aa.calculateInterestGiven(time);
                            }
                        }
                    } else {
                        System.out.println("No accounts created yet.");
                    }
                    break;
                case 2: //open account
                    Double depositAmount;
                    String accountHolder;
                    System.out.print("Enter account Type : \n");
                    System.out.println("1. Savings Account\n" + "2. Checking Account\n" + "3. Credit Account\n");
                    int choice = input.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.print("Enter your Name : ");
                            accountHolder = new Scanner(System.in).nextLine();
                            System.out.println("\nEnter your initial deposit amount ");
                            depositAmount = input.nextDouble();
                            BankAccount savingsAccount = new SavingsAccount(accountHolder, depositAmount);
                            System.out.println("**************************");
                            System.out.println("\nYour Account Details\nDon,t forget your account number\n");
                            savingsAccount.display_details();
                            accounts.add(savingsAccount);
                            break;
                        case 2:
                            System.out.print("Enter your Name : ");
                            accountHolder = new Scanner(System.in).nextLine();
                            System.out.println("\nEnter your initial deposit amount ");
                            depositAmount = input.nextDouble();
                            BankAccount checkingAccount = new CheckingAccount(accountHolder, depositAmount);
                            System.out.println("**************************");
                            System.out.println("\nYour Account Details\nDon,t forget your account number\n");
                            checkingAccount.display_details();
                            accounts.add(checkingAccount);
                            break;
                        case 3:
                            System.out.print("Enter your Name : ");
                            accountHolder = new Scanner(System.in).nextLine();
                            System.out.println("\nEnter your amount of credit ");
                            Double creditAmmount = input.nextDouble();
                            BankAccount creditAccount = new CreditAccount(accountHolder, creditAmmount);
                            System.out.println("**************************");
                            System.out.println("\nYour Account Details\nDon,t forget your account number\n");
                            creditAccount.display_details();
                            accounts.add(creditAccount);
                            break;
                    }
                    System.out.println("**************************");
                    break;

                case 3: // close account
                    if (accounts.size() > 0) {
                        System.out.print("Enter your account Number : ");
                        int accountNumberToBeDeleted = input.nextInt();
                        BankAccount accountToBeRemoved = null;
                        for (BankAccount account : accounts) {
                            if (account.accountNumber == accountNumberToBeDeleted) {
                                System.out.println("**************************");
                                System.out.println("\nYour Account details, which is being deleted.\n");
                                account.display_details();
                                accountToBeRemoved = account;
                            }
                        }
                        if (accountToBeRemoved != null) {
                            accounts.remove(accountToBeRemoved);
                            System.out.println("**************************");
                        } else {
                            System.out.println("Account doesn't exists");
                        }
                    } else {
                        System.out.println("No accounts created yet!");
                    }

                    break;

                case 4: // Credit an account
                    if (accounts.size() > 0) {
                        System.out.print("Enter your account Number : ");
                        int accountNumberToBeCredited = input.nextInt();
                        for (BankAccount account : accounts) {
                            if (account.accountNumber == accountNumberToBeCredited) {
                                System.out.println("**************************");
                                System.out.println("\nYour Account details, which is being Credited.\n");
                                account.display_details();
                                System.out.println("\nEnter your amount of credit :");
                                Double creditAmount = input.nextDouble();
                                account.credit(creditAmount);
                                account.display_details();
                                System.out.println("**************************");
                            } else {
                                System.out.println("Account doesn't exists");
                            }
                        }
                    } else {
                        System.out.println("No accounts created yet!");
                    }
                    break;

                case 5: // debit an account
                    if (accounts.size() > 0) {
                        System.out.print("Enter your account Number : ");
                        int accountNumberToBeDebited = input.nextInt();

                        for (BankAccount account : accounts) {
                            if (account.accountNumber == accountNumberToBeDebited) {
                                System.out.println("**************************");
                                System.out.println("\nYour Account details, which is being Debited.\n");
                                account.display_details();
                                System.out.println("\nEnter your amount of debit :");
                                Double debitAmount = input.nextDouble();
                                account.deposit(debitAmount);
                                account.display_details();
                                System.out.println("**************************");
                            } else {
                                System.out.println("Account doesn't exists");
                            }
                        }
                    } else {
                        System.out.println("No accounts created yet!");
                    }
                    break;

                case 6: // display all info
                    if (accounts.size() > 0) {
                        for (BankAccount account : accounts) {
                            System.out.println("**************************");
                            account.display_details();
                            System.out.println("**************************");
                        }
                    } else {
                        System.out.println("No accounts created yet!");
                    }
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