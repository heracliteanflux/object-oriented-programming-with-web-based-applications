import java.lang.Object;

public class LoanAccountTest extends Object {
	public static void main (String[] args) {
    LoanAccount loan1 = new LoanAccount( 5_000);
    LoanAccount loan2 = new LoanAccount(31_000);

    double      interestRates[] = {0.01, 0.05};
    LoanAccount loans[]         = {loan1, loan2};

    for (double interestRate : interestRates) {
      System.out.printf("%n%nMonthly payments for loan1 of $5000.00 and loan2 of $31000.00 for 3, 5, and 6 year loans at %.0f%% interest.", interestRate * 100);
      System.out.printf("%n%-7s\t%-7s\t%-7s\t%-7s", "Loan", "3 years", "5 years", "6 years");
      
      for (LoanAccount loan : loans) {
        LoanAccount.setAnnualInterestRate(interestRate);
        System.out.printf(
          "%n%-7s\t%-7.2f\t%-7.2f\t%-7.2f", "Loan1",
          loan.calculateMonthlyPayment(36),
          loan.calculateMonthlyPayment(60),
          loan.calculateMonthlyPayment(72)
        );
      }
    }
  }
}
