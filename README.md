# ATM_SIMULATION_USING_CORE_JAVA
💳 ATM Application in Java
This is a simple Java-based console ATM simulation project that demonstrates Object-Oriented Programming, Exception Handling, File I/O, and Java Time API. It provides users with features such as PIN authentication, balance check, deposit, withdrawal, and viewing mini statements with timestamps.

📌 Features
✅ Secure 3-attempt PIN login system

💰 Deposit and withdraw money

📄 Mini-statement feature (reads from atm_log.txt)

📊 Balance checking

📂 Transaction logging with timestamp

🔐 Custom exceptions for:

Negative input

Invalid ATM PIN

Insufficient balance

Unknown exception

🛠️ Technologies Used
Java SE (Core Java)

Java Exception Handling

Java I/O (FileReader, FileWriter, BufferedReader)

Java Time API (LocalDateTime, DateTimeFormatter)

OOP Concepts: Classes, Objects, Inheritance, Abstraction

🚀 Getting Started
✅ Prerequisites
Java JDK 8 or above

Any Java IDE (e.g., IntelliJ IDEA, Eclipse) or command-line terminal

▶️ How to Run
1. Clone the Repository
bash
Copy
Edit
git clone https://github.com/yourusername/ATM-Java-App.git
cd ATM-Java-App
2. Compile and Run
bash
Copy
Edit
javac ATMApp.java
java ATMApp
🧪 Sample Output
bash
Copy
Edit
Welcome to the ATM service!
Please insert your ATM card.
You have 3 attempt(s) left.
Please enter your ATM pin: 4632
PIN accepted.
Enter initial balance: 5000

Choose an option:
1. Check Balance
2. Deposit
3. Withdraw
4. Mini Statement
5. Exit
📂 Log File Output (atm_log.txt)
txt
Copy
Edit
2025-07-26 11:45:03 - Deposited: 500, Balance: 5500
2025-07-26 11:46:10 - Withdrawn: 1000, Balance: 4500
📌 Custom Exceptions Implemented
Exception Class	Description
NegativeInputException	Raised if user inputs a negative value
InsufficientBalanceException	Raised if withdrawal exceeds balance
InvalidAtmPinException	For incorrect PIN attempts
UnknownException	Placeholder for future unknown issues

🧑‍💻 Author
Mahendra Bella
📧 mahendrabella55@gmail.com
📍 Kadapa, India

📜 License
This project is open-source and available under the MIT License.

