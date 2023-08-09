import java.sql.Timestamp;

public class ScheduleEntry extends java.lang.Object {

  private    String semester;
  private    String courseCode;
  private    String studentID;
  private      char status;
  private Timestamp timestamp;


  // constructor
  public ScheduleEntry () {}
  public ScheduleEntry (String semester,
                        String courseCode,
                        String studentID,
                          char status,
                     Timestamp timestamp)
  {
    this.semester   = semester;
    this.courseCode = courseCode;
    this.studentID  = studentID;
    this.status     = status;
    this.timestamp  = timestamp;
  }
  // END constructor


  // getters
  public    String getSemester   () { return semester; }
  public    String getCourseCode () { return courseCode; }
  public    String getStudentID  () { return studentID; }
  public      char getStatus     () { return status; }
  public Timestamp getTimestamp  () { return timestamp; }
  // END getters


  // string representation of object `ScheduleEntry`
  @Override
  public String toString () {
    return String.format(
      "Schedule Entry%n%s%n%s%n%s%n%d%n%s%n%n",
      getSemester(),
      getCourseCode(),
      getStudentID(),
      getStatus(),
      getTimestamp()
    );
  }

}
