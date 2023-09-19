package uga.cs4370.mydb.impl;

import java.util.List;
/* public class */
import uga.cs4370.mydb.Cell;
/* Interfaces */

import uga.cs4370.mydb.Relation;
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
public class Relationimplementation implements Relation {

  private String name;
  private List<String> attrs;
  private List<Type> types;

  Relationimplementation(String name, List<String> attrs, List<Type> types) {
    this.name = name;
    this.attrs = attrs;
    this.types = types;
  }

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
}
