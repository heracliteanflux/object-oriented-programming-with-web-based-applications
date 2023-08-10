import java.lang.Object;

public class SavingsAccountTest extends Object {
	public static void main (String[] args) {
    SavingsAccount saver1 = new SavingsAccount(2_000.00);
    SavingsAccount saver2 = new SavingsAccount(3_000.00);

    SavingsAccount.setInterestRate(0.04);

    System.out.printf("Savings Account Balances");
    System.out.printf("%n%-5s\t%-7s\t%s", "Month", "Saver1", "Saver2");

    for (int count = 0; count < 12; count++) {
      System.out.printf(
				"%n%-5d\t%-7.2f\t%.2f", 
        count + 1,
        saver1.calculateMonthlyInterest(),
        saver2.calculateMonthlyInterest()
			);
    }

    SavingsAccount.setInterestRate(0.05);

    System.out.printf(
			"%n%-5d\t%-7.2f\t%.2f%n",
      13,
      saver1.calculateMonthlyInterest(),
      saver2.calculateMonthlyInterest()
		);
  }
}