import java.lang.Object;

public class AddressTest extends Object {
  public static void main (String[] args) {
    Address address = new Address(
			"321 Main Street",
			"State College",
			"PA",
			"16801"
		);
    System.out.printf("%s", address.toString());
  }
}