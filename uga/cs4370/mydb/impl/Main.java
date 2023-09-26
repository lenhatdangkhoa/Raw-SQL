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
      "students",
      Arrays.asList("ID", "fname", "lname", "major"),
      Arrays.asList(Type.INTEGER, Type.STRING, Type.STRING, Type.STRING)
    );
    Relation rel2 = rb.newRelation(
      "students",
      Arrays.asList("ID", "fname", "lname", "major"),
      Arrays.asList(Type.INTEGER, Type.STRING, Type.STRING, Type.STRING)
    );
    List<Cell> list1 = new ArrayList<>();
    list1.add(new Cell(1));
    list1.add(new Cell("Le"));
    list1.add(new Cell("Khoa"));
    list1.add(new Cell("Computer Science"));
    rel.insert(list1);
    List<Cell> list2 = new ArrayList<>();
    list2.add(new Cell(2));
    list2.add(new Cell("Tran"));
    list2.add(new Cell("Steven"));
    list2.add(new Cell("Women's Studies"));
    rel.insert(list2);
    rel.insert(
      new Cell(3),
      new Cell("Roney"),
      new Cell("Gage"),
      new Cell("Men's Studies")
    );

    List<Cell> list3 = new ArrayList<>();
    list3.add(new Cell(4));
    list3.add(new Cell("Nega"));
    list3.add(new Cell("Le"));
    list3.add(new Cell("Basketball"));
    rel2.insert(list3);
    List<Cell> list4 = new ArrayList<>();
    list4.add(new Cell(3));
    list4.add(new Cell("Roney"));
    list4.add(new Cell("Gage"));
    list4.add(new Cell("Men's Studies"));
    rel2.insert(list4);
    // RA ra = new RAImp();
    // Relation test = ra.diff(rel, rel2);
    // test.print();
    rel.print();
    rel2.print();
    System.out.println(rel.getRows());
  }
}
