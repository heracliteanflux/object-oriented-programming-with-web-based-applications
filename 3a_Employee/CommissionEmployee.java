public class CommissionEmployee extends Employee {
  // implementation
  private double gross_sales;
  private double commission_rate;

  private void validate_gross_sales (double gross_sales) {
    if (gross_sales < 0.0) { throw new IllegalArgumentException("Gross sales must be >= 0.0"); }
  }
  private void validate_commission_rate (double commission_rate) {
    if (commission_rate <= 0.0 || commission_rate >= 1.0) {
      throw new IllegalArgumentException("Commission rate must be > 0.0 and < 1.0");
    }
  }

  // constructor
  public CommissionEmployee (String first_name, String last_name,
    String social_security_number, double gross_sales,
    double commission_rate) {

    super(first_name, last_name, social_security_number);

    validate_gross_sales(gross_sales);
    validate_commission_rate(commission_rate);
    this.gross_sales = gross_sales;
    this.commission_rate = commission_rate;
  }

  // interface
  public void set_gross_sales (double gross_sales) {
    validate_gross_sales(gross_sales);
    this.gross_sales = gross_sales;
  }
  public double get_gross_sales () { return gross_sales; }
  public void set_commission_rate (double commission_rate) {
    validate_commission_rate(commission_rate);
    this.commission_rate = commission_rate;
  }
  public double get_commission_rate () { return commission_rate; }

  @Override
  public double earnings () { return get_commission_rate() * get_gross_sales(); }

  @Override
  public void raise (double percent) {
    set_commission_rate(get_commission_rate() + get_commission_rate() * percent);
  }

  @Override
  public String toString () {
    return String.format("Commissioned %s%n %s: %.2f%n %s: %.4f%n %s: $%.2f%n",
      super.toString(), "Gross Sales", get_gross_sales(), "Commission Rate", get_commission_rate(),
      "Earnings", earnings());
  }
}
