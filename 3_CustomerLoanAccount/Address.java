import java.lang.Object;

public class Address extends Object {
  private String street;
  private String city;
  private String state;
  private String zipcode;


	// constructor
  public Address (String street,
                  String city,
                  String state,
                  String zipcode)
  {
    this.street  = street;
    this.city    = city;
    this.state   = state;
    this.zipcode = zipcode;
  }
	// END constructor


	// getters
  public String getStreet  () { return street;  }
  public String getCity    () { return city;    }
  public String getState   () { return state;   }
  public String getZipcode () { return zipcode; }
  // END getters


  // string representation of `Address`
  @Override
  public String toString () {
    return String.format(
      "Property Address:%n    %s%n    %s, %s %s",
      getStreet(), getCity(), getState(), getZipcode()
    );
  }
}