import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class NegativeInputException extends Exception {
    
    public String getMessage() {
        return "Negative input is not allowed.";
    }
}
 
class InsufficientBalanceException extends Exception {
   
    public String getMessage() {
        return "Insufficient balance.";
    }
}

class InvalidAtmPinException extends Exception {
    
    public String getMessage() {
        return "Invalid ATM pin. Try again.";
    }
}

class UnknownException extends Exception {
    
    public String getMessage() {
        return "An unknown error occurred.";
    }
}


class ATM {
    int balance;
    String logFile = "atm_log.txt";
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    ATM(int balance) {
        this.balance = balance;
    }

    void logTransaction(String message) {
        try (FileWriter fw = new FileWriter(logFile, true)) {
            String time = LocalDateTime.now().format(dtf);
            fw.write(time + " - " + message + "\n");
        } catch (IOException e) {
            System.out.println("Error writing log: " + e.getMessage());
        }
    }

    void checkBalance() {
        System.out.println("Your balance is: " + balance);
    }

    void deposit(int amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
        logTransaction("Deposited: " + amount + ", Balance: " + balance);
        checkBalance();
    }

    void withdraw(int amount) {
        balance -= amount;
        System.out.println("Withdrawn successfully: " + amount);
        logTransaction("Withdrawn: " + amount + ", Balance: " + balance);
        checkBalance();
    }

    void printMiniStatement() {
        System.out.println("----- Mini Statement -----");
        System.out.println("Current Balance: ₹" + balance);
        System.out.println("Transaction Log:");
        try (BufferedReader br = new BufferedReader(new FileReader(logFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("No transactions to show.");
        }
    }
}


public class ATMApp {
    private static final int CORRECT_PIN = 4632;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int attempts = 0;

        // PIN Verification
        while (attempts < 3) {
            System.out.println("Welcome to the ATM service!");
            System.out.println("Please insert your ATM card.");
            System.out.println("You have " + (3 - attempts) + " attempt(s) left.");
            System.out.print("Please enter your ATM pin: ");

            int userPin;
            try {
                userPin = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter numeric pin.");
                sc.next(); 
                attempts++;
                continue;
            }

            if (userPin == CORRECT_PIN) {
                System.out.println("PIN accepted.");
                break;
            } else {
                System.out.println("Invalid ATM pin.");
                attempts++;
            }
        }

        if (attempts == 3) {
            System.out.println("Card blocked. Please contact your bank.");
            return;
        }

        // Start ATM operations
        int initialBalance = 0;
        while (true) {
            System.out.print("Enter initial balance: ");
            try {
                initialBalance = sc.nextInt();
                if (initialBalance < 0) {
                    throw new NegativeInputException();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid amount.");
                sc.next(); 
            } catch (NegativeInputException e) {
                System.out.println(e.getMessage());
            }
        }

        ATM account = new ATM(initialBalance);

        
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Mini Statement");
            System.out.println("5. Exit");

            int choice;
            try {
                choice = sc.nextInt();
                if (choice < 1 || choice > 5) throw new InputMismatchException();
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                sc.next(); 
                continue;
            }

            switch (choice) {
                case 1:
                    account.checkBalance();
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ");
                    try {
                        int depositAmount = sc.nextInt();
                        if (depositAmount < 0) {
                            throw new NegativeInputException();
                        }
                        account.deposit(depositAmount);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        sc.next(); 
                    } catch (NegativeInputException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    try {
                        int withdrawAmount = sc.nextInt();
                        if (withdrawAmount < 0) {
                            throw new NegativeInputException();
                        }
                        if (withdrawAmount > account.balance) {
                            throw new InsufficientBalanceException();
                        }
                        account.withdraw(withdrawAmount);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        sc.next(); 
                    } catch (NegativeInputException | InsufficientBalanceException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    account.printMiniStatement();
                    break;

                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
 
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
