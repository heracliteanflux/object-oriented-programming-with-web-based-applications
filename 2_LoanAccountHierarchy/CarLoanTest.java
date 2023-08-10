import java.lang.Object;

public class CarLoanTest extends Object {
  public static void main (String[] args) {
    CarLoan carLoan = new CarLoan(
			25000.00,
			4.25,
			72,
			"IRQ3458977"
		);
    System.out.printf("%s", carLoan.toString());
  }
}