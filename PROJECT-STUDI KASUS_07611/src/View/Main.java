package View;

import Entity.Transaksi;
import Entity.User;
import Model.HistoryTransaksi;
import Model.HistoryUser;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HistoryUser historyUser = new HistoryUser();
        HistoryTransaksi historyTransaksi = new HistoryTransaksi();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the E-Wallet Nothing!");

        while (true) {
            System.out.println("\nPilihan :");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Masukkan Pilihan :");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    User newUser = registerUser(scanner, historyUser);
                    if (newUser != null) {
                        System.out.println("Registration successful!");
                    }
                    break;
                    case 2:
                        User loggedInUser = login(scanner, historyUser);
                        if (loggedInUser != null) {
                                System.out.println("Login successful!");

                                while (true) {
                                    System.out.println("\nChoose a transaction type:");
                                    System.out.println("1. Transfer");
                                    System.out.println("2. View history");
                                    System.out.println("3. Logout");

                                    int transactionChoice = scanner.nextInt();

                                    switch (transactionChoice) {
                                        case 1:
                                            transfer(scanner, loggedInUser, historyTransaksi);
                                            break;
                                        case 2:
                                            viewHistory(loggedInUser, historyTransaksi);
                                            break;
                                        case 3:
                                            System.out.println("Logout successful!");
                                            loggedInUser = null;
                                            break;
                                        default:
                                            System.out.println("Invalid option. Please try again.");
                                    }
                                }
                            }
                            break;
                        case 3:
                            System.out.println("Goodbye!");
                            scanner.close();
                            System.exit(0);
                        default:
                            System.out.println("Invalid option. Please try again.");
            }
        }
    }

            private static User registerUser(Scanner scanner, HistoryUser historyUser) {
                System.out.print("Enter a username: ");
                String username = scanner.next();

                User existingUser = historyUser.seacrhUserByUsername(username);
                if (existingUser != null) {
                    System.out.println("Username already exists. Please choose another username.");
                    return null;
                }

                System.out.print("Enter a password: ");
                String password = scanner.next();

                System.out.print("Enter an initial balance: ");
                double balance = scanner.nextDouble();

                User newUser = new User(0, username, password, balance);
                return historyUser.saveUser(newUser);
            }

            private static User login(Scanner scanner, HistoryUser historyUser) {
                System.out.print("Enter your username: ");
                String username = scanner.next();

                System.out.print("Enter your password: ");
                String password = scanner.next();

                User user = historyUser.seacrhUserByUsername(username);
                if (user == null || !user.getPassword().equals(password)) {
                    System.out.println("Invalid. Please try again.");
                    return null;
                }

                return user;
            }

            private static void transfer(Scanner scanner, User user, HistoryTransaksi historyTransaksi) {
                System.out.print("Enter the Penerima username: ");
                String recipientUsername = scanner.next();

                User recipient = HistoryUser.seacrhUserByUsername(recipientUsername);
                if (recipient == null) {
                    System.out.println("Penerima not found. Please try again.");
                    return;
                }

                System.out.print("Jumlah Tranfers : ");
                double amount = scanner.nextDouble();

                if (amount <= 0 || amount > user.getBalance()) {
                    System.out.println("Invalid. Please try again.");
                    return;
                }

                user.setBalance(user.getBalance() - amount);
                recipient.setBalance(recipient.getBalance() + amount);

                Transaksi transferTransaction = new Transaksi(0, user, recipient, amount, "transfer");
                historyTransaksi.saveTransaksi(transferTransaction);

                System.out.printf("Transfer successful! New balance for %s: %.2f%n", user.getUsername(), user.getBalance());
            }

            private static void viewHistory(User user, HistoryTransaksi historyTransaksi) {
                List<Transaksi> transactions = historyTransaksi.getTrasaksiByUser(user);

                if (transactions.isEmpty()) {
                    System.out.println("No transaction history found.");
                    return;
                }

                System.out.println("\nTransaction History:");
                for (Transaksi transaction : transactions) {
                    System.out.printf("%s: %.2f%n", transaction.getType(), transaction.getJmlh());
                }
    }
}