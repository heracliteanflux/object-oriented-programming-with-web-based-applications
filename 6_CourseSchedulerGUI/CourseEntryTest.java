public class CourseEntryTest extends java.lang.Object {
  public static void main (String[] args) {

    CourseEntry   courseEntry1  = new CourseEntry("Summer 2023", "12345", "Course description here.", 25);
    CourseEntry   courseEntry2  = new CourseEntry(  "Fall 2023", "67890","Course description there.", 30);
    CourseEntry[] courseEntries = {courseEntry1, courseEntry2};
    
    for (CourseEntry courseEntry : courseEntries) {
      System.out.printf(
        "Course Entry%n%s%n%s%n%s%n%d%n%n",
        courseEntry.getSemester(),
        courseEntry.getCourseCode(),
        courseEntry.getDescription(),
        courseEntry.getSeats()
      );
    }
  
  }
}
