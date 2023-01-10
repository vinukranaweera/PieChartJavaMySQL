/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vinuk
 */
import java.sql.*;

public class ConnectData {
    private Connection conndb;
    private static String username = "root";
    private static String password = "Tigershark*456";
    private int Finalcount; // total number of students
    String[] storage; // created array for number of students because I was not able to use AggregateGrade 


    public ConnectData() {
        conndb = null;
        try {
            // import connector jar
            Class.forName("com.mysql.cj.jdbc.Driver");
            // jdbc:mysql://localhost:3306/Software_design ; added ?allowLoadLocalInfile=true to read textfile
            conndb = DriverManager.getConnection("jdbc:mysql://localhost:3306/Software_design?allowLoadLocalInfile=true", username, password);

            if (conndb != null) {
                System.out.println("Connected to server.");
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException:	" + ex.getMessage());
        } catch (SQLException sq) {
            System.out.println("SQLException: " + sq.getMessage());
        }
        
        Populate();
        createTables();
        Insert();
        ClassValue();
        CoursesValue();
        StudentsValue();
        PieChart();

    }

    public void createTables() {
        Statement statement = null;
        try {// reads primary key is not null and unique          
            PreparedStatement studentTable = conndb.prepareStatement("CREATE TABLE IF NOT EXISTS Students"
                    + "("
                    + "empID INT UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "firstName VARCHAR(255),"
                    + "lastName VARCHAR(255),"
                    + "email VARCHAR(255),"
                    + "gender VARCHAR(255),"
                    + "PRIMARY KEY(empID)"
                    + ")");

            studentTable.execute();
          
            PreparedStatement scheduletable = conndb.prepareStatement("CREATE TABLE IF NOT EXISTS Schedule"
                    + "("
                    + "courseID CHAR(12) NOT NULL UNIQUE,"
                    + "sectionNumber VARCHAR(8) NOT NULL UNIQUE,"
                    + "title VARCHAR(64),"
                    + "year INT,"
                    + "semester CHAR(6),"
                    + "instructor VARCHAR(24),"
                    + "department CHAR(16),"
                    + "program VARCHAR(48),"
                    + "PRIMARY KEY(courseID, sectionNumber)"
                    + ")");

            scheduletable.execute();

            PreparedStatement courseTable = conndb.prepareStatement("CREATE TABLE IF NOT EXISTS Courses"
                    + "("
                    + "courseID CHAR(12) NOT NULL UNIQUE,"
                    + "title VARCHAR(64),"
                    + "department CHAR(16),"
                    + "PRIMARY KEY(courseID)"
                    + ")");

            courseTable.execute();
            
            PreparedStatement Aggregatetable = conndb.prepareStatement("CREATE TABLE IF NOT EXISTS AggregateGrade"
                    + "("
                    + "Grade CHAR,"
                    + "numberStudents INT"
                    + ")");
            
            Aggregatetable.execute();
            
            PreparedStatement classTable = conndb.prepareStatement("CREATE TABLE IF NOT EXISTS Classes"
                    + "("
                    + "courseID INT UNSIGNED not NULL,"
                    + "studentID INT UNSIGNED not NULL,"
                    + "section INT UNSIGNED not NULL,"
                    + "year INT,"
                    + "semester VARCHAR(255),"
                    + "grade VARCHAR(255),"
                    + "PRIMARY KEY(section,studentID,courseID),"
                    + "FOREIGN KEY(studentID) REFERENCES Students(empID)"
                 //   + "FOREIGN KEY(courseID) REFERENCES Courses(empID)"
                    + ")");

            classTable.execute();
           /*
            PreparedStatement classTable1 = conndb.prepareStatement("CREATE TABLE IF NOT EXISTS Class1"
                    + "("
                    + "studentID INT UNSIGNED NOT NULL,"
                    + "grade VARCHAR(6)"
                    + ")");

            classTable1.execute();
            
            PreparedStatement classTable2 = conndb.prepareStatement("CREATE TABLE IF NOT EXISTS Class2"
                    + "("
                    + "courseID CHAR(12) NOT NULL UNIQUE,"
                    + "sectionNumber VARCHAR(8) NOT NULL UNIQUE,"
                    + "year INT,"
                    + "semester CHAR(6)"    
                    + ")");

            classTable2.execute();
            */
        }

        catch (Exception ex) {
            System.out.println("Exception1: " + ex.getMessage());
        }


    }
    
    
    public void Populate() {

        Statement statement = null;
        String path = "/Users/vinuk/Downloads/ScheduleSpring2021.txt";
        
        try {
            statement = conndb.createStatement();
            Statement statement1 = conndb.createStatement();
            Statement statement2 = conndb.createStatement();
            
            statement.execute(" SET GLOBAL local_infile = 'ON' ");
            statement1.executeUpdate(" LOAD DATA LOCAL INFILE '" + path +
                            "' INTO TABLE Schedule " +
                            " FIELDS TERMINATED BY '\t' " +
                            " LINES TERMINATED BY '\n' "+
                            " IGNORE 1 LINES ");
            statement2.execute("SELECT * FROM Software_design.Schedule");

        } catch (Exception ex) {
            System.out.println("Exception2:" + ex.getMessage());
        }
    }
    
    public void Insert() {
        try {
            drop();
            
            PreparedStatement resetPrimary = conndb.prepareStatement("ALTER TABLE Students AUTO_INCREMENT = 1"); // reads unique IDs
            resetPrimary.execute();
         /*   
            PreparedStatement resetPrimary1 = conndb.prepareStatement("ALTER TABLE Classes AUTO_INCREMENT = 1"); // reads unique IDs
            resetPrimary1.execute();
*/
            PreparedStatement insertStudent = conndb.prepareStatement("INSERT INTO Students(firstName,lastName,email,gender)"
                    + "VALUES('Corey','Richardson','corey@gmail.com','M'),"
                    + "('Toby','Ibarra','toby@gmail.com','M'),"
                    + "('Eli','Morrow','eli@gmail.com','M'),"
                    + "('Joy','Fischer','joy@gmail.com','F'),"
                    + "('Denny','Kaufman','denny@gmail.com','M'),"
                    + "('Doris','Mullins','doris@gmail.com','F'),"
                    + "('Yong','Fitzpatrick','yong@gmail.com','M'),"
                    + "('Leanna','Cruz','leanna@gmail.com','F'),"
                    + "('Brandie','Simpson','brandie@gmail.com','U'),"
                    + "('Harris','Green','harris@gmail.com','M'),"
                    + "('Rodrigo','Bean','rodrigo@gmail.com','M'),"
                    + "('Sylvia','Mercer','sylvia@gmail.com','F'),"
                    + "('Dallas','Galloway','dallas@gmail.com','M'),"
                    + "('Arline','Case','arline@gmail.com','U'),"
                    + "('Eusebio','Leon','eusebio@gmail.com','M'),"
                    + "('Juliana','Velasquez','juliana@gmail.com','F'),"
                    + "('Michael','Frazier','michael@gmail.com','M'),"
                    + "('Arron','Farrell','arron@gmail.com','M'),"
                    + "('Kristina','Smith','kristina@gmail.com','F'),"
                    + "('Blanca','Skinner','blanca@gmail.com','F'),"
                    + "('Jermaine','Ortiz','jermaine@gmail.com','M'),"
                    + "('Leigh','Faulkner','leigh@gmail.com','F'),"
                    + "('Nicholas','Sherman','nicholas@gmail.com','M'),"
                    + "('Amelia','Ramos','amelia@gmail.com','F'),"
                    + "('John','Lee','john@gmail.com','M')");

            insertStudent.execute();

            PreparedStatement insertCourses = conndb.prepareStatement("INSERT INTO Courses(courseID, title, department)"
                    + "SELECT courseID,title,department FROM Software_design.Schedule");
            insertCourses.execute();
            
            PreparedStatement insertClasses = conndb.prepareStatement("INSERT INTO Classes(courseID, studentID, section, year, semester, grade)"
                    + "VALUES(22100,1,32131,2021,'Spring','B'),"
                    + "(22100,2,32131,2021,'Spring','B'),"
                    + "(22100,3,32131,2021,'Spring','A'),"
                    + "(22100,4,32131,2021,'Spring','C'),"
                    + "(22100,5,32131,2021,'Spring','B'),"
                    + "(22100,6,32131,2021,'Spring','C'),"
                    + "(22100,7,32131,2021,'Spring','A'),"
                    + "(22100,8,32131,2021,'Spring','F'),"
                    + "(22100,9,32132,2021,'Spring','A'),"
                    + "(22100,10,32132,2021,'Spring','C'),"
                    + "(22100,11,32132,2021,'Spring','A'),"
                    + "(22100,12,32132,2021,'Spring','A'),"
                    + "(22100,13,32132,2021,'Spring','W'),"
                    + "(22100,14,32132,2021,'Spring','C'),"
                    + "(22100,15,32132,2021,'Spring','W'),"
                    + "(22100,16,32132,2021,'Spring','B'),"
                    + "(22100,17,32150,2021,'Spring','B'),"
                    + "(22100,18,32150,2021,'Spring','F'),"
                    + "(22100,19,32150,2021,'Spring','A'),"
                    + "(22100,20,32150,2021,'Spring','C'),"
                    + "(22100,21,32150,2021,'Spring','B'),"
                    + "(22100,22,32150,2021,'Spring','A'),"
                    + "(22100,23,32150,2021,'Spring','A'),"
                    + "(22100,24,32150,2021,'Spring','D'),"
                    + "(22100,25,32150,2021,'Spring','F')");

            insertClasses.execute();

           /*
            PreparedStatement insertClasses1 = conndb.prepareStatement("INSERT INTO Class1(studentID,grade)"
                    + "VALUES(1,'B'),"
                    + "(2,'B'),"
                    + "(3,'A'),"
                    + "(4,'C'),"
                    + "(5,'B'),"
                    + "(6,'C'),"
                    + "(7,'A'),"
                    + "(8,'F'),"
                    + "(9,'A'),"
                    + "(10,'C'),"
                    + "(11,'A'),"
                    + "(12,'A'),"
                    + "(13,'W'),"
                    + "(14,'C'),"
                    + "(15,'W'),"
                    + "(16,'B'),"
                    + "(17,'B'),"
                    + "(18,'F'),"
                    + "(19,'A'),"
                    + "(20,'C'),"
                    + "(21,'B'),"
                    + "(22,'A'),"
                    + "(23,'A'),"
                    + "(24,'D'),"
                    + "(25,'F')");

            insertClasses1.execute();
            
            PreparedStatement insertClasses2 = conndb.prepareStatement("INSERT INTO Class2(courseID, sectionNumber, year, semester)"
                    + "SELECT courseID,sectionNumber,year,semester FROM Software_design.Schedule");
            insertClasses2.execute();
            
            PreparedStatement insertClasses = conndb.prepareStatement("INSERT INTO Classes(courseID, sectionNumber, year, semester)"
                    + "SELECT * FROM Software_design.Class2"
                    + "SELECT * FROM Software_design.Class1");
            insertClasses.execute();
            */
            
            PreparedStatement insertAggregate = conndb.prepareStatement("INSERT INTO AggregateGrade(grade, numberStudents)"
                    + "SELECT grade,count(grade) FROM Software_design.Classes GROUP BY grade");
            insertAggregate.execute();
            
        } catch (Exception ex) {
            System.out.println("Exception2:" + ex.getMessage());
        }
    }


    public void drop() {
        try {
            PreparedStatement Student = conndb.prepareStatement("DELETE FROM Students");
            PreparedStatement Courses = conndb.prepareStatement("DELETE FROM Courses");
            PreparedStatement Classes = conndb.prepareStatement("DELETE FROM Classes");
          //  PreparedStatement Class1 = conndb.prepareStatement("DELETE FROM Class1");
          //  PreparedStatement Class2 = conndb.prepareStatement("DELETE FROM Class2");
            PreparedStatement aggregate = conndb.prepareStatement("DELETE FROM AggregateGrade");
            Classes.execute();
            Student.execute();
            Courses.execute();
          //  Class1.execute();
          //  Class2.execute();
            aggregate.execute();


        } catch (Exception ex) {
            System.out.println("DropException:" + ex.getMessage());
        }

    }

    public void ClassValue() {
        String print;
        int count = 0;

        try {
            Statement show = conndb.createStatement();
            ResultSet result = show.executeQuery("SELECT * FROM Software_design.Classes WHERE courseID = 22100"); // IN '22100 F', '22100 P', '22100 R')
            ResultSetMetaData r = result.getMetaData();
            int numColumns = r.getColumnCount();

            System.out.println("courseID  studentID   sectionNo   Year   Semester    grade");
            while (result.next()) {
                print = "";
                for (int i = 1; i <= numColumns; i++) {

                    String temp = result.getString(i);
                    if (temp != null) {
                        print += temp + "-------";
                        count++;

                    }

                }

                System.out.println(print);

            }
            Finalcount = count / numColumns;
            storage = new String[Finalcount];

            // count = number of students
            System.out.println("A total of " + Finalcount + " students are enrolled in CSC22100 in Spring 2021.");
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }

    public void StudentsValue() 
    {
        String print;


        try {
            Statement show = conndb.createStatement();
            ResultSet result = show.executeQuery("SELECT * FROM Software_design.Students");
            ResultSetMetaData r = result.getMetaData();
            int numColumns = r.getColumnCount();

            System.out.println("empID  firstName lastName         email             gender");
            while (result.next()) {
                print = "";
                for (int i = 1; i <= numColumns; i++) {

                    String temp = result.getString(i);
                    if (temp != null) {
                        print += temp + "--------";

                    }

                }

                System.out.println(print);

            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void CoursesValue() //To see values for course table
    {
        String print;

        try {
            Statement show = conndb.createStatement(); 
            ResultSet result = show.executeQuery("SELECT * FROM Software_design.Courses");
            ResultSetMetaData r = result.getMetaData();
            int numColumns = r.getColumnCount();

            System.out.println("");
            while (result.next()) {
                print = "";
                for (int i = 1; i <= numColumns; i++) {

                    String temp = result.getString(i);
                    if (temp != null) {
                        print += temp + "-------";

                    }

                }

                System.out.println(print);

            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void PieChart() {
        int j = 0;
        String print;
        try {
            Statement show = conndb.createStatement();
            ResultSet result = show.executeQuery("SELECT grade FROM Software_design.Classes WHERE courseID = 22100"); // IN '22100 F', '22100 P', '22100 R')
            ResultSetMetaData r = result.getMetaData();
            int numColumns = r.getColumnCount();

            System.out.println("grade");
            while (result.next()) {
                print = "";
                for (int i = 1; i <= numColumns; i++) {
                    String temp = result.getString(i);

                    if (temp != null) {
                        print += temp;
                    }

                }

                System.out.println(print);
                storage[j] = print;
                j++;
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }


    //for my use only
    public int getNumberOfStudent() {
        return Finalcount;
    }


    public void getArray() {

        for (int i = 0; i < storage.length; i++) {
            System.out.println(storage[i]);
        }
    }

}
