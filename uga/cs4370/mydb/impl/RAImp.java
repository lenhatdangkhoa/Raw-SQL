package uga.cs4370.mydb.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import uga.cs4370.mydb.Cell;
import uga.cs4370.mydb.Predicate;
import uga.cs4370.mydb.RA;
import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.RelationBuilder;
import uga.cs4370.mydb.Type;

public class RAImp implements RA {

  /**
   * Performs the select operation on the relation rel
   * by applying the predicate p.
   *
   * @return The resulting relation after applying the select operation.
   */
  public Relation select(Relation rel, Predicate p) {
    RelationBuilder ra = new RelationBuilderImpl();
    Relation relation = ra.newRelation(
        rel.getName(),
        rel.getAttrs(),
        rel.getTypes());
    for (List<Cell> list : rel.getRows()) {
      if (p.check(list)) {
        relation.insert(list);
      }
    }
    return relation;
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
    List<String> attList = rel.getAttrs();
    List<Type> typeList = rel.getTypes();

    for (String att : attrs) {
      if (!rel.hasAttr((att))) {
        throw new IllegalArgumentException("Attributes not found");
      }
    }
    List<String> newList = new ArrayList<>();
    List<Type> newTypes = new ArrayList<>();
    List<List<Cell>> tempTemp = new ArrayList<>();
    for (String temp : attrs) {
      if (attList.contains(temp)) {
        newList.add(temp);
        newTypes.add(typeList.get(attList.indexOf(temp)));
        List<Cell> tempTempTemp = new ArrayList<>();
        for (int i = 0; i < rel.getSize(); i++) {
          tempTempTemp.add(rel.getRows().get(i).get(attList.indexOf(temp)));
        }
        tempTemp.add(tempTempTemp);
      }
    }

    RelationBuilder ra = new RelationBuilderImpl();
    Relation res = ra.newRelation("New relation", newList, newTypes);

    int i = 0;
    List<List<Cell>> resList = new ArrayList<>();
    while (i < tempTemp.get(0).size()) {
      List<Cell> ttttInverse = new ArrayList<>();
      for (int j = 0; j < tempTemp.size(); j++) {
        ttttInverse.add(tempTemp.get(j).get(i));
      }
      resList.add(ttttInverse);
      i++;
    }
    List<List<Cell>> finalCheck = new ArrayList<>();

    for (List<Cell> temp : resList) {
      if (!finalCheck.contains(temp)) {
        finalCheck.add(temp);
        res.insert(temp);

      }
    }
    return res;
  }

