package uga.cs4370.mydb.impl;

import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.RelationBuilder;
import uga.cs4370.mydb.Type;
import uga.cs4370.mydb.Cell;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RelationBuilderImpl implements RelationBuilder {
    public Relation newRelation(String name, List<String> attrs, List<Type> types) {
        if (name == null)
            throw new IllegalArgumentException("Empty name");
        else if (attrs == null || types == null)
            throw new IllegalArgumentException("Wrong format");
        else if (attrs.size() != types.size())
            throw new IllegalArgumentException("attrs and types don't match length");
        RelationImp rel = new RelationImp();
        rel.setName(name);
        for (int i = 0; i < attrs.size(); i++) {
            HashMap<Type, List<Cell>> map = new HashMap<>();
            map.put(types.get(i), new ArrayList<>());
            rel.table.put(attrs.get(i), map);
        }
        return rel;
    }
}
