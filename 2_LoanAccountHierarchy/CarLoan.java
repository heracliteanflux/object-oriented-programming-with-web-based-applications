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