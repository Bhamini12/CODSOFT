import java.io.*;
import java.lang.*;
import java.util.*;
public class ATM {

    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void displayMenu() {
        System.out.println("Welcome to ATM!");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    public void performTransaction(int choice) {
        Scanner scanner = new Scanner(System.in);
        switch (choice) {
            case 1:
                System.out.print("Enter withdrawal amount: ");
                double amount = scanner.nextDouble();
                withdraw(amount);
                break;
            case 2:
                System.out.print("Enter deposit amount: ");
                amount = scanner.nextDouble();
                deposit(amount);
                break;
            case 3:
                checkBalance();
                break;
            case 4:
                System.out.println("Thank you for using ATM!");
                break;
            default:
                System.out.println("Invalid Choice!");
        }
    }

    private void withdraw(double amount) {
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful!");
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    private void deposit(double amount) {
        account.deposit(amount);
        System.out.println("Deposit successful!");
    }

    private void checkBalance() {
        System.out.println("Your current balance: " + account.getBalance());
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000); // Initial balance
        ATM atm = new ATM(account);

        while (true) {
            atm.displayMenu();
            int choice = new Scanner(System.in).nextInt();
            atm.performTransaction(choice);
            if (choice == 4) {
                break;
            }
        }
    }
}

class BankAccount {

    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public synchronized boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public synchronized void deposit(double amount) {
        balance += amount;
    }

    public double getBalance() {
        return balance;
    }
}
