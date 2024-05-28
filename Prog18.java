public class BankAccount {
    private int balance;

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
        System.out.println("Current Balance: " + balance);
    }

    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
            System.out.println("Current Balance: " + balance);
        } else {
            System.out.println("Insufficient funds for withdrawal.");
        }
    }
}

class BankOperations implements Runnable {
    private BankAccount account;
    private boolean isDeposit;
    private int amount;

    public BankOperations(BankAccount account, boolean isDeposit, int amount) {
        this.account = account;
        this.isDeposit = isDeposit;
        this.amount = amount;
    }

    @Override
    public void run() {
        if (isDeposit) {
            account.deposit(amount);
        } else {
            account.withdraw(amount);
        }
    }
}

class Prog18 {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);

        // Simulate deposit and withdrawal operations using threads
        Thread depositThread = new Thread(new BankOperations(account, true, 500));
        Thread withdrawThread = new Thread(new BankOperations(account, false, 300));

        depositThread.start();
        withdrawThread.start();
    }
}
