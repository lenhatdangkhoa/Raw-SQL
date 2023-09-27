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
        "rel1",
        Arrays.asList("ID", "fname", "lname", "major"),
        Arrays.asList(Type.INTEGER, Type.STRING, Type.STRING, Type.STRING));
    Relation rel2 = rb.newRelation(
        "rel2",
        Arrays.asList("ID", "wname", "bname", "year"),
        Arrays.asList(Type.INTEGER, Type.STRING, Type.STRING, Type.STRING));
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
        new Cell("Men's Studies"));
    rel2.insert(new Cell(1), new Cell("Dad"), new Cell("nihao"), new Cell("2023"));
    rel2.insert(new Cell(6), new Cell("Mom"), new Cell("zhizheng"), new Cell("2019"));
    rel2.insert(new Cell(1), new Cell("Kid"), new Cell("Pao"), new Cell("2018"));

    rel.print();
    rel2.print();
    RA ra = new RAImp();
    Relation rel3 = ra.join(rel, rel2);
    rel3.print();
  }
}
