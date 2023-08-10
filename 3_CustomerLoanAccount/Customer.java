import java.lang.Object;
import java.util.ArrayList;

public class Customer extends Object {

  private final String                 firstName;
  private final String                 lastName;
  private final String                 SSN;
  private       ArrayList<LoanAccount> loanAccounts = new ArrayList<>();


	// constructor
  public Customer (String firstName,
                   String lastName,
                   String SSN)
  {
    this.firstName = firstName;
    this.lastName  = lastName;
    this.SSN       = SSN;
  }
	// END constructor


  // getters
  public String getFirstName () { return firstName; }
  public String getLastName  () { return lastName; }
  public String getSSN       () { return SSN; }
	// END getters


  public void addLoanAccount (LoanAccount account) {
    loanAccounts.add(account);
  }


  public void printMonthlyReport () {
    System.out.printf(
			"Account Report for Customer: %s %s with SSN %s%n%n",
			getFirstName(),
			getLastName(),
			getSSN()
		);
    for (LoanAccount loanAccount : loanAccounts) {
      System.out.printf("%s%n%n%n", loanAccount.toString());
    }
  }
}