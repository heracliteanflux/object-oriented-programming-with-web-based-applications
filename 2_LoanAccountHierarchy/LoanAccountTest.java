import java.lang.Object;

public class LoanAccountTest extends Object {
  public static void main (String[] args) {
    LoanAccount loan = new LoanAccount(
			25000.00,
			4.25,
			72
		);
    System.out.printf("%s", loan.toString());
  }
}