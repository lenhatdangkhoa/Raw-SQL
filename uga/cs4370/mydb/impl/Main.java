package uga.cs4370.mydb.impl;

import uga.cs4370.mydb.RelationImp;
import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.Type;
import uga.cs4370.mydb.Cell;
import java.util.List;
import java.util.ArrayList;

/*After completing the implementation, you should create the schema from Assignment 1 using
your own 'mydb' implementation and insert sample data into the schema. Then, implement the
queries from Assignment 1 using your own relational algebra operators implementation and
print the resulting tables. Additionally, for each operator you implemented, provide one
meaningful query implementation using your 'mydb' implementation and print the tables. Do this
task in a class with the name Main under the default package */
public class Main {

  public static void main(String args[]) {
    RelationImp rel = new RelationImp();
    Cell c1 = new Cell("Hello");
    List<Cell> row1 = new ArrayList<>();
    row1.add(c1);

  }
}
