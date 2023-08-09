public class CourseEntry extends java.lang.Object {

  private String semester;
  private String courseCode;
  private String description;
  private    int seats;


  // constructor
  public CourseEntry () {}
  public CourseEntry (String semester,
                      String courseCode,
                      String description,
                         int seats)
  {
    this.semester    = semester;
    this.courseCode  = courseCode;
    this.description = description;
    this.seats       = seats;
  }
  // END constructor


  // getters
  public String getSemester    () { return semester; }
  public String getCourseCode  () { return courseCode; }
  public String getDescription () { return description; }
  public    int getSeats       () { return seats; }
  // END getters


  // string representation of object `CourseEntry`
  @Override
  public String toString () {
    return String.format(
      "Course Entry%n%s%n%s%n%s%n%d%n%n",
      getSemester(),
      getCourseCode(),
      getDescription(),
      getSeats()
    );
  }
  
}
