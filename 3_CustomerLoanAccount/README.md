### CustomerLoanAccount

#### Table of Contents

1. [Execution at the command line](#execution-at-the-command-line)
2. [`Customer` Class](#customer-class)
3. [`CustomerLoanAccount` Class](#customerloanaccount-class)
4. [Requirements](#requirements)

#### Execution at the command line

```
javac *.java &&
java  CustomerLoanAccount
```
```
Monthly Report of Customers by Loan Account
Account Report for Customer: Tony Stark with SSN 111-22-3333

Car Loan with:
Principal: $25000.00
Annual Interest Rate: 4.90%
Term of Loan in Months: 72
Monthly Payment: $401.46
Vehicle VIN: IRQ3458977


Primary Mortgage Loan with:
Principal: $250000.00
Annual Interest Rate: 3.75%
Term of Loan in Months: 360
Monthly Payment: $1157.79
PMI Monthly Amount: $35.12
Property Address:
    321 Main Street
    State College, PA 16801


Unsecured Loan with:
Principal: $5000.00
Annual Interest Rate: 10.75%
Term of Loan in Months: 48
Monthly Payment: $128.62


Account Report for Customer: Gal Gadot with SSN 444-55-6666

Car Loan with:
Principal: $12000.00
Annual Interest Rate: 5.00%
Term of Loan in Months: 60
Monthly Payment: $226.45
Vehicle VIN: NXK6767876


Primary Mortgage Loan with:
Principal: $375000.00
Annual Interest Rate: 2.50%
Term of Loan in Months: 360
Monthly Payment: $1481.70
PMI Monthly Amount: $53.12
Property Address:
    783 Maple Lane
    State College, PA 16801
```

#### `Customer` Class

```java
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
```

#### `CustomerLoanAccount` Class

```java
import java.lang.Object;
import java.util.ArrayList;

public class CustomerLoanAccount extends Object {
  public static void main (String[] args) {
    CarLoan         carLoan1         = new CarLoan(25_000.00, 4.9, 72, "IRQ3458977");
    Address         propertyAddress1 = new Address("321 Main Street", "State College", "PA", "16801");
    PrimaryMortgage propertyLoan1    = new PrimaryMortgage(250_000.00, 3.75, 360, 35.12, propertyAddress1);
    UnsecuredLoan   unsecuredLoan    = new UnsecuredLoan(5_000.00, 10.75, 48);

    CarLoan         carLoan2         = new CarLoan(12_000.00, 5.0, 60, "NXK6767876");
    Address         propertyAddress2 = new Address("783 Maple Lane", "State College", "PA", "16801");
    PrimaryMortgage propertyLoan2    = new PrimaryMortgage(375_000.00, 2.50, 360, 53.12, propertyAddress2);

    Customer customerA = new Customer("Tony", "Stark", "111-22-3333");
    Customer customerB = new Customer( "Gal", "Gadot", "444-55-6666");

    customerA.addLoanAccount(carLoan1);
    customerA.addLoanAccount(propertyLoan1);
    customerA.addLoanAccount(unsecuredLoan);

    customerB.addLoanAccount(carLoan2);
    customerB.addLoanAccount(propertyLoan2);

    ArrayList<Customer> customers = new ArrayList<>();
    customers.add(customerA);
    customers.add(customerB);

    System.out.println("Monthly Report of Customers by Loan Account");
    for (Customer customer : customers) {
      customer.printMonthlyReport();
    }
  }
}
```

#### Requirements

Enhance the Loan Accounts Hierarchy from project `2_LoanAccountHierarchy` by adding a `Customer` class with the following properties and methods:
* property `firstName` the customer's first name (`String`)
* property `lastName` the customer's last name (`String`)
* property `SSN` the customer's SSN (`String`)
* property `loanAccounts` an array list of loan accounts (`ArrayList<LoanAccount>`)
* a constructor that accepts `firstName`, `lastName`, and `SSN` as parameters
* getters for `firstName`, `lastName`, and `SSN`
* method `addLoanAccount(LoanAccount account)` adds a loan to the array list of loans for this customer
* method `printMonthlyReport()` prints all the information for all the accounts of this `Customer` and utilizes the `toString()` method of the corresponding loan class

Use the following code in the main function to test the classes:

```java
CarLoan         carLoan1         = new CarLoan(25_000.00, 4.9, 72, "IRQ3458977");
Address         propertyAddress1 = new Address("321 Main Street", "State College", "PA", "16801");
PrimaryMortgage propertyLoan1    = new PrimaryMortgage(250_000.00, 3.75, 360, 35.12, propertyAddress1);
UnsecuredLoan   unsecuredLoan    = new UnsecuredLoan(5_000.00, 10.75, 48);

CarLoan         carLoan2         = new CarLoan(12_000.00, 5.0, 60, "NXK6767876");
Address         propertyAddress2 = new Address("783 Maple Lane", "State College", "PA", "16801");
PrimaryMortgage propertyLoan2    = new PrimaryMortgage(375_000.00, 2.50, 360, 53.12, propertyAddress2);

Customer customerA = new Customer("Tony", "Stark", "111-22-3333");
Customer customerB = new Customer( "Gal", "Gadot", "444-55-6666");

customerA.addLoanAccount(carLoan1);
customerA.addLoanAccount(propertyLoan1);
customerA.addLoanAccount(unsecuredLoan);

customerB.addLoanAccount(carLoan2);
customerB.addLoanAccount(propertyLoan2);

// add the customers to an arraylist of customers
ArrayList<Customer> customers = new ArrayList<>();
customers.add(customerA);
customers.add(customerB);

// print the loan information for each customer polymorhically
System.out.println("Monthly Report of Customers by Loan Account");
for (Customer customer : customers) {
	customer.printMonthlyReport();
}
```