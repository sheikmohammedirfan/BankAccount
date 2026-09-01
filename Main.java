import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a simple bank account with deposit, withdrawal,
 * and transaction history features.
 */
 class BankAccount {
    private final String accountNumber;
    private final String accountHolderName;
    private double balance;
    private final List<String> transactionHistory;

    public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        log("Account opened with balance: " + formatAmount(initialBalance));
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        balance += amount;
        log("Deposited: " + formatAmount(amount));
        System.out.println("Deposited " + formatAmount(amount) + ". New balance: " + formatAmount(balance));
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds. Current balance: " + formatAmount(balance));
            log("Failed withdrawal attempt: " + formatAmount(amount) + " (insufficient funds)");
            return;
        }
        balance -= amount;
        log("Withdrew: " + formatAmount(amount));
        System.out.println("Withdrew " + formatAmount(amount) + ". New balance: " + formatAmount(balance));
    }

    public void transferTo(BankAccount other, double amount) {
        if (other == null) {
            System.out.println("Target account does not exist.");
            return;
        }
        if (amount <= 0) {
            System.out.println("Transfer amount must be positive.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds for transfer.");
            return;
        }
        this.balance -= amount;
        other.balance += amount;
        this.log("Transferred " + formatAmount(amount) + " to account " + other.accountNumber);
        other.log("Received " + formatAmount(amount) + " from account " + this.accountNumber);
        System.out.println("Transferred " + formatAmount(amount) + " to " + other.accountHolderName);
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void printStatement() {
        System.out.println("\n---- Statement for " + accountHolderName + " (Acct #" + accountNumber + ") ----");
        for (String entry : transactionHistory) {
            System.out.println(entry);
        }
        System.out.println("Current Balance: " + formatAmount(balance));
        System.out.println("---------------------------------------------------\n");
    }

    private void log(String entry) {
        transactionHistory.add(entry);
    }

    private String formatAmount(double amount) {
        return String.format("$%.2f", amount);
    }

    @Override
    public String toString() {
        return "BankAccount{accountNumber='" + accountNumber + "', holder='" + accountHolderName +
                "', balance=" + formatAmount(balance) + "}";
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Welcome to Simple Java Bank ===");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter initial deposit amount: ");
        double initial = readDouble(scanner);

        BankAccount account = new BankAccount("ACC" + (int) (Math.random() * 100000), name, initial);

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter deposit amount: ");
                    account.deposit(readDouble(scanner));
                    break;
                case "2":
                    System.out.print("Enter withdrawal amount: ");
                    account.withdraw(readDouble(scanner));
                    break;
                case "3":
                    System.out.println("Current balance: $" + String.format("%.2f", account.getBalance()));
                    break;
                case "4":
                    account.printStatement();
                    break;
                case "5":
                    running = false;
                    System.out.println("Thank you for banking with us, " + name + "!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Check Balance");
        System.out.println("4. Print Statement");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    private static double readDouble(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid number, please enter again: ");
            }
        }
    }
}