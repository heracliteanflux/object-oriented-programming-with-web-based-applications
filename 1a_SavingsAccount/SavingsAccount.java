import java.lang.Object;

public class SavingsAccount extends Object {
  private static double annualInterestRate;
  private        double savingsBalance;


  // constructors
  public SavingsAccount () {
    this(0.0);
  }
  public SavingsAccount (double savingsBalance) {
    this.savingsBalance = savingsBalance;
  }
	// END constructors


	// setters
  public static void setInterestRate (double newInterestRate) {
    annualInterestRate = newInterestRate;
  }
	// END setters


  public double calculateMonthlyInterest () {
    this.savingsBalance += this.savingsBalance * annualInterestRate / 12;
    return this.savingsBalance;
  }
}