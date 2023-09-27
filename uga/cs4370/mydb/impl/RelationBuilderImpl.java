package uga.cs4370.mydb.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import uga.cs4370.mydb.Cell;
import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.RelationBuilder;
import uga.cs4370.mydb.Type;

public class RelationBuilderImpl implements RelationBuilder {

  public Relation newRelation(
    String name,
    List<String> attrs,
    List<Type> types
  ) {
    if (name == null || name.length() == 0) throw new IllegalArgumentException(
      "Empty name"
    ); else if (
      attrs == null || types == null
    ) throw new IllegalArgumentException(
      "Wrong format for attributes and types"
    ); else if (!checkValidity(attrs)) throw new IllegalArgumentException(
      "include non alphanumerical characters"
    ); else if (
      attrs.size() != types.size()
    ) throw new IllegalArgumentException(
      "attributes and types's length don't match"
    );
    RelationImp rel = new RelationImp();
    rel.setName(name);
    for (int i = 0; i < attrs.size(); i++) {
      HashMap<Type, List<Cell>> map = new HashMap<>();
      map.put(types.get(i), new ArrayList<>());
      rel.table.put(attrs.get(i), map);
    }
    return rel;
  }

  /*
  Check each string in the String List, if it doesn't match the regex
  then return false as it doesn't have alphanumerical or is empty
   */
  private boolean checkValidity(List<String> attrs) {
    for (int i = 0; i < attrs.size(); i++) {
      if ((!attrs.get(i).matches("[a-zA-Z0-9]+"))) return false; // if string doesn't have alphanumerical or is empty
    }
    return true;
  }
}
