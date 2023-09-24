package uga.cs4370.mydb.impl;

import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.RelationBuilder;
import uga.cs4370.mydb.Type;
import uga.cs4370.mydb.Cell;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/*After completing the implementation, you should create the schema from Assignment 1 using
your own 'mydb' implementation and insert sample data into the schema. Then, implement the
queries from Assignment 1 using your own relational algebra operators implementation and
print the resulting tables. Additionally, for each operator you implemented, provide one
meaningful query implementation using your 'mydb' implementation and print the tables. Do this
task in a class with the name Main under the default package */
public class Main {

  public static void main(String args[]) {
    RelationBuilder rb = new RelationBuilderImpl();
    Relation rel = rb.newRelation("students",
        Arrays.asList("ID", "fname", "lname", "major"),
        Arrays.asList(Type.INTEGER, Type.STRING, Type.STRING, Type.STRING));
    List<Cell> list = new ArrayList<>();
    list.add(new Cell(1));
    list.add(new Cell("Le"));
    list.add(new Cell("Khoa"));
    list.add(new Cell("Computer Science"));
    rel.insert(list);
    rel.print();
  }
}
