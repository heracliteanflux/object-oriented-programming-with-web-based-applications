public class PrimaryMortgageTest {
  public static void main (String[] args) {
    Address         address         = new Address("321 Main Street", "State College", "PA", "16801");
    PrimaryMortgage primaryMortgage = new PrimaryMortgage(250000, 3.10, 360, 35.12, address);
    System.out.printf("%s", primaryMortgage.toString());
  }
}
