public class StudentEntry extends java.lang.Object {

  private String studentID;
  private String firstName;
  private String lastName;


  // constructor
  public StudentEntry () {}
  public StudentEntry (String studentID,
                       String firstName,
                       String lastName)
  {
    this.studentID = studentID;
    this.firstName = firstName;
    this.lastName  = lastName;
  }
  // END constructor


  // getters
  public String getStudentID () { return studentID; }
  public String getFirstName () { return firstName; }
  public String getLastName  () { return lastName; }
  // END getters

}
