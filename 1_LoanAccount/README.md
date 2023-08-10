### LoanAccount

#### Table of Contents
1. [Execution at the command line](#execution-at-the-command-line)
2. [`LoanAccount` Class](#loanaccount-class)
3. [`LoanAccountTest` Class](#loanaccounttest-class)
4. [Requirements](#requirements)

#### Execution at the command line

```
javac *.java &&
java  LoanAccountTest
```
```
Monthly payments for loan1 of $5000.00 and loan2 of $31000.00 for 3, 5, and 6 year loans at 1% interest.
Loan   	3 years	5 years	6 years
Loan1  	141.04 	85.47  	71.58
Loan1  	874.45 	529.91 	443.78

Monthly payments for loan1 of $5000.00 and loan2 of $31000.00 for 3, 5, and 6 year loans at 5% interest.
Loan   	3 years	5 years	6 years
Loan1  	149.85 	94.36  	80.52
Loan1  	929.10 	585.01 	499.25
```

#### `LoanAccount` Class

```java
import java.lang.Object;

public class LoanAccount extends Object {
  public static double annualInterestRate;
  private       double principal;


	// constructors
  public LoanAccount () {
    this(0.0);
  }
  public LoanAccount (double principal) {
    this.principal = principal;
  }
	// END constructors


	// setters
  public static void setAnnualInterestRate (double newInterestRate) {
    annualInterestRate = newInterestRate;
  }
	// END setters


  public double calculateMonthlyPayment (int numberOfPayments) {
    double monthlyInterest = annualInterestRate / 12;
    double monthlyPayment  = this.principal * (monthlyInterest / (1 - Math.pow(1 + monthlyInterest, -numberOfPayments)));
    return monthlyPayment;
  }
}
```

#### `LoanAccountTest` Class

```java
import java.lang.Object;

public class LoanAccountTest extends Object {
	public static void main (String[] args) {
    LoanAccount loan1 = new LoanAccount( 5_000);
    LoanAccount loan2 = new LoanAccount(31_000);

    double      interestRates[] = {0.01, 0.05};
    LoanAccount loans[]         = {loan1, loan2};

    for (double interestRate : interestRates) {
      System.out.printf("%n%nMonthly payments for loan1 of $5000.00 and loan2 of $31000.00 for 3, 5, and 6 year loans at %.0f%% interest.", interestRate * 100);
      System.out.printf("%n%-7s\t%-7s\t%-7s\t%-7s", "Loan", "3 years", "5 years", "6 years");
      
      for (LoanAccount loan : loans) {
        LoanAccount.setAnnualInterestRate(interestRate);
        System.out.printf(
          "%n%-7s\t%-7.2f\t%-7.2f\t%-7.2f", "Loan1",
          loan.calculateMonthlyPayment(36),
          loan.calculateMonthlyPayment(60),
          loan.calculateMonthlyPayment(72)
        );
      }
    }
  }
}
```

#### Requirements

Create class `LoanAccount`. Use a static variable `annualInterestRate` to store the annual interest rate for all account holders. Each object of the class contains a private instance variable `principal` indicating the amount the person is borrowing. Provide method `public double calculateMonthlyPayment(int numberOfPayments)` to calculate the monthly payment by using the following formula `double monthlyPayment = principal * ( monthlyInterest / (1 - Math.pow(1 + monthlyInterest, -numberOfPayments)));` where `monthly interest = annualInterestRate / 12`. Provide a static method `setAnnualInterestRate` that sets the `annualInterestRate` to a new value.  Set the initial loan amount (Principal) for a new loan through the constructor. Write a program to test class `LoanAccount`. Instantiate two `LoanAccount` objects, `loan1` and `loan2`, with principal loan amounts of $5000.00 and $31000.00, respectively. Set `annualInterestRate` to 1%, then calculate the monthly payment for each loan for 36 months, 60 months, and 72 months and print the monthly payment amounts for both loans. Next, set the `annualInterestRate` to 5%, and output the same information again.  Make sure your dollar amounts are displayed to two decimal places. Put the code to test the class in the main method of the `LoanAccount` class.