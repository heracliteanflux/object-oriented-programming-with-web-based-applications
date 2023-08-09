public class BasePlusCommissionEmployee extends CommissionEmployee {
  // implementation
  private double base_salary;

  private void validate_base_salary (double base_salary) {
    if (base_salary < 0.0) { throw new IllegalArgumentException("Base salary must be >= 0.0"); }
  }

  // constructor
  public BasePlusCommissionEmployee (String first_name, String last_name,
    String social_security_number, double gross_sales,
    double commission_rate, double base_salary) {
  
    super(first_name, last_name, social_security_number, gross_sales, commission_rate);

    validate_base_salary(base_salary);
    this.base_salary = base_salary;
  }

  // interface
  public void set_base_salary (double base_salary) {
    validate_base_salary(base_salary);
    this.base_salary = base_salary;
  }
  public double get_base_salary () { return base_salary; }
  
  @Override
  public double earnings () { return get_base_salary() + super.earnings(); }

  @Override
  public String toString () {
    return String.format("Base Salary Plus Commissioned Employee: %s %s with ssn: %s%n %s: %.2f%n %s: %.2f%n %s $%.2f%n %s: $%.2f%n",
      super.get_first_name(), super.get_last_name(),
      super.get_social_security_number(),
      "Gross Sales", super.get_gross_sales(),
      "Commission Rate", super.get_commission_rate(),
      "with Base Salary of", get_base_salary(),
      "Earnings", earnings());
  }
}
