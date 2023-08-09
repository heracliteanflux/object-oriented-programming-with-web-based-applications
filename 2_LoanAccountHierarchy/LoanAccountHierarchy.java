public class LoanAccountHierarchy {
  public static void main (String[] args) {
    CarLoan         carLoan         = new CarLoan(25000.00, 4.25, 72, "IRQ3458977");
    Address         propertyAddress = new Address("321 Main Street", "State College", "PA", "16801");
    PrimaryMortgage propertyLoan    = new PrimaryMortgage(250000.00, 3.1, 360, 35.12, propertyAddress);
    UnsecuredLoan unsecuredLoan     = new UnsecuredLoan(5000.00, 10.75, 48);
    System.out.printf("%s%n%n%s%n%n%s%n", carLoan, propertyLoan, unsecuredLoan);
  }
}
