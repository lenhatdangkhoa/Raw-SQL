package uga.cs4370.mydb.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import uga.cs4370.mydb.Cell;
import uga.cs4370.mydb.RA;
import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.RelationBuilder;
import uga.cs4370.mydb.Type;
import uga.cs4370.mydb.impl.RAImp;
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
    Relation rel = rb.newRelation(
        "rel1",
        Arrays.asList("ID", "Name", "Std"),
        Arrays.asList(Type.INTEGER, Type.STRING, Type.INTEGER));
    Relation rel2 = rb.newRelation(
        "rel2",
        Arrays.asList("Class", "Subject"),
        Arrays.asList(Type.INTEGER, Type.STRING));
    List<Cell> list1 = new ArrayList<>();
    list1.add(new Cell(101));
    list1.add(new Cell("Le"));
    list1.add(new Cell(10));
    rel.insert(list1);
    List<Cell> list2 = new ArrayList<>();
    list2.add(new Cell(102));
    list2.add(new Cell("Tran"));
    list2.add(new Cell(11));
    rel.insert(list2);
    rel2.insert(new Cell(10), new Cell("English"));
    rel2.insert(new Cell(10), new Cell("Math"));
    rel2.insert(new Cell(11), new Cell("Comp"));
    rel2.insert(new Cell(11), new Cell("Music"));

    rel.print();
    rel2.print();
    RA ra = new RAImp();
    Relation rel3 = ra.join(rel, rel2, row -> {
      Cell cell1 = row.get(2);
      Cell cell2 = row.get(3);
      // System.out.println(cell1);
      // System.out.println(cell2);

      if (cell1.equals(cell2)) {
        return true;
      }
      return false;
    });

    rel3.print();

  }
}