  /**
   * Performs the union operation on the relations rel1 and rel2.
   *
   * @return The resulting relation after applying the union operation.
   *
   * @throws IllegalArgumentException If rel1 and rel2 are not compatible.
   */
  public Relation union(Relation rel1, Relation rel2) {
    if (rel1 == null || rel2 == null)
      throw new IllegalArgumentException(
          "Relation is null");
    else if (rel1.getAttrs().size() != rel2.getAttrs().size()) {
      throw new IllegalArgumentException("Relations'length do not match");
    }

    RelationBuilder rb = new RelationBuilderImpl();
    Relation relation = rb.newRelation(
        "New Relation",
        rel1.getAttrs(),
        rel1.getTypes());
    for (List<Cell> row : rel1.getRows()) {
      if (!relation.getRows().contains(row))
        relation.insert(row);
    }
    for (List<Cell> row : rel2.getRows()) {
      if (!relation.getRows().contains(row))
        relation.insert(row);
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
    if (rel1 == null || rel2 == null)
      throw new IllegalArgumentException(
          "Relation is null");
    else if (rel1.getAttrs().size() != rel2.getAttrs().size()) {
      throw new IllegalArgumentException("Relations'length do not match");
    }
    RelationBuilder rb = new RelationBuilderImpl();
    Relation relation = rb.newRelation(
        "New Relation",
        rel1.getAttrs(),
        rel1.getTypes());
    List<List<Cell>> temp = rel1.getRows();
    for (List<Cell> row : rel2.getRows()) {
      if (temp.contains(row)) {
        temp.remove(row);
      }
    }
    for (List<Cell> row : temp) {
      relation.insert(row);
    }
    return relation;
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
      List<String> renamedAttr) {
    List<String> rel1 = rel.getAttrs();
    if (renamedAttr.size() != origAttr.size())
      throw new IllegalArgumentException(
          "origAttr and renamedAttr do not have matching argument counts.");
    int i = 0;
    while (i < origAttr.size()) {
      if (!rel1.contains(origAttr.get(i)))
        throw new IllegalArgumentException(
            "attributes in origAttr are not present in rel");
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
    List<String> newAttr = rel1.getAttrs();
    List<Type> types = rel1.getTypes();
    for (String temp : rel2.getAttrs()) {
      if (newAttr.contains(temp)) {
        newAttr.set(newAttr.indexOf(temp), temp + "OF" + rel1.getName());
        newAttr.add(temp + "OF" + rel2.getName());
      } else {
        newAttr.add(temp);
      }
    }
    for (Type type : rel2.getTypes()) {
      types.add(type);
    }
    List<List<Cell>> bigList = new ArrayList<>();
    for (List<Cell> list : rel1.getRows()) {
      for (List<Cell> list2 : rel2.getRows()) {
        List<Cell> temp = new ArrayList<>();
        temp.addAll(list);
        temp.addAll(list2);
        bigList.add(temp);
      }
    }

    RelationBuilder ra = new RelationBuilderImpl();
    Relation relation = ra.newRelation("New Relation", newAttr, types);

    for (List<Cell> tempList : bigList) {
      relation.insert(tempList);
    }
    return relation;
  }

  /**
   * Peforms natural join on relations rel1 and rel2.
   *
   * @return The resulting relation after applying natural join.
   */
  public Relation join(Relation rel1, Relation rel2) {
    List<String> attrs = rel1.getAttrs();
    List<String> attrs2 = rel2.getAttrs();
    List<Type> newTypes = rel2.getTypes();
    List<Type> bigTypes = rel1.getTypes();
    List<List<Cell>> common1 = new ArrayList<>();
    List<List<Cell>> common2 = new ArrayList<>();
    String toRemove = "";
    int indexToRemove = 0;
    for (String temp : attrs2) {
      if (attrs.contains(temp)) {
        toRemove = temp;
        indexToRemove = attrs2.indexOf(temp);
        for (List<Cell> row : rel1.getRows()) {
          for (List<Cell> row2 : rel2.getRows()) {
            if (row
                .get(attrs.indexOf(temp))
                .equals(row2.get(attrs2.indexOf(temp)))) {
              List<Cell> newList = new ArrayList<>();
              for (Cell cell : row2) {
                if (!row2.get(attrs2.indexOf(temp)).equals(cell))
                  newList.add(
                      cell);
              }
              common1.add(row);
              common2.add(newList);
            }
          }
        }
      }
    }
    attrs2.remove(toRemove);
    newTypes.remove(indexToRemove);
    attrs.addAll(attrs2);
    bigTypes.addAll(newTypes);
    List<List<Cell>> finalRes = new ArrayList<>();
    for (int i = 0; i < common1.size(); i++) {
      List<Cell> temp = new ArrayList<>();
      temp.addAll(common1.get(i));
      temp.addAll(common2.get(i));
      finalRes.add(temp);
    }

    RelationBuilder ra = new RelationBuilderImpl();
    Relation res = ra.newRelation("New Relation", attrs, bigTypes);
    for (List<Cell> row : finalRes) {
      res.insert(row);
    }
    return res;
  }

  /**
   * Performs theta join on relations rel1 and rel2 with predicate p.
   *
   * @return The resulting relation after applying theta join.
   */
  public Relation join(Relation rel1, Relation rel2, Predicate p) {
    List<String> attrs1 = rel1.getAttrs();
    List<String> attrs2 = rel2.getAttrs();
    attrs1.addAll(attrs2);
    List<Type> types1 = rel1.getTypes();
    List<Type> types2 = rel2.getTypes();
    types1.addAll(types2);
    RelationBuilder ra = new RelationBuilderImpl();
    Relation relation = ra.newRelation("New Relation", attrs1, types1);
    for (List<Cell> row1 : rel1.getRows()) {
      for (List<Cell> row2 : rel2.getRows()) {
        List<Cell> temp = new ArrayList<>();
        temp.addAll(row1);
        temp.addAll(row2);
        if (p.check(temp)) {
          relation.insert(temp);
        }
      }
    }

    return relation;
  }
}
