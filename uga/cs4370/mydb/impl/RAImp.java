package uga.cs4370.mydb.impl;

import java.util.ArrayList;
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
        if (rel1 == null || rel2 == null)
            throw new RuntimeException("Relation is null");
        else if (rel1.getAttrs().size() != rel2.getAttrs().size()) {
            throw new RuntimeException("Relations'length do not match");

        }

        RelationBuilder rb = new RelationBuilderImpl();
        Relation relation = rb.newRelation("New Relation", rel1.getAttrs(), rel1.getTypes());
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
            throw new RuntimeException("Relation is null");
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
        Relation res = ra.newRelation("New Relation", rel1.getAttrs(), rel1.getTypes());
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
