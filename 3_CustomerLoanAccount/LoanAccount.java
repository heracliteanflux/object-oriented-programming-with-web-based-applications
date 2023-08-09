public class LoanAccount extends java.lang.Object {
  private double principal;          // the original loan amount
  private double annualInterestRate; // the loan's annual interest rate
  private int    months;             // the number of months in the loan's term (i.e., the loan's length)

  public LoanAccount (double principal,
                      double annualInterestRate,
                      int    months)
  {
    this.principal          = principal;
    this.annualInterestRate = annualInterestRate;
    this.months             = months;
  }

  public double getPrincipal          () { return principal;          }
  public double getAnnualInterestRate () { return annualInterestRate; }
  public int    getMonths             () { return months;             }

  public double calculateMonthlyPayment () {
    double monthlyInterest = this.annualInterestRate / 100 / 12;
    double monthlyPayment  = this.principal * (monthlyInterest / (1 - Math.pow(1 + monthlyInterest, -this.months)));
    return monthlyPayment;
  }

  // string representation of `LoanAccount`
  @Override
  public String toString () {
    return String.format(
      "Principal: $%.2f%nAnnual Interest Rate: %.2f%%%nTerm of Loan in Months: %d%nMonthly Payment: $%.2f",
      getPrincipal(), getAnnualInterestRate(), getMonths(), calculateMonthlyPayment()
    );
  }
}
