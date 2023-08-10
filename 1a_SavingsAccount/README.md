### SavingsAccount

#### Table of Contents
1. [Execution at the command line](#execution-at-the-command-line)
2. [`SavingsAccount` Class](#savingsaccount-class)
3. [`SavingsAccountTest` Class](#savingsaccounttest-class)

#### Execution at the command line

```
javac *.java &&
java  SavingsAccountTest
```
```
Savings Account Balances
Month	Saver1 	Saver2
1    	2006.67	3010.00
2    	2013.36	3020.03
3    	2020.07	3030.10
4    	2026.80	3040.20
5    	2033.56	3050.33
6    	2040.33	3060.50
7    	2047.14	3070.70
8    	2053.96	3080.94
9    	2060.81	3091.21
10   	2067.68	3101.51
11   	2074.57	3111.85
12   	2081.48	3122.22
13   	2090.16	3135.23
```

#### `SavingsAccount` Class

```java
import java.lang.Object;

public class SavingsAccount extends Object {
  private static double annualInterestRate;
  private        double savingsBalance;


  // constructors
  public SavingsAccount () {
    this(0.0);
  }
  public SavingsAccount (double savingsBalance) {
    this.savingsBalance = savingsBalance;
  }
	// END constructors


	// setters
  public static void setInterestRate (double newInterestRate) {
    annualInterestRate = newInterestRate;
  }
	// END setters


  public double calculateMonthlyInterest () {
    this.savingsBalance += this.savingsBalance * annualInterestRate / 12;
    return this.savingsBalance;
  }
}
```

#### `SavingsAccountTest` Class

```java
import java.lang.Object;

public class SavingsAccountTest extends Object {
	public static void main (String[] args) {
    SavingsAccount saver1 = new SavingsAccount(2_000.00);
    SavingsAccount saver2 = new SavingsAccount(3_000.00);

    SavingsAccount.setInterestRate(0.04);

    System.out.printf("Savings Account Balances");
    System.out.printf("%n%-5s\t%-7s\t%s", "Month", "Saver1", "Saver2");

    for (int count = 0; count < 12; count++) {
      System.out.printf(
				"%n%-5d\t%-7.2f\t%.2f", 
        count + 1,
        saver1.calculateMonthlyInterest(),
        saver2.calculateMonthlyInterest()
			);
    }

    SavingsAccount.setInterestRate(0.05);

    System.out.printf(
			"%n%-5d\t%-7.2f\t%.2f%n",
      13,
      saver1.calculateMonthlyInterest(),
      saver2.calculateMonthlyInterest()
		);
  }
}
```