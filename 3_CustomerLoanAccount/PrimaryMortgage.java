public class PrimaryMortgage extends LoanAccount {
  
  private double  PMIMonthlyAmount; // the amount of the primary mortgage insurance which is required for all mortgages where the down payment is not at least 20% of the home value
  private Address address;          // the address of the real estate

  public PrimaryMortgage (double  principal,
                          double  annualInterestRate,
                          int     months,
                          double  PMIMonthlyAmount,
                          Address address)
  {
    super(principal, annualInterestRate, months);
    this.PMIMonthlyAmount = PMIMonthlyAmount;
    this.address          = address;
  }

  // string representation of `PrimaryMortgage`
  @Override
  public String toString () {
    return String.format(
      "Primary Mortgage Loan with:%n%s%nPMI Monthly Amount: $%.2f%n%s",
      super.toString(), PMIMonthlyAmount, address.toString()
    );
  }
}
