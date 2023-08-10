import java.lang.Object;

public class LoanAccount extends Object {
  public static double annualInterestRate;
  private       double principal;


	// constructors
  public LoanAccount () {
    this(0.0);
  }
  public LoanAccount (double principal) {
    this.principal = principal;
  }
	// END constructors


	// setters
  public static void setAnnualInterestRate (double newInterestRate) {
    annualInterestRate = newInterestRate;
  }
	// END setters
	

  public double calculateMonthlyPayment (int numberOfPayments) {
    double monthlyInterest = annualInterestRate / 12;
    double monthlyPayment  = this.principal * (monthlyInterest / (1 - Math.pow(1 + monthlyInterest, -numberOfPayments)));
    return monthlyPayment;
  }
}