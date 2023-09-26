package uga.cs4370.mydb.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
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
    if (rel1 == null || rel2 == null) throw new RuntimeException(
      "Relation is null"
    );
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
  public Relation diff(Relation rel1, Relation rel2) {
    if (rel1 == null || rel2 == null) throw new RuntimeException(
      "Relation is null"
    );
    List<List<Cell>> list1 = rel1.getRows();
    List<List<Cell>> list2 = rel2.getRows();
    List<List<Cell>> newList = new ArrayList<>();
    List<List<Cell>> tempList = new ArrayList<>();
    int x = 0;
    while (x < list1.get(0).size()) {
      List<Cell> tempList2 = new ArrayList<>();
      for (int j = 0; j < list1.size(); j++) {
        tempList2.add(list1.get(j).get(x));
      }
      if (list1.contains(tempList2)) {
        list1.remove(tempList2);
        list2.remove(tempList2);
      } else {
        newList.add(tempList2);
      }
      x++;
    }
    // System.out.println(newList);
    /*
     * for (List<Cell> temp : list2) {
     * System.out.println("temp " + temp);
     * if (list1.contains(temp)) {
     * list1.remove(temp);
     * list2.remove(temp);
     * } else {
     * newList.add(temp);
     * }
     * }
     */
    RelationBuilder ra = new RelationBuilderImpl();
    Relation res = ra.newRelation(
      "New Relation",
      rel1.getAttrs(),
      rel1.getTypes()
    );
    // System.out.println(list1);
    int i = 0;
    while (i < list1.get(0).size()) {
      List<Cell> temp = new ArrayList<>();
      // System.out.println(list1.size());
      for (int j = 0; j < list1.size(); j++) {
        temp.add(list1.get(j).get(i));
      }
      // System.out.println(temp);
      res.insert(temp);
      i++;
    }
    i = 0;
    while (i < newList.get(0).size()) {
      List<Cell> temp = new ArrayList<>();
      // System.out.println(list1.size());
      for (int j = 0; j < newList.size(); j++) {
        temp.add(newList.get(j).get(i));
      }
      // System.out.println(temp);
      // System.out.println(temp);
      res.insert(temp);
      i++;
    }
    return res;
  }

  private boolean checkCell(List<Cell> cells) {
    return true;
  }

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
    List<String> rel1 = rel.getAttrs();
    if (
      renamedAttr.size() != origAttr.size()
    ) throw new IllegalArgumentException(
      "origAttr and renamedAttr do not have matching argument counts."
    );
    int i = 0;
    while (i < origAttr.size()) {
      if (!rel1.contains(origAttr.get(i))) throw new IllegalArgumentException(
        "attributes in origAttr are not present in rel"
      );
      i++;
    }
    int j = 0;
    for (String c : rel1) {
      if (origAttr.contains(c)) {
        Collections.replaceAll(rel1, c, renamedAttr.get(j));
        j++;
      }
    }
    RelationBuilder ra = new RelationBuilderImpl();
    Relation relation = ra.newRelation(rel.getName(), rel1, rel.getTypes());
    for (List<Cell> temp : rel.getRows()) {
      relation.insert(temp);
    }
    return relation;
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
