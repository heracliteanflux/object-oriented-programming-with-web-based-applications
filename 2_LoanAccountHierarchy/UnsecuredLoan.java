public class UnsecuredLoan extends LoanAccount {
  
  public UnsecuredLoan (double principal,
                        double annualInterestRate,
                        int    months)
  {
    super(principal, annualInterestRate, months);
  }

  // string representation of `UnsecuredLoan`
  @Override
  public String toString () {
    return String.format("Unsecured Loan with:%n%s", super.toString());
  }
}
