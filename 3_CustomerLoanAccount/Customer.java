import java.util.ArrayList;

public class Customer extends java.lang.Object {
  private final String                 firstName;
  private final String                 lastName;
  private final String                 SSN;
  private       ArrayList<LoanAccount> loanAccounts = new ArrayList<>();

  public Customer (String firstName,
                   String lastName,
                   String SSN)
  {
    this.firstName = firstName;
    this.lastName  = lastName;
    this.SSN       = SSN;
  }

  public String getFirstName () { return firstName; }
  public String getLastName  () { return lastName; }
  public String getSSN       () { return SSN; }

  public void addLoanAccount (LoanAccount account) {
    loanAccounts.add(account);
  }

  public void printMonthlyReport () {
    System.out.printf("Account Report for Customer: %s %s with SSN %s%n%n", getFirstName(), getLastName(), getSSN());
    for (LoanAccount loanAccount : loanAccounts) {
      System.out.printf("%s%n%n%n", loanAccount.toString());
    }
  }
}
