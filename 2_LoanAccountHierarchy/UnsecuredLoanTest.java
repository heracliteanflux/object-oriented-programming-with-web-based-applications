import java.lang.Object;

public class UnsecuredLoanTest extends Object {
  public static void main (String[] args) {
    UnsecuredLoan unsecuredLoan = new UnsecuredLoan(
			5_000.00, 
			10.75,
			48
		);
    System.out.printf("%s", unsecuredLoan.toString());
  }
}