### LoanAccountHierarchy

#### Table of Contents
1. [Execution at the command line](#execution-at-the-command-line)
2. [`Address` Class](#address-class)
3. [`AddressTest` Class](#addresstest-class)
4. [`CarLoan` Class](#carloan-class)
5. [`CarLoanTest` Class](#carloantest-class)
6. [`LoanAccount` Class](#loanaccount-class)
7. [`LoanAccountTest` Class](#loanaccounttest-class)
8. [`PrimaryMortgage` Class](#primarymortgage-class)
9. [`PrimaryMortgageTest` Class](#primarymortgagetest-class)
10. [`UnsecuredLoan` Class](#unsecuredloan-class)
11. [`UnsecuredLoanTest` Class](#unsecuredloantest-class)
12. [`LoanAccountHierarchy` Class](#loanaccounthierarchy-class)
13. [Requirements](#requirements)

#### Execution at the command line

```
javac *.java &&
java  LoanAccountHierarchy
```
```
Car Loan with:
Principal: $25000.00
Annual Interest Rate: 4.25%
Term of Loan in Months: 72
Monthly Payment: $393.98
Vehicle VIN: IRQ3458977

Primary Mortgage Loan with:
Principal: $250000.00
Annual Interest Rate: 3.10%
Term of Loan in Months: 360
Monthly Payment: $1067.54
PMI Monthly Amount: $35.12
Property Address:
    321 Main Street
    State College, PA 16801

Unsecured Loan with:
Principal: $5000.00
Annual Interest Rate: 10.75%
Term of Loan in Months: 48
Monthly Payment: $128.62
```

#### `Address` Class

```java
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
```

#### `AddressTest` Class

```java
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
```

#### `CarLoan` Class

```java
public class CarLoan extends LoanAccount {

  private String vehicleVIN;


	// constructor
  public CarLoan (double principal,
                  double annualInterestRate,
                  int    months,
                  String vehicleVIN)
  {
    super(principal, annualInterestRate, months);
    this.vehicleVIN = vehicleVIN;
  }
	// END constructor


	// getters
  public String getVehicleVIN () { return vehicleVIN; }
  // END getters


  // string representation of `CarLoan`
  @Override
  public String toString () {
    return String.format(
      "Car Loan with:%n%s%nVehicle VIN: %s",
      super.toString(), getVehicleVIN()
    );
  }
}
```

#### `CarLoanTest` Class

```java
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
```

#### `LoanAccount` Class

```java
import java.lang.Object;

public class LoanAccount extends Object {
  private double principal;          // the original loan amount
  private double annualInterestRate; // the loan's annual interest rate
  private int    months;             // the number of months in the loan's term (i.e., the loan's length)


	// constructor
  public LoanAccount (double principal,
                      double annualInterestRate,
                      int    months)
  {
    this.principal          = principal;
    this.annualInterestRate = annualInterestRate;
    this.months             = months;
  }
	// END constructor


	// getters
  public double getPrincipal          () { return principal; }
  public double getAnnualInterestRate () { return annualInterestRate; }
  public int    getMonths             () { return months; }
	// END getters


  public double calculateMonthlyPayment () {
    double monthlyInterest = this.annualInterestRate / 100 / 12;
    double monthlyPayment  = this.principal * (monthlyInterest / (1 - Math.pow(1 + monthlyInterest, -this.months)));
    return monthlyPayment;
  }

	
  // string representation of `LoanAccount`
  @Override
  public String toString () {
    return String.format(
      "Principal: $%.2f%nAnnual Interest Rate: %.2f%%%nTerm of Loan in Months: %d%nMonthly Payment: $%.2f",
      getPrincipal(), getAnnualInterestRate(), getMonths(), calculateMonthlyPayment()
    );
  }
}
```

#### `LoanAccountTest` Class

```java
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
```

#### `PrimaryMortgage` Class

```java
public class PrimaryMortgage extends LoanAccount {
  
  private double  PMIMonthlyAmount; // the amount of the primary mortgage insurance which is required for all mortgages where the down payment is not at least 20% of the home value
  private Address address;          // the address of the real estate


	// constructor
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
	// END constructor


  // string representation of `PrimaryMortgage`
  @Override
  public String toString () {
    return String.format(
      "Primary Mortgage Loan with:%n%s%nPMI Monthly Amount: $%.2f%n%s",
      super.toString(), PMIMonthlyAmount, address.toString()
    );
  }
}
```

#### `PrimaryMortgageTest` Class

```java
import java.lang.Object;

public class PrimaryMortgageTest extends Object {
  public static void main (String[] args) {
    Address         address         = new Address(
			"321 Main Street",
			"State College",
			"PA",
			"16801"
		);
    PrimaryMortgage primaryMortgage = new PrimaryMortgage(
			250000,
			3.10,
			360,
			35.12, address
		);
    System.out.printf("%s", primaryMortgage.toString());
  }
}
```

#### `UnsecuredLoan` Class

```java
public class UnsecuredLoan extends LoanAccount {
  
	// constructor
  public UnsecuredLoan (double principal,
                        double annualInterestRate,
                        int    months)
  {
    super(principal, annualInterestRate, months);
  }
	// END constructor


  // string representation of `UnsecuredLoan`
  @Override
  public String toString () {
    return String.format(
			"Unsecured Loan with:%n%s",
			super.toString()
		);
  }
}
```

#### `UnsecuredLoanTest` Class

```java
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
```

#### `LoanAccountHierarchy` Class

```java
import java.lang.Object;

public class LoanAccountHierarchy extends Object {
  public static void main (String[] args) {
    CarLoan         carLoan         = new CarLoan(25_000.00, 4.25, 72, "IRQ3458977");
    Address         propertyAddress = new Address("321 Main Street", "State College", "PA", "16801");
    PrimaryMortgage propertyLoan    = new PrimaryMortgage(250_000.00, 3.1, 360, 35.12, propertyAddress);
    UnsecuredLoan unsecuredLoan     = new UnsecuredLoan(5_000.00, 10.75, 48);
    System.out.printf("%s%n%n%s%n%n%s%n", carLoan, propertyLoan, unsecuredLoan);
  }
}
```

#### Requirements

Create a Loan Account Hierarchy consisting of the following classes:
* `LoanAccount`
* `CarLoan`
* `PrimaryMortgage`
* `UnsecuredLoan`
* `Address`

Each class should be in it's own .java file.

The `LoanAccount` class consists of the following properties:
* `principal` the original amount of the loan
* `annualInterestRate` the annual interest rate for the loan (it is not static as each loan can have it's own interest rate)
* `months` the number of months in the term of the loan (i.e. the length of the loan)

and the following methods:
* a constructor that takes the three properties as parameters
* `calculateMonthlyPayment()` takes no parameters and calculates the monthly payment using the same formula as project `1_LoanAccount`
* getters for the three property variables
* `toString()` displays the information about the `principal`, `annualInterestRate`, and `months`
 
The `CarLoan` class which is a subclass of the `LoanAccount` class and consists of the following property:
* `vehicleVIN` the VIN number of the car, as a string

and the following methods:
* constructor that accepts the three parameters of the LoanAccount class and the vehicleVIN
* `toString()` displays information about the VIN number
 
The `PrimaryMortgage` class which is a subclass of `LoanAccount` and consists of the following properties:
* `PMIMonthlyAmount` the amount of the Primary Mortgage Insurance which is required for all mortgages where the down payment is not at least 20% of the home value
* `Address` the address of the real estate and is an object of the `Address` class

and the following methods:
* constructor that accepts the three parameters of the LoanAccount class, the PMIMonthlyAmount, and the Address object containing the address
* `toString()` displays information about the PMIMonthlyAmount and Address
 
The `UnsecuredLoan` class which is a subclass of `LoanAccount` and has no additional properties and has the following methods:
* constructor that accepts the three parameters of the `LoanAccount`
* `toString()` displays that it is an Unsecured Loan
 
The `Address` class which consists of the following properties:
* `street` the house number and the street name
* `city`
* `state`
* `zipcode`

and the following methods:
* constructor which accepts the values for the four properties as parameters
* getters for each property
* `toString()` displays the address information
 
Code in the subclasses should call methods in the super classes whenever possible to reduce the amount of code in the subclasses and utilize the code already developed in the super classes. Use the following code in the main function to test the classes:

```java
// Create three different loan objects, one of each type.
CarLoan         carLoan         = new CarLoan(25_000.00, 4.25, 72, "IRQ3458977");
Address         propertyAddress = new Address("321 Main Street", "State College", "PA", "16801");
PrimaryMortgage propertyLoan    = new PrimaryMortgage(250_000.00, 3.1, 360, 35.12, propertyAddress);
UnsecuredLoan   unsecuredLoan   = new UnsecuredLoan(5_000.00, 10.75, 48);
//Print out the load information for each loan using the toString() method.
System.out.format("%n%s%s%s%n", carLoan, propertyLoan, unsecuredLoan);
```