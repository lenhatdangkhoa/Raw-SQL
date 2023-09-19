package uga.cs4370.mydb.impl;

import java.util.List;
import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.RelationBuilder;
import uga.cs4370.mydb.Type;

public class RelationBuilderImplementation implements RelationBuilder {

  /**
   * Creates an empty relation with attribute names attrs and
   * attribute types types.
   *
   * @throws IllegalArgumentException if attrs and types have different counts
   * or attrs have empty or non-alphanumeric attribute names.
   */
  public Relation newRelation(
    String name,
    List<String> attrs,
    List<Type> types
  ) {
    if (attrs.size() != types.size()) throw new IllegalArgumentException(
      "attrs and types have differen counts"
    );
    if (!(alphanumeric(attrs))) throw new IllegalArgumentException(
      "atts has empty or non-alphanumeric attribute names"
    );
    return new Relationimplementation(name, attrs, types);
  }

  private boolean alphanumeric(List<String> attrs) {
    for (int i = 0; i < attrs.size(); i++) {
      if (
        !(attrs.get(i).matches("/W")) || attrs.get(i).matches("")
      ) return false; // if string doesn't have alphanumerical or is empty
    }
    return true;
  }
}