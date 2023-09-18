package uga.cs4370.mydb.impl;

import java.util.List;
/* public class */
import uga.cs4370.mydb.Cell;
/* Interfaces */
import uga.cs4370.mydb.Predicate;
import uga.cs4370.mydb.RA;
import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.RelationBuilder;
/* public enum */
import uga.cs4370.mydb.Type;

/**
 * You must implement the provided interfaces in this project. You should not modify the provided
interfaces. However, you can include additional methods in the implementing classes as
needed.

Your implementation must use the interfaces as types whenever it is possible instead of
using concrete implementing classes as types. You can not use any external libraries in this
project. You can use Java built in data structures.
 */
public class implementation
  implements Predicate, RA, Relation, RelationBuilder {

  public String getName() {}

  public int getSize() {}

  public List<List<Cell>> getRows() {}

  public List<Type> getTypes() {}

  public List<String> getAttrs() {}

  public boolean hasAttr(String attr) {}

  public int getAttrIndex(String attr) {}

  public void insert(Cell... cells) {}

  public void insert(List<Cell> cells) {}

  public void print() {}

  public boolean check(List<Cell> row) {}
}
