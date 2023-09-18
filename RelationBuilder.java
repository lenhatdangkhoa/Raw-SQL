package uga.cs4370.mydb;

import java.util.List;

/**
 * An interface to create a new relation.
 */
public interface RelationBuilder {

    /**
     * Creates an empty relation with attribute names attrs and
     * attribute types types.
     * 
     * @throws IllegalArgumentException if attrs and types have different counts
     * or attrs have empty or non-alphanumeric attribute names.
     */
    public Relation newRelation(String name, List<String> attrs, List<Type> types);
    
}
