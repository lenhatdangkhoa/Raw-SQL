package uga.cs4370.mydb;

import java.util.List;

/**
 * Represents a relation instance.
 */
public interface Relation {
    
    /**
     * Returns the name of the relation.
     */
    public String getName();

    /**
     * Returns the row count of the relation.
     */
    public int getSize();

    /**
     * Get the rows of the relation.
     * Return a deep copy of the rows to avoid 
     * modifications to the rows by the callers of this method.
     */
    public List<List<Cell>> getRows();

    /**
     * Return the type of each column in a list.
     */
    public List<Type> getTypes();

    /**
     * Returns the list of attributes of the relation.
     */
    public List<String> getAttrs();

    /**
     * Returns true only if attr exist in the relation.
     */
    public boolean hasAttr(String attr);

    /**
     * Returns the index of the attr.
     * 
     * @throws IllegalArgumentException if attr does not 
     * exist in the relation.
     */
    public int getAttrIndex(String attr);

    /**
     * Inserts a row in the relation.
     * 
     * @throws IllegalArgumentException if the cell types do not correspond 
     * to the attibute types of the relation or if the row already exists.
     */
    public void insert(Cell... cells);

    /**
     * Inserts a row in the relation.
     * 
     * @throws IllegalArgumentException if the cell types do not correspond 
     * to the attibute types of the relation or if the row already exists.
     */
    public void insert(List<Cell> cells);

    /**
     * Print the relation properly formatted as a table 
     * to the standard ouput.
     * The result should look similar to MySql table outputs.
     */
    public void print();

}
