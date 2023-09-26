package uga.cs4370.mydb.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.List;
import java.util.Set;
import uga.cs4370.mydb.Cell;
import uga.cs4370.mydb.Predicate;
import uga.cs4370.mydb.RA;
import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.RelationBuilder;
import uga.cs4370.mydb.Type;
import uga.cs4370.mydb.impl.RelationBuilderImpl;
import uga.cs4370.mydb.impl.RelationImp;

public class RAImp implements RA {

  /**
   * Performs the select operation on the relation rel
   * by applying the predicate p.
   *
   * @return The resulting relation after applying the select operation.
   */
  public Relation select(Relation rel, Predicate p) {
    return null;
  }

  /**
   * Performs the project operation on the relation rel
   * given the attributes list attrs.
   *
   * @return The resulting relation after applying the project operation.
   *
   * @throws IllegalArgumentException If attributes in attrs are not
   *                                  present in rel.
   */
  public Relation project(Relation rel, List<String> attrs) {
    return null;
  }

  /**
   * Performs the union operation on the relations rel1 and rel2.
   *
   * @return The resulting relation after applying the union operation.
   *
   * @throws IllegalArgumentException If rel1 and rel2 are not compatible.
   */
  public Relation union(Relation rel1, Relation rel2) {
    List<String> rel = new ArrayList<>();
    List<Type> types = new ArrayList<>();
    List<String> tempAttr = rel1.getAttrs();
    List<Type> tempType = rel1.getTypes();
    for (int i = 0; i < tempAttr.size(); i++) {
      rel.add(tempAttr.get(i));
      types.add(tempType.get(i));
    }
    tempAttr = rel2.getAttrs();
    tempType = rel2.getTypes();
    for (
      int i = 0, j = 0;
      i < tempAttr.size() && j < tempType.size();
      i++, j++
    ) {
      if (!rel.contains(tempAttr.get(i))) {
        rel.add(tempAttr.get(i));
        types.add(tempType.get(j));
      }
    }
    RelationBuilder rb = new RelationBuilderImpl();
    Relation relation = rb.newRelation("New Relation", rel, types);
    relation.print();
    List<List<Cell>> list = rel1.getRows(); // List<List<Cell>>
    int i = 0;
    while (i < list.get(0).size()) {
      List<Cell> temp = new ArrayList<>();
      for (int j = 0; j < list.size(); j++) {
        temp.add(list.get(j).get(i));
      }
      relation.insert(temp);
      i++;
    }
    List<List<Cell>> list2 = rel2.getRows(); // List<List<Cell>>
    int x = 0;
    System.out.println(list2);
    while (x < list2.get(0).size()) {
      List<Cell> temp = new ArrayList<>();
      for (int j = 0; j < list2.size(); j++) {
        temp.add(list2.get(j).get(x));
      }
      relation.insert(temp);
      x++;
    }
    return relation;
  }

  /**
   * Performs the set difference operaion on the relations rel1 and rel2.
   *
   * @return The resulting relation after applying the set difference operation.
   *
   * @throws IllegalArgumentException If rel1 and rel2 are not compatible.
   */
  public Relation diff(Relation rel1, Relation rel2) {}

  /**
   * Renames the attributes in origAttr of relation rel to corresponding
   * names in renamedAttr.
   *
   * @return The resulting relation after renaming the attributes.
   *
   * @throws IllegalArgumentException If attributes in origAttr are not present in
   *                                  rel or origAttr and renamedAttr do not have
   *                                  matching argument counts.
   */
  public Relation rename(
    Relation rel,
    List<String> origAttr,
    List<String> renamedAttr
  ) {
    return null;
  }

  /**
   * Performs cartisian product on relations rel1 and rel2.
   *
   * @return The resulting relation after applying cartisian product.
   *
   * @throws IllegalArgumentException if rel1 and rel2 have common attibutes.
   */
  public Relation cartesianProduct(Relation rel1, Relation rel2) {
    return null;
  }

  /**
   * Peforms natural join on relations rel1 and rel2.
   *
   * @return The resulting relation after applying natural join.
   */
  public Relation join(Relation rel1, Relation rel2) {
    return null;
  }

  /**
   * Performs theta join on relations rel1 and rel2 with predicate p.
   *
   * @return The resulting relation after applying theta join.
   */
  public Relation join(Relation rel1, Relation rel2, Predicate p) {
    return null;
  }
}
