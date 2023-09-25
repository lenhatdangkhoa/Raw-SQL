package uga.cs4370.mydb.impl;

import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.Type;
import uga.cs4370.mydb.Cell;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.HashMap;

public class RelationImp implements Relation {

    public String name;
    public int size;
    public LinkedHashMap<String, HashMap<Type, List<Cell>>> table;

    public RelationImp() {
        table = new LinkedHashMap<>();
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Set name of the relation.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public List<List<Cell>> getRows() {
        List<List<Cell>> deepCopy = new ArrayList<List<Cell>>();
        for (HashMap<Type, List<Cell>> map : table.values()) {
            for (List<Cell> cells : map.values()) {
                deepCopy.add(cells);
            }
        }
        return deepCopy;

    }

    @Override
    public List<Type> getTypes() {
        List<Type> types = new ArrayList<>();
        for (HashMap<Type, List<Cell>> map : table.values()) {
            for (Type type : map.keySet()) {
                types.add(type);
            }
        }
        return types;
    }

    @Override
    public List<String> getAttrs() {
        List<String> list = new ArrayList<>();
        for (String key : table.keySet()) {
            list.add(key);
        }
        return list;
    }

    @Override
    public boolean hasAttr(String attr) {
        return table.containsKey(attr);
    }

    @Override
    public int getAttrIndex(String attr) {
        int i = 0;
        for (String key : table.keySet()) {
            if (attr.equals(key))
                return i;
            else
                i++;
        }
        throw new IllegalArgumentException("no attr");
    }

    @Override
    public void insert(Cell... cells) {

    }

    @Override
    public void insert(List<Cell> cells) {
        List<Type> types = this.getTypes();
        int i = 0;
        int tempInt = 0;
        double tempDouble = 0.0;
        String tempString = "";
        System.out.println("here");
        /*
         * for (Cell cell : cells) {
         * try {
         * tempInt = cell.getAsInt();
         * if (types.get(i) == Type.INTEGER) {
         * i++;
         * continue;
         * } else {
         * return;
         * }
         * 
         * } catch (RuntimeException ex) {
         * try {
         * tempDouble = cell.getAsDouble();
         * if (types.get(i) == Type.DOUBLE) {
         * i++;
         * continue;
         * } else {
         * return;
         * }
         * } catch (RuntimeException ex2) {
         * try {
         * tempString = cell.getAsString();
         * if (types.get(i) == Type.STRING) {
         * i++;
         * continue;
         * } else {
         * return;
         * }
         * } catch (RuntimeException ex3) {
         * System.err.println("Wrong format");
         * }
         * }
         * }
         * 
         * }
         */
        System.out.println("before loop");
        int j = 0;
        for (String key : table.keySet()) {
            HashMap<Type, List<Cell>> temp = table.get(key);
            for (Type type : temp.keySet()) {
                List<Cell> tempList = temp.get(type);
                tempList.add(cells.get(j));
                temp.put(type, tempList);
                j++;
            }
            table.put(key, temp);

        }

    }

    @Override
    public void print() {
        System.out.println(this.table);
    }
}
