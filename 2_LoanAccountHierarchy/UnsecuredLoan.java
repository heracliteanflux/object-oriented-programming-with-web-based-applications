public class UnsecuredLoan extends LoanAccount {
  
	// constructor
  public UnsecuredLoan (double principal,
                        double annualInterestRate,
                        int    months)
  {
    super(principal, annualInterestRate, months);
  }
	// END constructor


  // string representation of `UnsecuredLoan`
  @Override
  public String toString () {
    return String.format(
			"Unsecured Loan with:%n%s",
			super.toString()
		);
  }
}