package uga.cs4370.mydb.impl;

import java.util.List;
import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.Predicate;
import uga.cs4370.mydb.impl.RelationImp;
import uga.cs4370.mydb.Cell;
import java.util.ArrayList;

public class RAImp {
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
        return null;
    }

    /**
     * Performs the set difference operaion on the relations rel1 and rel2.
     *
     * @return The resulting relation after applying the set difference operation.
     *
     * @throws IllegalArgumentException If rel1 and rel2 are not compatible.
     */
    public Relation diff(Relation rel1, Relation rel2) {
        return null;
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
        RelationImp output = new RelationImp();
        List<String> commonColumns = findCommonColumns(rel1, rel2);
        for (String attr : rel1.getAttrs()) {
            if (!commonColumns.contains(attr)) {
                output.copyColumn(attr, rel1);
            }
        }
        for (String attr : rel2.getAttrs()) {
            if (!commonColumns.contains(attr)) {
                output.copyColumn(attr, rel2);
            }
        }
        for (List<Cell> row1 : rel1.getRows()) {
            for (List<Cell> row2 : rel2.getRows()) {
                if (matchRows(row1, row2, commonColumns)) { // surely a better check can be done here, just combine all rows proeprly here
                    output.mergeRows(row1, row2);
                }
            }
        }
        return output;
    }

    // helper function to find common relations
    private List<String> findCommonColumns(Relation rel1, Relation rel2) {
        List<String> commonColumns = new ArrayList<>();
        for (String attr1 : rel1.getAttrs()) {
            if (rel2.hasAttr(attr1)) {
                commonColumns.add(attr1);
            }
        }
        return commonColumns;
    }

    private void copyColumn(String columnName, Relation source) {
        for (List<Cell> row : source.getRows()) {
            Cell cell = source.getRows(source.getAttrIndex(columnName));
            this.insert(cell);
        }
    }
    
    private void mergeRows(List<Cell> row1, List<Cell> row2) {

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
