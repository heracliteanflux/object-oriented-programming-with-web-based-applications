### Course Scheduler GUI

#### Table of Contents
1. [`schema.sql`](#schemasql)
2. [`CourseEntry` Class](#courseentry-class)
3. [`StudentEntry` Class](#studententry-class)
4. [`ScheduleEntry` Class](#scheduleentry-class)
5. [`CourseQueries` Class](#coursequeries-class)
6. [`StudentQueries` Class](#studentqueries-class)
7. [`ScheduleQueries` Class](#schedulequeries-class)
8. [`SemesterQueries` Class](#semesterqueries-class)
9. [`DBConnection` Class](#dbconnection-class)
10. [`DBConnectionEmbedded` Class](#dbconnectionembedded-class)
11. [`CourseSchedulerGUI` Class](#courseschedulergui-class)
12. [Scenario](#scenario)
13. [Requirements](#requirements)
14. [Setup](#setup)

#### `schema.sql`

```sql
drop table schedules;
drop table students;
drop table courses;
drop table semesters;

create table semesters (
  semester varchar(100) not null primary key
);

create table courses (
  semester    varchar(100) not null,
  courseCode  varchar(100) not null,
  description varchar(100) not null,
  seats           int      not null,
  foreign key             (semester) references semesters (semester),
  primary key (courseCode, semester)
);

create table students (
  studentID varchar(100) not null primary key,
  firstName varchar(100) not null,
  lastName  varchar(100) not null
);

create table schedules (
  semester   varchar(100) not null,
  courseCode varchar(100) not null,
  studentID  varchar(100) not null,
  status        char(1)   not null,
  timestamp  timestamp    not null,
  foreign key             (semester) references semesters           (semester),
  foreign key (courseCode, semester) references courses (courseCode, semester),
  foreign key (studentID)            references students (studentID),
  primary key (studentID, courseCode, semester)
);
```

#### `CourseEntry` Class

```java
import java.lang.Object;

public class CourseEntry extends Object {

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
      "%s",
      getCourseCode()
    );
  }

}
```

#### `StudentEntry` Class

```java
import java.lang.Object;

public class StudentEntry extends Object {

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

  
  // string representation of object `StudentEntry`
  @Override
  public String toString () {
    return String.format(
      "%s,%s",
      getLastName(),
      getFirstName()
    );
  }
  
}
```

#### `ScheduleEntry` Class

```java
import java.lang.Object;
import java.sql.Timestamp;

public class ScheduleEntry extends Object {

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
```

#### `CourseQueries` Class

```java
import java.lang.Object;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseQueries extends Object {

  private static        Connection conn;
  private static PreparedStatement selectAllCourses;
  private static PreparedStatement insertCourse;
  private static PreparedStatement selectAllCourseCodes;
  private static PreparedStatement selectCourseSeats;
  private static PreparedStatement deleteCourse;
  private static         ResultSet resultSet;


  public static ArrayList<CourseEntry> getAllCourses (String semester) {
    conn = DBConnection.getConnection();
    ArrayList<CourseEntry> results = new ArrayList<CourseEntry>();
    try {
      selectAllCourses = conn.prepareStatement("select * from app.courses where semester = ?");
      selectAllCourses.setString(1, semester);
      resultSet = selectAllCourses.executeQuery();
      while (resultSet.next()) {
        results.add(new CourseEntry(
          resultSet.getString("semester"),
          resultSet.getString("courseCode"),
          resultSet.getString("description"),
          resultSet.getInt("seats")
        ));
      }
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
    return results;
  }


  public static void addCourse (CourseEntry course) {
    conn = DBConnection.getConnection();
    try {
      insertCourse = conn.prepareStatement("insert into app.courses (semester, courseCode, description, seats) values (?, ?, ?, ?)");
      insertCourse.setString(1, course.getSemester());
      insertCourse.setString(2, course.getCourseCode());
      insertCourse.setString(3, course.getDescription());
      insertCourse.setInt(   4,    course.getSeats());
      insertCourse.executeUpdate();
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
  }


  public static ArrayList<String> getAllCourseCodes (String semester) {
    conn = DBConnection.getConnection();
    ArrayList<String> results = new ArrayList<String>();
    try {
      selectAllCourseCodes = conn.prepareStatement("select courseCode from app.courses where semester = ?");
      selectAllCourseCodes.setString(1, semester);
      resultSet = selectAllCourseCodes.executeQuery();
      while (resultSet.next()) {
        results.add(resultSet.getString("courseCode"));
      }
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
    return results;
  }


  public static int getCourseSeats (String semester,
                                    String courseCode)
  {
    conn = DBConnection.getConnection();
    try {
      selectCourseSeats = conn.prepareStatement("select seats from app.courses where semester = ? and courseCode = ?");
      selectCourseSeats.setString(1, semester);
      selectCourseSeats.setString(2, courseCode);
      resultSet = selectCourseSeats.executeQuery();
      resultSet.next();
      return resultSet.getInt("seats");
    }
    catch (SQLException se) {
      se.printStackTrace();
      return 0;
    }
  }

	
  public static void dropCourse (String semester,
                                 String courseCode)
  {
    conn = DBConnection.getConnection();
    try {
      deleteCourse = conn.prepareStatement("delete from app.courses where semester = ? and courseCode = ?");
      deleteCourse.setString(1, semester);
      deleteCourse.setString(2, courseCode);
      deleteCourse.executeUpdate();
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
  }

}
```

#### `StudentQueries` Class

```java
import java.lang.Object;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentQueries extends Object {

  private static        Connection conn;
  private static PreparedStatement insertStudent;
  private static PreparedStatement selectAllStudents;
  private static PreparedStatement selectStudentByName;
  private static PreparedStatement selectStudent;
  private static PreparedStatement deleteStudent;
  private static         ResultSet resultSet;

  
  public static void addStudent (StudentEntry student) {
    conn = DBConnection.getConnection();
    try {
      insertStudent = conn.prepareStatement("insert into app.students (studentID, firstName, lastName) values (?, ?, ?)");
      insertStudent.setString(1, student.getStudentID());
      insertStudent.setString(2, student.getFirstName());
      insertStudent.setString(3, student.getLastName());
      insertStudent.executeUpdate();
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
  }

  
  public static ArrayList<StudentEntry> getAllStudents () {
    conn = DBConnection.getConnection();
    ArrayList<StudentEntry> results = new ArrayList<StudentEntry>();
    try {
      selectAllStudents = conn.prepareStatement("select * from app.students");
      resultSet = selectAllStudents.executeQuery();
      while (resultSet.next()) {
        results.add(new StudentEntry(
          resultSet.getString("studentID"),
          resultSet.getString("firstName"),
          resultSet.getString("lastName")
        ));
      }
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
    return results;
  }
  
  
  public static StudentEntry getStudentByName (String firstName,
                                               String lastName)
  {
    conn = DBConnection.getConnection();
    try {
      selectStudentByName = conn.prepareStatement("select * from app.students where firstName = ? and lastName = ?");
      selectStudentByName.setString(1, firstName);
      selectStudentByName.setString(2, lastName);
      resultSet = selectStudentByName.executeQuery();
      resultSet.next();
      return new StudentEntry(
        resultSet.getString(1),
        resultSet.getString(2),
         resultSet.getString(3)
      );
    }
    catch (SQLException se) {
      se.printStackTrace();
      return new StudentEntry();
    }
  }

  
  public static StudentEntry getStudent (String studentID) {
    conn = DBConnection.getConnection();
    try {
      selectStudent = conn.prepareStatement("select * from app.students where studentID = ?");
      selectStudent.setString(1, studentID);
      resultSet = selectStudent.executeQuery();
      return new StudentEntry(
        resultSet.getString("studentID"),
        resultSet.getString("firstName"),
        resultSet.getString("lastName")
      );
    }
    catch (SQLException se) {
      se.printStackTrace();
      return new StudentEntry();
    }
  }

  
  public static void dropStudent (String studentID) {
    conn = DBConnection.getConnection();
    try {
      deleteStudent = conn.prepareStatement("delete from app.students where studentID = ?");
      deleteStudent.setString(1, studentID);
      deleteStudent.executeUpdate();
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
  }

}
```

#### `ScheduleQueries` Class

```java
import java.lang.Object;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ScheduleQueries extends Object {

  private static        Connection conn;
  private static PreparedStatement insertScheduleEntry;
  private static PreparedStatement selectScheduleByStudent;
  private static PreparedStatement selectScheduledStudentCount;
  // private static PreparedStatement selectScheduledStudentsByCourse;
  // private static PreparedStatement selectWaitlistedStudentsByCourse;
  // private static PreparedStatement deleteStudentScheduleByCourse;
  // private static PreparedStatement deleteScheduleByCourse;
  // private static PreparedStatement updateScheduleEntry;
  private static         ResultSet resultSet;

  // selectScheduledStudentsByCourse  = conn.prepareStatement("select count(*) from schedules where status = 'S' and courseCode = ?");
  // selectWaitlistedStudentsByCourse = conn.prepareStatement("select count(*) from schedules where status = 'W' and courseCode = ?");
  // deleteStudentScheduleByCourse    = conn.prepareStatement("delete from schedules where semester = ? and courseCode = ? and studentID = ? ");
  // deleteScheduleByCourse           = conn.prepareStatement("delete from schedules where semester = ? and courseCode = ?");
  // updateScheduleEntry              = conn.prepareStatement("update schedules set courseCode = ?, studentID = ?, status = ?, timestamp = ? where semester = ?");

  
  public static void addScheduleEntry (ScheduleEntry entry) {
    conn = DBConnection.getConnection();
    try {
      insertScheduleEntry = conn.prepareStatement("insert into app.schedules (semester, courseCode, studentID, status, timestamp) values (?, ?, ?, ?, ?)");
      insertScheduleEntry.setString(1, entry.getSemester());
      insertScheduleEntry.setString(2, entry.getCourseCode());
      insertScheduleEntry.setString(3, entry.getStudentID());
      insertScheduleEntry.setString(4, Character.toString(entry.getStatus()));
      insertScheduleEntry.setTimestamp(5, entry.getTimestamp());
      insertScheduleEntry.executeUpdate();
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
  }

  
  public static ArrayList<ScheduleEntry> getScheduleByStudent (String semester,
                                                               String studentID)
  {
    conn = DBConnection.getConnection();
    ArrayList<ScheduleEntry> results = new ArrayList<ScheduleEntry>();
    try {
      selectScheduleByStudent = conn.prepareStatement("select * from app.schedules where semester = ? and studentID = ?");
      selectScheduleByStudent.setString(1, semester);
      selectScheduleByStudent.setString(2, studentID);
      resultSet = selectScheduleByStudent.executeQuery();
      while (resultSet.next()) {
        results.add(new ScheduleEntry(
          resultSet.getString("semester"),
          resultSet.getString("courseCode"),
          resultSet.getString("studentID"),
          resultSet.getString("status").charAt(0),
          resultSet.getTimestamp("timestamp")
        ));
      }
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
    return results;
  }

  
  public static int getScheduledStudentCount (String semester,
                                              String courseCode)
  {
    conn = DBConnection.getConnection();
    int count = 0;
    try {
      selectScheduledStudentCount = conn.prepareStatement("select count(*) from app.schedules where semester = ? and courseCode = ?");
      selectScheduledStudentCount.setString(1, semester);
      selectScheduledStudentCount.setString(2, courseCode);
      resultSet = selectScheduledStudentCount.executeQuery();
      // resultSet.last();
      // numberOfRows = resultSet.getRow();
      resultSet.next();
      count = resultSet.getInt(1);
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
    return count;
  }
  
}
```

#### `SemesterQueries` Class

```java
import java.lang.Object;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SemesterQueries extends Object {

  private static        Connection conn;
  private static PreparedStatement insertSemester;
  private static PreparedStatement selectSemesterList;
  private static PreparedStatement deleteSemester;
  private static         ResultSet resultSet;

  
  public static void addSemester (String semester) {
    conn = DBConnection.getConnection();
    try {
      insertSemester = conn.prepareStatement("insert into app.semesters (semester) values (?)");
      insertSemester.setString(1, semester);
      insertSemester.executeUpdate();
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
  }


  public static ArrayList<String> getSemesterList () {
    conn = DBConnection.getConnection();
    ArrayList<String> results = new ArrayList<String>();
    try {
      selectSemesterList = conn.prepareStatement("select semester from app.semesters");
      resultSet = selectSemesterList.executeQuery();
      while (resultSet.next()) {
        results.add(resultSet.getString("semester"));
      }
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
    return results;
  }


  public static void dropSemester (String semester) {
    conn = DBConnection.getConnection();
    try {
      deleteSemester = conn.prepareStatement("delete from app.semesters where semester = ?");
      deleteSemester.setString(1, semester);
      deleteSemester.executeUpdate();
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
  }

}
```

#### `DBConnection` Class

```java
import java.lang.Object;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection extends Object {
    
  private static final     String URL  = "jdbc:derby://localhost:1527/CourseSchedulerDB";
  private static final     String USER = "java";
  private static final     String PASS = "java";
  private static       Connection conn;

  public static Connection getConnection () {
    if (conn == null) {
      try {
        conn = DriverManager.getConnection(URL, USER, PASS);
      }
      catch (SQLException se) {
        se.printStackTrace();
        System.out.println("Could not open database.");
        System.exit(1);
      }
    }
    return conn;
  }
    
}
```

#### `DBConnectionEmbedded` Class

```java
import java.lang.Object;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionEmbedded extends Object {

  private static final     String URL  = "jdbc:derby:coursescheduler";
  private static final     String USER = "derby";
  private static final     String PASS = "derby";
  private static       Connection conn;

  public static Connection getConnection () {
    if (conn == null) {
      try {
        conn = DriverManager.getConnection(URL, USER, PASS);
      }
      catch (SQLException se) {
        se.printStackTrace();
        System.out.println("Could not open database.");
        System.exit(1);
      }
    }
    return conn;
  }
    
}
```

#### `CourseSchedulerGUI` Class

```java
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class CourseSchedulerGUI extends JFrame {

    public CourseSchedulerGUI() {
        initComponents();
        rebuildSemestersComboBox();
        rebuildCourseCodesComboBox();
        rebuildStudentNamesComboBox();
    }
    
    private String currentSemester;
    
    public void rebuildSemestersComboBox () {
        ArrayList<String> semesters = SemesterQueries.getSemesterList();
        changeSemesterComboBox.setModel(new javax.swing.DefaultComboBoxModel(semesters.toArray()));
        if (semesters.size() > 0) {
            currentSemesterLabel.setText(semesters.get(0));
            currentSemester = semesters.get(0);
        } else {
            currentSemesterLabel.setText("None, add a semester.");
            currentSemester = "None";
        }
    }
    
    public void rebuildCourseCodesComboBox () {
        String semester = currentSemesterLabel.getText();
        ArrayList<String> courseCodes = CourseQueries.getAllCourseCodes(semester);
        scheduleCoursesCourseComboBox.setModel(new javax.swing.DefaultComboBoxModel(courseCodes.toArray()));
    }
    
    public void rebuildStudentNamesComboBox () {
        ArrayList<StudentEntry> studentNames = StudentQueries.getAllStudents();
        scheduleCoursesStudentComboBox.setModel(new javax.swing.DefaultComboBoxModel(studentNames.toArray()));
        displayScheduleStudentComboBox.setModel(new javax.swing.DefaultComboBoxModel(studentNames.toArray()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        currentSemesterLabel = new javax.swing.JLabel();
        changeSemesterComboBox = new javax.swing.JComboBox<>();
        changeSemesterButton = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        addSemesterTextField = new javax.swing.JTextField();
        addSemesterButton = new javax.swing.JButton();
        addSemesterStatusLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        addCourseCodeTextField = new javax.swing.JTextField();
        addCourseButton = new javax.swing.JButton();
        addCourseStatusLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        addCourseSeatsTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        addCourseDescriptionTextField = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        addStudentIDTextField = new javax.swing.JTextField();
        addStudentFirstNameTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        addStudentLastNameTextField = new javax.swing.JTextField();
        addStudentButton = new javax.swing.JButton();
        addStudentStatusLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        displayCoursesTable = new javax.swing.JTable();
        displayCoursesDisplayButton = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        scheduleCoursesSubmitButton = new javax.swing.JButton();
        scheduleCourseStatusButton = new javax.swing.JLabel();
        scheduleCoursesCourseComboBox = new javax.swing.JComboBox<>();
        scheduleCoursesStudentComboBox = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        displayScheduleTable = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        displayScheduleStudentComboBox = new javax.swing.JComboBox<>();
        displayScheduleDisplayButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Futura", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 153, 255));
        jLabel1.setText("Course Scheduler");

        jLabel2.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 153, 255));
        jLabel2.setText("Current Semester :");

        currentSemesterLabel.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        currentSemesterLabel.setForeground(new java.awt.Color(102, 153, 255));
        currentSemesterLabel.setText("None");

        changeSemesterComboBox.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        changeSemesterComboBox.setForeground(new java.awt.Color(102, 153, 255));
        changeSemesterComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        changeSemesterButton.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        changeSemesterButton.setForeground(new java.awt.Color(102, 153, 255));
        changeSemesterButton.setText("Change Semester");
        changeSemesterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeSemesterButtonActionPerformed(evt);
            }
        });

        jTabbedPane1.setForeground(new java.awt.Color(102, 153, 255));
        jTabbedPane1.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N

        jTabbedPane2.setForeground(new java.awt.Color(102, 153, 255));
        jTabbedPane2.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 153, 255));
        jLabel4.setText("Semester");

        addSemesterTextField.setColumns(20);
        addSemesterTextField.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        addSemesterTextField.setForeground(new java.awt.Color(102, 153, 255));

        addSemesterButton.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        addSemesterButton.setForeground(new java.awt.Color(102, 153, 255));
        addSemesterButton.setText("Submit");
        addSemesterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSemesterButtonActionPerformed(evt);
            }
        });

        addSemesterStatusLabel.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        addSemesterStatusLabel.setForeground(new java.awt.Color(102, 153, 255));
        addSemesterStatusLabel.setText("          ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addComponent(addSemesterButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(addSemesterStatusLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(addSemesterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(283, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(addSemesterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSemesterButton)
                    .addComponent(addSemesterStatusLabel)))
        );

        jTabbedPane2.addTab("Add Semester", jPanel3);

        jLabel3.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 153, 255));
        jLabel3.setText("Course Code");

        addCourseCodeTextField.setColumns(20);
        addCourseCodeTextField.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        addCourseCodeTextField.setForeground(new java.awt.Color(102, 153, 255));

        addCourseButton.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        addCourseButton.setForeground(new java.awt.Color(102, 153, 255));
        addCourseButton.setText("Submit");
        addCourseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCourseButtonActionPerformed(evt);
            }
        });

        addCourseStatusLabel.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        addCourseStatusLabel.setForeground(new java.awt.Color(102, 153, 255));
        addCourseStatusLabel.setText("          ");

        jLabel5.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 153, 255));
        jLabel5.setText("Description");

        addCourseSeatsTextField.setColumns(20);
        addCourseSeatsTextField.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        addCourseSeatsTextField.setForeground(new java.awt.Color(102, 153, 255));

        jLabel6.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 153, 255));
        jLabel6.setText("Seats");

        addCourseDescriptionTextField.setColumns(20);
        addCourseDescriptionTextField.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        addCourseDescriptionTextField.setForeground(new java.awt.Color(102, 153, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addCourseSeatsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addCourseCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addCourseDescriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel6)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(addCourseButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addCourseStatusLabel)))
                .addContainerGap(249, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(addCourseCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(addCourseDescriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(addCourseSeatsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addCourseButton)
                    .addComponent(addCourseStatusLabel)))
        );

        jTabbedPane2.addTab("Add Course", jPanel4);

        jLabel7.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 153, 255));
        jLabel7.setText("Student ID");

        jLabel8.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 153, 255));
        jLabel8.setText("First Name");

        addStudentIDTextField.setColumns(20);
        addStudentIDTextField.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        addStudentIDTextField.setForeground(new java.awt.Color(102, 153, 255));

        addStudentFirstNameTextField.setColumns(20);
        addStudentFirstNameTextField.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        addStudentFirstNameTextField.setForeground(new java.awt.Color(102, 153, 255));

        jLabel9.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 153, 255));
        jLabel9.setText("Last Name");

        addStudentLastNameTextField.setColumns(20);
        addStudentLastNameTextField.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        addStudentLastNameTextField.setForeground(new java.awt.Color(102, 153, 255));

        addStudentButton.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        addStudentButton.setForeground(new java.awt.Color(102, 153, 255));
        addStudentButton.setText("Submit");
        addStudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStudentButtonActionPerformed(evt);
            }
        });

        addStudentStatusLabel.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        addStudentStatusLabel.setForeground(new java.awt.Color(102, 153, 255));
        addStudentStatusLabel.setText("          ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addStudentLastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addStudentFirstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addStudentIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(270, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(addStudentButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addStudentStatusLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(addStudentIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addStudentFirstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(addStudentLastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addStudentButton)
                    .addComponent(addStudentStatusLabel)))
        );

        jTabbedPane2.addTab("Add Student", jPanel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Admin", jPanel1);

        jTabbedPane3.setForeground(new java.awt.Color(102, 153, 255));
        jTabbedPane3.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N

        displayCoursesTable.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        displayCoursesTable.setForeground(new java.awt.Color(102, 153, 255));
        displayCoursesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Course Code", "Description", "Seats"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(displayCoursesTable);

        displayCoursesDisplayButton.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        displayCoursesDisplayButton.setForeground(new java.awt.Color(102, 153, 255));
        displayCoursesDisplayButton.setText("Display");
        displayCoursesDisplayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayCoursesDisplayButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(displayCoursesDisplayButton)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayCoursesDisplayButton))
        );

        jTabbedPane3.addTab("Display Courses", jPanel6);

        jLabel10.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 153, 255));
        jLabel10.setText("Course");

        jLabel11.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 153, 255));
        jLabel11.setText("Student");

        scheduleCoursesSubmitButton.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        scheduleCoursesSubmitButton.setForeground(new java.awt.Color(102, 153, 255));
        scheduleCoursesSubmitButton.setText("Submit");
        scheduleCoursesSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scheduleCoursesSubmitButtonActionPerformed(evt);
            }
        });

        scheduleCourseStatusButton.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        scheduleCourseStatusButton.setForeground(new java.awt.Color(102, 153, 255));
        scheduleCourseStatusButton.setText("          ");

        scheduleCoursesCourseComboBox.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        scheduleCoursesCourseComboBox.setForeground(new java.awt.Color(102, 153, 255));
        scheduleCoursesCourseComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        scheduleCoursesStudentComboBox.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        scheduleCoursesStudentComboBox.setForeground(new java.awt.Color(102, 153, 255));
        scheduleCoursesStudentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scheduleCoursesStudentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scheduleCoursesCourseComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(502, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(scheduleCoursesSubmitButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scheduleCourseStatusButton)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(scheduleCoursesCourseComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(scheduleCoursesStudentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(scheduleCoursesSubmitButton)
                    .addComponent(scheduleCourseStatusButton)))
        );

        jTabbedPane3.addTab("Schedule Courses", jPanel7);

        displayScheduleTable.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        displayScheduleTable.setForeground(new java.awt.Color(102, 153, 255));
        displayScheduleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Course Code", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(displayScheduleTable);

        jLabel12.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 153, 255));
        jLabel12.setText("Student");

        displayScheduleStudentComboBox.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        displayScheduleStudentComboBox.setForeground(new java.awt.Color(102, 153, 255));
        displayScheduleStudentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        displayScheduleDisplayButton.setFont(new java.awt.Font("Futura", 0, 18)); // NOI18N
        displayScheduleDisplayButton.setForeground(new java.awt.Color(102, 153, 255));
        displayScheduleDisplayButton.setText("Display");
        displayScheduleDisplayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayScheduleDisplayButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(displayScheduleDisplayButton)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayScheduleStudentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(displayScheduleStudentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayScheduleDisplayButton))
        );

        jTabbedPane3.addTab("Display Schedule", jPanel8);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Student", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(currentSemesterLabel)
                                .addGap(102, 102, 102)
                                .addComponent(changeSemesterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(changeSemesterButton)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(currentSemesterLabel)
                    .addComponent(changeSemesterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changeSemesterButton))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>                        

    
    private void addSemesterButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        String semester = addSemesterTextField.getText();
        SemesterQueries.addSemester(semester);
        addSemesterStatusLabel.setText("Semester " + semester + " has been added.");
        rebuildSemestersComboBox();
        
        // set the current semester to the newly-added semester
        currentSemesterLabel.setText(semester);
        
        // clear TextField
        addSemesterTextField.setText("");
    }                                                 

    
    private void addCourseButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                
        String semester          = currentSemesterLabel.getText();
        String courseCode        = addCourseCodeTextField.getText();
        String courseDescription = addCourseDescriptionTextField.getText();
           int courseSeats       = Integer.parseInt(addCourseSeatsTextField.getText());
        CourseEntry course = new CourseEntry(semester, courseCode, courseDescription, courseSeats);
        CourseQueries.addCourse(course);
        addCourseStatusLabel.setText("Course " + course + " has been added.");
        
        // clear TextFields
        addCourseCodeTextField.setText("");
        addCourseDescriptionTextField.setText("");
        addCourseSeatsTextField.setText("");
        
        rebuildCourseCodesComboBox();
    }                                               

    
    private void changeSemesterButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        String semester = changeSemesterComboBox.getSelectedItem().toString();
        currentSemesterLabel.setText(semester);
        rebuildCourseCodesComboBox();
    }                                                    

    
    private void addStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        String studentID = addStudentIDTextField.getText();
        String firstName = addStudentFirstNameTextField.getText();
        String lastName  = addStudentLastNameTextField.getText();
        StudentEntry student = new StudentEntry(studentID, firstName, lastName);
        StudentQueries.addStudent(student);
        addStudentStatusLabel.setText("Student " + student.getFirstName() + " " + student.getLastName() + " has been added.");
        
        // clear TextFields
        addStudentIDTextField.setText("");
        addStudentFirstNameTextField.setText("");
        addStudentLastNameTextField.setText("");
        
        rebuildStudentNamesComboBox();
    }                                                

    
    private void scheduleCoursesSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                            
        String semester    = currentSemesterLabel.getText();
        String courseCode  = scheduleCoursesCourseComboBox.getSelectedItem().toString();
        
        String[] studentNames = scheduleCoursesStudentComboBox.getSelectedItem().toString().split(",");
        String studentID = StudentQueries.getStudentByName(studentNames[1], studentNames[0]).getStudentID();
        
        char status;
        int  seats     = CourseQueries.getCourseSeats(semester, courseCode);
        int  scheduled = ScheduleQueries.getScheduledStudentCount(semester, courseCode);
        if (seats - scheduled <= 0) {
          status = 'W';
          scheduleCourseStatusButton.setText(studentNames[1] + " " + studentNames[0] + " has been waitlisted.");
        }
        else {
          status = 'S';
          scheduleCourseStatusButton.setText(studentNames[1] + " " + studentNames[0] + " has been scheduled. There are " + (seats - scheduled - 1) + " seats left.");
        }
        
        Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
        
        ScheduleEntry scheduleEntry = new ScheduleEntry(semester, courseCode, studentID, status, timestamp);
        ScheduleQueries.addScheduleEntry(scheduleEntry);
    }                                                           

    
    private void displayCoursesDisplayButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                            
        String semester = currentSemesterLabel.getText();
        ArrayList<CourseEntry> courses = CourseQueries.getAllCourses(semester);
        DefaultTableModel displayCoursesTableModel = (DefaultTableModel)displayCoursesTable.getModel();
        
        displayCoursesTableModel.setNumRows(0);
        Object[] rowData = new Object[3];
        for (CourseEntry course : courses) {
          rowData[0] = course.getCourseCode();
          rowData[1] = course.getDescription();
          rowData[2] = course.getSeats();
          displayCoursesTableModel.addRow(rowData);
        }
    }                                                           

    
    private void displayScheduleDisplayButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                             
        String semester = currentSemesterLabel.getText();
        ArrayList<StudentEntry> students = StudentQueries.getAllStudents();
        String studentID = students.get(displayScheduleStudentComboBox.getSelectedIndex()).getStudentID();
        ArrayList<ScheduleEntry> schedule = ScheduleQueries.getScheduleByStudent(semester, studentID);
        DefaultTableModel displayScheduleTableModel = (DefaultTableModel)displayScheduleTable.getModel();
        
        displayScheduleTableModel.setNumRows(0);
        Object[] rowData = new Object[2];
        for (ScheduleEntry scheduleEntry : schedule) {
          rowData[0] = scheduleEntry.getCourseCode();
          rowData[1] = scheduleEntry.getStatus();
          displayScheduleTableModel.addRow(rowData);
        }
    }                                                            

    
    /**
     * @param args the command line arguments
     */
    public static void main (String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CourseSchedulerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CourseSchedulerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CourseSchedulerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CourseSchedulerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CourseSchedulerGUI().setVisible(true);
            }
        });
    }

    
    // Variables declaration - do not modify                     
    private javax.swing.JButton addCourseButton;
    private javax.swing.JTextField addCourseCodeTextField;
    private javax.swing.JTextField addCourseDescriptionTextField;
    private javax.swing.JTextField addCourseSeatsTextField;
    private javax.swing.JLabel addCourseStatusLabel;
    private javax.swing.JButton addSemesterButton;
    private javax.swing.JLabel addSemesterStatusLabel;
    private javax.swing.JTextField addSemesterTextField;
    private javax.swing.JButton addStudentButton;
    private javax.swing.JTextField addStudentFirstNameTextField;
    private javax.swing.JTextField addStudentIDTextField;
    private javax.swing.JTextField addStudentLastNameTextField;
    private javax.swing.JLabel addStudentStatusLabel;
    private javax.swing.JButton changeSemesterButton;
    private javax.swing.JComboBox<String> changeSemesterComboBox;
    private javax.swing.JLabel currentSemesterLabel;
    private javax.swing.JButton displayCoursesDisplayButton;
    private javax.swing.JTable displayCoursesTable;
    private javax.swing.JButton displayScheduleDisplayButton;
    private javax.swing.JComboBox<String> displayScheduleStudentComboBox;
    private javax.swing.JTable displayScheduleTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel scheduleCourseStatusButton;
    private javax.swing.JComboBox<String> scheduleCoursesCourseComboBox;
    private javax.swing.JComboBox<String> scheduleCoursesStudentComboBox;
    private javax.swing.JButton scheduleCoursesSubmitButton;
    // End of variables declaration                   
}

```

#### Scenario

```
current  semester   None
add      semester   2021 Fall
current  semester   2021 Fall
add      semester   2022 Spring
current  semester   2022 spring
add      course     CMPSC 131 - Introduction to Programming - 2 (2022 Spring)
add      course     CMPSC 132 - Data Structures - 2 (2022 Spring)
add      course     PHYS  101 - Introduction to Physics - 2 (2022 Spring)
add      course     BIOL  101 - Introduction to Biology - 2 (2022 Spring)
add      student    111111111 - Sue Jones
add      student    222222222 - Sam Roberts
add      student    333333333 - Shawna Sampson
add      studnet    444444444 - John Jensen
display  courses
schedule course     CMPSC 131 - Sue Jones      (scheduled)
schedule course     PHYS  101 - Sue Jones      (scheduled)
schedule course     PHYS  101 - Sam Roberts    (scheduled)
schedule course     CMPSC 131 - Sam Roberts    (scheduled)
schedule course     BIOL  101 - Sam Roberts    (scheduled)
schedule course     BIOL  101 - Shawna Sampson (scheduled)
schedule course     PHYS  101 - Shawna Sampson (waitlisted)
schedule course     BIOL  101 - Sue Jones      (waitlisted)
display  schedule   Sue Jones
display  schedule   Sam Roberts
display  schedule   Shawna Sampson
change   semester   2021 Fall
current  semester   2021 Fall
add      course     CMPSC 131 - Introduction to Programming - 2 (2021 Fall)
add      course     PHYS  101 - Introduction to Physics - 2 (2021 Fall)
display  courses
schedule course     CMPSC 131 - Sue Jones      (scheduled)
schedule course     PHYS  101 - Sue Jones      (scheduled)
schedule course     PHYS  101 - Shawna Sampson (scheduled)
display schedule    Sue Jones
display schedule    Shawna Sampson
```

![](img/00.png)
![](img/01.png)
![](img/02.png)
![](img/03.png)
![](img/04.png)
![](img/05.png)
![](img/06.png)
![](img/07.png)
![](img/08.png)
![](img/09.png)
![](img/10.png)
![](img/11.png)
![](img/12.png)
![](img/13.png)
![](img/14.png)
![](img/15.png)
![](img/16.png)
![](img/17.png)
![](img/18.png)
![](img/19.png)
![](img/20.png)
![](img/21.png)
![](img/22.png)
![](img/23.png)
![](img/24.png)
![](img/25.png)
![](img/26.png)
![](img/27.png)
![](img/28.png)
![](img/29.png)
![](img/30.png)
![](img/31.png)
![](img/32.png)
![](img/33.png)
![](img/34.png)
![](img/35.png)
![](img/36.png)
![](img/37.png)
![](img/38.png)
![](img/39.png)
![](img/40.png)

#### Requirements

You have been asked to develop a Course Scheduling application for a College. The application will enable two types of Users to perform their necessary functions to schedule courses by semester. The Admin User will perform multiple functions to set up the database so that Students my schedule courses. The functions each User will be able to perform will be described below. This application should have a very nice GUI interface and will be a database driven application. The database used will be Derby. This application must use good Object-Oriented Design and Programming. The database must use good Object-Oriented Design and Programming. There is a very close correlation between Object-Oriented Design and Database Design. Your application design should include at least four classes besides the main GUI class. Your database accesses should be in the classes that correlate with the database tables.

Admin functions
* add semester - A semester is added to the database. The semester is identified by one name.
* add course - A new course is added to the database. The course is identified by the semester it is to be added to, the code for the course, the description of the course and the maximum number of students the course will contain that semester.
* add student - A student is added to the database. The student is identified by a studentID, the student’s first name, and the student’s last name.

Student functions
* schedule course - The student will be scheduled in the class for the specified semester, if there are seats available. If there are no seats available, the student will be put a wait list for that Course. The waiting list must be maintained in the order the students tried to schedule the course.
* display schedule - The Display Schedule function will display the current schedule for a specified student for the current semester.
* display courses - The Display Courses function will display a complete list of courses for the current semester.

GUI guidelines - The user should be required to enter only unknown data. Drop down lists of known data such as Student names, Course Codes, or Semesters should be displayed for the user to select. Combo Boxes should be used for the drop-down lists on the form. When information is requested to be displayed, e.g., for a Display command, all of the requested information must be displayed. When a command is performed, the results of that command should be displayed to the user on the same display without the user needing to use a Display function to see what was done.

Good object-oriented design practices include:
• Use of getter and setter methods for class variables
• Good naming of your classes, methods and variables
• Correct use of static and non-static methods
• The way you split this project into classes.
• All of your updates to the database must be done using SQL statements, do not use ResultSetTableModels to update the database.
• If a SQL statement to update the database needs to contain a variable, then you must use PreparedStatements, do not use concatenation of strings to create the SQL statement.

#### Setup

1

```
$ vim ~/.zshrc
```
```
# JAVA 8
#export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk1.8.0_311.jdk/Contents/Home"

# JAVA 18
export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk-18.0.2.1.jdk/Contents/Home"

# JAVA AUXILIARIES
export PATH="$PATH:$JAVA_HOME/bin"
export DERBY_HOME="$JAVA_HOME/db"
export PATH="$PATH:$JAVA_HOME/db/bin"
export CLASSPATH=".:$DERBY_HOME/lib/derby.jar:$DERBY_HOME/lib/derbyclient.jar:$DERBY_HOME/lib/derbynet.jar:$DERBY_HOME/lib/derbytools.jar"
#:$DERBY_HOME/lib/derbyoptionaltools.jar:$DERBY_HOME/lib/derbyrun.jar:$DERBY_HOME/lib/derbyclient.jar:$DERBY_HOME/lib/derbynet.jar:."
```

2

```
$ echo $CLASSPATH | tr : '\n'
```
```
.
/Library/Java/JavaVirtualMachines/jdk-18.0.2.1.jdk/Contents/Home/db/lib/derby.jar
/Library/Java/JavaVirtualMachines/jdk-18.0.2.1.jdk/Contents/Home/db/lib/derbyclient.jar
/Library/Java/JavaVirtualMachines/jdk-18.0.2.1.jdk/Contents/Home/db/lib/derbynet.jar
/Library/Java/JavaVirtualMachines/jdk-18.0.2.1.jdk/Contents/Home/db/lib/derbytools.jar
```

3

```
which sysinfo
```
```
/Library/Java/JavaVirtualMachines/jdk-18.0.2.1.jdk/Contents/Home/db/bin/sysinfo
```

4

```
sysinfo
```
or
```
$ java org.apache.derby.tools.sysinfo
```
```
------------------ Java Information ------------------
Java Version:    18.0.2.1
Java Vendor:     Oracle Corporation
Java home:       /Library/Java/JavaVirtualMachines/jdk-18.0.2.1.jdk/Contents/Home
Java classpath:  /Library/Java/JavaVirtualMachines/jdk-18.0.2.1.jdk/Contents/Home/db/lib/derbyshared.jar:/Library/Java/JavaVirtualMachines/jdk-18.0.2.1.jdk/Contents/Home/db/lib/derby.jar:/Library/Java/JavaVirtualMachines/jdk-18.0.2.1.jdk/Contents/Home/db/lib/derbynet.jar:/Library/Java/JavaVirtualMachines/jdk-18.0.2.1.jdk/Contents/Home/db/lib/derbytools.jar:/Library/Java/JavaVirtualMachines/jdk-18.0.2.1.jdk/Contents/Home/db/lib/derbyoptionaltools.jar:/Library/Java/JavaVirtualMachines/jdk-18.0.2.1.jdk/Contents/Home/db/lib/derbyclient.jar:.:/Library/Java/JavaVirtualMachines/jdk-18.0.2.1.jdk/Contents/Home/db/lib/derby.jar:/Library/Java/JavaVirtualMachines/jdk-18.0.2.1.jdk/Contents/Home/db/lib/derbyclient.jar:/Library/Java/JavaVirtualMachines/jdk-18.0.2.1.jdk/Contents/Home/db/lib/derbynet.jar:/Library/Java/JavaVirtualMachines/jdk-18.0.2.1.jdk/Contents/Home/db/lib/derbytools.jar
OS name:         Mac OS X
OS architecture: aarch64
OS version:      13.5
Java user name:  davefriedman
Java user home:  /Users/davefriedman
Java user dir:   /Users/davefriedman
java.specification.name: Java Platform API Specification
java.specification.version: 18
java.runtime.version: 18.0.2.1+1-1
--------- Derby Information --------
[/Library/Java/JavaVirtualMachines/jdk-18.0.2.1.jdk/Contents/Home/db/lib/derby.jar] 10.16.1.1 - (1901046)
[/Library/Java/JavaVirtualMachines/jdk-18.0.2.1.jdk/Contents/Home/db/lib/derbytools.jar] 10.16.1.1 - (1901046)
[/Library/Java/JavaVirtualMachines/jdk-18.0.2.1.jdk/Contents/Home/db/lib/derbynet.jar] 10.16.1.1 - (1901046)
[/Library/Java/JavaVirtualMachines/jdk-18.0.2.1.jdk/Contents/Home/db/lib/derbyclient.jar] 10.16.1.1 - (1901046)
[/Library/Java/JavaVirtualMachines/jdk-18.0.2.1.jdk/Contents/Home/db/lib/derbyshared.jar] 10.16.1.1 - (1901046)
[/Library/Java/JavaVirtualMachines/jdk-18.0.2.1.jdk/Contents/Home/db/lib/derbyoptionaltools.jar] 10.16.1.1 - (1901046)
------------------------------------------------------
----------------- Locale Information -----------------
Current Locale :  [English/United States [en_US]]
Found support for locale: [cs]
	 version: 10.16.1.1 - (1901046)
Found support for locale: [de_DE]
	 version: 10.16.1.1 - (1901046)
Found support for locale: [es]
	 version: 10.16.1.1 - (1901046)
Found support for locale: [fr]
	 version: 10.16.1.1 - (1901046)
Found support for locale: [hu]
	 version: 10.16.1.1 - (1901046)
Found support for locale: [it]
	 version: 10.16.1.1 - (1901046)
Found support for locale: [ja_JP]
	 version: 10.16.1.1 - (1901046)
Found support for locale: [ko_KR]
	 version: 10.16.1.1 - (1901046)
Found support for locale: [pl]
	 version: 10.16.1.1 - (1901046)
Found support for locale: [pt_BR]
	 version: 10.16.1.1 - (1901046)
Found support for locale: [ru]
	 version: 10.16.1.1 - (1901046)
Found support for locale: [zh_CN]
	 version: 10.16.1.1 - (1901046)
Found support for locale: [zh_TW]
	 version: 10.16.1.1 - (1901046)
------------------------------------------------------
------------------------------------------------------
```

5 - Embedded Derby

```
$ ij
```
or
```
$ java org.apache.derby.tools.ij
```
```
ij> connect 'jdbc:derby:coursescheduler;create=true;user=derby;password=derby;';
ij> show connections;
ij> run 'coursescheduler.sql';
ij> show tables;
ij> disconnect;
ij> exit;
```

6

```
$ java SemesterQueriesTest
```
```
> []
> 
> [2022 Fall, 2023 Fall, 2023 Spring, 2023 Summer]
> 
> [2022 Fall, 2023 Spring, 2023 Summer]
> 
> [2022 Fall, 2023 Fall, 2023 Spring, 2023 Summer, 2024 Spring]
> 
```

7 - Embedded Derby SQL

```
$ ij
```
```
ij version 10.14
ij> show connections;
No connections available.
ij> connect 'jdbc:derby:coursescheduler;user=derby;password=derby;';
ij> show connections;
CONNECTION0* - 	jdbc:derby:coursescheduler
* = current connection
ij> describe semesters;
COLUMN_NAME         |TYPE_NAME|DEC&|NUM&|COLUM&|COLUMN_DEF|CHAR_OCTE&|IS_NULL&
------------------------------------------------------------------------------
SEMESTER            |VARCHAR  |NULL|NULL|100   |NULL      |200       |NO

1 row selected
ij> select * from semesters;
SEMESTER
----------------------------------------------------------------------------------------------------
2022 Fall
2023 Fall
2023 Spring
2023 Summer
2024 Spring

5 rows selected
ij> select substr(semester, 1, 4) as "year" from semesters;
year
----
2022
2023
2023
2023
2024

5 rows selected
ij> select cast(substr(semester, 1, 4) as int) as "year", substr(semester, 6) as "season" from semesters;
year       |season
----------------------------------------------------------------------------------------------------------------
2022       |Fall
2023       |Fall
2023       |Spring
2023       |Summer
2024       |Spring

5 rows selected
ij> select cast(substr(semester, 1, 4) as int) as "year", substr(semester, 6) as "season" from semesters order by "season";
year       |season
----------------------------------------------------------------------------------------------------------------
2023       |Fall
2022       |Fall
2024       |Spring
2023       |Spring
2023       |Summer

5 rows selected
ij> disconnect;
ij> exit;
```