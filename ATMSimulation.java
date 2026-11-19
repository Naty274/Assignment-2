
import java.util.ArrayList;
import java.util.Scanner;

public class ATMSimulation {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Initial account details
        int correctPIN = 1234;
        int enteredPIN;
        int attempts = 0;
        int maxAttempts = 3;

        double balance = 5000.00;

        // Store transaction history
        ArrayList<String> transactions = new ArrayList<>();

        // PIN Authentication
        System.out.println("===== WELCOME TO ATM =====");

        while (attempts < maxAttempts) {
            System.out.print("Enter your PIN: ");
            enteredPIN = readInt(input);

            if (enteredPIN == correctPIN) {
                System.out.println("Login successful!");
                break;
            } else {
                attempts++;
                System.out.println("Incorrect PIN!");

                if (attempts < maxAttempts) {
                    System.out.println("Attempts remaining: "
                            + (maxAttempts - attempts));
                }
            }
        }

        // Check if maximum attempts reached
        if (attempts == maxAttempts) {
            System.out.println("Maximum attempts reached.");
            System.out.println("Your account is locked.");
            input.close();
            return;
        }

        // Main ATM Menu
        int choice;

        do {
            System.out.println("\n===== ATM MAIN MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Transactions");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            choice = readInt(input);

            switch (choice) {

                case 1:
                    // Check balance
                    System.out.println("Your current balance is: "
                            + balance);
                    break;

                case 2:
                    // Deposit money
                    System.out.print("Enter amount to deposit: ");
                    double deposit = readDouble(input);

                    if (deposit > 0) {
                        balance += deposit;

                        transactions.add("Deposited: " + deposit);

                        System.out.println("Deposit successful!");
                        System.out.println("New balance: " + balance);
                    } else {
                        System.out.println("Invalid deposit amount.");
                    }
                    break;

                case 3:
                    // Withdraw money
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawal = readDouble(input);

                    if (withdrawal <= 0) {
                        System.out.println("Invalid withdrawal amount.");
                    } else if (withdrawal <= balance) {
                        balance -= withdrawal;

                        transactions.add("Withdrawn: " + withdrawal);

                        System.out.println("Withdrawal successful!");
                        System.out.println("Remaining balance: "
                                + balance);
                    } else {
                        System.out.println("Insufficient balance!");
                    }
                    break;

                case 4:
                    // View transaction history
                    System.out.println("\n===== TRANSACTION HISTORY =====");

                    if (transactions.isEmpty()) {
                        System.out.println("No transactions found.");
                    } else {
                        for (int i = 0; i < transactions.size(); i++) {
                            System.out.println((i + 1) + ". "
                                    + transactions.get(i));
                        }
                    }
                    break;

                case 5:
                    // Exit program
                    System.out.println("Thank you for using our ATM.");
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }

        } while (choice != 5);

        input.close();
    }

    private static int readInt(Scanner input) {
        while (!input.hasNextInt()) {
            input.next();
            System.out.print("Invalid input. Enter a whole number: ");
        }

        int value = input.nextInt();
        input.nextLine();
        return value;
    }

    private static double readDouble(Scanner input) {
        while (!input.hasNextDouble()) {
            input.next();
            System.out.print("Invalid input. Enter an amount: ");
        }

        double value = input.nextDouble();
        input.nextLine();
        return value;
    }
}