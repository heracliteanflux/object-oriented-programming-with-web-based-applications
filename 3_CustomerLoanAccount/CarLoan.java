public class CarLoan extends LoanAccount {

  private String vehicleVIN;

  public CarLoan (double principal,
                  double annualInterestRate,
                  int    months,
                  String vehicleVIN)
  {
    super(principal, annualInterestRate, months);
    this.vehicleVIN = vehicleVIN;
  }

  public String getVehicleVIN () { return vehicleVIN; }

  // string representation of `CarLoan`
  @Override
  public String toString () {
    return String.format(
      "Car Loan with:%n%s%nVehicle VIN: %s",
      super.toString(), getVehicleVIN()
    );
  }
}
