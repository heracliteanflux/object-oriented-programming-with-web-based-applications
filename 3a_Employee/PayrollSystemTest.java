public class PayrollSystemTest {
  public static void main (String[] args) {
    SalariedEmployee se = new SalariedEmployee("John", "Smith", "111-11-1111", 800.00);
    HourlyEmployee he = new HourlyEmployee("Karen", "Price", "222-22-2222", 16.75, 40);
    CommissionEmployee ce = new CommissionEmployee("Sue", "Jones", "333-33-3333", 10000, .06);
    BasePlusCommissionEmployee bpce = new BasePlusCommissionEmployee("Bob", "Lewis", "444-44-4444", 5000, .04, 300);

    System.out.println("Employees processed individually:");
    System.out.printf("%n%s%n%s: $%,.2f%n%n",
      se, "earned", se.earnings());
    System.out.printf("%n%s%n%s: $%,.2f%n%n",
      he, "earned", he.earnings());
    System.out.printf("%n%s%n%s: $%,.2f%n%n",
      ce, "earned", ce.earnings());
    System.out.printf("%n%s%n%s: $%,.2f%n%n",
      bpce, "earned", bpce.earnings());

    Employee[] es = new Employee[4];
    es[0] = se;
    es[1] = he;
    es[2] = ce;
    es[3] = bpce;

    System.out.printf("Employees processed polymorphically:%n%n");
    for (Employee current : es) {
      System.out.println(current);
      if (current instanceof BasePlusCommissionEmployee) {
        BasePlusCommissionEmployee employee = (BasePlusCommissionEmployee) current;
        employee.set_base_salary(1.10 * employee.get_base_salary());
        System.out.printf("new Base Salary with 10%% increase: $%,.2f%n",
          employee.get_base_salary());
      }
      System.out.printf("Earned: $%,.2f%n%n", current.earnings());
    }
    for (int j = 0; j < es.length; j++) {
      System.out.printf("Employee %d is a %s%n", j, es[j].getClass().getName());
    }
  }
}
