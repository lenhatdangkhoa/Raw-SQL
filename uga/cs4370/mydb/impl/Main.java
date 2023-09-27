package uga.cs4370.mydb.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import uga.cs4370.mydb.Cell;
import uga.cs4370.mydb.RA;
import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.RelationBuilder;
import uga.cs4370.mydb.Type;
import uga.cs4370.mydb.Predicate;

/*After completing the implementation, you should create the schema from Assignment 1 using
your own 'mydb' implementation and insert sample data into the schema. Then, implement the
queries from Assignment 1 using your own relational algebra operators implementation and
print the resulting tables. Additionally, for each operator you implemented, provide one
meaningful query implementation using your 'mydb' implementation and print the tables. Do this
task in a class with the name Main under the default package */
public class Main {

  public static void main(String args[]) {
    RelationBuilder rb = new RelationBuilderImpl();
    Relation student = rb.newRelation(
        "Students",
        Arrays.asList("StudentID", "FName", "LName", "DoB", "Major"),
        Arrays.asList(Type.INTEGER, Type.STRING, Type.STRING, Type.STRING, Type.STRING));
    Relation course = rb.newRelation(
        "Courses",
        Arrays.asList("CourseID", "CName", "Credits"),
        Arrays.asList(Type.INTEGER, Type.STRING, Type.INTEGER));
    Relation enrollment = rb.newRelation(
        "Enrollment",
        Arrays.asList("EnrollmentID", "StudentID", "CourseID", "grade"),
        Arrays.asList(Type.INTEGER, Type.INTEGER, Type.INTEGER, Type.STRING));
    Relation professor = rb.newRelation(
        "Professors",
        Arrays.asList("ProfessorID", "FName", "LName", "department"),
        Arrays.asList(Type.INTEGER, Type.STRING, Type.STRING, Type.STRING));
    Relation teach = rb.newRelation(
        "Teaches",
        Arrays.asList("TeachID", "ProfessorID", "CourseID"),
        Arrays.asList(Type.INTEGER, Type.INTEGER, Type.INTEGER));

    List<Cell> list1 = new ArrayList<>();
    list1.add(new Cell(1234));
    list1.add(new Cell("John"));
    list1.add(new Cell("Wang"));
    list1.add(new Cell("2002-12-11"));
    list1.add(new Cell("business"));
    student.insert(list1);
    student.insert(new Cell(21),
        new Cell("Khoa"),
        new Cell("Le"),
        new Cell("2003-05-06"),
        new Cell("computer science"));
    student.insert(new Cell(3),
        new Cell("Gage"),
        new Cell("Roney"),
        new Cell("2001-02-02"),
        new Cell("computer science"));
    student.insert(new Cell(45),
        new Cell("Owen"),
        new Cell("Na"),
        new Cell("2003-04-12"),
        new Cell("english"));
    student.insert(new Cell(60),
        new Cell("Sammy"),
        new Cell("Beard"),
        new Cell("2003-08-12"),
        new Cell("women studies"));
    student.insert(new Cell(5),
        new Cell("Steven"),
        new Cell("Tran"),
        new Cell("2000-07-01"),
        new Cell("computer science"));
    // student.print();
    course.insert(new Cell(101),
        new Cell("Intro to Finance"),
        new Cell(3));
    course.insert(new Cell(102),
        new Cell("Data Structures"),
        new Cell(4));
    course.insert(new Cell(103),
        new Cell("Algorithms"),
        new Cell(4));
    course.insert(new Cell(104),
        new Cell("Database Management"),
        new Cell(3));
    course.insert(new Cell(105),
        new Cell("Theory of Computation"),
        new Cell(3));

    professor.insert(new Cell(201),
        new Cell("David"),
        new Cell("Smith"),
        new Cell("business"));
    professor.insert(new Cell(202),
        new Cell("Hao"),
        new Cell("Peng"),
        new Cell("computer science"));
    professor.insert(new Cell(203),
        new Cell("Sachin"),
        new Cell("Meena"),
        new Cell("computer science"));
    professor.insert(new Cell(204),
        new Cell("Sami"),
        new Cell("Menik"),
        new Cell("computer science"));

    enrollment.insert(new Cell(1),
        new Cell(1234),
        new Cell(101),
        new Cell("A"));
    enrollment.insert(new Cell(2),
        new Cell(21),
        new Cell(102),
        new Cell("B"));
    enrollment.insert(new Cell(3),
        new Cell(3),
        new Cell(103),
        new Cell("A"));
    enrollment.insert(new Cell(4),
        new Cell(45),
        new Cell(104),
        new Cell("F"));
    enrollment.insert(new Cell(5),
        new Cell(5),
        new Cell(102),
        new Cell("F"));
    teach.insert(new Cell(301),
        new Cell(201),
        new Cell(101));
    teach.insert(new Cell(302),
        new Cell(202),
        new Cell(102));
    teach.insert(new Cell(303),
        new Cell(203),
        new Cell(103));
    teach.insert(new Cell(304),
        new Cell(204),
        new Cell(104));
    student.print();
    course.print();
    professor.print();
    teach.print();
    enrollment.print();

    RA ra = new RAImp();
  }
}
