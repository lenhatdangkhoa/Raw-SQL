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
        student.insert(new Cell(300),
                new Cell("Gage"),
                new Cell("Roney"),
                new Cell("2001-02-02"),
                new Cell("computer science"));
        student.insert(new Cell(445),
                new Cell("Owen"),
                new Cell("Na"),
                new Cell("2003-04-12"),
                new Cell("english"));
        student.insert(new Cell(610),
                new Cell("Sammy"),
                new Cell("Beard"),
                new Cell("2003-08-12"),
                new Cell("women studies"));
        student.insert(new Cell(95),
                new Cell("Steven"),
                new Cell("Tran"),
                new Cell("2000-07-01"),
                new Cell("computer science"));
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
                new Cell(300),
                new Cell(103),
                new Cell("A"));
        enrollment.insert(new Cell(4),
                new Cell(45),
                new Cell(104),
                new Cell("F"));
        enrollment.insert(new Cell(5),
                new Cell(95),
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
        List<String> wantedAttrs = new ArrayList<>();
        RA ra = new RAImp();
        System.out.println("Retrieve all course IDs a student with ID 1234 has enrolled in");
        Relation relation = ra.select(enrollment, n -> {
            if (n.get(1).getAsInt() == 1234) {
                return true;
            } else
                return false;
        });
        wantedAttrs.add("CourseID");
        relation = ra.project(relation, wantedAttrs);
        wantedAttrs.clear();
        relation.print();
        System.out.println("All student names and ids who major in computer science");
        relation = ra.select(student, n -> {
            if (n.get(4).getAsString().equals("computer science")) {
                return true;
            } else
                return false;
        });
        wantedAttrs.addAll(Arrays.asList("StudentID", "FName", "LName"));
        relation = ra.project(relation, wantedAttrs);
        relation.print();
        wantedAttrs.clear();
        System.out.println("Retrieve all course names a student with ID 1234 has enrolled in");
        Relation newRelation = ra.join(course, enrollment);
        relation = ra.select(newRelation, n -> {
            if (n.get(4).getAsInt() == 1234) {
                return true;
            } else
                return false;
        });
        wantedAttrs.add("CName");
        relation = ra.project(relation, wantedAttrs);
        relation.print();
        wantedAttrs.clear();
        System.out.println("List of professor names and IDs who teach courses of more than 2 credits");

        newRelation = ra.join(teach, professor);
        newRelation = ra.join(newRelation, course);
        relation = ra.select(newRelation, n -> {
            if (n.get(7).getAsInt() > 2) {
                return true;
            } else
                return false;
        });
        wantedAttrs.addAll(Arrays.asList("FName", "LName"));
        relation = ra.project(relation, wantedAttrs);
        relation.print();
        wantedAttrs.clear();
        System.out.println("Student names and ids who have not enrolled in any course");
        relation = ra.select(student, n -> {
            List<Cell> temp = new ArrayList<>();
            for (int i = 0; i < enrollment.getRows().size(); i++) {
                temp.add(enrollment.getRows().get(i).get(1));
            }
            if (!temp.contains(n.get(0))) {
                return true;
            }
            return false;

        });
        wantedAttrs.addAll(Arrays.asList("StudentID", "FName", "LName"));
        relation = ra.project(relation, wantedAttrs);
        relation.print();
        wantedAttrs.clear();
        System.out.println("Course names and IDs that no professor teaches");
        relation = ra.select(course, n -> {
            List<Cell> temp = new ArrayList<>();
            for (int i = 0; i < teach.getRows().size(); i++) {
                temp.add(enrollment.getRows().get(i).get(2));
            }
            if (!temp.contains(n.get(0))) {
                return true;
            }
            return false;

        });
        wantedAttrs.addAll(Arrays.asList("CourseID", "CName"));
        relation = ra.project(relation, wantedAttrs);
        relation.print();
        wantedAttrs.clear();
        System.out.println("All student names and their IDs who major in computer science who got an 'F'");
        relation = ra.join(student, enrollment);
        relation = ra.select(relation, n -> {
            if (n.get(4).getAsString().equals("computer science")
                    && n.get(7).getAsString().equals("F")) {
                return true;
            }
            return false;

        });
        wantedAttrs.addAll(Arrays.asList("StudentID", "LName", "FName"));
        relation = ra.project(relation, wantedAttrs);
        relation.print();
        wantedAttrs.clear();
        System.out.println("Professor names and their IDs who teach students doing computer science major");
        relation = ra.join(professor, teach);
        relation = ra.join(relation, course);
        relation = ra.join(relation, enrollment);
        Relation tempRel = ra.select(student, n -> {
            if (n.get(4).getAsString().equals("computer science")) {
                return true;
            }
            ;
            return false;
        });
        List<Cell> temp = new ArrayList<>();
        for (int i = 0; i < tempRel.getRows().size(); i++) {
            temp.add(tempRel.getRows().get(i).get(0));
        }
        relation = ra.select(relation, n -> {
            if (temp.contains(n.get(9))) {
                return true;
            }
            return false;
        });
        wantedAttrs.addAll(Arrays.asList("ProfessorID", "FName", "LName"));
        relation = ra.project(relation, wantedAttrs);
        relation.print();
        wantedAttrs.clear();
        System.out.println("Union between student and student2");
        Relation student2 = rb.newRelation("student2",
                Arrays.asList("StudentID", "FName", "LName", "DoB", "Major"),
                Arrays.asList(Type.INTEGER, Type.STRING, Type.STRING, Type.STRING, Type.STRING));
        relation = ra.union(student, teach);
    }
}
