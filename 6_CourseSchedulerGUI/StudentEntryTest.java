public class StudentEntryTest extends java.lang.Object {
  public static void main (String[] args) {

    StudentEntry   studentEntry1  = new StudentEntry("987654321", "Alice", "Eve");
    StudentEntry   studentEntry2  = new StudentEntry("976543218", "John", "Smith");
    StudentEntry[] studentEntries = {studentEntry1, studentEntry2};
    
    for (StudentEntry studentEntry : studentEntries) {
      System.out.printf(
        "Student Entry%n%s%n%s%n%s%n%n",
        studentEntry.getStudentID(),
        studentEntry.getFirstName(),
        studentEntry.getLastName()
      );
    }
  
  }
}
