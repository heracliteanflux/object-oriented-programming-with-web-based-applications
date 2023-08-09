public class HourlyEmployee extends Employee{
  // implementation
  private double hourly_wage;
  private double hours_worked;

  private void validate_hourly_wage (double hourly_wage) {
    if (hourly_wage <= 0.0) { throw new IllegalArgumentException("Hourly wage must be > 0.0"); }
  }
  private void validate_hours_worked (double hours_worked) {
    if (hours_worked < 1.0 || hours_worked > 168.0) {
      throw new IllegalArgumentException("Hours worked must be >= 1.0 and <= 168.0");
    }
  }

  // constructor
  public HourlyEmployee (String first_name, String last_name,
    String social_security_number, double hourly_wage,
    double hours_worked) {
  
    super(first_name, last_name, social_security_number);

    validate_hourly_wage(hourly_wage);
    validate_hours_worked(hours_worked);
    this.hourly_wage = hourly_wage;
    this.hours_worked = hours_worked;
  }

  // interface
  public void set_hourly_wage (double hourly_wage) {
    validate_hourly_wage(hourly_wage);
    this.hourly_wage = hourly_wage;
  }
  public double get_hourly_wage () { return hourly_wage; }
  public void set_hours_worked (double hours_worked) {
    validate_hours_worked(hours_worked);
    this.hours_worked = hours_worked;
  }
  public double get_hours_worked () { return hours_worked; }

  public double earnings () {
    if (get_hours_worked() <= 40.0) { return get_hours_worked() * get_hourly_wage(); }
    else { return 40.0 * get_hourly_wage() + (get_hours_worked() - 40.0) * get_hourly_wage() * 1.5; }
  }

  @Override
  public String toString () {
    return String.format("Hourly %s%n %s: %.2f%n %s: %.2f%n %s: $%.2f%n",
      super.toString(), "Hourly Wage", get_hourly_wage(), "Hours Worked", get_hours_worked(),
      "Earnings", earnings());
  }
}
